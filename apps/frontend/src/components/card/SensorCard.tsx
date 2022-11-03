interface SensorCardProps {
  id: number;
  room: string;
  place: string;
}

const SensorCard: React.FC<SensorCardProps> = ({ id, place, room }) => {
  return (
    <div>
      <div className='divide-y-2 divide-zinc-600 grid grid-cols-1'>
        <button className='text-white hover:bg-zinc-700 px-1.5 py-0.5'>
          <span className='font-bold'>
            {id}/{place}/{room}
          </span>
          <span className='pl-20 font-bold'>90°C</span>
          <p className='text-[8px] text-right'>Updated 3s ago</p>
        </button>
      </div>
    </div>
  );
};

export default SensorCard;
