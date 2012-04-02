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

import com.google.gson.Gson;

public class Viewer extends JPanel {

	public HashMap<Integer, SpriteInfo> CharacterTable = new HashMap<Integer, SpriteInfo>();
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

	}

	private Component makeButtonPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(5, 1));
		panel.add(addBadGuyButton());
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

	private Component addBadGuyButton() {
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

	public void makeBadGuys() throws IOException {

		JPanel imageInfo = new JPanel();
		imageInfo.setLayout(new GridLayout(2, 1));
		String imageNumber = "This image is represented by:" + ID;

		JTextField imageLabel = new JTextField(imageNumber);
		imageLabel.setEditable(false);
		File file = getImage();
		BufferedImage myPicture = ImageIO.read(file);
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));

		imageInfo.add(imageLabel);
		imageInfo.add(picLabel);
		myPicturePanel.add(imageInfo);
		// add to local character map
		SpriteInfo info = new SpriteInfo("Bad_Guys", file.getCanonicalPath(),
				0, 0, true, true);
		CharacterTable.put(ID, info);
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
		imageInfo.add(imageLabel);
		imageInfo.add(picLabel);

		SpriteInfo info = new SpriteInfo("Platform", file.getCanonicalPath(),
				0, 0, true, true);
		CharacterTable.put(ID, info);

		myPicturePanel.add(imageInfo);
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

		imageInfo.add(imageLabel);
		imageInfo.add(picLabel);

		SpriteInfo info = new SpriteInfo("Character", file.getCanonicalPath(),
				0, 0, true, true);
		CharacterTable.put(ID, info);

		myPicturePanel.add(imageInfo);
		ID++;

	}

	private class ChangeNameAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.SetLevelName(myNameText.getText());
		}
	}

	public void Export() throws IOException {
		Gson gson5 = new Gson();

		String jsonString5 = gson5.toJson(CharacterTable);
		System.out.println("Generated json text: " + jsonString5);

		ArrayList<SpriteInfo> list = new ArrayList<SpriteInfo>();
		String level = LevelEditor.getText();
		System.out.println(level);
		Scanner scanner = new Scanner(level);
		int count = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (int i = 0; i < line.length(); i++) {
				String val = line.substring(i, i + 1);
				if (!(val.equals(" "))) {
					Integer x = Integer.parseInt(val);
					SpriteInfo spi = CharacterTable.get(x);
					if (CharacterTable.keySet().contains(x))
						;
					{
						SpriteInfo mySpi = new SpriteInfo(spi.getType(), spi
								.getPath(), i * width, count * height, spi
								.getT1(), spi.getT2());
						list.add(mySpi);
					}
				}
			}
			count++;
		}
		model.Export(list);
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
				makeBadGuys();
				myPanel.revalidate();
				validate();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
