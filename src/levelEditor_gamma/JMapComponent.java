package levelEditor_gamma;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

import javax.swing.*;

import levelEditor_delta.MapEdit;

@SuppressWarnings("serial")
public class JMapComponent extends JComponent implements MouseListener,
MouseMotionListener {

	private Map map;
	private LevelEditorView view;
	private JViewport viewport;

	private int width, height = 0;
	private int tileWidth, tileHeight = 0;
	int oldX, oldY = 0;
	int grabX, grabY = 0;
	int offsetX, offsetY = 0;

	Stack undoStack;
	Stack redoStack;

	boolean showGrid = true;
	boolean stateChanged = false;
	boolean dragged = false;
	boolean btn1Pressed = false;
	boolean btn2Pressed = false;

	public JMapComponent(Map m, LevelEditorView v) {
		this.map = m;
		this.view = v;
		width = m.getWidth();
		height = m.getHeight();

		tileWidth = m.getZoomWidth();
		tileHeight = m.getZoomHeight();

		setPreferredSize(new Dimension(tileWidth * width, tileHeight * height));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		undoStack = new Stack();
		redoStack = new Stack();
		stateChanged = true;
	}

	public void setViewport(JViewport vp) {
		this.viewport = vp;
	}

	synchronized public void setMap(Map m) {
		this.map = m;

		width = m.getWidth();
		height = m.getHeight();

		tileWidth = m.getZoomWidth();
		tileHeight = m.getZoomHeight();

		setPreferredSize(new Dimension(tileWidth * width, tileHeight * height));

		revalidate();
		undoStack.clear();
		redoStack.clear();
		stateChanged = true;
	}

	synchronized public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, width * tileWidth, height * tileHeight);
		map.render(g, viewport.getViewPosition(), viewport.getSize());

		g.setColor(Color.gray);
		for (int i = 0; i < width; i++) {
			g.drawLine(i * tileWidth, 0, i * tileWidth, height * tileHeight);
		}

		for (int j = 0; j < height; j++) {
			g.drawLine(0, j * tileHeight, width * tileWidth, j * tileHeight);
		}

		((Graphics2D) g).setStroke(new BasicStroke(2));
		g.setColor(Color.black);
		g.drawLine(0, 0, width * tileWidth, 0);
		g.drawLine(0, 0, 0, height * tileHeight);
		g.drawLine(width * tileWidth, 0, width * tileWidth, height * tileHeight);
		g.drawLine(0, height * tileHeight, width * tileWidth, height
				* tileHeight);

	}

	public void mapClicked(int x, int y) {
		x = x / tileWidth;
		y = y / tileHeight;
		if (x < map.getWidth() && x >= 0 && y < map.getHeight() && y >= 0) {
			map.setTile(x, y, view.getSelectedTile());
			stateChanged = true;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (btn1Pressed) {
			mapClicked(e.getX(), e.getY());
			this.repaint();
		} else if (btn2Pressed) {
			int offX = e.getX() - grabX;
			int offY = e.getY() - grabY;
			Dimension d = viewport.getSize();
			Point p = viewport.getViewPosition();
			Point newPoint = new Point(p.x - offX, p.y - offY);
			viewport.setViewPosition(newPoint);
			dragged = true;
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Row: " +arg0.getY() / tileHeight + " Column: " + arg0.getX() / tileWidth);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (stateChanged) {
			stateChanged = false;
		}
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			btn1Pressed = true;
			mapClicked(e.getX(), e.getY());
			this.repaint();
			break;
		default:
			btn2Pressed = true;
			grabX = e.getX();
			grabY = e.getY();
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case MouseEvent.BUTTON1:
			btn1Pressed = false;
			oldX = e.getX();
			oldY = e.getY();
			break;
		default:
			btn2Pressed = false;
			oldX = e.getX();
			oldY = e.getY();

			if (!dragged) {
				Dimension d = viewport.getSize();
				Point newPoint = new Point((int) (e.getX() - d.getWidth() / 2),
						(int) (e.getY() - d.getHeight() / 2));
				viewport.setViewPosition(newPoint);
			}
			dragged = false;
			break;
		}
	}

}
