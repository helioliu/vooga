package levelEditor;


public class SpriteInfo {

	String type;
	String path;
	double x;
	double y;
	boolean isTrue1;
	boolean isTrue2;
	
	public SpriteInfo(){
		type="";
		path="";
		x=0;
		y=0;
		isTrue1=true;
		isTrue2=false;
	}
	
	public SpriteInfo(String t,String path1, double x1, double y1, boolean t1, boolean t2){
		type=t;
	    path=path1;
		x=x1;
		y=y1;
		isTrue1=t1;
		isTrue2=t2;
	}
	
	public String getType() {
		return type;
	}
	
	public String getPath() {
		return path;
	}
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public boolean getT1() {
		return isTrue1;
	}
	public boolean getT2() {
		return isTrue2;
	}
	
	public void setX(double x1) {
		x=x1;
	}
	
	public void setY(double y1) {
	    y=y1;
	}
}
