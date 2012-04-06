package levelEditor;

import java.util.ArrayList;


public class SpriteInfo {

	String className;
	ArrayList<Object> list;
	
	public SpriteInfo(String name, ArrayList<Object> o){
		className= name;
		list=o;
	}
	public String getClassName() {
		return className;
	}
	
	public ArrayList<Object> getList() {
		return list;
	}
}
