package fr.spriithy.snake.content;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class SubMonster extends Monster {

	private Monster	monster;
	private Color	color	= new Color((new Random().nextInt(256)), (new Random().nextInt(256)), (new Random().nextInt(256)));

	public SubMonster(int x, int y, Monster monster) {
		super(x, y);
		this.monster = monster;
	}

	@Override
	public void move() {
		lastDirection = direction;
		super.move();
		direction = monster.lastDirection;

	}

	@Override
	public void draw(Graphics gc) {
		gc.setColor(color);
		gc.fillRoundRect(x * 11 + 10, y * 11 + 10, 11, 11, 3, 3);
	}

}
