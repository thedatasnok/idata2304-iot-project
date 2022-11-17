import { useEffect, useState } from 'react';
import {
  CartesianGrid,
  Label,
  Legend,
  Line,
  LineChart,
  ReferenceLine,
  ResponsiveContainer,
  XAxis,
  YAxis,
} from 'recharts';
import SensorCard from './components/card/SensorCard';
import Card from './components/container/Card';
import SensorStatusIndicatorHints from './components/indicator/SensorStatusIndicatorHints';
import dayjs from 'dayjs';
import {
  CpuTemperatureMeasurementProjection,
  SensorListDetails,
} from './types';
import { hashCode, intToRGB } from './utils/color';

interface CpuTemperatureMeasurementData {
  /**
   * gbfur/room/1 = [..., ..., ...]
   */
  [key: string]: CpuTemperatureMeasurementProjection[];
}

const convertSensorDataToKey = (
  measurement: CpuTemperatureMeasurementProjection
) => {
  return `${measurement.sensorPlace}/${measurement.sensorRoom}/${measurement.sensorId}`;
};

const App = () => {
  const [measurements, setMeasurements] =
    useState<CpuTemperatureMeasurementData>({});
  const [sensors, setSensors] = useState<SensorListDetails[]>([]);

  const TICK_INTERVAL_SECONDS = 5;
  const MINUTES_TO_SHOW = 2;
  const SLICE_INDEX = (MINUTES_TO_SHOW * 60) / TICK_INTERVAL_SECONDS - 1;

  /**
   * Subscribe to events when a new temperature measurement is stored.
   */
  useEffect(() => {
    const eventSource = new EventSource('/api/v1/cpu-temperatures/events');
    eventSource.onmessage = (event) => {
      const newMeasurement: CpuTemperatureMeasurementProjection = JSON.parse(
        event.data
      );

      const key = convertSensorDataToKey(newMeasurement);

      setMeasurements((old) => ({
        ...old,
        [key]: [
          ...old[key].slice(
            old[key].length > SLICE_INDEX ? 1 : 0,
            old[key].length
          ),
          newMeasurement,
        ],
      }));

      return () => eventSource.close();
    };
  }, []);

  /**
   * Fetch initial temperature mesaurement within the last five minutes.
   */
  useEffect(() => {
    fetch(
      '/api/v1/cpu-temperatures?after=' +
        dayjs().subtract(MINUTES_TO_SHOW, 'minutes').toISOString()
    )
      .then((res) => {
        return res.json();
      })
      .then((data: CpuTemperatureMeasurementProjection[]) => {
        const map: CpuTemperatureMeasurementData = {};

        data.forEach((measurement) => {
          const key = convertSensorDataToKey(measurement);

          if (map[key] === undefined) {
            map[key] = [];
          }

          map[key].push(measurement);
        });

        setMeasurements(map);
      });
  }, []);

  /**
   * Fetch stored sensors
   */
  useEffect(() => {
    fetch('/api/v1/sensors')
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setSensors(data);
      });
  }, []);

  /**
   * Whenever measurements are updated - update the sensor details to
   * include the last measured temperature and timestamp.
   */
  useEffect(() => {
    Object.keys(measurements).forEach((sensorKey) => {
      const sensorMeasurements = measurements[sensorKey];
      const lastMeasurement = sensorMeasurements[sensorMeasurements.length - 1];

      setSensors((oldSensors) => {
        return oldSensors.map((sensor) => {
          if (
            sensor.id === lastMeasurement.sensorId &&
            sensor.room === lastMeasurement.sensorRoom &&
            sensor.place === lastMeasurement.sensorPlace
          ) {
            sensor.lastMeasuredAt = lastMeasurement.measuredAt;
            sensor.lastMeasuredTemperature = lastMeasurement.temperature;
          }

          return sensor;
        });
      });
    });
  }, [measurements]);

  return (
    <div className='flex flex-col h-screen w-screen overflow-hidden'>
      <h1 className='font-bold text-2xl text-center py-2 bg-zinc-800 border-b border-zinc-700 md:bg-transparent md:border-transparent'>
        <span className='text-white'>anti</span>
        <span className='text-amber-600'>boom</span>
      </h1>

      <a
        href='https://github.com/thedatasnok/idata2304-iot-project'
        className='fixed top-4 right-2 text-amber-600 font-semibold text-sm'
      >
        github
      </a>

      <div className='flex-1 flex flex-col-reverse items-center overflow-y-auto px-4 md:flex-row md:items-stretch md:overflow-hidden md:px-8 lg:px-24 md:pb-24'>
        <div className='w-full md:w-96 my-4 overflow-hidden flex flex-col flex-shrink-0'>
          <h2 className='font-semibold text-xl'>Sensors</h2>

          <SensorStatusIndicatorHints />
          <div className='flex flex-col overflow-y-auto gap-2 p-1'>
            {sensors.map((sensor) => (
              <Card
                key={`${sensor.place}-${sensor.room}-${sensor.id}`}
                className='p-2 hover:bg-zinc-700 border-l-4 transition-all border-green-500 cursor-pointer'
              >
                <SensorCard {...sensor} />
              </Card>
            ))}
          </div>
        </div>

        <div className='flex-1 w-full'>
          <ResponsiveContainer width='99%'>
            <LineChart>
              <CartesianGrid strokeDasharray='3 3' />
              <XAxis
                type='number'
                dataKey='measuredAt'
                domain={['dataMin', 'dataMax']}
                scale='time'
                interval='preserveEnd'
                tickCount={(MINUTES_TO_SHOW * 60) / TICK_INTERVAL_SECONDS}
                tickFormatter={(tick: number) => dayjs(tick).format('HH:mm:ss')}
              />
              <YAxis domain={[0, 100]} />
              <Legend />
              {Object.keys(measurements).map((sensorKey) => (
                <Line
                  key={sensorKey}
                  name={sensorKey}
                  type='linear'
                  isAnimationActive={false}
                  data={measurements[sensorKey]}
                  dataKey='temperature'
                  stroke={intToRGB(hashCode(sensorKey))}
                  dot={{ r: 4, fill: intToRGB(hashCode(sensorKey)) }}
                />
              ))}
              <ReferenceLine
                y={85}
                stroke='rgb(239 68 68)' // text-red-500
                strokeDasharray='3 3'
                label={<Label value='Boom' position={'left'} fill='rgb(239 68 68)' />}
              />
            </LineChart>
          </ResponsiveContainer>
        </div>
      </div>
    </div>
  );
};

export default App;
