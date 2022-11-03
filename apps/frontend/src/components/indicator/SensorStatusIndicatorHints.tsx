const SensorStatusIndicatorHints = () => {
  return (
    <div className='flex flex-row justify-between mb-2'>
      <div className='flex items-center gap-2 text-cyan-500'>
        <div className='w-2 h-2 bg-cyan-500 rounded-full' />
        <p className='text-sm'>frys 🥶</p>
      </div>
      <div className='flex items-center gap-2 text-green-500'>
        <div className='w-2 h-2 bg-green-500 rounded-full' />
        <p className='text-sm'>good</p>
      </div>
      <div className='flex items-center gap-2 text-yellow-500'>
        <div className='w-2 h-2 bg-yellow-500 rounded-full' />
        <p className='text-sm'>kinda hot 😳</p>
      </div>
      <div className='flex items-center gap-2 text-red-500'>
        <div className='w-2 h-2 bg-red-500 rounded-full' />
        <p className='text-sm'>boom! 🧨</p>
      </div>
    </div>
  );
};

export default SensorStatusIndicatorHints;
