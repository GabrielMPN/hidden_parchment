package objectsMap;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import hidden_parchment.Camera;
import hidden_parchment.Game;
import hidden_parchment.SpriteSheet;

public class ObjectsMap {

	private static SpriteSheet sheet = new SpriteSheet("/tiles.png");

	public static BufferedImage TILE_HOUSE = sheet.getSprite(50, 0, 200, 200);

	private BufferedImage sprite;
	protected int x;

	protected int y;

	protected int yDinamica;
	Rectangle colisaoTile = new Rectangle();

	Rectangle2D rect = new Rectangle2D.Double(0, 0, 0, 0);

	protected float opacity;

	public ObjectsMap(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void tick() {

	}

	public boolean naTela() {
		if ((Camera.x + Camera.xCentro) + 500 > this.x && (Camera.x + Camera.xCentro) - 500 < this.x
				&& (Camera.y + Camera.yCentro) + 500 > this.y && (Camera.y + Camera.yCentro) - 500 < this.y) {
			return true;
		} else {
			return false;
		}
	}

	public void render(Graphics2D g) {
		g.setColor(new Color(0, 0, 255));
		rect = new Rectangle2D.Double((int) (x - Camera.x)+5, (int) (y - Camera.y), 190,200);
		
		if (naTela()) {	
			//g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
			g.drawImage(sprite, (int) (x - Camera.x), (int) (y - Camera.y), null);
		}
	}

	public Rectangle getColisaoTile() {
		return colisaoTile;
	}

	public void setColisaoTile(Rectangle colisaoTile) {
		this.colisaoTile = colisaoTile;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getyDinamica() {
		return yDinamica;
	}

	public void setyDinamica(int yDinamica) {
		this.yDinamica = yDinamica;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}

	public float getOpacity() {
		return opacity;
	}

	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}

}
