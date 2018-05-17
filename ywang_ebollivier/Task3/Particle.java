public class Particle{

  private double m;//not equal to 0!!!
  private double[] pos;//X,Y,Z
  private double[] previouspos;
  private double[] vel;
  private double[] f;//force on X,force Y,force Z
  private double dt;
  private double radius;//radius to display the particle
  private static double[] posMAX={0,0,0};
  private static double[] posMIN={0,0,0};
  private static double cr=1;


public Particle(double m,double[] pos,double[] vel,double[] f,double dt,double radius){
  if(m>0){
  this.m=m;
    this.previouspos=new double[3];
    this.pos=pos.clone();
    for(int i=0;i<pos.length;i++){
    this.previouspos[i]=pos[i]-vel[i]*dt;
    }
    this.vel=vel.clone();
    this.f=f.clone();
    this.radius=radius;
    this.dt=dt;

}
//else throw "masse equal to 0!!"
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

public double getRadius(){
  return this.radius;
}

public double getF(){
  return this.f[0];
}

public void setF(double fx,double fy,double fz){
  this.f[0]=fx;
  this.f[1]=fy;
  this.f[2]=fz;

}

public void setLimit(double px0,double py0,double pz0,double px1,double py1,double pz1){
  this.posMIN[0]=px0;
  this.posMIN[1]=py0;
  this.posMIN[2]=pz0;
  this.posMAX[0]=px1;
  this.posMAX[1]=py1;
  this.posMAX[2]=pz1;

}

public void calculatePos(double t, double d){

  double[] inter = {0,0,0};
  for(int i=0;i<3;i++){
    //Verlet for x,y,z
    inter[i] = 2*this.pos[i]-this.previouspos[i]+this.dt*this.dt*this.f[i]/this.m;

    // this.previouspos[i] = this.pos[i];

  }


  if(inter[0]>(this.posMAX[0])){
    for(int i=0;i<3;i++){
      double vel=-this.cr*(inter[i]-this.pos[i]);
      this.previouspos[i]=this.posMAX[i]-vel;
      this.pos[i]=this.posMAX[i];
    }
  }
  else if(inter[2]>(this.posMAX[2])){
    for(int i=0;i<3;i++){
      double vel=-this.cr*(inter[i]-this.pos[i]);
      this.previouspos[i]=this.posMAX[i]-vel;
      this.pos[i]=this.posMAX[i];
    }
  }
  else if(inter[0]<this.posMIN[0]){
    for(int i=0;i<3;i++){
      double vel=-this.cr*(inter[i]-this.pos[i]);
      this.previouspos[i]=this.posMIN[i]-vel;
      this.pos[i]=this.posMIN[i];
    }
  }
  else if(inter[2]<this.posMIN[2]){
    for(int i=0;i<3;i++){
      double vel=-this.cr*(inter[i]-this.pos[i]);
      this.previouspos[i]=this.posMIN[i]-vel;
      this.pos[i]=this.posMIN[i];
    }
  }
  else{
    for(int i=0;i<3;i++){
      this.previouspos[i]=this.pos[i];
      // this.pos[i]=inter[i];
    }
    // Position-based
    this.pos[0] = (inter[2] - d)/t;
    this.pos[1] = inter[1];
    this.pos[2] = inter[2];
  }
}

public void calculatePosF(){

  double[] inter = {0,0,0};
  for(int i=0;i<3;i++){
    inter[i]=2*this.pos[i]-this.previouspos[i]+this.dt*this.dt*this.f[i]/this.m;//Verlet for x,y,z
  }
  if(inter[0]>(this.posMAX[0])){
    for(int i=0;i<3;i++){
      double vel=-this.cr*(this.posMAX[i]-this.pos[i]);
      this.previouspos[i]=this.posMAX[i]-vel;
      this.pos[i]=this.posMAX[i];
    }
  }
  else if(inter[2]>(this.posMAX[2])){
    for(int i=0;i<3;i++){
      double vel=-this.cr*(this.posMAX[i]-this.pos[i]);
      this.previouspos[i]=this.posMAX[i]-vel;
      this.pos[i]=this.posMAX[i];
    }
  }
  else if(inter[0]<this.posMIN[0]){
    for(int i=0;i<3;i++){
      double vel=-this.cr*(this.posMIN[i]-this.pos[i]);
      this.previouspos[i]=this.posMIN[i]-vel;
      this.pos[i]=this.posMIN[i];
    }
  }
  else if(inter[2]<this.posMIN[2]){
    for(int i=0;i<3;i++){
      double vel=-this.cr*(this.posMIN[i]-this.pos[i]);
      this.previouspos[i]=this.posMIN[i]-vel;
      this.pos[i]=this.posMIN[i];
    }
  }
  else{
    for(int i=0;i<3;i++){
      this.previouspos[i]=this.pos[i];
      this.pos[i]=inter[i];
    }
  }


}


}
