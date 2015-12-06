//Author: Kai Cheng

package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;

public class TriangleShape extends Shape {

	public TriangleShape(Rectangle rectangle, int xSpeed, int ySpeed, Color color) {
		super(rectangle, xSpeed, ySpeed, color);
	}

	@Override
	public void paint(Graphics g, int x, int y, int width, int height) {
		int[] xPoints=new int[3];
		int[] yPoints=new int[3];
		if(height>width){
			xPoints[0]=x;
			yPoints[0]=y-height/2;
			xPoints[1]=x-width/2;
			yPoints[1]=y+height/2;
			xPoints[2]=x+width/2;
			yPoints[2]=y+height/2;
		}else{
			xPoints[0]=x-width/2;
			yPoints[0]=y-height/2;
			xPoints[1]=x+width/2;
			yPoints[1]=y;
			xPoints[2]=x-width/2;
			yPoints[2]=y+height/2;			
		}
		g.fillPolygon(xPoints, yPoints, 3);
	}

}
