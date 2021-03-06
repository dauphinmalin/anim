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
		JFrame frame=new JFrame("Task4");
		GLCanvas canvas=new GLCanvas();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    frame.setSize(950,650+22);

		//wave init
		double x = 0;
		double lambda = 10.0;
		double dt = 0.05;
		Wave wave = new Wave(130,130,10,10,5);

		//spheres in wave init
		Random rand = new Random();
		int n=130*130;
		ElasticSquare[] objects = new ElasticSquare[n];
		double dt = 0.01;
		int[] collisiontab=new int[n];
		double m = 2;
		double[] f = {0,0,0};

		for(int i=0;i<n;i++){
			collisiontab[i]=i;
			// double[][] pos ={{250,50,250},{250,500,250},{25,50,20}};
			double[] pos = {500*rand.nextDouble(),500*rand.nextDouble(),500*rand.nextDouble()};
			double[] vel = {500*rand.nextDouble(),500*rand.nextDouble(),500*rand.nextDouble()};
			double[] rotation = {Math.PI*rand.nextDouble(),Math.PI*rand.nextDouble(),Math.PI*rand.nextDouble()};
			double[] w = {0.1*rand.nextDouble(),0.1*rand.nextDouble(),0.1*rand.nextDouble()};
			double radius = 2;
			// double[][] vel = {{0,80,0},{0,-80,0},{70,20,30}};
			//double[] rotation={0,0,0};
			// double[] w={0,0,0};
			// double[] radius={50,100};
			objects[i]=new ElasticSquare(m,pos, vel,rotation, f, dt,radius,w);
		}



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
