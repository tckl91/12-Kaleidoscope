//Author: Kai Cheng

package kaleidoscope;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is the Model class for a bouncing ball. It is an Observable, which means
 * that it can notifyObservers that something in the model has changed, and they
 * should take appropriate actions.
 * 
 * @author David Matuszek
 * @author <Your name goes here>
 * @author <Your name goes here>
 */
public class Model extends Observable {
	private Timer timer;
	private int xLimit, yLimit;
	private List<Shape> shapeList = new ArrayList<Shape>();
	private Random random = new Random();
	private Color background;
	private boolean showCoordinate;

	/**
	 * Sets the "walls" that the ball should bounce off from.
	 * 
	 * @param xLimit
	 *            The position (in pixels) of the wall on the right.
	 * @param yLimit
	 *            The position (in pixels) of the floor.
	 */
	public void setLimits(int xLimit, int yLimit) {
		this.xLimit = xLimit;
		this.yLimit = yLimit;
	}

	public List<Shape> getShapeList() {
		return shapeList;
	}

	/**
	 * Tells the ball to start moving. This is done by starting a Timer that
	 * periodically executes a TimerTask. The TimerTask then tells the ball to
	 * make one "step."
	 */
	public void start() {
		if (shapeList.size() == 0) {
			addShape();
		}
		changeBackground();
		timer = new Timer(true);
		timer.schedule(new Strobe(), 0, 40); // 25 times a second
	}

	/**
	 * Tells the ball to stop where it is.
	 */
	public void pause() {
		timer.cancel();
	}

	public void addShape() {
		int shapeType = random.nextInt(4);
		int xSpeed = random.nextInt(5) + 1;
		int ySpeed = random.nextInt(5) + 1;
		int width = random.nextInt(20) + 10;
		int height = random.nextInt(20) + 10;
		int x = random.nextInt(xLimit - width/2);
		int y = random.nextInt(yLimit - height/2);
		Color color = new Color(random.nextInt(255), random.nextInt(255),
				random.nextInt(255));
		Shape shape;
		Rectangle rectangle = new Rectangle(x, y, width, height);
		if (shapeType == 0) {
			shape = new RectShape(rectangle, xSpeed, ySpeed, color);
		} else if (shapeType==1){
			shape = new OvalShape(rectangle, xSpeed, ySpeed, color);
		} else if (shapeType==2){
			shape = new TriangleShape(rectangle, xSpeed, ySpeed, color);
		} else{
			shape = new PrismaticShape(rectangle, xSpeed, ySpeed, color);
		}
		shapeList.add(shape);
		setChanged();
		notifyObservers();
	}

	public Color getBackground() {
		return background;
	}
	
	public void clearShape() {
		shapeList.clear();
		addShape();
		setChanged();
		notifyObservers();
	}

	public void setShowCoordinate(boolean showCoordinate) {
		this.showCoordinate = showCoordinate;
		setChanged();
		notifyObservers();
	}
	
	public boolean isShowCoordinate() {
		return showCoordinate;
	}
	
	public void changeBackground(){
		background=new Color(random.nextInt(255), random.nextInt(255),
				random.nextInt(255));
		setChanged();
		notifyObservers();
	}
	/**
	 * Tells the ball to advance one step in the direction that it is moving. If
	 * it hits a wall, its direction of movement changes.
	 */
	public void makeOneStep() {
		// Notify observers
		for (Shape shape : shapeList) {
			shape.move(xLimit, yLimit);
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Tells the model to advance one "step."
	 */
	private class Strobe extends TimerTask {
		@Override
		public void run() {
			makeOneStep();
		}
	}

}
