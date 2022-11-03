interface SensorCardProps {
  id: number;
  room: string;
  place: string;
}

const SensorCard: React.FC<SensorCardProps> = ({ id, place, room }) => {
  return (
    <div className='text-white px-1.5 py-0.5 w-full'>
      <p className='flex flex-row justify-between'>
        <span className='font-bold'>
          {place}/{room}/{id}
        </span>
        <span className='font-bold text-xl'>90°C</span>
      </p>
      <p className='text-xs text-right'>Updated 3s ago</p>
    </div>
  );
};

export default SensorCard;
