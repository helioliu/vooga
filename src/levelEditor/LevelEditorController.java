package levelEditor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class LevelEditorController {

	private LevelEditorModel myModel;
	private LevelEditorView myView;
	private LevelEditorController myController;
	private List<String> attributeList;
	private Map<String, Object> attributeMap;
	
	
	public LevelEditorController(LevelEditorModel model){
		myController = this;
		myModel = model;
		
		myView = new LevelEditorView(myController, myModel);
		attributeList = new ArrayList<String>();
		attributeMap = new HashMap<String, Object>();
	}

	
	public void loadXMLFile(File file) {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(file);
			
//            String backgroundFilePath = doc.getElementsByTagName("background").item(0).getTextContent();

            NodeList listOfSprites = doc.getElementsByTagName("sprite");
            int totalSprites = listOfSprites.getLength();
            
            for(int i = 0; i < totalSprites ; i++){
                Node spriteNode = listOfSprites.item(i);
                if(spriteNode.getNodeType() == Node.ELEMENT_NODE){
                	
                	Element spriteNodeElement = (Element)spriteNode;

                	for(String attribute : attributeList){
                		NodeList attributeList = spriteNodeElement.getElementsByTagName(attribute);
                        Element attributeElement = (Element)attributeList.item(0);
                        NodeList textAttributeList = attributeElement.getChildNodes();
                        attributeMap.put(attribute, ((Node)textAttributeList.item(0)).getNodeValue().trim());
                	}
                }
            }
            
		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public void saveXMLFile(File file) {
		// TODO Auto-generated method stub
		
	}

	public void createNewLevel(File file) {
		// TODO Auto-generated method stub
		
	}


	public void addDraggableImage(String filePath) {
        myView.addDraggableImagetoView(filePath);
	}


	public void setBackground(String filePath) {
		myModel.setBackground(filePath);
		updateView();
	}


	public void updateView(){
		myView.updateView(myModel);
	}
	
	public void addDraggableImagetoController(int x, int y, Tile currentTile) {
		myModel.addDraggableImagetoModel(x,y, currentTile);
		myView.addDraggableImagetoView(x, y, convertTiletoDraggableIamge(currentTile));
	}
	
	public DraggableImage convertTiletoDraggableIamge(Tile t){
		DraggableImage img = new DraggableImage(t.number, t.name, t.info, t.path, t.type, t.image);
		return img;
	}
}
