package levelEditor;

import java.io.*;
import java.awt.*;
import java.util.*;

public class GraphicsBank {

	final static int DEFAULT_TILE_WIDTH = 32;
	final static int DEFAULT_TILE_HEIGHT = 32;

	private ArrayList<Tile> tiles;
	private ArrayList<GraphicsBankChangeListener> changeListeners;
	File baseDirectory;

	Dimension baseTileSize;

	public GraphicsBank() {
		tiles = new ArrayList<Tile>();
		changeListeners = new ArrayList<GraphicsBankChangeListener>();
		baseTileSize = new Dimension(DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT);
	}
	

	public Tile getTile(int number) {
		Iterator<Tile> i = tiles.iterator();
		while (i.hasNext()) {
			Tile t = (Tile) i.next();
			if (t.number == number) {
				return t;
			}
		}
		return null;
	}

	public Tile getTile(String name) {
		Iterator<Tile> i = tiles.iterator();
		while (i.hasNext()) {
			Tile t = (Tile) i.next();
			if (t.getName().equals(name)) {
				return t;
			}
		}
		return null;
	}

	
	public Tile remove(Tile t) {
		Tile rm = null;
		if (tiles.remove(t)) {
			rm = t;
		}
		if (t != null) {
			fireRemoveEvent(t);
		}
		return t;
	}

	public void add(Tile t) {
		tiles.add(t);
		fireAddEvent(t);
	}

	public int size() {
		return tiles.size();
	}

	public Dimension getBaseTileSize() {
		return baseTileSize;
	}

	public Iterator<Tile> iterator() {
		return tiles.iterator();
	}

	public File getBaseDirectory() {
		return baseDirectory;
	}

	int getUnusedNumber() {
		int n = 1;
		Iterator<Tile> i = tiles.iterator();
		while (i.hasNext()) {
			Tile t = (Tile) i.next();
			if (n <= t.getNumber()) {
				n = t.getNumber() + 1;
			}
		}
		return n;
	}

	void addChangeListener(GraphicsBankChangeListener l) {
		changeListeners.add(l);
	}

	void removeChangeListener(GraphicsBankChangeListener l) {
		changeListeners.remove(l);
	}

	public void fireChangeEvent() {
		GraphicsBankChangeListener l;
		Iterator i = changeListeners.iterator();
		while (i.hasNext()) {
			l = (GraphicsBankChangeListener) i.next();
			l.tilesetUpdated(this);
		}
	}

	private void fireAddEvent(Tile t) {
		GraphicsBankChangeListener l;
		Iterator i = changeListeners.iterator();
		while (i.hasNext()) {
			l = (GraphicsBankChangeListener) i.next();
			l.tileAdded(this, t);
		}
	}

	private void fireRemoveEvent(Tile t) {
		GraphicsBankChangeListener l;
		Iterator i = changeListeners.iterator();
		while (i.hasNext()) {
			l = (GraphicsBankChangeListener) i.next();
			l.tileRemoved(this, t);
		}
	}
}

