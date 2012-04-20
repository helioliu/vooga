package levelEditor;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import sprites.*;
import sprites.Character;

import levelEditor.*;

import com.golden.gamedev.object.Sprite;
import com.google.gson.Gson;

public class Viewer extends JPanel {

	private BackgroundObject backgroundPath;
	private LevelNameObject levelName;
	private HashMap<Integer, GeneralSprite> CharacterTable = new HashMap<Integer, GeneralSprite>();
	private Model model;
    private JPanel myPanel;
	private JPanel myPicturePanel;
	private IDObject ID;
	private TextArea LevelEditor;
	private double height = 32;
	private double width = 32;

	public Viewer(Model model) throws IOException {
		this.model = model;
		myPanel = new JPanel();
		JScrollPane scrollbar = new JScrollPane();
		add(scrollbar);
		setLayout(new FlowLayout());
		LevelEditor = new TextArea("Create your level here", 20, 85);
		myPanel.add(LevelEditor);
		myPanel.add(makeButtonPanel());
		add(myPanel);

		myPicturePanel = new JPanel();
		myPicturePanel.setLayout(new FlowLayout());
		add(myPicturePanel);
		
		ID=new IDObject();
		levelName = new LevelNameObject();
		backgroundPath = new BackgroundObject();

	}
	

	private Component makeButtonPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(5, 1));
		panel.add(addSpriteButton());
		panel.add(addBackgroundButton());
		panel.add(addPlatformButton());
		panel.add(addExportButton());
		panel.add(addLoadButton());
		panel.add(addChangeNameButton());
		return panel;
	}

	private Component addLoadButton() {
		JButton LoadButton = new JButton("Load Level");
		LoadButton.addActionListener(new LoadAction());
		return LoadButton;
	}

	private Component addExportButton() {
		JButton ExportButton = new JButton("Export Level");
		ExportButton.addActionListener(new ExportAction());
		return ExportButton;
	}

	private Component addPlatformButton() {
		JButton platformButton = new JButton("Add Platform");
		platformButton.addActionListener(new PlatformAction());
		return platformButton;
	}

	private Component addBackgroundButton() {
		JButton mybackgroundButton = new JButton("Add Background");
		mybackgroundButton.addActionListener(new BackgroundAction());
		return mybackgroundButton;
	}

	private Component addSpriteButton() {
		JButton BadGuyButton = new JButton("Add Sprite");
		BadGuyButton.addActionListener(new BadGuyAction());
		return BadGuyButton;
	}

	private Component addChangeNameButton() {
		JButton addNameButton = new JButton("Change Level Name");
		addNameButton.addActionListener(new ChangeNameAction());
		return addNameButton;
	}




	public void makeSprite()  {
		SpriteWindowMaker display = new SpriteWindowMaker(myPicturePanel, CharacterTable, ID);
		JFrame frame = new JFrame("Make a new sprite");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 600));
		frame.getContentPane().add(display);
		frame.pack();
		frame.setVisible(true);
	}


	public void makeBackground() {
		    BackgroundMakerWindow bmw= new BackgroundMakerWindow(backgroundPath);
	}

	private class ChangeNameAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SetLevelName();
		}
	}

	public void SetLevelName(){
		LevelNameWindowMaker display = new LevelNameWindowMaker(levelName);
		JFrame frame = new JFrame("Make a new sprite");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 100));
		frame.getContentPane().add(display);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void Export() throws IOException {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		String level = LevelEditor.getText();
		Scanner scanner = new Scanner(level);
		int count = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (int i = 0; i < line.length(); i++) {
				String val = line.substring(i, i + 1);
				if (!(val.equals(" "))) {
					Integer x = Integer.parseInt(val);
					GeneralSprite mySprite = CharacterTable.get(x);
					if (CharacterTable.keySet().contains(x))
						;
					{
						mySprite.setX(i * width);
						mySprite.setY(count * height);
						
						list.add(mySprite.writableObject());
					}
				}
			}
			count++;
		}
		
		GameFile gameFile= new GameFile(backgroundPath.getPath(), list);
		model.Export(gameFile,levelName.getName());
	}

	public void Load() {

		// windowmaker.loadSelectedLevel();

	}

	private class PlatformAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
						
				myPanel.revalidate();
				validate();
		}
	}

	private class BackgroundAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				makeBackground();
				myPanel.revalidate();
				validate();
		}
	}

	private class BadGuyAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				makeSprite();
				myPanel.revalidate();
				validate();
		}
	}

	private class ExportAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Export();
			} catch (IOException e1) {

			}

		}
	}

	private class LoadAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Load();
		}

	}
}
