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
		Square[] objects = new Square[n];
		// double m = 1;
		double dt = 0.01;
		// double radius=0.2;
		int[] collisiontab=new int[n];


		for(int i=0;i<n;i++){
			collisiontab[i]=i;
			double[] pos ={250,250,250};
			double[] f = {0,0,0};
			double[] vel = {2000*rand.nextDouble(),200*rand.nextDouble(),200*rand.nextDouble()};
			//double[] pos ={radius+(1-9.5*radius)*rand.nextDouble(),0,radius+(1-6.5*radius)*rand.nextDouble()};
		//	double[] vel={0,0,0};
			//double[] rotation = {rand.nextDouble(),rand.nextDouble(),rand.nextDouble()};
			double[] rotation={0,0,0};
			// double[] po = {3.0,0.0,3.0};
			double m = 1*rand.nextDouble();
			double radius=70;
			double length = radius;
			double width = radius;
			double height = radius;
			objects[i]=new Square(m,pos, vel,rotation, f, dt,radius);
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


			}
		}

			// Thread.sleep((int)(1000*dt));

		}

	}


	public static void collisionResponse(Integer[] collArray, Spheric[] objects){

		if(collArray.length==2){

			objects[collArray[0]].calculatePos();
			objects[collArray[1]].calculatePos();

			double deltaX = objects[collArray[0]].getX()-objects[collArray[1]].getX();
			double deltaZ = objects[collArray[0]].getZ()-objects[collArray[1]].getZ();
			double distance = Math.sqrt(deltaX*deltaX + deltaZ*deltaZ);
			double twoRadius = objects[collArray[0]].getRadius() + objects[collArray[1]].getRadius();
			if(distance < twoRadius){
				//velocity
				double mdm = Math.sqrt(objects[collArray[1]].getM()/objects[collArray[0]].getM());
				double[] vela = objects[collArray[1]].getVel();
				double[] velb = objects[collArray[0]].getVel();
				for(int i=0; i<3; i++){
					vela[i] *= mdm;
					velb[i] /= mdm;

				}
				objects[collArray[0]].setVel(vela);
				objects[collArray[1]].setVel(velb);

				//push objects away from each other
				objects[collArray[0]].setX(objects[collArray[0]].getX() + deltaX*(twoRadius-distance)*0.5/distance);
				objects[collArray[0]].setZ(objects[collArray[0]].getZ() + deltaZ*(twoRadius-distance)*0.5/distance);
				objects[collArray[1]].setX(objects[collArray[1]].getX() - deltaX*(twoRadius-distance)*0.5/distance);
				objects[collArray[1]].setZ(objects[collArray[1]].getZ() - deltaZ*(twoRadius-distance)*0.5/distance);

				//previous position
				objects[collArray[0]].setPreviousPos(objects[collArray[0]].getX()-objects[collArray[0]].getVel()[0],0,objects[collArray[0]].getZ()-objects[collArray[0]].getVel()[2]);
				objects[collArray[1]].setPreviousPos(objects[collArray[1]].getX()-objects[collArray[1]].getVel()[0],0,objects[collArray[1]].getZ()-objects[collArray[1]].getVel()[2]);
			}


		}
		else if(collArray.length>2){

		}

	}



}
