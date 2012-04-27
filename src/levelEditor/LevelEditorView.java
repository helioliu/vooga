package levelEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class LevelEditorView extends JFrame{

	private LevelEditorController myController;

	private LevelEditorModel myModel;
	private JMapComponent jMap;
	private JSplitPane split;
	private JScrollPane mapScroll;
	private JTabbedPane tabPane;
	private JPanel chooserPanel;
	private JPanel settingsPanel;
	private GraphicsBank myLevelEditorModel;
	private TileChooser tileChooser;

	public LevelEditorView(LevelEditorController controller, LevelEditorModel model) {

		myController = controller;
		myModel = model;

		JPanel outerToolPane = (JPanel) this.getContentPane();
		JPanel innerToolPane = new JPanel(new BorderLayout());
		JPanel cp = new JPanel(new BorderLayout());

		outerToolPane.setLayout(new BorderLayout());
		outerToolPane.add(innerToolPane, BorderLayout.CENTER);
		innerToolPane.add(cp, BorderLayout.CENTER);

		myLevelEditorModel = new GraphicsBank();
		tileChooser = new TileChooser(myLevelEditorModel, myController, this);

		JScrollPane tileScroll = new JScrollPane(tileChooser);
		chooserPanel = new JPanel(new BorderLayout());
		chooserPanel.add(tileScroll, BorderLayout.CENTER);

		settingsPanel = new JPanel(new BorderLayout());
		settingsPanel.setBorder(new TitledBorder("Settings"));
		settingsPanel.add(new SpritePropertiesTable());

		tabPane = new JTabbedPane();
		tabPane.add("Tiles", chooserPanel);
		tabPane.add("Settings", settingsPanel);

		
		jMap =	new JMapComponent(myController, this, myModel);		
		mapScroll = new JScrollPane(jMap);
		jMap.setViewport(mapScroll.getViewport());

		split = new JSplitPane();
		split.setDividerLocation(400);
		split.setLeftComponent(tabPane);
		split.setRightComponent(mapScroll);
		cp.add(split, BorderLayout.CENTER);

		setUpToolBars();
		setUpMenus();

		setSize(new Dimension(1400, 800));
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setUpMenus() {
		this.setJMenuBar(new MenuBar(myController));
	}

	private void setUpToolBars() {
		
	}

	public Tile getSelectedTile() {
		return tileChooser.getSelectedTile();
	}

	public void addDraggableImagetoView(String filePath) {
		Image img = Toolkit.getDefaultToolkit().createImage(filePath);

		DraggableImage photo = new DraggableImage();
		jMap.add(photo);
		photo.setImage(img);
		photo.setAutoSize(true);
		photo.setOverbearing(true);
		photo.setBorder(new LineBorder(Color.black, 1));
		
		photo.setSize(jMap.getTileWidth(), jMap.getTileHeight());
		photo.setLocation(jMap.getTileWidth(), jMap.getTileHeight());
		jMap.repaint();
	}
	
	public void addDraggableImagetoView(int x, int y, DraggableImage img){
		jMap.add(img, 500);
		img.setAutoSize(true);
		img.setOverbearing(true);
		img.setBorder(new LineBorder(Color.black, 1));
		img.setSize(jMap.getTileWidth(), jMap.getTileHeight());
		img.setLocation(x, y);
		jMap.repaint();
	}
	

	public void updateView(LevelEditorModel model) {
		System.out.println(model.getAllDraggableImages().size());
		jMap.removeAll();
		jMap.setBackgroundImage(model.getBackground());
		
		for(DraggableImage img : model.getAllDraggableImages()){
			addDraggableImagetoView(img.getMyX(), img.getMyY(), img);
		}
	}

}
