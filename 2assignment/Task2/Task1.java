import java.util.*;
class Task1{
	public static void main (String args[])throws InterruptedException {


		//Init for the particle

		Random rand = new Random();
		int n=10;
		// Arraylist<Particle> particles = new Arraylist<Particle>();
		Particle[] particles = new Particle[n];
		// double m = 1;
		double[] vel0 = {0,0,0};
		double[] f = {0,0,0};
		double[] po = {450,0,350};
		double radius=9;
		double dt = 0.01;
		double G=6.67e-3;
		// double radius=0.2;
		int[] collisiontab=new int[n];
		double M=10000000;

		for(int i=0;i<n;i++){
			double m=(1)*rand.nextDouble();
			int q=rand.nextInt(2);
			double sign=-1+2*rand.nextInt(2);
			double R=(i+1)*30;
			double v=Math.sqrt(G*M/(R));
			if(q==1){
			double z=350+R*sign;
			double[] pos ={450,0,z};
			double[] vel = {-v*sign,0,0};
			particles[i]=new Particle(m,pos, vel, f, dt,radius);

			}
			// double[] pos ={radius+(1-9.5*radius)*rand.nextDouble(),0,radius+(1-6.5*radius)*rand.nextDouble()};
			else{
			double x=450+R*sign;
			double[] pos ={x,0,350};
			double[] vel = {0,0,v*sign};
						particles[i]=new Particle(m,pos, vel, f, dt,radius);
					}





			// double[] po = {3.0,0.0,3.0};
			// particles[0]=new Particle(10,po, vel0, f, dt,radius);


		}
		particles[n-1]=new Particle(M,po, vel0, f, dt,20);


		//Collision
		Collision collision = new Collision(particles, 950, 650);
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

		Viewing viewer = new Viewing(950,650,particles,"Task6");



		while(true){


			viewer.drawParticles(particles);
			collision.calculateForce();
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
		double cr = 0.5;

		if(collArray.length==2){

			// particles[collArray[0]].calculatePos();
			// particles[collArray[1]].calculatePos();

			double deltaX = particles[collArray[0]].getX()-particles[collArray[1]].getX();
			double deltaZ = particles[collArray[0]].getZ()-particles[collArray[1]].getZ();
			double distance = Math.sqrt(deltaX*deltaX + deltaZ*deltaZ);
			double twoRadius = particles[collArray[0]].getRadius() + particles[collArray[1]].getRadius();
			if(distance < twoRadius){
				//method to calculate the velocity
				// double w = particles[collArray[0]].getM()/(particles[collArray[0]].getM()+particles[collArray[1]].getM());
				// //velocity
				// double[] vel = particles[collArray[0]].getVel();
				// for(int i=0;i<3;i++){
				// 	vel[i] *= w*cr;
				// }
				// double[] vel1 = particles[collArray[1]].getVel();
				// for(int i=0;i<3;i++){
				// 	vel1[i] *= (1-w)*cr;
				// }
				// particles[collArray[0]].setVel(vel1);
				// particles[collArray[1]].setVel(vel);

				//new method
				double mdm = Math.sqrt(particles[collArray[1]].getM()/particles[collArray[0]].getM());
				double[] vela = particles[collArray[1]].getVel();
				double[] velb = particles[collArray[0]].getVel();
				for(int i=0; i<3; i++){
					vela[i] *= mdm;
					velb[i] /= mdm;

				}
				particles[collArray[0]].setVel(vela);
				particles[collArray[1]].setVel(velb);

				//method2
				// double[] vela ={0,0,0.0};
				// double[] velb ={0,0,0.0};
				// for(int i=0;i<3;i++){
				// 	vela[i] = (particles[collArray[0]].getVel()[i]*(particles[collArray[0]].getM()-(particles[collArray[1]].getM()))+(2*particles[collArray[1]].getM()*particles[collArray[1]].getVel()[i]))/(particles[collArray[0]].getM()-(particles[collArray[1]].getM()));
				// 	velb[i] = (particles[collArray[1]].getVel()[i]*(particles[collArray[1]].getM()-(particles[collArray[0]].getM()))+(2*particles[collArray[0]].getM()*particles[collArray[0]].getVel()[i]))/(particles[collArray[0]].getM()-(particles[collArray[1]].getM()));
				// }
				// particles[collArray[0]].setVel(vela);
				// particles[collArray[1]].setVel(velb);

				//method3
				// double f1 = (cr*(deltaX*particles[collArray[0]].getVel()[0]+deltaZ*particles[collArray[0]].getVel()[2]))/(distance*distance);
    // 			double f2 = (cr*(deltaX*particles[collArray[1]].getVel()[0]+deltaZ*particles[collArray[1]].getVel()[2]))/(distance*distance);

    // 			double[] vela ={0,0,0.0};
				// double[] velb ={0,0,0.0};

				// vela[0] = particles[collArray[0]].getX() + f2*deltaX-f1*deltaX;
				// velb[0] = particles[collArray[1]].getX() + f1*deltaX-f2*deltaX;
				// vela[2] = particles[collArray[0]].getZ() + f2*deltaZ-f1*deltaZ;
				// velb[2] = particles[collArray[1]].getZ() + f1*deltaZ-f2*deltaZ;

			 //    particles[collArray[0]].setVel(vela);
			 //    particles[collArray[1]].setVel(velb);


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
