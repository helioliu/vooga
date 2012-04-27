package levelEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

	private LevelEditorController myController;
	private JFileChooser fileChooser;

	public MenuBar(LevelEditorController controller) {
		super();

		myController = controller;

		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu view = new JMenu("View");

		this.add(file);
		this.add(edit);
		this.add(view);

		JMenuItem newFileMenuOption = new JMenuItem("New");
		newFileMenuOption.addActionListener(new NewFileAction());
		file.add(newFileMenuOption);

		JMenuItem loadFileMenuOption = new JMenuItem("Load");
		loadFileMenuOption.addActionListener(new LoadFileAction());
		file.add(loadFileMenuOption);

		JMenuItem saveFileMenuOption = new JMenuItem("Save");
		saveFileMenuOption.addActionListener(new SaveFileAction());
		file.add(saveFileMenuOption);

		JMenuItem exitFileMenuOption = new JMenuItem("Exit");
		exitFileMenuOption.addActionListener(new ExitLevelEditorAction());
		file.add(exitFileMenuOption);

		JMenuItem addSpriteFileMenuOption = new JMenuItem("Add Sprite");
		addSpriteFileMenuOption.addActionListener(new AddNewSpriteAction());
		edit.add(addSpriteFileMenuOption);

		JMenuItem addBackgroundOption = new JMenuItem("Set Background");
		addBackgroundOption.addActionListener(new SetBackgroundAction());
		edit.add(addBackgroundOption);

	}

	private class NewFileAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			File file = fileChooser.getSelectedFile();
			myController.createNewLevel(file);
		}

	}

	private class LoadFileAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			File file = fileChooser.getSelectedFile();
			myController.loadXMLFile(file);
		}
	}

	private class SaveFileAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			fileChooser = new JFileChooser();
			int val = fileChooser.showOpenDialog(MenuBar.this);
			if (val == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				myController.saveXMLFile(file);
			}
		}
	}

	private class ExitLevelEditorAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	private class AddNewSpriteAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			fileChooser = new JFileChooser(".");
			int val = fileChooser.showOpenDialog(MenuBar.this);
			if (val == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				String filePath = file.getAbsolutePath();

				myController.addDraggableImage(filePath);
			}
		}
	}

	private class SetBackgroundAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			fileChooser = new JFileChooser(".");
			int val = fileChooser.showOpenDialog(MenuBar.this);
			if (val == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				String filePath = file.getAbsolutePath();

				myController.setBackground(filePath);
			}

		}
	}

}
