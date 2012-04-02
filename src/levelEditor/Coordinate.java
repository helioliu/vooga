package levelEditor;

public class Coordinate {
	private double x=0;
	private double y=0;
	private int val;
	
	public Coordinate(double x1, double y1, int val1) {
		x=x1;
		y=y1;
		val=val1;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getVal() {
		return val;
	}

}
