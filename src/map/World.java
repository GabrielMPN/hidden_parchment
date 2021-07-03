package map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import enemies.Zumbi;
import hidden_parchment.Game;
import objectsMap.Casa;
import objectsMap.ObjectsMap;

public class World {
	private Tile[] tiles;
	public static int WIDTH, HEIGHT;

	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for (int x = 0; x < map.getWidth(); x++) {
				for (int y = 0; y < map.getHeight(); y++) {
					int pixelAtual = pixels[x + (y * map.getWidth())];
					tiles[x + (y * map.getWidth())] = new FloorTile(x * 50, y * 50, Tile.TILE_FLOOR);

					if (pixelAtual == 0xFF007F0E) {
						tiles[x + (y * map.getWidth())] = new FloorTile(x * 50, y * 50, Tile.TILE_FLOOR);
					} else if (pixelAtual == 0xFF808080) {
						Game.objectsMap.add(new Casa(x * 50, y * 50, ObjectsMap.TILE_HOUSE));
					} else if (pixelAtual == 0xFF7F3300) {
						tiles[x + (y * map.getWidth())] = new Tile(x * 50, y * 50, Tile.TILE_PLANTATION);
					} else if (pixelAtual == 0xFF6DAAFF) {
						//Game.enemy.add(new Zumbi(x * 50, y * 50));
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				Tile tile = tiles[x + (y * WIDTH)];
				tile.render(g);
			}
		}
	}
}
