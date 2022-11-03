import TemperatureChart from './components/charts/CpuTemperatureChart';
import SensorNavigation from './components/navigation/SensorNavigation';

const App = () => {
  return (
    <div>
      <h1 className='font-bold text-xl text-center pb-10'>
        <span className='text-white'>anti</span>
        <span className='text-amber-700'>boom</span>
      </h1>

      <div className='grid grid-cols-2'>
        <div className='flex static col-end-1'>
          <SensorNavigation />
        </div>
        <div className='flex static col-span-2'>
          <TemperatureChart />
        </div>
      </div>
    </div>
  );
};

export default App;
