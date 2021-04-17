package rolling_averages;

public class InputData {

	private int time;
	private int input;
	
	public InputData() {
		
	}
	
	
	public InputData(int time, int input) {
		this.time = time;
		this.input = input;
	}

	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}


	public int getInput() {
		return input;
	}


	public void setInput(int input) {
		this.input = input;
	}
	

	@Override
	public String toString() {
		System.out.println("Time: " + time + ", input: " + input);
		return "InputData [time=" + time + ", input=" + input + "]";
	}
	
	
}
