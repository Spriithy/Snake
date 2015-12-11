package fr.spriithy.snake.content;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import fr.spriithy.snake.interfaces.Drawable;

public class Monster implements Drawable {

	public ArrayList<Monster> body = new ArrayList<>();

	public int	x;
	public int	y;

	public Direction	direction;
	public Direction	lastDirection;

	public Monster(int x, int y) {
		this.x = x;
		this.y = y;
		direction = Direction.UP;
		lastDirection = direction;
		body.add(this);
	}

	public void moveAll() {
		for (Monster monster : body)
			monster.move();
	}

	public boolean isNextPositionOK() {
		for (Monster monster : body)
			switch (direction) {
				case RIGHT:
					if (monster.x == x + 1 && monster.y == y) return false;
					break;
				case LEFT:
					if (monster.x == x - 1 && monster.y == y) return false;
					break;
				case UP:
					if (monster.x == x && monster.y == y - 1) return false;
					break;
				case DOWN:
					if (monster.x == x && monster.y == y + 1) return false;
					break;
			}
		return inBounds(39, 34);
	}

	public void move() {
		lastDirection = direction;
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
		body.add(new SubMonster(body.get(body.size() - 1).x, body.get(body.size() - 1).y + 1, body.get(body.size() - 1)));
	}

	public boolean hasBodyAt(int x, int y) {
		for (Monster monster : body)
			if (monster.x == x && monster.y == y) return true;
		return false;
	}

	public boolean inBounds(int width, int height) {
		if (direction.equals(Direction.RIGHT) && x <= width) return true;
		if (direction.equals(Direction.LEFT) && x > 0) return true;
		if (direction.equals(Direction.DOWN) && y <= height) return true;
		if (direction.equals(Direction.UP) && y > 0) return true;
		return false;
	}

	@Override
	public void setColor(Color color) {}

	@Override
	public void draw(Graphics gc) {
		gc.setColor(Color.ORANGE);
		gc.fillRoundRect(x * 11 + 10, y * 11 + 10, 11, 11, 3, 3);
		for (Monster monster : body.subList(1, body.size()))
			monster.draw(gc);
	}

	@Override
	public void resetPosition() {}

}
