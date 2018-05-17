import java.util.*;

class Task7{
	public static void main (String args[])throws InterruptedException {


		//Init for the particle

		Random rand = new Random();
		int n=2;
		Particle[] particles = new Particle[n];
		double m = 1;
		double dt = 0.001;
		double radius=0.2;
		double[] pos ={1,0,3};
		double[] f = {0,0,0};
		double[] vel = {0,0,0};
		particles[0]=new Particle(m,pos, vel, f, dt,radius);

		pos[0] =5;
		pos[2]=0.5;
		vel[0]=1;
		particles[1]=new Particle(m,pos, vel, f, dt,radius);

		//between 0 and 10 ratio 1/100, position size of the window

		//Collision
		Collision collision = new Collision(particles, 9.5, 6.5);


		ArrayList<Integer[]> list = collision.checkCollision();
		Iterator<Integer[]> iterator;

		for(int i=0;i<particles.length;i++){
			particles[i].calculatePos();
			particles[i].borderResponse();
		}
		// list = collision.checkCollision();
		iterator = list.iterator();
		while (iterator.hasNext()) {
			collisionResponse(iterator.next(), particles);
		}


		//Init for the viewer
		Viewing viewer = new Viewing(950,650,particles,"Task4");


		while(true){


			viewer.drawParticles(particles);
			for(int i=0;i<particles.length;i++){
				particles[i].calculatePos();
				particles[i].borderResponse();
			}
			list = collision.checkCollision();
			iterator = list.iterator();
			while (iterator.hasNext()) {
				collisionResponse(iterator.next(), particles);

			}

			Thread.sleep((int)(1000*dt));



		}

	}


	public static void collisionResponse(Integer[] collArray, Particle[] particles){

		if(collArray.length==2){

			particles[collArray[0]].calculatePos();
			particles[collArray[1]].calculatePos();

			double deltaX = particles[collArray[0]].getX()-particles[collArray[1]].getX();
			double deltaZ = particles[collArray[0]].getZ()-particles[collArray[1]].getZ();
			double distance = Math.sqrt(deltaX*deltaX + deltaZ*deltaZ);
			double twoRadius = particles[collArray[0]].getRadius() + particles[collArray[1]].getRadius();
			if(distance < twoRadius){
				//velocity
				double[] vel = particles[collArray[0]].getVel();
				particles[collArray[0]].setVel(particles[collArray[1]].getVel());
				particles[collArray[1]].setVel(vel);

				//push particles away from each other
				particles[collArray[0]].setX(particles[collArray[0]].getX() + deltaX*(twoRadius-distance)*0.5/distance);
				particles[collArray[0]].setZ(particles[collArray[0]].getZ() + deltaZ*(twoRadius-distance)*0.5/distance);
				particles[collArray[1]].setX(particles[collArray[1]].getX() - deltaX*(twoRadius-distance)*0.5/distance);
				particles[collArray[1]].setZ(particles[collArray[1]].getZ() - deltaZ*(twoRadius-distance)*0.5/distance);

				//previous position
				particles[collArray[0]].setPreviousPos(particles[collArray[0]].getX()-particles[collArray[0]].getVel()[0],0,particles[collArray[0]].getZ()-particles[collArray[0]].getVel()[2]);
				particles[collArray[1]].setPreviousPos(particles[collArray[1]].getX()-particles[collArray[1]].getVel()[0],0,particles[collArray[1]].getZ()-particles[collArray[1]].getVel()[2]);
			}


		}
		else if(collArray.length>2){

		}

	}



}
