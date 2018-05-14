import Particle.java
import Viewing.java

class Task1{
	public static void main (String args[]){

		//Init for the particle
		Particle p = new Particle[];

		double m = 1;
		double dt = 0.1;
		double[3] pos = {1,1,1};
		double[3] vel = {1,1,1};
		double[3] f = {0,0,-9.8};

		p.Particle(m, pos[], vel[], f[], dt);

		//Init for the viewer
		Viewing viewer = new Viewing;


		while(true){

			//Draw the particle
			viewer.drawParticles();

			//Calculate the next position of the particle
			p.calculatePos();

			//Time descretization for dt second
			Thread.sleep((int)(1000*dt);

		}

	}



}