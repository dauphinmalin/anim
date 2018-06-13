abstract class PrimitiveObject{
  private double m;//not equal to 0!!!
  private double[] pos;//X,Y,Z
  private double[] previouspos;
  private double[] nextpos;
  private double[] vel;
  private double[] f;//force on X,force Y,force Z
  private double dt;
  private static double[] posMAX;


  abstract void borderResponse();
  abstract void calculateForce(double m,double cmx,double cmz);
  /*abstract void draw();

  abstract void Collision();*/
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
