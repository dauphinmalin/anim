
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

class Task{
	public static void main (String args[])throws InterruptedException {

		JFrame frame=new JFrame("Task1");
		GLCanvas canvas=new GLCanvas();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(950,650+22);
		Random rand = new Random();
		int n=400;
		Spheric[] objects = new Spheric[n];
		Spheric[] particles;
		double dt = 0.1;
		int[] collisiontab=new int[n];
		double m = 20;
		double[] f = {0,0,-98.1*m};

		for(int i=0;i<n;i++){
			collisiontab[i]=i;
			double[] pos = {275+100*rand.nextDouble(),275+100*rand.nextDouble(),100+1000*rand.nextDouble()};

			double[] vel = {0,0,0};
			double[] rotation = {Math.PI*rand.nextDouble(),Math.PI*rand.nextDouble(),Math.PI*rand.nextDouble()};
			double[] w = {0.1*rand.nextDouble(),0.1*rand.nextDouble(),0.1*rand.nextDouble()};
			double radius = 10;

			objects[i]=new Spheric(m,pos, vel,rotation, f, dt,radius);
			PrimitiveObject.particles[i]=(Spheric)objects[i];

		}

		System.out.println(PrimitiveObject.indicemax);
		PrimitiveObject.indicemax=n;

			Collision collision = new Collision(objects, 650, 650, 650);
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

			for(int i=0;i<objects.length;i++){
				objects[i].calculatePos();
			}
				/*for(int i=0;i<objects.length;i++){
				objects[i].shapeMatching();
			}*/
			 collision = new Collision(objects, 650, 650, 650);

			ArrayList<Integer[]> list = collision.checkCollision(PrimitiveObject.indicemax);
			Iterator<Integer[]> iterator;
			if(list!=null){
				iterator = list.iterator();
				while (iterator.hasNext()) {
					Integer[] col=iterator.next();
					PrimitiveObject.particles[col[1]].checkCollision(PrimitiveObject.particles[col[0]]);
				}
			}
			collision.calculateForce();

			canvas.display();


		//	Thread.sleep((int)(1000*dt));
		}
	}
}
