package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import hidden_parchment.Camera;
import hidden_parchment.Game;
import hidden_parchment.SpriteSheet;

public class Tile {

	private static SpriteSheet sheet = new SpriteSheet("/tiles.png");

	public static BufferedImage TILE_FLOOR = sheet.getSprite(0, 0, 50, 50);
	public static BufferedImage TILE_PLANTATION = sheet.getSprite(0, 50, 50, 50);
	private BufferedImage sprite;
	private double x, y;

	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void render(Graphics g) {
		if ((Camera.x + Camera.xCentro) + 250 > this.x && (Camera.x + Camera.xCentro) - 300 < this.x
				&& (Camera.y + Camera.yCentro) + 250 > this.y && (Camera.y + Camera.yCentro) - 250 < this.y) {
			g.drawImage(sprite, (int) (x - Camera.x), (int) (y - Camera.y), null);
		}
	}
}
