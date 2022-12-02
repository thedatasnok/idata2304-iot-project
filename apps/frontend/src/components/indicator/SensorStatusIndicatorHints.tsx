import clsx from 'clsx';
import colors from 'tailwindcss/colors';

/**
 * Temperature thresholds for determining the colors.
 */
export enum SensorTemperatureThreshold {
  INDETERMINATE = -1000,
  COLD = 0,
  GOOD = 20,
  HOT = 60,
  BOOM = 85,
}

/**
 * Map of temperature thresholds to colors.
 */
export const SensorTemperatureColors: { [k: number]: string } = {
  [SensorTemperatureThreshold.INDETERMINATE]: colors.zinc[400],
  [SensorTemperatureThreshold.COLD]: colors.cyan[500],
  [SensorTemperatureThreshold.GOOD]: colors.green[500],
  [SensorTemperatureThreshold.HOT]: colors.yellow[500],
  [SensorTemperatureThreshold.BOOM]: colors.red[500],
};

/**
 * Converts a temperature to a color, based on the defined thresholds.
 *
 * @param temperature the temperature to convert to a color
 *
 * @returns a color as a hex string
 */
export const convertTemperatureToColor = (temperature: number | undefined) => {
  if (temperature === undefined)
    return SensorTemperatureColors[SensorTemperatureThreshold.INDETERMINATE];

  // reversed because we want to find the first (and highest) threshold
  const foundColor = Object.entries(SensorTemperatureColors)
    .sort()
    .reverse()
    .find(([threshold, _]) => temperature >= Number(threshold));

  return foundColor
    ? foundColor[1]
    : SensorTemperatureColors[SensorTemperatureThreshold.INDETERMINATE];
};

const SensorStatusIndicatorHints = () => {
  return (
    <div className='flex flex-row justify-between mb-2'>
      {Object.keys(SensorTemperatureColors).map((key) => (
        <div key={key} className='flex items-center gap-2'>
          <div
            className={clsx('w-2 h-2 rounded-full')}
            style={{
              backgroundColor: SensorTemperatureColors[Number(key)],
            }}
          />
          <p
            className='text-xs'
            style={{
              color: SensorTemperatureColors[Number(key)],
            }}
          >
            {Number(key) === SensorTemperatureThreshold.INDETERMINATE && 'unknown'}
            {Number(key) === SensorTemperatureThreshold.COLD && 'frys 🥶'}
            {Number(key) === SensorTemperatureThreshold.GOOD && 'good'}
            {Number(key) === SensorTemperatureThreshold.HOT && 'kinda hot 😳'}
            {Number(key) === SensorTemperatureThreshold.BOOM && 'boom! 🧨'}
          </p>
        </div>
      ))}
    </div>
  );
};

export default SensorStatusIndicatorHints;
