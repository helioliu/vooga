package levelEditor;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

@SuppressWarnings("serial")
public class FileDropHandler extends TransferHandler {

	private TileChooser myFileChooser;
	
	public FileDropHandler(TileChooser chooser){
		myFileChooser = chooser;
	}
	
	public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
		for (int i = 0; i < transferFlavors.length; i++) {
			if (transferFlavors[i].equals(DataFlavor.javaFileListFlavor)) {
				return true;
			}
		}
		return false;
	}

	public boolean importData(JComponent comp, Transferable t) {
		try {
			java.util.List files = (java.util.List) t
					.getTransferData(DataFlavor.javaFileListFlavor);
			if (files.size() > 4) {
			}
			Iterator itr = files.iterator();
			while (itr.hasNext()) {
				File f = (File) itr.next();
				myFileChooser.importImageAsTile(f);
			}
		} catch (UnsupportedFlavorException e) {
			System.err.println("Unsupported drop content: " + e);
		} catch (IOException e) {
			System.err
					.println("Unexpected IO Exception while importing tile: "
							+ e);
			e.printStackTrace();
		}
		return true;
	}
}
