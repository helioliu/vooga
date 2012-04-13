package levelEditor;

import java.util.ArrayList;

public class GameFile {
	String backgroundpath;
	ArrayList<ArrayList<String>> myList;  
	
	public GameFile(String path, ArrayList<ArrayList<String>> list) {
		backgroundpath=path;
		myList=list;
	}
	
	public String getBackground() {
		return backgroundpath;
	}
	
	public ArrayList<ArrayList<String>> getList() {
		return myList;
	}
}
