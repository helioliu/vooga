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

import com.google.gson.Gson;

public class Viewer extends JPanel {

	private String backgroundPath;
	private HashMap<Integer, PlatformSprite> CharacterTable = new HashMap<Integer, PlatformSprite>();
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
		JFileChooser fc = new JFileChooser(".");
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			return file;
		}
		return null;
	}
	
	private String getBackgroundImage() throws IOException {
		JFileChooser fc = new JFileChooser(".");
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			return file.getCanonicalPath();
		}
		return null;
	}
	
	private PlatformSprite getPlatformSprite() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
	    JFileChooser chooser = new JFileChooser(".");
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getName());
	    }
	    int periodIndex =  chooser.getSelectedFile().getName().lastIndexOf(".");
	    String spriteClassName =chooser.getSelectedFile().getName().substring(0,periodIndex);
	    PlatformSprite s;
	    try {  s= (PlatformSprite) Class.forName(spriteClassName).newInstance();
	      s.toString();
	      return s;
	    } catch (Exception e) {
	    	return null;
	    }

	}


	public void makeSprite() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		JPanel imageInfo = new JPanel();
		imageInfo.setLayout(new GridLayout(2, 1));
		String imageNumber = "This image is represented by:" + ID;

		JTextField imageLabel = new JTextField(imageNumber);
		imageLabel.setEditable(false);
		File file = getImage();
		BufferedImage myPicture = ImageIO.read(file);
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));

		PlatformSprite s= getPlatformSprite();
		s.setImage(myPicture);
	    if (s==null) {
	    	return;
	    }
	    
		imageInfo.add(imageLabel);
		imageInfo.add(picLabel);
		myPicturePanel.add(imageInfo);
		// add to local character map

		CharacterTable.put(ID, s);
		ID++;

	}

	public void makePlatform() throws IOException {
//		JPanel imageInfo = new JPanel();
//		imageInfo.setLayout(new GridLayout(2, 1));
//		String imageNumber = "This image is represented by:" + ID;
//		JTextField imageLabel = new JTextField(imageNumber);
//		imageLabel.setEditable(false);
//		File file = getImage();
//		BufferedImage myPicture = ImageIO.read(file);
//		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//		imageInfo.add(imageLabel);
//		imageInfo.add(picLabel);
//
//
//		CharacterTable.put(ID, info);
//
//		myPicturePanel.add(imageInfo);
//		ID++;

	}

	public void makeGoodGuy() throws IOException {
//
//		JPanel imageInfo = new JPanel();
//		imageInfo.setLayout(new GridLayout(2, 1));
//		String imageNumber = "This image is represented by:" + ID;
//
//		JTextField imageLabel = new JTextField(imageNumber);
//		imageLabel.setEditable(false);
//		File file = getImage();
//		BufferedImage myPicture = ImageIO.read(file);
//		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//
//		imageInfo.add(imageLabel);
//		imageInfo.add(picLabel);
//
//		SpriteInfo info = new SpriteInfo("Character", file.getCanonicalPath(),
//				0, 0, true, true);
//		CharacterTable.put(ID, info);
//
//		myPicturePanel.add(imageInfo);
//		ID++;

	}

	private class ChangeNameAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.SetLevelName(myNameText.getText());
		}
	}

	public void Export() throws IOException {

		ArrayList<SpriteInfo> list = new ArrayList<SpriteInfo>();
		
		String level = LevelEditor.getText();
		Scanner scanner = new Scanner(level);
		int count = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (int i = 0; i < line.length(); i++) {
				String val = line.substring(i, i + 1);
				if (!(val.equals(" "))) {
					Integer x = Integer.parseInt(val);
					PlatformSprite mySprite = CharacterTable.get(x);
					if (CharacterTable.keySet().contains(x))
						;
					{
						mySprite.setX(i * width);
						mySprite.setY(count * height);
						SpriteInfo info = new SpriteInfo(mySprite.getClass().getName(), mySprite.writableObject());
						list.add(info);
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
		@Override
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
		@Override
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
		@Override
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
		@Override
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
		@Override
		public void actionPerformed(ActionEvent e) {
			Load();
		}

	}
}
