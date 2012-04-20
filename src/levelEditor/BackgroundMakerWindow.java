package levelEditor;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class BackgroundMakerWindow extends JPanel{
	
	private BackgroundObject path;
	
	public BackgroundMakerWindow(BackgroundObject backgroundpath) {
		path=backgroundpath;
		JFileChooser fc = new JFileChooser("./src/images");
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				path.setPath(file.getCanonicalPath());
			} catch (IOException e) {
			}
		}
	}

	
}
