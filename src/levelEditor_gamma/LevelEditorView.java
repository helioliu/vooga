package levelEditor_gamma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import levelEditor_delta.Tile;
import levelEditor_delta.TileChooser;

public class LevelEditorView extends JFrame {

	private LevelEditorController myController;
	private MenuBar myMenuBar;
	
//	private MapComponent mapPanel;      // Special panel for rendering map with a viewport.
	private Map map;
	private JMapComponent jMap;
	private JSplitPane   split;         // provides the movable divider next to tile chooser
	private JScrollPane  mapScroll;     // ScrollPane for the Map
	private JTabbedPane tabPane;
	private JPanel mapPanel;
	private JPanel chooserPanel;
	private JPanel settingsPanel;
	private TileChooser tileChooser;
	private LevelEditorMainPanel myLevelEditorMainPanel;

	public LevelEditorView(LevelEditorController controller) {
		
		myController = controller;
		
		JPanel outerToolPane = (JPanel) this.getContentPane();
		JPanel innerToolPane = new JPanel(new BorderLayout());
		JPanel cp = new JPanel(new BorderLayout());
		
		outerToolPane.setLayout(new BorderLayout());
		outerToolPane.add(innerToolPane, BorderLayout.CENTER);
		innerToolPane.add(cp, BorderLayout.CENTER);
		
		chooserPanel = new JPanel(new BorderLayout());
		chooserPanel.setBorder(new TitledBorder("Tiles"));
		
		settingsPanel = new JPanel(new BorderLayout());
		settingsPanel.setBorder(new TitledBorder("Settings"));
		settingsPanel.add(new SpritePropertiesTable());
		
		tabPane = new JTabbedPane();
		tabPane.add("Tiles", chooserPanel);
		tabPane.add("Settings", settingsPanel);

		map = new Map(15,15);
		jMap = new JMapComponent(map, this);
		mapScroll = new JScrollPane(jMap);
		jMap.setViewport(mapScroll.getViewport());
		
		split = new JSplitPane();
		split.setDividerLocation(400);
		split.setLeftComponent(tabPane);
		split.setRightComponent(mapScroll);
		cp.add(split, BorderLayout.CENTER);
		
		setUpToolBars();
		setUpMenus();
				
		setSize(new Dimension(1200, 650));
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	private void setUpMenus() {
		System.out.println(myController == null);
		this.setJMenuBar(new MenuBar(myController));
	}

	private void setUpToolBars() {
		// TODO Auto-generated method stub
	}


	public Tile getSelectedTile() {
		return null;
	}
	
	public void addNewSprite(String filePath){
		Image img = Toolkit.getDefaultToolkit().createImage(filePath);
        DraggableImage sprite = new DraggableImage();
        jMap.add(sprite);
        sprite.setImage(img);
        sprite.setAutoSize(true);
        sprite.setOverbearing(true);
        sprite.setBorder(new LineBorder(Color.black, 1));

        sprite.setSize(map.getTileWidth(), map.getTileHeight());
        sprite.setLocation(map.getTileWidth(), map.getTileHeight());
        jMap.repaint();
	}

}
