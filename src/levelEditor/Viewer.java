package levelEditor;
import java.awt.Component;
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

	private String backgroundPath;
	private HashMap<Integer, LESprite> CharacterTable = new HashMap<Integer, LESprite>();
	private Model model;
    private JPanel myPanel;
	private JPanel myPicturePanel;
	private int ID;
	private TextArea LevelEditor;
	private JTextField myNameText;
	private JButton myAddNameButton;
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
		myNameText = new JTextField(15);
		add(myPanel);

		myPicturePanel = new JPanel();
		myPicturePanel.setLayout(new FlowLayout());
		add(myPicturePanel);
		
		backgroundPath= getBackgroundImage();

	}

	private Component makeButtonPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(5, 1));
		panel.add(addSpriteButton());
		panel.add(addGoodGuyButton());
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

	private Component addGoodGuyButton() {
		JButton GoodGuyButton = new JButton("Add Good Guy");
		GoodGuyButton.addActionListener(new GoodGuyAction());
		return GoodGuyButton;
	}

	private Component addSpriteButton() {
		JButton BadGuyButton = new JButton("Add Bad Guy");
		BadGuyButton.addActionListener(new BadGuyAction());
		return BadGuyButton;
	}

	private Component addChangeNameButton() {
		JButton addNameButton = new JButton("Change Level Name");
		addNameButton.addActionListener(new ChangeNameAction());
		return addNameButton;
	}

	private File getImage() {
		JFileChooser fc = new JFileChooser("./src/images/");
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			return file;
		}
		return null;
	}
	
	private String getBackgroundImage() throws IOException {
		JFileChooser fc = new JFileChooser("./src/images");
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			return file.getCanonicalPath();
		}
		return null;
	}
	
//	private LevelEditable getSprite() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
//	    JFileChooser chooser = new JFileChooser("./src/sprites");
//	    int returnVal = chooser.showOpenDialog(null);
//	    if(returnVal == JFileChooser.APPROVE_OPTION) {
//	       System.out.println("You chose to open this file: " +
//	            chooser.getSelectedFile().getName());
//	    }
//	    int periodIndex =  chooser.getSelectedFile().getName().lastIndexOf(".");
//	    System.out.println(periodIndex);
//	    String spriteClassName =chooser.getSelectedFile().getName().substring(0,periodIndex);
//	    System.out.println(spriteClassName);
//	    LevelEditable aLE;
//	    aLE= (LevelEditable) Class.forName(spriteClassName).newInstance();
//	      System.out.println(aLE.toString());
//	      return aLE;
//
//	}


	public void makeSprite() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		JPanel imageInfo = new JPanel();
		imageInfo.setLayout(new GridLayout(2, 1));
		String imageNumber = "This image is represented by:" + ID;

		JTextField imageLabel = new JTextField(imageNumber);
		imageLabel.setEditable(false);
		File file = getImage();
		BufferedImage myPicture = ImageIO.read(file);
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));

		//s.setLEImage(myPicture);
		imageInfo.add(imageLabel);
		imageInfo.add(picLabel);
		myPicturePanel.add(imageInfo);
		// add to local character map

		Bad_Guys s= new Bad_Guys();
		s.setInitPath(file.getCanonicalPath());
		CharacterTable.put(ID, s);
		ID++;

	}

	public void makePlatform() throws IOException {
		JPanel imageInfo = new JPanel();
		imageInfo.setLayout(new GridLayout(2, 1));
		String imageNumber = "This image is represented by:" + ID;

		JTextField imageLabel = new JTextField(imageNumber);
		imageLabel.setEditable(false);
		File file = getImage();
		BufferedImage myPicture = ImageIO.read(file);
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));

		//s.setLEImage(myPicture);
		imageInfo.add(imageLabel);
		imageInfo.add(picLabel);
		myPicturePanel.add(imageInfo);
		// add to local character map

		Platform s= new Platform();
		s.setInitPath(file.getCanonicalPath());
		CharacterTable.put(ID, s);
		ID++;

	}

	public void makeGoodGuy() throws IOException {
		JPanel imageInfo = new JPanel();
		imageInfo.setLayout(new GridLayout(2, 1));
		String imageNumber = "This image is represented by:" + ID;

		JTextField imageLabel = new JTextField(imageNumber);
		imageLabel.setEditable(false);
		File file = getImage();
		BufferedImage myPicture = ImageIO.read(file);
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));

		//s.setLEImage(myPicture);
		imageInfo.add(imageLabel);
		imageInfo.add(picLabel);
		myPicturePanel.add(imageInfo);
		// add to local character map

		Character s= new Character();
		s.setInitPath(file.getCanonicalPath());
		CharacterTable.put(ID, s);
		ID++;

	}

	private class ChangeNameAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model.SetLevelName(myNameText.getText());
		}
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
					LESprite mySprite = CharacterTable.get(x);
					if (CharacterTable.keySet().contains(x))
						;
					{
						mySprite.setInitX(i * width);
						mySprite.setInitX(count * height);
						
						list.add(mySprite.writableObject());
					}
				}
			}
			count++;
		}
		
		GameFile gameFile= new GameFile(backgroundPath, list);
		model.Export(gameFile);
	}

	public void Load() {

		// The Level will be loaded here but we need Sprite Classes

	}

	private class PlatformAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				makePlatform();
				myPanel.revalidate();
				validate();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class GoodGuyAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				makeGoodGuy();
				myPanel.revalidate();
				validate();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class BadGuyAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

				try {
					makeSprite();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myPanel.revalidate();
				validate();
		}
	}

	private class ExportAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Export();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	private class LoadAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Load();
		}

	}
}
