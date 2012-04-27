package hudDisplay;

import com.golden.gamedev.object.Timer;

public abstract class Stat {

	private double myValue;
	private double myStartValue;
	private Timer myTimer;
	private double myDelta;

	Stat(double value) {
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

	public void incrementWithTimer(int time, double delta) {
		myTimer = new Timer(time);
		myDelta = delta;
	}

	public void StartTimer() {
		myTimer.setActive(true);
	}
	
	public String toString(){
		return myValue+"";
	}

	public void stopTimer() {
		myTimer.setActive(false);
	}
	
	public void update(long ElapsedTime){
		if (myTimer.action(ElapsedTime)) {
			myValue += myDelta;
			return;
		}
	}

}