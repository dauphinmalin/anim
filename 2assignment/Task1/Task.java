
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

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
		int n=1;
		ElasticSquare[] objects = new ElasticSquare[n];
		double dt = 0.01;
		int[] collisiontab=new int[n];
		double m = 2000;
		double[] f = {0,0,-9.8*m};

		for(int i=0;i<n;i++){
			collisiontab[i]=i;
			double[] pos = {500*rand.nextDouble(),500*rand.nextDouble(),500*rand.nextDouble()};
			double[] vel = {0,0,0};
			double[] rotation = {Math.PI*rand.nextDouble(),Math.PI*rand.nextDouble(),Math.PI*rand.nextDouble()};
			double[] w = {0.1*rand.nextDouble(),0.1*rand.nextDouble(),0.1*rand.nextDouble()};
			double radius = 100;
			objects[i]=new ElasticSquare(m,pos, vel,rotation, f, dt,radius,w);
		}
		for(int i=0;i<objects.length;i++){
			objects[i].calculatePos();
		}

		//Init for the viewer
		Viewing viewer = new Viewing(objects);
		canvas.addGLEventListener(viewer);
		canvas.addMouseListener((MouseListener)viewer);
		canvas.addMouseMotionListener((MouseMotionListener)viewer);
		canvas.addMouseWheelListener((MouseWheelListener)viewer);
		canvas.addKeyListener((KeyListener)viewer);


		frame.getContentPane().add(canvas);
		SwingUtilities.updateComponentTreeUI(frame);

		int k=0;
		while(true){
			canvas.display();
			for(int i=0;i<objects.length;i++){
				objects[i].calculatePos();
			}
		//	Thread.sleep((int)(1000*dt));
		}
	}
}
