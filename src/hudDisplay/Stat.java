package hudDisplay;

public abstract class Stat {

	private double myValue;
	
	Stat(double startValue){
		myValue = startValue;
	}

	public double getValue() {
		return myValue;
	}

}