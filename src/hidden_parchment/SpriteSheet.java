package hidden_parchment;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	BufferedImage img;

	public SpriteSheet(String path) {
		try {
			img = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BufferedImage getSprite(int x, int y, int width, int height) {
		return img.getSubimage(x, y, width, height);

	}
}
