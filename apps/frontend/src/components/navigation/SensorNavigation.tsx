import SensorCard from '../card/SensorCard';

const SensorNavigation = () => {
  return (
    <div className='p-2 max-w-sm mx-20 my-20 bg-zinc-800 rounded-xl shadow-lg flex items-center space-x-2'>
      <div>
        <div className='text-xl font-medium text-white pb-5'>CPU temp</div>
        <SensorCard id={1} place='place' room='room' />
      </div>
    </div>
  );
};

export default SensorNavigation;
