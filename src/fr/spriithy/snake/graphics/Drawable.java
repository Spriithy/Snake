package fr.spriithy.snake.graphics;

import java.awt.Color;
import java.awt.Graphics;

public interface Drawable {
	
	public void setColor(Color color);
	
	public void draw(Graphics gc);
	
	public void resetPosition();

}
