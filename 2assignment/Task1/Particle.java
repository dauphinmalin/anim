

public class Particle extends PrimitiveObject{


  private double radius;//radius to display the particle
  private static double[] posMAX={9.5,0,6.5};
  private static double cr=0.7;
  private static double gcst = 6.67e-1;

@Override
public Particle(double m,double[] pos,double[] vel,double[] f,double dt,double radius){
  if(m>0){
    this.m=m;
    this.dt=dt;
    this.previouspos=new double[3];
    this.nextpos=new double[3];
    this.pos=pos.clone();
    for(int i=0;i<pos.length;i++){
    this.previouspos[i]=pos[i]-vel[i]*dt;
    this.nextpos[i] = 2*pos[i]-pos[i]-vel[i]*dt+dt*dt*f[i]/m;
    }
    this.vel=vel.clone();
    this.f=f.clone();
    this.radius=radius;


}
//else throw "masse equal to 0!!"
}


public double getRadius(){
  return this.radius;
}

public double[] getVel(){
  return this.vel;
}

public double getM(){
  return this.m;
}

public void setVel(double[] vel){
  this.vel = vel;
}

public void setX(double x){
  this.pos[0] = x;
}

public void setY(double y){
  this.pos[1] = y;
}

public void setZ(double z){
  this.pos[2] = z;
}

public void setF(double fx,double fy,double fz){
  this.f[0]=fx;
  this.f[1]=fy;
  this.f[2]=fz;

}

public void setPreviousPos(double px,double py,double pz){
  this.previouspos[0]=px;
  this.previouspos[1]=py;
  this.previouspos[2]=pz;

}

// public void calculatePos(){
//   double inter;
//   for(int i=0;i<3;i++){
//     inter=2*this.pos[i]-this.previouspos[i]+this.dt*this.dt*this.f[i]/this.m;//Verlet for x,y,z


//     if(inter>(this.posMAX[i]-this.radius)){
//       this.vel[i]=-this.cr*(inter-this.pos[i]);
//       this.previouspos[i]=this.posMAX[i]-this.radius-this.vel[i];
//       this.pos[i]=this.posMAX[i]-this.radius;
//     }
//     else if(inter<this.radius){
//       this.vel[i]=-this.cr*(inter-this.pos[i]);
//       this.previouspos[i]=this.radius-this.vel[i];
//       this.pos[i]=this.radius;
//     }
//     else{
//     this.previouspos[i]=this.pos[i];
//     this.pos[i]=inter;
//     this.vel[i]=this.cr*(this.pos[i]-this.previouspos[i]);
//     }
//   }
// }

public void calculatePos(){
  for(int i=0;i<3;i++){
    this.nextpos[i] = 2*this.pos[i]-this.previouspos[i]+this.dt*this.dt*this.f[i]/this.m;//Verlet for x,y,z
  }
}
@Override
public void borderResponse(){

  for(int i=0;i<3;i++){

    if(this.nextpos[i]>(this.posMAX[i]-this.radius)){
      this.vel[i]=-this.cr*(this.nextpos[i]-this.pos[i]);
      this.previouspos[i]=this.posMAX[i]-this.radius-this.vel[i];
      this.pos[i]=this.posMAX[i]-this.radius;
    }
    else if(this.nextpos[i]<this.radius){
      this.vel[i]=-this.cr*(this.nextpos[i]-this.pos[i]);
      this.previouspos[i]=this.radius-this.vel[i];
      this.pos[i]=this.radius;
    }
    else{
    this.previouspos[i]=this.pos[i];
    this.pos[i]=this.nextpos[i];
    this.vel[i]=this.cr*(this.pos[i]-this.previouspos[i]);
    }
  }
}
@Override
public void calculateForce(double m,double cmx,double cmz){
  this.f[0]=0;
  this.f[2]=0;
        double x = cmx - this.pos[0];
        double z = cmz - this.pos[2];
        double distance = Math.sqrt(x*x+z*z);
        if(distance!=0){
          double force = this.gcst*this.m*m/(distance*distance*distance);
          this.f[0] += force*x;
          this.f[2] += force*z;


    }
}





}
