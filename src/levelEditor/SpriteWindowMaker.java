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
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sprites.Bad_Guys;
import sprites.Chris_TestSprite;
import sprites.GeneralSprite;
import sprites.Platform;
import sprites.WalkingBadGuy;

public class SpriteWindowMaker extends JPanel {
	private JPanel picPanel;
	private String imagePath;
	HashMap<Integer, GeneralSprite> CTable;
	private TextArea SpriteAttributeEditor;
	int ID;
	private GeneralSprite currSprite;
	private JPanel myPanel;
	
	public SpriteWindowMaker(JPanel myPicturePanel, HashMap<Integer, GeneralSprite> characterTable, int iD) {
		myPanel=new JPanel();
		setLayout(new FlowLayout());
		SpriteAttributeEditor = new TextArea("Change your sprites attributes", 40, 40 );
		myPanel.add(makeButtonPanel());
		add(myPanel);
		picPanel=myPicturePanel;
		CTable=characterTable;
		ID=iD;
	}
	
	private Component makeButtonPanel() {
	JPanel panel = new JPanel();

	panel.setLayout(new GridLayout(5, 1));
	panel.add(SpriteChooser());
	panel.add(addImageButton());
    panel.add(CreateSpriteButton());
    
    return panel;
	}
	
	private Component SpriteChooser() {

			GeneralSprite[] spriteNames =  {
				new Chris_TestSprite(),
				new Bad_Guys(),
				new WalkingBadGuy(),
				new Platform(),

			};
			currSprite=spriteNames[0];

			JComboBox SpriteChooser = new JComboBox(spriteNames);
			SpriteChooser.setSelectedIndex(spriteNames.length-1);
			SpriteChooser.addActionListener(new GeneralSpriteAction());
			return SpriteChooser;
		
	}
	
	private class GeneralSpriteAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
			GeneralSprite newSelection = (GeneralSprite)cb.getSelectedItem();
			makeSprite(newSelection);
			myPanel.revalidate();
		}
	}
	
	public void makeSprite(GeneralSprite newSelection) {
		currSprite=newSelection;
		
	}

	
	
	private Component addImageButton() {
		JButton ImageButton = new JButton("Set Image");
		ImageButton.addActionListener(new ImageAction());
		return ImageButton;
	}

	
	private class ImageAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				getImage();
				myPanel.revalidate();
		}
	}
	

	
	private void getImage() {
		JFileChooser fc = new JFileChooser("./src/images/");
	
		int returnVal = fc.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		
			File file = fc.getSelectedFile();		
			try {
				imagePath = file.getCanonicalPath();

		    return;
			} catch (IOException e) {
				return;
			}
		}
	    return;

	}
	
	
	private Component CreateSpriteButton() {
		JButton CreateSpriteButton = new JButton("Create Sprite");
		CreateSpriteButton.addActionListener(new CreateSpriteAction());
		return CreateSpriteButton;
	}	
	
	private class CreateSpriteAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				createSprite();
				myPanel.revalidate();
		}
	}
	
	private void createSprite() {
		
		if (imagePath!=null) {
		JPanel imageInfo = new JPanel();
		imageInfo.setLayout(new GridLayout(2, 1));
		String imageNumber = "This image is represented by:" + ID;

		JTextField imageLabel = new JTextField(imageNumber);
		imageLabel.setEditable(false);
		BufferedImage myPicture;
		
	    try {
	    File file = new File(this.imagePath);

			myPicture = ImageIO.read(file);


		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		
		imageInfo.add(imageLabel);
		imageInfo.add(picLabel);
		picPanel.add(imageInfo);
	   
		picPanel.revalidate();
        currSprite.setInitPath(imagePath);
        
        currSprite.setInitPath(file.getCanonicalPath());
		CTable.put(ID, currSprite);
		ID++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	    }
	}
}
