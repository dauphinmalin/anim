import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.util.*;
public class Square extends PrimitiveObject{
  private double side;

public Square(double m,double[] pos,double[] vel,double[] rotation,double[] f,double dt,double side){
  super(m,pos,vel,rotation,f,dt);
  for(int j=0;j<3;j++){

  }
  this.side=side;
  this.extremeInf=new double[3];
  this.extremeSup=new double[3];
  for(int j=0;j<3;j++){
    this.extremeInf[j]=pos[j]-this.side/2;
    this.extremeSup[j]=pos[j]+this.side/2;
}
}
@Override
  public void Draw(GLAutoDrawable drawable,GLU glu,GL2 gl){
    gl.glPushMatrix();
    gl.glTranslatef((float)pos[0],(float)pos[1],(float)pos[2]);
    GLUquadric quad = glu.gluNewQuadric();
    glu.gluCylinder(quad,this.side,this.side,this.side,4,4);
    glu.gluDeleteQuadric(quad);
    gl.glPopMatrix();
}

public void calculateBoundingVolume(){
}

public void calculatePos(){
  for(int i=0;i<3;i++){
    this.nextpos[i] = 2*this.pos[i]-this.previouspos[i]+this.dt*this.dt*this.f[i]/this.m;//Verlet for x,y,z
  }
}
@Override
public void borderResponse(){

  for(int i=0;i<3;i++){

    if(this.nextpos[i]>(this.posMAX[i]-this.side/2)){
      this.vel[i]=-this.cr*(this.nextpos[i]-this.pos[i]);
      this.previouspos[i]=this.posMAX[i]-this.side/2-this.vel[i];
      this.pos[i]=this.posMAX[i]-this.side/2;
      this.extremeSup[i]=this.posMAX[i];
    }
    else if(this.nextpos[i]<this.side/2){
      this.vel[i]=-this.cr*(this.nextpos[i]-this.pos[i]);
      this.previouspos[i]=this.side/2-this.vel[i];
      this.pos[i]=this.side/2;
      this.extremeInf[i]=0;
    }
    else{
      this.previouspos[i]=this.pos[i];
      this.pos[i]=this.nextpos[i];
      this.extremeInf[i]=this.pos[i]-this.side/2;
      this.extremeSup[i]=this.pos[i]+this.side/2;
      this.vel[i]=this.cr*(this.pos[i]-this.previouspos[i]);
    }
  }
}

public void calculateForce(double m,double[] cm){
  this.f[0]=0;
  this.f[1]=0;
  this.f[2]=0;
  double x = cm[0] - this.pos[0];
  double y = cm[1] - this.pos[1];
  double z = cm[2] - this.pos[2];
  double distance = Math.sqrt(x*x+y*y+z*z);
  if(distance!=0){
    double force = this.gcst*this.m*m/(distance*distance*distance);
    this.f[0] += force*x;
    this.f[1] += force*y;
    this.f[2] += force*z;


  }
}
}
