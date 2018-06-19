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
		int n=2;
		Square[] objects = new Square[n];
		// double m = 1;
		double dt = 0.01;
		// double radius=0.2;
		int[] collisiontab=new int[n];

		double[] f = {0,0,0};
		for(int i=0;i<n;i++){
			collisiontab[i]=i;
			double[][] pos ={{250,50,250},{250,500,250},{25,50,20}};
			// double[] f = {0,0,0};
			// double[] vel = {40*rand.nextDouble(),40*rand.nextDouble(),40*rand.nextDouble()};
			// double[] pos = {650*rand.nextDouble(),650*rand.nextDouble(),650*rand.nextDouble()};
			double[][] vel = {{0,80,0},{0,-80,0},{70,20,30}};


			// double[] pos ={radius+(1-9.5*radius)*rand.nextDouble(),0,radius+(1-6.5*radius)*rand.nextDouble()};




			// double[] rotation = {Math.PI/2*rand.nextDouble(),Math.PI/2*rand.nextDouble(),Math.PI/2*rand.nextDouble()};
			// double[] w = {100*rand.nextDouble(),100*rand.nextDouble(),100*rand.nextDouble()};

			 double[] rotation={0,0,0};
			 double[] w={0,0,0};
			// double[] po = {3.0,0.0,3.0};
			double m = 0.5+0.1*rand.nextDouble();
			// double radius=70*m;
			// double radius=((int)(100*m));
			double[] radius={50,100};
			// double length = radius;
			// double width = radius;
			// double height = radius;
			objects[i]=new Square(m,pos[i], vel[i],rotation, f, dt,radius[i],w);
			// objects[0]=new Spheric(m,po, vel, f, dt,radius);

		}


		//between 0 and 10 ratio 1/100, position size of the window

		//Collision
		Collision collision = new Collision(objects, 650, 650, 650);


		ArrayList<Integer[]> list = collision.checkCollision();
		Iterator<Integer[]> iterator;

		for(int i=0;i<objects.length;i++){
			objects[i].calculatePos();
			objects[i].borderResponse();
		}
		 list = collision.checkCollision();
	/*	 if(list!=null){
		iterator = list.iterator();
		while (iterator.hasNext()) {
			collisionResponse(iterator.next(), objects);
		}
	}*/


		//Init for the viewer
		Viewing viewer = new Viewing(objects);
		canvas.addGLEventListener(viewer);
		frame.getContentPane().add(canvas);
		SwingUtilities.updateComponentTreeUI(frame);

		int k=0;
		while(true){
			System.out.println("step");
			canvas.display();
			viewer.drawObject(objects);
			for(int i=0;i<objects.length;i++){
				// objects[i].setF(f[0],f[1],f[2]);
				objects[i].calculatePos();
			}
			// System.out.println("rotation: "+objects[0].getRot()[0]+"  "+objects[0].getRot()[1]+"  "+objects[0].getRot()[2]);

			list = collision.checkCollision();
			if(list!=null){
				// System.out.println("what happened?");

				iterator = list.iterator();

				while (iterator.hasNext()) {
					// System.out.println("have collision!");
					Integer[] col=iterator.next();
					// System.out.println("col: "+col[0]+"  "+col[1]);
					if(objects[col[0]].checkCollision(objects[col[1]])){
						k+=1;
						for(int i=0;i<objects.length;i++){
							objects[i].calculatePos();
						}
						System.out.println(k+"A:  "+col[0]+"    "+col[1]);
						// System.out.println(objects[col[0]].checkCollision(objects[col[1]]));
					}
					else if(objects[col[1]].checkCollision(objects[col[0]])){
						k+=1;
						for(int i=0;i<objects.length;i++){
							objects[i].calculatePos();
						}
						System.out.println(k+"B:  "+col[1]+"    "+col[0]);
						// System.out.println(objects[col[0]].checkCollision(objects[col[1]]));
					}
					// System.out.println(k+":  "+col[0]+"    "+col[1]);
					// System.out.println(objects[col[0]].checkCollision(objects[col[1]]));


				}
		}
		}

			// Thread.sleep((int)(1000*dt));

		}

	}
