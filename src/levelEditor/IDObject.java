package levelEditor;

public class IDObject {
	private int ID;
	
	public IDObject(){
		ID=0;
	}
	
	public void increment() {
		ID++;
	}
	
	public int getID(){
		return ID;
	}
}
