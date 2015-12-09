package fr.spriithy.snake.content;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import fr.spriithy.snake.graphics.Drawable;

@SuppressWarnings("all")
public class ItemSpawner implements Drawable {

	private Random	random;
	private int		x;
	private int		y;

	public ItemSpawner() {
		random = new Random();
		next();
	}

	private int nextAxisPosition(int range, Monster monster) {
		return random.nextInt(range);
	}

	public void next() {
		x = (nextAxisPosition(36, null)) * 11;
		y = (nextAxisPosition(31, null)) * 11;
		System.out.println(x / 11 + ", " + y / 11);
	}

	@Override
	public void setColor(Color color) {}

	@Override
	public void draw(Graphics gc) {
		gc.setColor(Color.DARK_GRAY);
		gc.fillOval(x, y, 10, 10);
	}

	@Override
	public void resetPosition() {}

}
