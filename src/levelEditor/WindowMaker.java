package levelEditor;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Enemies.Enemy;

import levelEditor.Viewer.PlatformAction;

import sprites.Bad_Guys;
import sprites.Chris_TestSprite;
import sprites.GeneralSprite;
import sprites.Platform;

public class WindowMaker {
	private JPanel picPanel;
	HashMap<Integer, GeneralSprite> CTable;
	int ID;
	private GeneralSprite currSprite;
	private JPanel myPanel;


	
	public WindowMaker(JPanel myPicturePanel, HashMap<Integer, GeneralSprite> characterTable, int iD) {
		picPanel=myPicturePanel;
		CTable=characterTable;
		ID=iD;
	}
	

	
	public GeneralSprite createSpriteWindow() {
		JPanel imageInfo = new JPanel();
		imageInfo.setLayout(new GridLayout(2, 1));
		String imageNumber = "This image is represented by:" + ID;

		JTextField imageLabel = new JTextField(imageNumber);
		imageLabel.setEditable(false);
		File file = getImage();
		BufferedImage myPicture;
		
	    try {
			myPicture = ImageIO.read(file);


		JLabel picLabel = new JLabel(new ImageIcon(myPicture));

		imageInfo.add(imageLabel);
		imageInfo.add(picLabel);
		picPanel.add(imageInfo);
		} catch (IOException e) {
	           return null;
			}
        GeneralSprite s= getSprite();
	//	s.setInitPath(file.getCanonicalPath());
		return null;

		
	}
	
	private Component makeButtonPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(5, 1));
		panel.add(selectSpriteChooser());
		panel.add(addImageButton());
		panel.add(exportSpriteButton());
		return panel;
	}
	
	private GeneralSprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	public String createLevelNameWindow() {
		return null;
	}
	

	private Component selectSpriteChooser() {
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
	
	private GeneralSprite makeSprite(GeneralSprite selection) {
		return selection;
	}
	
	private Component addImageButton() {
		JButton platformButton = new JButton("Add Platform");
		platformButton.addActionListener(new ImageAction());
		return platformButton;
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
	
	private File getImage() {
		JFileChooser fc = new JFileChooser("./src/images/");
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			return file;
		}
		return null;
	}
}
