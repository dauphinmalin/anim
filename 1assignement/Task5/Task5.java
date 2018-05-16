import java.util.Random;

class Task5{
	public static void main (String args[])throws InterruptedException {


		//Init for the particle

		Random rand = new Random();
		int n=2;
		// Arraylist<Particle> particles = new Arraylist<Particle>();
		Particle[] particles = new Particle[n];
		// double m = 1;
		double[] vel0 = {0,0,0};
		double[] vel1 = {0,0,5};
		double[] po = {4.5,0,3.5};
		double[] po1 = {2,0,3.5};
		double dt = 0.1;
		// double radius=0.2;
		int[] collisiontab=new int[n];

		for(int i=0;i<n;i++){
			collisiontab[i]=i;
			// double[] pos ={radius+(1-9.5*radius)*rand.nextDouble(),0,radius+(1-6.5*radius)*rand.nextDouble()};
			double[] pos ={9.5*rand.nextDouble(),0,6.5*rand.nextDouble()};
			double[] f = {0,0,0};
			double[] vel = {4*rand.nextDouble(),0,4*rand.nextDouble()};
			double m = 1*rand.nextDouble();
			double radius=m;


			// double[] po = {3.0,0.0,3.0};
			particles[i]=new Particle(10,po1, vel1, f, dt,radius);
			particles[0]=new Particle(10,po, vel0, f, dt,radius);


		}

		// particles.remove(1);
		//between 0 and 10 ratio 1/100, position size of the window

		//Collision
		// Collision collision = new Collision(particles, 9.5, 6.5);

		//get a tab from checkCollision;
		// int[][] tab = collision.checkCollision();
		// int[][] tab = {{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},
		// 				{1,2},{1,3},{1,4},{1,5},{1,6},{1,7},{1,8},{1,9},
		// 				{2,3},{2,4},{2,5},{2,6},{2,7},{2,8},{2,9},
		// 				{3,4},{3,5},{3,6},{3,7},{3,8},{3,9},
		// 				{4,5},{4,6},{4,7},{4,8},{4,9},
		// 				{5,6},{5,7},{5,8},{5,9},
		// 				{6,7},{6,8},{6,9},
		// 				{7,8},{7,9},
		// 				{8,9}};

		int[][] tab = {{0,1}};


		//Init for the viewer
		Viewing viewer = new Viewing(950,650,particles,"Task5");



		while(true){
		
		
			viewer.drawParticles(particles);
		
			//get a tab from checkCollision;
			// tab = collision.checkCollision();


			for(int i=0;i<particles.length;i++){
				particles[i].calculateForce(particles);
				particles[i].calculatePos();
				particles[i].borderResponse();
			}
			
			//collision response
			for(int j=0; j<tab.length;j++){
				collisionResponse(tab[j], particles);
			}
			
			Thread.sleep((int)(1000*dt));		
		}

	}


	public static void collisionResponse(int[] collArray, Particle[] particles){

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