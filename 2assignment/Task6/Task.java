import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import java.lang.Math;

class Task{



	public static void main (String args[])throws InterruptedException {

		//Init for the Spheric
		JFrame frame=new JFrame("Task1");
		GLCanvas canvas=new GLCanvas();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    frame.setSize(950,650+22);

		Random rand = new Random();


		double x = 0;
		double lambda = 10.0;
		double dt = 0.05;
		Wave wave = new Wave(130,130,10,10,5);
		// wave.setBoundary();




		//Init for the viewer
		Viewing viewer = new Viewing(wave);
		canvas.addGLEventListener(viewer);
		canvas.addMouseListener((MouseListener)viewer);
	canvas.addMouseMotionListener((MouseMotionListener)viewer);
	canvas.addMouseWheelListener((MouseWheelListener)viewer);
	canvas.addKeyListener((KeyListener)viewer);


		frame.getContentPane().add(canvas);
		SwingUtilities.updateComponentTreeUI(frame);

		int k=0;
		while(true){
			x += dt;
			wave.perturbation(65,65,325+200*Math.exp(-lambda*x)*Math.cos(x));

			wave.calculatePos();
			canvas.display();
			// viewer.drawObject(objects);

			// Thread.sleep((int)(1000*dt));
		}
		}





	}
