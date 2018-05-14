

class Task1{
	public static void main (String args[]){

		//Init for the particle


		double m = 1;
		double dt = 0.1;
		double[] pos ={1,1,1};
		double[] vel = {1,1,1};
		double[] f = {0,0,-9.8};
		Particle p = new Particle(m, pos, vel, f, dt);


		//Init for the viewer
		Viewing viewer = new Viewing(100,100);


		/*while(true){

			//Draw the particle
			viewer.drawParticles();

			//Calculate the next position of the particle
			p.calculatePos();

			//Time descretization for dt second
			Thread.sleep((int)(1000*dt)

		}*/

	}



}
