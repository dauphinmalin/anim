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
			double[] vel = {2*rand.nextDouble(),0,2*rand.nextDouble()};


			particles[i]=new Particle(m,pos, vel, f, dt,radius);


		}


		//between 0 and 10 ratio 1/100, position size of the window



		//Init for the viewer
		Viewing viewer = new Viewing(950,650,particles,"Task4");

		Collision check = new Collision(particles,9.5,6.5);
		int[][] cluster=check.Cluster(collisiontab);
		System.out.println("left");
		for(int i=0;i<cluster[0].length;i++){
			System.out.println(cluster[0][i]);
		}
		System.out.println("right");
		for(int i=0;i<cluster[1].length;i++){
			System.out.println(cluster[1][i]);
		}

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
