//Author: Kai Cheng

package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;

public class PrismaticShape extends Shape {

	public PrismaticShape(Rectangle rectangle, int xSpeed, int ySpeed, Color color) {
		super(rectangle, xSpeed, ySpeed, color);
	}

	@Override
	public void paint(Graphics g, int x, int y, int width, int height) {
		int[] xPoints = new int[4];
		int[] yPoints = new int[4];
		xPoints[0] = x;
		yPoints[0] = y - height / 2;
		xPoints[1] = x - width / 2;
		yPoints[1] = y;
		xPoints[2] = x;
		yPoints[2] = y + height / 2;
		xPoints[3] = x + width / 2;
		yPoints[3] = y;
		g.fillPolygon(xPoints, yPoints, 4);
	}

}
