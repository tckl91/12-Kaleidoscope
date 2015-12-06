//Author: Kai Cheng

package kaleidoscope;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Controller sets up the GUI and handles all the controls (buttons, menu
 * items, etc.)
 * 
 * @author David Matuszek
 * @author Kai Cheng
 */
public class Controller extends JFrame {
	JCheckBox coordCheckBox=new JCheckBox("Coordinate");
	JPanel buttonPanel = new JPanel();
	JButton runButton = new JButton("Run");
	JButton stopButton = new JButton("Stop");
	JButton addFigureButton = new JButton("Add Figure");
	JButton clearFigureButton = new JButton("Clear Figure");
	JButton changeBackgrondButton = new JButton("Change Background");
	Timer timer;

	/**
	 * The Model is the object that does all the computations. It is completely
	 * independent of the Controller and View objects.
	 */
	Model model;

	/** The View object displays what is happening in the Model. */
	View view;

	/**
	 * Runs the bouncing ball program.
	 * 
	 * @param args
	 *            Ignored.
	 */
	public static void main(String[] args) {
		Controller c = new Controller();
		c.init();
		c.display();
	}

	/**
	 * Sets up communication between the components.
	 */
	private void init() {
		model = new Model(); // The model is independent from the other classes
		view = new View(model); // The view needs to know what model to look at
		model.addObserver(view); // The model needs to give permission to be
									// observed
	}

	/**
	 * Displays the GUI.
	 */
	private void display() {
		layOutComponents();
		attachListenersToComponents();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - 600) / 2, (screenSize.height - 400) / 2,
				600, 400);
		setVisible(true);
		setTitle("kaleidoscope");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Arranges the various components in the GUI.
	 */
	private void layOutComponents() {
		setLayout(new BorderLayout());
		this.add(BorderLayout.SOUTH, buttonPanel);
		buttonPanel.add(runButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(addFigureButton);
		buttonPanel.add(clearFigureButton);
		buttonPanel.add(changeBackgrondButton);
		buttonPanel.add(coordCheckBox);
		stopButton.setEnabled(false);
		addFigureButton.setEnabled(false);
		clearFigureButton.setEnabled(false);
		changeBackgrondButton.setEnabled(false);
		this.add(BorderLayout.CENTER, view);
	}

	/**
	 * Attaches listeners to the components, and schedules a Timer.
	 */
	private void attachListenersToComponents() {
		// The Run button tells the Model to start
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				runButton.setEnabled(false);
				stopButton.setEnabled(true);
				addFigureButton.setEnabled(true);
				clearFigureButton.setEnabled(true);
				changeBackgrondButton.setEnabled(true);
				model.start();
			}
		});
		// The Stop button tells the Model to pause
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				runButton.setEnabled(true);
				stopButton.setEnabled(false);
				addFigureButton.setEnabled(false);
				clearFigureButton.setEnabled(false);
				changeBackgrondButton.setEnabled(false);
				model.pause();
			}
		});
		addFigureButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.addShape();
			}

		});
		clearFigureButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.clearShape();
			}

		});
		changeBackgrondButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.changeBackground();
			}
		});
		coordCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setShowCoordinate(coordCheckBox.isSelected());
			}
		});
		// When the window is resized, the Model is given the new limits
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				model.setLimits(view.getWidth(), view.getHeight());
			}
		});
	}

}
