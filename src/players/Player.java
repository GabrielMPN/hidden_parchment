package players;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import hidden_parchment.Camera;
import hidden_parchment.Game;
import hidden_parchment.SpriteSheet;

public class Player implements KeyListener {

	private int frame, temp;
	private double x = 300;
	private double y = 200;
	private boolean right, left, up, down, idleRight = false, idleLeft = false;

	private double speed = 1;

	private static Rectangle2D rectPe = new Rectangle2D.Double(0, 0, 0, 0);

	SpriteSheet sheet = new SpriteSheet("/spritesGame.png");
	BufferedImage[] img = new BufferedImage[8];

	Rectangle2D rectCorpo = new Rectangle2D.Double(0, 0, 0, 0);

	public Player() {
		// iddle left
		img[0] = sheet.getSprite(0, 50, 50, 50);
		img[1] = sheet.getSprite(0, 100, 50, 50);

		// iddle right
		img[2] = sheet.getSprite(50, 50, 50, 50);
		img[3] = sheet.getSprite(50, 100, 50, 50);

		// walk left
		img[4] = sheet.getSprite(100, 50, 50, 50);
		img[5] = sheet.getSprite(100, 100, 50, 50);

		// walk right
		img[6] = sheet.getSprite(150, 50, 50, 50);
		img[7] = sheet.getSprite(150, 100, 50, 50);

	}

	public void animar() {
		if (right) {
			if (this.colisao(x + speed, y)) {
				x += speed;
			}
			if (frame < 6) {
				frame = 6;
			}
			if (temp > 18) {
				frame++;
				temp = 0;
			}
			if (frame > 7) {
				frame = 6;
			}
			temp++;
		} else if (left) {
			if (this.colisao(x - speed, y)) {
				x -= speed;
			}

			if (frame < 4) {
				frame = 4;
			}
			if (temp > 18) {
				frame++;
				temp = 0;
			}
			if (frame > 5) {
				frame = 4;
			}
			temp++;
		}

		

		if (down && this.colisao(x, y + speed)) {
			y += speed;
		}

		else if (up && this.colisao(x, y - speed)) {
			y -= speed;
		}
		
		else if (idleLeft) {
		
			if (frame < 0) {
				frame = 0;
			}
			if (temp > 40) {
				frame++;
				temp = 0;
			}
			if (frame > 1) {
				frame = 0;
			}
			temp++;
		}
		else if (idleRight) {
		
			if (frame < 2) {
				frame = 2;
			}
			if (temp > 40) {
				frame++;
				temp = 0;
			}
			if (frame > 3) {
				frame = 2;
			}
			temp++;
		}
	}

	public void render(Graphics2D g) {
		g.setColor(new Color(255, 0, 0));
		rectCorpo = new Rectangle2D.Double((int) (x - Camera.x) + 25, (int) (y - Camera.y), 1, 50);
		g.fill(rectCorpo);
		g.drawImage(img[frame], (int) (x - Camera.x), (int) (y - Camera.y), null);
	}

	public static boolean colisao(double x, double y) {
		rectPe = new Rectangle2D.Double((int) (x - Camera.x + 18), (int) (y - Camera.y + 46), 14, 5);
		for (int i = 0; i < Game.objectsMap.size(); i++) {
			if (rectPe.intersects(Game.objectsMap.get(i).getColisaoTile())) {
				return false;
			}
		}
		return true;
	}

	public void tick() {
		animar();
		if (this.getX() - (Game.width / 4) + 20 > 0) {
			Camera.x = (int) (this.getX() - (Game.width / 4) + 20);
		}
		if (this.getY() - (Game.height / 4) + 20 > 0) {
			Camera.y = (int) (this.getY() - (Game.height / 4) + 44);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			idleLeft = false;
			idleRight = false;
			left = true;
		} else if (e.getKeyCode() == 38) {
			up = true;
		} else if (e.getKeyCode() == 39) {
			idleLeft = false;
			idleRight = false;
			right = true;
		} else if (e.getKeyCode() == 40) {
			down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			left = false;
			idleLeft = true;
		} else if (e.getKeyCode() == 38) {
			up = false;
		} else if (e.getKeyCode() == 39) {
			right = false;
			idleRight = true;
		} else if (e.getKeyCode() == 40) {
			down = false;

		}

	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public static Rectangle2D getRectPe() {
		return rectPe;
	}

	public static void setRectPe(Rectangle2D rectPe) {
		Player.rectPe = rectPe;
	}

	public Rectangle2D getRectCorpo() {
		return rectCorpo;
	}

	public void setRectCorpo(Rectangle2D rectCorpo) {
		this.rectCorpo = rectCorpo;
	}

}
