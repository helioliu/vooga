package levelEditor_gamma;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import levelEditor_delta.Tile;

public class Map {

	int tileWidth = 32;
	int tileHeight = 32;
	int zoomWidth = 32;
	int zoomHeight = 32;
	Tile[][] tiles;

	public Map(int width, int height) {
		tiles = new Tile[width][height];
	}

	public Map(int width, int height, int tileWidth, int tileHeight) {
		this(width, height);
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		zoomWidth = tileWidth;
		zoomHeight = tileHeight;
	}

	public void setTile(int x, int y, Tile t) {
		tiles[x][y] = t;
	}

	public void render(Graphics g, Point origin, Dimension size) {

		double minX = Math.max(origin.getX() / zoomWidth, 0);
		double maxX = Math.min((origin.getX() + size.getWidth()) / zoomWidth,tiles.length);
		double minY = Math.max(origin.getY() / zoomHeight, 0);
		double maxY = Math.min((origin.getY() + size.getHeight()) / zoomHeight,tiles[0].length);

		for (int y = (int) minY; y < maxY; y++) {
			for (int x = (int) minX; x < maxX; x++) {
				if (tiles[x][y] != null) {
					tiles[x][y].render(g, x * zoomWidth + zoomWidth, y
							* zoomHeight + zoomHeight);
				}
			}
		}
	}

	public int getWidth() {
		return tiles.length;
	}

	public int getHeight() {
		return tiles[0].length;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public int getZoomWidth() {
		return zoomWidth;
	}

	public int getZoomHeight() {
		return zoomHeight;
	}

	public Tile getTile(int x, int y, int z) {
		return tiles[x][y];
	}
}
