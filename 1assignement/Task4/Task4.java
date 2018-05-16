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
		int[] collisiontab=new int[n];

		for(int i=0;i<n;i++){
			collisiontab[i]=i;
			double[] pos ={radius+(1-9.5*radius)*rand.nextDouble(),0,radius+(1-6.5*radius)*rand.nextDouble()};
			double[] f = {0,0,0};
			double[] vel = {20*rand.nextDouble(),0,20*rand.nextDouble()};


			particles[i]=new Particle(m,pos, vel, f, dt,radius);


		}


		//between 0 and 10 ratio 1/100, position size of the window

		//Collision
		Collision collision = new Collision(particles, 9.5, 6.5);

		//get a tab from checkCollision;
		int[][] tab = collision.checkCollision();


		//Init for the viewer
		Viewing viewer = new Viewing(950,650,particles,"Task4");



		while(true){
		
		
			viewer.drawParticles(particles);
		
			//get a tab from checkCollision;
			tab = collision.checkCollision();

			//collision response
			for (int i=0; i<tab.length; i++){
				

			}

			for(int i=0;i<particles.length;i++){
				particles[i].calculatePos();
			}

			Thread.sleep((int)(1000*dt));
		
			
		
		}

	}



}
