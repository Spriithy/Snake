package fr.spriithy.snake.graphics;

import java.awt.Color;
import java.awt.Graphics;

import fr.spriithy.snake.Game;
import fr.spriithy.snake.content.ItemSpawner;

public class ContentManager {

	private Graphics	gc;
	private ItemSpawner	spawner;

	public ContentManager() {
		spawner = new ItemSpawner();
	}

	public void update(Graphics gc) {
		this.gc = gc;
	}

	public void render() {
		gc.setColor(Color.WHITE);
		gc.fillRect(10, 10, 452, 397);
		if (Game.GRID) {
			gc.setColor(Color.RED);
			for (int i = 10; i < 470; i += 11)
				gc.drawLine(i, 10, i, 406); // vert (36)
			for (int i = 10; i < 410; i += 11)
				gc.drawLine(10, i, 460, i); // hor (31)
		}
		gc.setColor(Color.WHITE);
		gc.fillRect(10, 412, 451, 30);
		spawner.draw(gc);
	}

	public void clear() {
		gc.setColor(Color.BLACK);
		gc.fillRect(0, 0, 480, 480);
	}

}
