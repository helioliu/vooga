package levelEditor;

import java.io.IOException;

public class Main {

	public static final String TITLE = "2D Platform Level Editor";

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		LevelEditorModel model = new LevelEditorModel();
		LevelEditorController controller = new LevelEditorController(model);
	}	
}
