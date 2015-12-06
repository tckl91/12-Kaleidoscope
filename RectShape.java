//Author: Kai Cheng

package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;

public class RectShape extends Shape {

	public RectShape(Rectangle rectangle, int xSpeed, int ySpeed, Color color) {
		super(rectangle, xSpeed, ySpeed, color);
	}

	@Override
	public void paint(Graphics g, int x, int y, int width, int height) {
		g.fillRect(x-width/2,y-height/2,width,height);
	}

}
