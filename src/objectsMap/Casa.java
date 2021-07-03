package objectsMap;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import hidden_parchment.Camera;
import map.Tile;

public class Casa extends ObjectsMap {

	public Casa(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		yDinamica = 200;
	}

	public void tick() {
		yDinamica = 135;
		this.setColisaoTile(new Rectangle((int) (x - Camera.x + 20), (int) (y - Camera.y + 80), 160, 112));
	}
}
