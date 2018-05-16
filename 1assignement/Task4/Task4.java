import java.util.Random;

class Task4{
	public static void main (String args[])throws InterruptedException {


		//Init for the particle

		Random rand = new Random();
		int n=10;
		Particle[] particles = new Particle[n];
		double m = 1;
		double dt = 0.001;
		double radius=0.2;

		for(int i=0;i<n;i++){
			double[] pos ={(9.5-radius)*rand.nextDouble(),0,(6.5-radius)*rand.nextDouble()};
			double[] f = {0,0,0};
			double[] vel = {2*rand.nextDouble(),0,2*rand.nextDouble()};


			particles[i]=new Particle(m,pos, vel, f, dt,radius);


		}

		//between 0 and 10 ratio 1/100, position size of the window



		//Init for the viewer
		Viewing viewer = new Viewing(950,650,particles,"Task4");

		int k=0;

		// while(true){
		//
		//
		// 	viewer.drawParticles(particles);
		//
		//
		// 	for(int i=0;i<particles.length;i++){
		// 		particles[i].calculatePos();
		// 	Thread.sleep((int)(1000*dt));
		//
		// 	}
		//
		// }

	}



}
