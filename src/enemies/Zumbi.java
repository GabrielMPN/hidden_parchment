package enemies;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;

import hidden_parchment.Camera;
import hidden_parchment.Game;

public class Zumbi extends Enemy {

	public Zumbi(int x, int y) {
		super(x, y);
		this.speed = 0.7;
		img = new BufferedImage[4];
		this.img[0] = sheet.getSprite(0, 0, 50, 50);
		this.img[1] = sheet.getSprite(0, 50, 50, 50);

		this.img[2] = sheet.getSprite(50, 0, 50, 50);
		this.img[3] = sheet.getSprite(50, 50, 50, 50);

	}

	public void tick() {
		rectPerseguir = new Rectangle2D.Double(this.getX()-Camera.x-150,this.getY()-Camera.y-130,300,300);
		rectCorpo = new Rectangle2D.Double(this.getX()-Camera.x+26,this.getY()-Camera.y,2,50);
		this.mover();
		this.animar(0, 1, "Right");
		this.animar(2, 3, "Left");
		this.moverAleatorio();
		this.perseguir();
	}
}
