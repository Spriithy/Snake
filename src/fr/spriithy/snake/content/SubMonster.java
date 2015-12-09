package fr.spriithy.snake.content;

public class SubMonster extends Monster {

	private Monster monster;

	public SubMonster(int x, int y, Monster monster) {
		super(x, y);
		this.monster = monster;
	}

	@Override
	public void move() {
		//  TODO
		direction = monster.lastDirection;
		super.move();
	}

}
