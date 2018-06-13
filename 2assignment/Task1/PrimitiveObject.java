abstract class PrimitiveObject{
  protected double m;//not equal to 0!!!
  protected double[] pos;//X,Y,Z
  protected double[] previouspos;
  protected double[] nextpos;
  protected double[] vel;
  protected double[] rotation;
  protected double[] f;//force on X,force Y,force Z
  protected double dt;
  protected static double[] posMAX;
  protected double width;
  protected double height;


PrimitiveObject(double m,double[] pos,double[] vel,double[] rotation,double[] f,double dt,double width,double height){
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
  this.rotation=rotation.clone();
  this.width=width;
  this.height=height;
}

PrimitiveObject(double m,double[] pos,double[] vel,double[] f,double dt,double width,double height){
  this.m=m;
  this.dt=dt;
  this.previouspos=new double[3];
  this.nextpos=new double[3];
  this.pos=pos.clone();
  double[] rotation = {0,0,0};
  this.rotation=rotation;
  for(int i=0;i<pos.length;i++){
  this.previouspos[i]=pos[i]-vel[i]*dt;
  this.nextpos[i] = 2*pos[i]-pos[i]-vel[i]*dt+dt*dt*f[i]/m;
  }
  this.vel=vel.clone();
  this.f=f.clone();
  this.rotation=rotation.clone();
  this.width=width;
  this.height=height;
}
  abstract void borderResponse();
  abstract void calculateForce(double m,double cmx,double cmz);
  abstract void calculatePos();

  public void setPos(double[] pos,double[] rotation){
    this.pos=pos;
    this.rotation=rotation;
  }
  /*abstract void draw();

  abstract void Collision();*/

  public double getHeight(){
    return this.height;

  }
  public double getWidth(){
    return this.width;
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

  public double getpX(){
    return this.previouspos[0];
  }

  public double getpY(){
    return this.previouspos[1];
  }

  public double getpZ(){
    return this.previouspos[2];
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



  /*abstract void draw();

  abstract void Collision();*/
}
