interface CardProps {
  className?: string;
  children: React.ReactNode;
  onClick?: () => void;
}

const Card: React.FC<CardProps> = ({ children, className, onClick }) => {
  return (
    <div
      className={'bg-zinc-800 rounded-md shadow-sm ' + className}
      onClick={() => onClick?.()}
    >
      {children}
    </div>
  );
};

export default Card;
