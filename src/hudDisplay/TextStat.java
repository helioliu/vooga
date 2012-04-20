package hudDisplay;

public class TextStat extends Stat {
	
	private double myValue;
	
	public TextStat(double startValue) {
		super(startValue);
	}

	public void adjust(double delta) {
		myValue += delta; 
	}

}
