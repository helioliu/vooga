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

import levelEditor.WindowMaker.GeneralSpriteAction;
import levelEditor.WindowMaker.ImageAction;

import sprites.Bad_Guys;
import sprites.Chris_TestSprite;
import sprites.GeneralSprite;

public class SpriteWindowMaker extends JPanel {
	private JPanel picPanel;
	private String imagePath;
	HashMap<Integer, GeneralSprite> CTable;
	private TextArea SpriteAttributeEditor;
	int ID;
	private GeneralSprite currSprite;
	private JPanel myPanel;
	
	public SpriteWindowMaker(JPanel myPicturePanel, HashMap<Integer, GeneralSprite> characterTable, int iD) {
		setLayout(new FlowLayout());
		SpriteAttributeEditor = new TextArea("Change your sprites attributes", 40, 40 );
		myPanel.add(SpriteAttributeEditor);
		myPanel.add(makeButtonPanel());
		picPanel=myPicturePanel;
		CTable=characterTable;
		ID=iD;
	}
	
	private Component makeButtonPanel() {
	JPanel panel = new JPanel();

	panel.setLayout(new GridLayout(5, 1));
	panel.add(SpriteChooser());
	panel.add(addImageButton());
	panel.add(createSprite());
	}
	
	private Component SpriteChooser() {

			GeneralSprite[] spriteNames =  {
				new Chris_TestSprite(),
				new Bad_Guys(),
									
			};

			JComboBox SpriteChooser = new JComboBox(spriteNames);
			SpriteChooser.setSelectedIndex(spriteNames.length);
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
	
	
	private Component addImageButton() {
		JButton platformButton = new JButton("Add Platform");
		platformButton.addActionListener(new ImageAction());
		return platformButton;
	}
	
	public void makeSprite(GeneralSprite newSelection) {
		// TODO Auto-generated method stub
		
	}

	public GeneralSprite createSpriteWindow() {
		myPanel= new JPanel();
		setLay

	    
	//	s.setInitPath(file.getCanonicalPath());
		return null;

		
	}
	
	private class ImageAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				getImage();
				myPanel.revalidate();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	

	
	private void getImage() {
		
		JFileChooser fc = new JFileChooser("./src/images/");
		
		int returnVal = fc.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			
			JPanel imageInfo = new JPanel();
			imageInfo.setLayout(new GridLayout(2, 1));
			String imageNumber = "This image is represented by:" + ID;

			JTextField imageLabel = new JTextField(imageNumber);
			imageLabel.setEditable(false);
			BufferedImage myPicture;
			
		    try {
				myPicture = ImageIO.read(file);


			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			
			imageInfo.add(imageLabel);
			imageInfo.add(picLabel);
			picPanel.add(imageInfo);
			
			imagePath=file.getCanonicalPath();
			} catch (IOException e) {

			}
		    return;
		}
	    return;

	}
}
