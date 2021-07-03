package hidden_parchment;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import disco.Disco;
import enemies.Enemy;
import map.World;
import objectsMap.ObjectsMap;
import players.Player;

public class Game extends Canvas implements Runnable {

	JFrame frame = new JFrame();
	public static int width = 900, height = 700;
	private boolean isRunning = true;

	World world;
	public static Player player = new Player();
	public static List<Enemy> enemy = new ArrayList<Enemy>();
	public static List<ObjectsMap> objectsMap = new ArrayList<ObjectsMap>();
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	private Disco disco = new Disco();

	public Game() {
		world = new World("/map.png");
		this.addKeyListener(player);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.tela();
	}

	public void tela() {
		frame.setSize(width, height);
		frame.add(this);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		start();
	}

	private synchronized void start() {
		new Thread(this).start();
		tDisco.start();
	}

	private void tick() {
		player.tick();
		for (int i = 0; i < objectsMap.size(); i++) {
			objectsMap.get(i).tick();
		}

		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).tick();
		}

	}

	private void render() {

		if (this.getBufferStrategy() == null) {
			this.createBufferStrategy(3);
		}

		BufferStrategy bs = this.getBufferStrategy();

		Graphics2D g = image.createGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);

		world.render(g);

		player.render(g);

		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).render(g, "baixo");
		}

		for (int i = 0; i < objectsMap.size(); i++) {
			objectsMap.get(i).render(g);
		}

		for (int i = 0; i < objectsMap.size(); i++) {
			if (player.getRectCorpo().intersects(objectsMap.get(i).getRect())
					&& player.getY() > objectsMap.get(i).getY() + objectsMap.get(i).getyDinamica()) {
				player.render(g);
			}
		}

		for (int i = 0; i < enemy.size(); i++) {
			if (enemy.get(i).naTela()) {
				for (int o = 0; o < objectsMap.size(); o++) {
					if (enemy.get(i).getRectCorpo().intersects(objectsMap.get(o).getRect())
							&& enemy.get(i).getY() > objectsMap.get(o).getY() + objectsMap.get(o).getyDinamica()) {
						enemy.get(i).render(g, "cima");
					}
				}
			} else {
				enemy.remove(i);
			}
		}

		this.setSize(frame.getWidth(), frame.getHeight());
		g = (Graphics2D) bs.getDrawGraphics();
		g.drawImage(image, 0, 0, frame.getWidth() * 2, frame.getHeight() * 2, null);
		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		while (isRunning) {
			tick();
			render();
			try {
				Thread.sleep(6);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	Thread tDisco = new Thread() {
		public void run() {
			disco.addMap("/map.png");
			while (isRunning) {

				try {
					disco.addEnemy();

					Thread.sleep(6);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	};

	public static Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
