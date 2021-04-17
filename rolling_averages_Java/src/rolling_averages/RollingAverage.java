package rolling_averages;

public class RollingAverage {
	
	
	private int maxValue;
	private int time;
	private int averageValue;

	
	public RollingAverage() {
		
	}

	public RollingAverage(int maxValue, int time, int averageValue ) {
		this.maxValue = maxValue;
		this.time = time;
		this.averageValue = averageValue;
	}

	/**
	 * @return the maxValue
	 */
	public int getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @return the averageValue
	 */
	public int getAverageValue() {
		return averageValue;
	}

	/**
	 * @param averageValue the averageValue to set
	 */
	public void setAverageValue(int averageValue) {
		this.averageValue = averageValue;
	}

	@Override
	public String toString() {
		System.out.println("RollingAverage [maxValue=" + maxValue + ", time=" + time + ", averageValue=" + averageValue + "]");
		return "RollingAverage[maxValue=" + maxValue + ", time=" + time + ", averageValue=" + averageValue + "]";
	}
	
	
	
}
