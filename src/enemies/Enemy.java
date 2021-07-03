package enemies;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.util.Random;

import hidden_parchment.Camera;
import hidden_parchment.Game;
import hidden_parchment.SpriteSheet;

public abstract class Enemy {
	protected Random rand = new Random();
	private int random = (int) (Math.random() * 200) + 400;
	protected static Rectangle2D rectPerseguir = new Rectangle2D.Double();
	protected static Rectangle2D rectPe = new Rectangle2D.Double();
	protected static Rectangle2D rectCorpo = new Rectangle2D.Double();
	private boolean perseguindo;
	private int temporizadorAnimacao;
	private int temporizadorMoverAleatorio;
	String direcao = "Right";
	private double x;
	private double y;
	protected double speed;
	protected double moveX, moveY;

	SpriteSheet sheet = new SpriteSheet("/enemies.png");
	protected BufferedImage[] img;
	private int frames;

	public abstract void tick();

	public String pixel;
	
	public void mover() {
		x += moveX;
		y += moveY;
	}

	private void animar(int frameInicial, int frameFinal) {
		if (frames < frameInicial || frames > frameFinal) {
			frames = frameInicial;
		}

		if (temporizadorAnimacao > 24) {
			frames++;
			temporizadorAnimacao = 0;
			if (frames > frameFinal) {
				frames = frameInicial;
			}
		}

		temporizadorAnimacao++;
	}

	public void animar(int frameInicial, int frameFinal, String direcao) {
		if (moveX < 0 && direcao.equals("Left")) {
			animar(frameInicial, frameFinal);
			this.direcao = direcao;
		} else if (moveX > 0 && direcao.equals("Right")) {
			animar(frameInicial, frameFinal);
			this.direcao = direcao;
		}
	}

	// *IA*
	public void moverAleatorio() {
		boolean parado;
		if (moveX == 0) {
			parado = true;
		} else {
			parado = false;
		}

		if (!perseguindo) {
			if (moveY != 0) {
				moveY = 0;
			}

			if (moveX == 0 && moveY == 0) {
				frames = 0;
			}

			if (this.temporizadorMoverAleatorio > random && parado && this.direcao.equals("Left")) {
				moveX = speed;
				random = (int) (Math.random() * 200) + 400;
				this.temporizadorMoverAleatorio = 0;
			} else if (this.temporizadorMoverAleatorio > random && parado && this.direcao.equals("Right")) {
				moveX = -speed;
				random = (int) (Math.random() * 200) + 400;
				this.temporizadorMoverAleatorio = 0;
			} else if (!parado && temporizadorMoverAleatorio > 150) {
				moveX = 0;
				this.temporizadorMoverAleatorio = 0;
			}

			this.temporizadorMoverAleatorio++;
		}
	}

	public void perseguir() {
		if (rectPerseguir.intersects(Game.getPlayer().getRectPe())) {
			perseguindo = true;

			if (x < Game.getPlayer().getX() - 5) {
				moveX = speed;

				if (!this.colisao(x + speed, y)) {
					moveX = 0;
				}
			}

			else if (x > Game.getPlayer().getX()) {
				moveX = -speed;

				if (!this.colisao(x - speed, y)) {
					moveX = 0;
				}
			} else {
				if (moveX == speed || moveX == 0)
					moveX = 0.0000000001;

				if (moveX == -speed)
					moveX = -0.0000000001;

			}

			if (y < Game.getPlayer().getY() - 5) {
				moveY = speed;

				if (!this.colisao(x, y + speed)) {
					moveY = 0;
				}
			}

			else if (y > Game.getPlayer().getY()) {
				moveY = -speed;

				if (!this.colisao(x, y - speed)) {
					moveY = 0;
				}
			} else {
				moveY = 0.0000000001;
			}
		} else {
			perseguindo = false;
		}
	}

	// *FIM IA*

	public boolean colisao(double x, double y) {
		rectPe = new Rectangle2D.Double((int) (x - Camera.x + 20), (int) (y - Camera.y + 45), 12, 6);
		for (int i = 0; i < Game.objectsMap.size(); i++) {
			if (rectPe.intersects(Game.objectsMap.get(i).getColisaoTile())) {
				return false;
			}
			for (int e = 0; e < Game.enemy.size(); e++) {
				Enemy enemy = Game.enemy.get(e);
				if (enemy == this) {
					continue;
				}

				Rectangle2D rectEnemy = new Rectangle2D.Double((int) (enemy.getX() - Camera.x + 20),
						(int) (enemy.getY() - Camera.y + 45), 12, 6);
				if (rectPe.intersects(rectEnemy)) {
					return false;
				}
			}
		}
		return true;
	}

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean naTela() {
		if ((Camera.x + Camera.xCentro) + 250 > this.x && (Camera.x + Camera.xCentro) - 250 < this.x
				&& (Camera.y + Camera.yCentro) + 250 > this.y && (Camera.y + Camera.yCentro) - 250 < this.y)
			return true;
		
		else
			return false;
		
	}


	public void render(Graphics2D g, String posicao) {
		if (naTela() && posicao.equals("baixo")) {
			g.drawImage(img[frames], (int) (this.x - Camera.x), (int) (this.y - Camera.y), null);
		} else if (naTela() && posicao.equals("cima")) {
			g.drawImage(img[frames], (int) (this.x - Camera.x), (int) (this.y - Camera.y), null);
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

	public void setY(int y) {
		this.y = y;
	}

	public static Rectangle2D getRectCorpo() {
		return rectCorpo;
	}

	public static void setRectCorpo(Rectangle2D rectCorpo) {
		Enemy.rectCorpo = rectCorpo;
	}

	public String getPixel() {
		return pixel;
	}

	public void setPixel(String pixel) {
		this.pixel = pixel;
	}



}
