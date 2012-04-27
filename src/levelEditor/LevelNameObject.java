package levelEditor;

public class LevelNameObject {
	private String name;
	
	public LevelNameObject(){
		name="default.json";
	}
	
	public void setName(String newName) {
		name=newName;
	}

	public String getName(){
		return name;
	}
}
