package fr.spriithy.snake;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import fr.spriithy.snake.graphics.ContentManager;
import fr.spriithy.snake.graphics.Window;
import fr.spriithy.snake.input.InputManager;

public class Snake extends Canvas implements Runnable {

	private Window			window;
	public ContentManager	cManager;
	public InputManager		inputManager;
	private Thread			displayThread;

	private boolean shouldRun = false;

	public Snake() {
		setPreferredSize(new Dimension(480, 480));

		cManager = new ContentManager();
		inputManager = new InputManager();

		window = new Window("Snake revamp", 480, 480);

		window.add(this);
		window.addKeyListener(inputManager);
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
		// TODO
	}

	public void render() {
		BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics gc = buffer.getDrawGraphics();

		cManager.update(gc);
		cManager.clear();
		cManager.render();

		gc.dispose();
		buffer.show();
	}

}
