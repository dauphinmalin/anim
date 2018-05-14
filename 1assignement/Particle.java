public class Particle{

  private double m;//not equal to 0!!!
  private double[3] pos;//X,Y,Z
  private double[3] previouspos;
  private double[3] vel;
  private double[3] f;//force on X,force Y,force Z
  private double dt;


public void Particle(double m,pos[],vel[],f[],dt){
  if(m>0){
  this.m=m;

    this.pos=pos;
    for(int i=0;i<3;i++){
    this.previouspos[i]=x[i]-vel[i]*dt;
    }
    this.vel=vel
    this.f=f;

}
else throw "masse equal to 0!!"
}

public double getX(){
  return this.pos[0];
}

public double getY(){
  return this.pos[1];
}

public double getZ(){
  return this.pos[2];
}

public void setF(fx,fy,fz){
  this.f[0]=fx;
  this.f[1]=fy;
  this.f[2]=fz;

}

public void calculatePos(){
  double x[3];
  x=this.pos;
  for(i=0;i<3;i++){
    this.pos[i]=2*this.pos[i]-this.previouspos[i]+this.dt*this.dt*this.f[i]/this.m;//Verlet for x,y,z

  }
  this.previouspos=x;

}



}
