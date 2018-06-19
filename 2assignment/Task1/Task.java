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
		double dt = 0.01;
		int[] collisiontab=new int[n];
		double m = 2*rand.nextDouble();

		double[] f = {0,0,0};
		for(int i=0;i<n;i++){
			collisiontab[i]=i;
			double[][] pos ={{250,250,500},{250,250,50},{250,50,20}};
			double[][] vel = {{00,0,200},{0,0,-200},{70,20,30}};
			 double[] rotation={0,0,0};
			 double[] w={0,0,0};
			double[] radius={50,100};
			objects[i]=new Square(m,pos[i], vel[i],rotation, f, dt,radius[i],w);



		}


		//between 0 and 10 ratio 1/100, position size of the window

		//Collision
		Collision collision = new Collision(objects, 650, 650, 650);


		ArrayList<Integer[]> list = collision.checkCollision();
		Iterator<Integer[]> iterator;

		for(int i=0;i<objects.length;i++){
			objects[i].calculatePos();
		}
		 list = collision.checkCollision();



		//Init for the viewer
		Viewing viewer = new Viewing(objects);
		canvas.addGLEventListener(viewer);
		frame.getContentPane().add(canvas);
		SwingUtilities.updateComponentTreeUI(frame);

		int k=0;
		while(true){
			canvas.display();
			viewer.drawObject(objects);
			for(int i=0;i<objects.length;i++){
				objects[i].calculatePos();

			}

			list = collision.checkCollision();
			if(list!=null){

				iterator = list.iterator();

				while (iterator.hasNext()) {
					Integer[] col=iterator.next();
					if(objects[col[1]].checkCollision(objects[col[0]])){}


					else {objects[col[0]].checkCollision(objects[col[1]]);

					}


				}
		}
		}

			// Thread.sleep((int)(1000*dt));

		}

	}
