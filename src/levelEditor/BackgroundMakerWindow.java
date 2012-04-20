package levelEditor;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class BackgroundMakerWindow extends JPanel{
	
	private String path;
	
	public BackgroundMakerWindow(String backgroundpath) {
		path=backgroundpath;
		JFileChooser fc = new JFileChooser("./src/images");
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				path=file.getCanonicalPath();
			} catch (IOException e) {
			}
		}
	}

	
}
