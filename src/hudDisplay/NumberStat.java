package hudDisplay;

public class NumberStat extends Stat{
	
	private double myValue;
	
	NumberStat(double startValue){
		super(startValue);
		myValue = startValue;
	}
	
	public void adjust( double delta){
		myValue= myValue + delta;
	}

}