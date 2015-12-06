//Author: Kai Cheng

package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
	private Rectangle rectangle;
	private int xSpeed;
	private int ySpeed;
	private Color color;

	public Shape(Rectangle rectangle, int xSpeed, int ySpeed, Color color) {
		this.rectangle = rectangle;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void move(int xLimit, int yLimit) {
		rectangle.setX(rectangle.getX() + xSpeed);
		rectangle.setY(rectangle.getY() + ySpeed);
		if (rectangle.getX() < rectangle.getWidth() / 2) {
			xSpeed = -xSpeed;
			rectangle.setX(rectangle.getWidth() / 2);
		}
		if (rectangle.getX() > xLimit - rectangle.getWidth() / 2) {
			xSpeed = -xSpeed;
			rectangle.setX(xLimit - rectangle.getWidth() / 2);
		}
		if (rectangle.getY() < rectangle.getHeight() / 2) {
			ySpeed = -ySpeed;
			rectangle.setY(rectangle.getHeight() / 2);
		}
		if (rectangle.getY() > yLimit - rectangle.getHeight() / 2) {
			ySpeed = -ySpeed;
			rectangle.setY(yLimit - rectangle.getHeight() / 2);
		}
	}

	public abstract void paint(Graphics g, int x, int y, int width, int height);

	public void paint(Graphics g, int width, int height) {
		g.setColor(getColor());
		int x = rectangle.getX() - width / 2;
		int y = rectangle.getY() - height / 2;
		int w = rectangle.getWidth();
		int h = rectangle.getHeight();
		for (int i = 0; i < 8; i++) {
			switch (i) {
			case 0:
				// (X,Y)
				paint(g, x + width / 2, y + height / 2, w, h);
				break;
			case 1:
				// (-X,Y)
				paint(g, -x + width / 2, y + height / 2, w, h);
				break;
			case 2:
				// (X,-Y)
				paint(g, x + width / 2, -y + height / 2, w, h);
				break;
			case 3:
				// (-X,-Y)
				paint(g, -x + width / 2, -y + height / 2, w, h);
				break;
			case 4:
				// (Y,X)
				paint(g, y + width / 2, x + height / 2, h, w);
				break;
			case 5:
				// (-Y,X)
				paint(g, -y + width / 2, x + height / 2, h, w);
				break;
			case 6:
				// (Y,-X)
				paint(g, y + width / 2, -x + height / 2, h, w);
				break;
			case 7:
				// (-Y,-X)
				paint(g, -y + width / 2, -x + height / 2, h, w);
				break;
			}
		}
	}
}
