package fr.spriithy.snake;

import static fr.spriithy.snake.input.InputManager.*;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import fr.spriithy.snake.content.Direction;
import fr.spriithy.snake.graphics.ContentManager;
import fr.spriithy.snake.graphics.Window;
import fr.spriithy.snake.input.InputManager;

public class Snake extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private Window			window;
	public ContentManager	content;
	public InputManager		input;
	private Thread			displayThread;

	private boolean	shouldRun	= false;
	private int		i			= 0, j = 0;

	public Snake() {
		setPreferredSize(new Dimension(480, 480));

		content = new ContentManager();
		input = new InputManager();

		window = new Window("Snake revamp", 480, 480);

		window.addKeyListener(input);
		addKeyListener(input);
		window.add(this);
	}

	@Override
	public void run() {
		long previous = System.nanoTime();
		final double frameRate = 1000000000 / 60.;
		double d = 0;

		while (shouldRun) {
			long current = System.nanoTime();
			d += (current - previous) / frameRate;
			previous = current;

			for (; d >= 1; d--)
				update();
			render();
		}
		stop();
	}

	public synchronized void start() {
		window.setVisible(true);
		shouldRun = true;
		displayThread = new Thread(this, "SnakeDisplayThread");
		displayThread.start();
	}

	public synchronized void stop() {
		shouldRun = false;
		try {
			displayThread.join();
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void update() {

		if (input.keys[LEFT]) content.monster.direction = Direction.LEFT;
		if (input.keys[RIGHT]) content.monster.direction = Direction.RIGHT;
		if (input.keys[UP]) content.monster.direction = Direction.UP;
		if (input.keys[DOWN]) content.monster.direction = Direction.DOWN;

		i++;
		if (i % 12 == 0 && content.monster.inBounds(39, 34)) {
			content.monster.moveAll();
			if (j <= 4) {
				content.monster.extend();
				j++;
			}
			i = 1;
		} else if (i % 12 == 0) {
			// TODO game lost
		}
	}

	public void render() {
		BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics gc = buffer.getDrawGraphics();

		content.update(gc);
		content.clear();
		content.render();

		gc.dispose();
		buffer.show();
	}

}
