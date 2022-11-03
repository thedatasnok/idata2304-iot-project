import {
  CartesianGrid,
  Legend,
  Line,
  LineChart,
  ResponsiveContainer,
  XAxis,
  YAxis,
} from 'recharts';
import SensorCard from './components/card/SensorCard';
import Card from './components/container/Card';
import SensorStatusIndicatorHints from './components/indicator/SensorStatusIndicatorHints';

const App = () => {
  return (
    <div className='flex flex-col h-screen w-screen overflow-hidden'>
      <h1 className='font-bold text-2xl text-center py-4'>
        <span className='text-white'>anti</span>
        <span className='text-amber-600'>boom</span>
      </h1>

      <div className='flex-1 flex flex-row px-24 pb-24 overflow-hidden'>
        <div className='w-96 px-4 py-2 overflow-hidden flex flex-col flex-shrink-0'>
          <h2 className='font-medium text-xl'>Sensors</h2>

          <SensorStatusIndicatorHints />

          <div className='flex flex-col overflow-y-auto gap-2'>
            {[...Array(30)].map((_, i) => (
              <Card
                key={i + 1}
                className='p-2 hover:bg-zinc-700 border-l-4 transition-all border-green-500 cursor-pointer'
              >
                <SensorCard id={i + 1} place='snk1' room='room' />
              </Card>
            ))}
          </div>
        </div>

        <div className='flex-1'>
          <ResponsiveContainer>
            <LineChart
              data={[]}
              margin={{
                top: 5,
                right: 30,
                left: 20,
                bottom: 5,
              }}
            >
              <CartesianGrid strokeDasharray='3 3' />
              <XAxis dataKey='name' />
              <YAxis />
              <Legend />
              <Line
                type='linear'
                dataKey='snk/room/1'
                stroke='#FFFFFF'
                activeDot={{ r: 8 }}
              />
              <Line type='linear' dataKey='snk/room/2' stroke='#d97706' />
            </LineChart>
          </ResponsiveContainer>
        </div>
      </div>
    </div>
  );
};

export default App;
