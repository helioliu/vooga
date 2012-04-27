package levelEditor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class JMapComponent extends JLayeredPane implements MouseListener,
		MouseMotionListener {

	private LevelEditorView myView;
	private LevelEditorController myController;
	private LevelEditorModel myModel;

	private JViewport viewport;

	private BufferedImage bgimage;
	private JLabel background;
	
	private int width = 600;
	private int height = 400;

	private int tileWidth = 32;
	private int tileHeight = 32;

	int oldX, oldY = 0;
	int grabX, grabY = 0;
	int offsetX, offsetY = 0;

	boolean showGrid = true;

	public static final int LAYER_GREENSCREEN = -100;
	public static final int LAYER_BACKGROUND = 0;
	public static final int LAYER_GRID = 50;
	public static final int LAYER_SPRITES = 100;
	public static final int LAYER_DRAG = 500;

	public JMapComponent(LevelEditorController cont, LevelEditorView v,
			LevelEditorModel model) {
		myView = v;
		myController = cont;

		setPreferredSize(new Dimension(tileWidth * width, tileHeight * height));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public void setBackgroundImage(BufferedImage img) {
		bgimage = img;
	}

	public void setBackgroundImage(String FilePath) {
		bgimage = null;
		try {
			bgimage = ImageIO.read(new File(FilePath));

		} catch (IOException e) {
			e.printStackTrace();
		}
		width = tileWidth * (bgimage.getWidth() / tileWidth);
		height = tileHeight * (bgimage.getHeight() / tileHeight);
		background = new JLabel(new ImageIcon(bgimage));
		background.setSize(width, height);
		background.setOpaque(true);
		background.setBounds(100, 100, width, height);
		background.setLocation(0, 0);
		
		repaint();

	}

	synchronized public void paintComponent(Graphics g) {
		if(bgimage != null){
			width = tileWidth * (bgimage.getWidth() / tileWidth);
			height = tileHeight * (bgimage.getHeight() / tileHeight);
		
			Image img = ((ImageIcon) background.getIcon()).getImage();
		    g.drawImage(img, 0, 0, null);
			
			Grid myGrid = new Grid();
			myGrid.render(g, tileWidth, tileHeight, width / tileWidth, height
					/ tileHeight);
			JLabel gridLines = new JLabel((Icon) myGrid.getGraphics());
			add(gridLines, LAYER_GRID);
		}else{
			Grid myGrid = new Grid();
			myGrid.render(g, tileWidth, tileHeight, width / tileWidth, height
					/ tileHeight);
			JLabel gridLines = new JLabel((Icon) myGrid.getGraphics());
			this.add(gridLines, LAYER_GRID);
		}
	}

	public void setViewport(JViewport vp) {
		this.viewport = vp;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Tile currentTile = myView.getSelectedTile();
		myController.addDraggableImagetoController(e.getX(), e.getY(),
				currentTile);
		this.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
