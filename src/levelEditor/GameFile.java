package levelEditor;

import java.util.ArrayList;

public class GameFile {
	String backgroundpath;
	ArrayList<SpriteInfo> myList;  
	
	public GameFile(String path, ArrayList<SpriteInfo> list) {
		backgroundpath=path;
		myList=list;
	}
	
	public String getBackground() {
		return backgroundpath;
	}
	
	public ArrayList<SpriteInfo> getList() {
		return myList;
	}
}
