package hudDisplay;

public class NumberStat extends Stat{
	
	public NumberStat(double value){
		super(value);
		setMyValue(value);
	}

	public void adjust( double delta){
		if(getMyValue() + delta > 0)
		setMyValue(getMyValue() + delta);
		else
		setMyValue(0);
		
	}

}