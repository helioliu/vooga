package levelEditor;

import java.awt.Image;
import java.util.ArrayList;

public class LevelEditorModel {

	private ArrayList<DraggableImage> myDraggableImages;
	private String backgroundFilePath = "src/images/background.jpg";
	
	public LevelEditorModel(){
		myDraggableImages = new ArrayList<DraggableImage>();
	}
	
	public void addDraggableImagetoModel(int x, int y, Tile t){
		String type = t.type;
		String path = t.path;
		String info = t.info;
		Image image = t.image;
		String name = t.name;
		int number = t.number;
		
		DraggableImage newDraggableImage = new DraggableImage(number,name,info,path,type, image);
		newDraggableImage.setCorrdinates(x, y);
		myDraggableImages.add(newDraggableImage);
	}
	
	public ArrayList<DraggableImage> getAllDraggableImages(){
		return myDraggableImages;
	}
	
	public void setBackground(String filePath){
		backgroundFilePath = filePath;
	}
	
	public String getBackground(){
		return backgroundFilePath;
	}	
}
