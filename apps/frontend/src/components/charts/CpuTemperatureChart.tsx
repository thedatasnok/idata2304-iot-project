import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from 'recharts';


const TemperatureChart = () => {
  return (
    <ResponsiveContainer width='100%' height='100%'>
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
        <Tooltip />
        <Legend />
        <Line
          type='monotone'
          dataKey='pv'
          stroke='#FFFFFF'
          activeDot={{ r: 8 }}
        />
        <Line type='monotone' dataKey='temp' stroke='#d97706' />
      </LineChart>
    </ResponsiveContainer>
  );
};

export default TemperatureChart;
