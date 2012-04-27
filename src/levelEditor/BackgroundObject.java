package levelEditor;

public class BackgroundObject {

	private String BO;
	
	public BackgroundObject(){
		BO="images/background.jpg";
	}
	
	public void setPath(String newName) {
		BO=newName;
	}

	public String getPath(){
		return BO;
	}
}
