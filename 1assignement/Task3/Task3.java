

class Task3{
	public static void main (String args[])throws InterruptedException {


		//Init for the particle


		double m = 1;
		double dt = 0.001;
		double[] pos ={1.0,0,47.0/110+0.3};//between 0 and 10 ratio 1/100, position size of the window
		double[] vel = {0,0,0};

		double[] f = {0,0,9.8*m};

		// //Force-based
		double k = 47.0/88;
		double a = Math.atan(k);
		// double fp = f[2]*Math.sin(a);

		// double[] fnew = {fp*Math.cos(a),0,fp*Math.sin(a)};
		// // particles[i].setF(fnew*Math.cos(a),0,fnew*Math.sin(a));
		// System.out.println(fnew[0]);
		


		Particle p = new Particle(m, pos, vel, f, dt,0.2);
		Particle[] particles = {p};


		//Set the PosMax and PosMin
		for(int i=0;i<particles.length;i++){
			particles[i].setLimit(0.2+particles[i].getRadius()*Math.cos(a),0,0.3+particles[i].getRadius()*Math.sin(a),9-particles[i].getRadius()*Math.cos(a),0,5-particles[i].getRadius()*Math.sin(a));

		}



		//Init for the viewer
		Viewing viewer = new Viewing(950,660,particles,"Task3");

		long time=System.nanoTime();
		while(true){


			viewer.drawParticles(particles);


			for(int i=0;i<particles.length;i++){
				particles[i].calculatePos(viewer.getT(), viewer.getD());
				// particles[i].calculatePosF();
			}
			Thread.sleep((int)(1000*dt));

		}

	}



}
