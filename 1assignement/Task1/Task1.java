

class Task1{
	public static void main (String args[])throws InterruptedException {


		//Init for the particle


		double m = 1;
		double dt = 0.001;
		double[] pos ={2,5,2};//between 0 and 10 ratio 1/100, position size of the window
		double[] vel = {1,1,-1};

		double[] f = {0,0,9.8*m};
		Particle p = new Particle(m, pos, vel, f, dt,0.2);
		Particle[] particles = {p};

		//Init for the viewer
		
		Viewing viewer = new Viewing(1000,1000,particles,"Task1");

		int k=0;
		long time=System.nanoTime();
		while(true){

			Thread.sleep((int)(5000*dt));
			viewer.drawParticles(particles);


			for(int i=0;i<particles.length;i++){
				particles[i].calculatePos();
			}
			Thread.sleep((int)(1000*dt));
		}

	}



}
