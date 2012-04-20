package hudDisplay;

public abstract class Stat {

	private double myValue;
	private double myStartValue;
	
	Stat(double value){
		setMyValue(value);
		myStartValue = value;
	}

	public double getValue() {
		return getMyValue();
	}
	
	public double getStartValue() {
		return myStartValue;
	}
	
	public abstract void adjust(double x);

	public double getMyValue() {
		return myValue;
	}

	public void setMyValue(double myValue) {
		this.myValue = myValue;
	}

}