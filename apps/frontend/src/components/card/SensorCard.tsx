import { SensorListDetails } from '@/types';
import dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime';
import { useEffect, useState } from 'react';

dayjs.extend(relativeTime);

interface SensorCardProps extends SensorListDetails {}

const SensorCard: React.FC<SensorCardProps> = ({
  id,
  place,
  room,
  lastMeasuredAt,
  lastMeasuredTemperature,
}) => {
  const PLACEHOLDER = '--';
  const [timeSinceLastMeasurement, setTimeSinceLastMeasurement] =
    useState(PLACEHOLDER);

  /**
   * Interval to update the time since last measurement was received.
   */
  useEffect(() => {
    const interval = setInterval(() => {
      if (lastMeasuredAt) {
        setTimeSinceLastMeasurement(
          `Updated ${dayjs(lastMeasuredAt).fromNow(true)} ago`
        );
      } else {
        setTimeSinceLastMeasurement(PLACEHOLDER);
      }
    }, 1000);

    return () => clearInterval(interval);
  }, [lastMeasuredAt]);

  return (
    <div className='text-white px-1.5 py-0.5 w-full'>
      <p className='flex flex-row justify-between'>
        <span className='font-bold'>
          {place}/{room}/{id}
        </span>
        <span className='font-bold text-xl'>
          {lastMeasuredTemperature
            ? `${lastMeasuredTemperature.toFixed(2)}°C`
            : PLACEHOLDER}
        </span>
      </p>
      <p className='text-xs text-right'>{timeSinceLastMeasurement}</p>
    </div>
  );
};

export default SensorCard;
