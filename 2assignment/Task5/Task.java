
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

		Deformable[] objects = new Deformable[n];
		double dt = 0.01;
		int[] collisiontab=new int[n];
		double m = 2;
		double[] f = {0,0,0};

		for(int i=0;i<n;i++){
			collisiontab[i]=i;
			 double[] pos ={100,325,325	};
			//double[] pos = {500*rand.nextDouble(),500*rand.nextDouble(),500*rand.nextDouble()};
			double[] vel = {500*rand.nextDouble(),500*rand.nextDouble(),500*rand.nextDouble()};
		//	double[] rotation = {Math.PI*rand.nextDouble(),Math.PI*rand.nextDouble(),Math.PI*rand.nextDouble()};
			double[] w = {0.1*rand.nextDouble(),0.1*rand.nextDouble(),0.1*rand.nextDouble()};
			double radius = 5;
			 //double[] vel = {80,0,0};
			double[] rotation={0,0,0};
			// double[] w={0,0,0};
			// double[] radius={50,100};
			objects[i]=new Deformable(m,pos, vel,rotation, f, dt,radius,w);
		}


		//
		// Collision collision = new Collision(objects, 650, 650, 650);
		// ArrayList<Integer[]> list = collision.checkCollision();
		// Iterator<Integer[]> iterator;

		for(int i=0;i<objects.length;i++){
			objects[i].calculatePos();
		}
		// list = collision.checkCollision();

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
			// list = collision.checkCollision();
			// if(list!=null){
			// 	iterator = list.iterator();
			// 	while (iterator.hasNext()) {
			// 		Integer[] col=iterator.next();
			// 		if(objects[col[1]].checkCollision(objects[col[0]])){
			// 		}
			// 		else {objects[col[0]].checkCollision(objects[col[1]]);
			// 		}
			// 	}
			// }
			Thread.sleep((int)(1000*dt));
		}
	}
}
