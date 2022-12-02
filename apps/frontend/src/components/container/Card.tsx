interface CardProps {
  className?: string;
  children: React.ReactNode;
  style?: React.CSSProperties;
  onClick?: () => void;
}

const Card: React.FC<CardProps> = ({ children, className, style, onClick }) => {
  return (
    <div
      style={style}
      className={'bg-zinc-800 rounded-md shadow-sm ' + className}
      onClick={() => onClick?.()}
    >
      {children}
    </div>
  );
};

export default Card;
