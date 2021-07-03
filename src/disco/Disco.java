package disco;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import enemies.Zumbi;
import hidden_parchment.Game;
import map.Tile;

public class Disco {
	private Tile[] tiles;
	public static int WIDTH, HEIGHT;
	BufferedImage map;

	public void addMap(String path) {
		try {
			map = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	boolean podeInstanciar = true;

	public void instanciar(int pixelAtual, int x, int y) {
		if (pixelAtual == 0xFF6DAAFF && Game.getPlayer().getX() + 500 > x * 50
				&& Game.getPlayer().getX() - 500 < x * 50 && Game.getPlayer().getY() + 500 > y * 50
				&& Game.getPlayer().getY() - 500 < y * 50) {

			// inimigo
			if (Game.enemy.size() == 0) {
				if (!podeInstanciar)
					podeInstanciar = true;
			}

			for (int i = 0; i < Game.enemy.size(); i++) {
				try {
					if (Game.enemy.get(i).getPixel().equals(x + "|" + y)) {
						podeInstanciar = false;
						break;
					} else {
						if (!podeInstanciar)
							podeInstanciar = true;
					}
				} catch (Exception e) {
				}
			}

			if (podeInstanciar) {
				//Game.enemy.add(new (x * 50, y * 50));
				//Game.enemy.get(Game.classGame.size() - 1).setPixel(x + "|" + y);
			}
		}
	}
	
	public void addEnemy() {
		try {
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for (int x = 0; x < map.getWidth(); x++) {
				for (int y = 0; y < map.getHeight(); y++) {
					int pixelAtual = pixels[x + (y * map.getWidth())];

					// se o player estiver perto do pixel do inimigo
	
					

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
