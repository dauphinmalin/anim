import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.GL2;
abstract class PrimitiveObject{
  protected double m;//not equal to 0!!!
  protected double[] pos;//X,Y,Z
  protected double[] previouspos;
  protected double[] nextpos;
  protected double[] vel;
  protected double[] rotation;
  protected double[] f;//force on X,force Y,force Z
  protected double dt;
  protected double[] extremeInf;
  protected double[] extremeSup;
  protected static double[] posMAX={650,650,1500};
  protected double[] pos0;
  protected static double cr=0.5;
  protected static double gcst = 6.67e-3;
  protected static double alpha=0.5;
  protected static Spheric[] particles= new Spheric[10000];
  protected static int indicemax =0;
  protected static double cf =0.5;
  protected static double h=2;
  protected static double p0=1000;
  protected static double cs=1;





  PrimitiveObject(double m,double[] pos,double[] vel,double[] rotation,double[] f,double dt){
    this.m=m;
    this.dt=dt;
    this.previouspos=new double[3];
    this.nextpos=new double[3];
    this.pos=pos.clone();
    this.pos0=pos.clone();
    this.rotation=rotation;
    for(int i=0;i<pos.length;i++){
      this.previouspos[i]=pos[i]-vel[i]*dt;
      this.nextpos[i] = 2*pos[i]-pos[i]-vel[i]*dt+dt*dt*f[i]/m;
    }
    this.vel=vel.clone();
    this.f=f.clone();
    this.rotation=rotation.clone();

  }

  abstract void calculateBoundingVolume();
  abstract void borderResponse();
  abstract void calculateForce(double m,double[] cm);
  abstract void calculatePos();
  abstract void Draw(GLAutoDrawable drawable,GLU glu,GL2 gl);

  public void setPos(double[] pos,double[] rotation){
    this.pos=pos;
    this.rotation=rotation;
  }
  /*abstract void draw();

  abstract void Collision();*/

  public double[] getExtremeInf(){
    return this.extremeInf;

  }
  public double[] getExtremeSup(){
    return this.extremeSup;

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

  public void addForce(double f[]){
    this.f[0]+=f[0];
    this.f[1]+=f[1];
    this.f[2]+=f[2];
  }

  public double[] getVel(){
    return this.vel;
  }

  public double[] getRot(){
    return this.rotation;
  }

  public double getM(){
    return this.m;
  }

  public void setVel(double[] vel){
    this.vel = vel;
  }
  public void addVel(double[] F){
    for(int i=0;i<3;i++){
      this.vel[i] += F[i]*this.dt/this.m;
    }
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

  public void setRot(double rotx, double roty, double rotz){
    this.rotation[0]+=rotx;
    this.rotation[1]+=roty;
    this.rotation[2]+=rotz;

  }

  public void setPreviousPos(double px,double py,double pz){
    this.previouspos[0]=px;
    this.previouspos[1]=py;
    this.previouspos[2]=pz;

  }



  /*abstract void draw();

  abstract void Collision();*/
}
