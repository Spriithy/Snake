package fr.spriithy.snake.content;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import fr.spriithy.snake.graphics.Drawable;

public class Monster implements Drawable {

	public ArrayList<Monster> body = new ArrayList<>();

	public int	x;
	public int	y;

	public Direction direction;

	public Monster(int x, int y) {
		this.x = x;
		this.y = y;
		direction = Direction.UP;
		body.add(this);
	}

	public void moveAll() {
		for (Monster monster : body)
			monster.move();
	}

	public void move() {
		switch (direction) {
			case RIGHT:
				x += 1;
				break;
			case LEFT:
				x -= 1;
				break;
			case UP:
				y -= 1;
				break;
			case DOWN:
				y += 1;
				break;
		}
	}

	public void extend() {
		body.add(new SubMonster(body.get(body.size() - 1).x, body.get(body.size() - 1).y + 1, direction));
	}

	public boolean hasBodyAt(int x, int y) {
		for (Monster monster : body)
			if (monster.x == x && monster.y == y) return true;
		return false;
	}

	public boolean inBounds(int width, int height) {
		return (x >= 1 && x <= width && y >= 1 && y <= height);
	}

	@Override
	public void setColor(Color color) {}

	@Override
	public void draw(Graphics gc) {
		gc.setColor(Color.ORANGE);
		for (Monster monster : body)
			gc.fillRoundRect(monster.x * 11 + 10, monster.y * 11 + 10, 11, 11, 3, 3);
	}

	@Override
	public void resetPosition() {}

}
