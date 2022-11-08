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
      <h1 className='font-bold text-2xl text-center py-2 bg-zinc-800 border-b border-zinc-700 md:bg-transparent md:border-transparent'>
        <span className='text-white'>anti</span>
        <span className='text-amber-600'>boom</span>
      </h1>
      
      <a href='https://github.com/thedatasnok/idata2304-iot-project' className='fixed top-4 right-2 text-amber-600 font-semibold text-sm'>
        github
      </a>

      <div className='flex-1 flex flex-col-reverse items-center overflow-y-auto px-4 md:flex-row md:items-stretch md:overflow-hidden md:px-8 lg:px-24 md:pb-24'>
        <div className='w-full md:w-96 my-4 overflow-hidden flex flex-col flex-shrink-0'>
          <h2 className='font-semibold text-xl'>Sensors</h2>

          <SensorStatusIndicatorHints />

          <div className='flex flex-col overflow-y-auto gap-2 p-1'>
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

        <div className='flex-1 w-full'>
          <ResponsiveContainer width='99%'>
            <LineChart
              data={[]}
              margin={{
                top: 65,
                right: 0,
                left: 0,
                bottom: 0,
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
