import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.util.*;
import java.lang.Math;

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

private double[][] matrixProduct(double[][] A,double [][] B){
  double[][] C=new double[3][3];
  for (int i = 0; i < A.length; i++) { // aRow
      for (int j = 0; j < B.length; j++) { // bColumn
          for (int k = 0; k < A.length; k++) { // aColumn
              C[i][j] += A[i][k] * B[k][j];
          }
      }
  }

  return C;
}


public double[][] calculateSummit(){
  double[][] summit={{this.pos[0]-this.side/2,this.pos[1]-this.side/2,this.pos[2]-this.side/2},{this.pos[0]-this.side/2,this.pos[1]-this.side/2,this.pos[2]+this.side/2},
  {this.pos[0]-this.side/2,this.pos[1]+this.side/2,this.pos[2]-this.side/2},{this.pos[0]-this.side/2,this.pos[1]+this.side/2,this.pos[2]+this.side/2},
  {this.pos[0]+this.side/2,this.pos[1]-this.side/2,this.pos[2]-this.side/2},{this.pos[0]+this.side/2,this.pos[1]-this.side/2,this.pos[2]+this.side/2},
  {this.pos[0]+this.side/2,this.pos[1]+this.side/2,this.pos[2]-this.side/2},{this.pos[0]+this.side/2,this.pos[1]+this.side/2,this.pos[2]+this.side/2}};
  double[][] summitbis=new double[8][3];
  double[][] rotX ={{1,0,0},{0,Math.cos(this.rotation[0]),Math.sin(this.rotation[0])},{0,-Math.sin(this.rotation[0]),Math.cos(this.rotation[0])}};
  double[][] rotY ={{Math.cos(this.rotation[1]),0,Math.sin(this.rotation[1])},{0,1,0},{-Math.sin(this.rotation[1]),0,Math.cos(this.rotation[1])}};
  double[][] rotZ ={{Math.cos(this.rotation[2]),Math.sin(this.rotation[2]),0},{-Math.sin(this.rotation[2]),Math.cos(this.rotation[2]),0},{0,0,1}};
  double[][] rot=matrixProduct(rotX,rotY);
  rotX=matrixProduct(rot,rotZ);
  for(int i=0;i<8;i++){
    for(int j=0;j<3;j++){
    summitbis[i][j]=rotX[j][1]*summit[i][0]+rotX[j][1]*summit[i][1]+rotX[j][2]*summit[i][0];
  }
  }
  return summitbis;
}
@Override
public void borderResponse(){
  double[][] summit=calculateSummit();

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

public double[] calculateRelCenter(){
  double[] center = new double[3];
  double[][] rotX ={{1,0,0},{0,Math.cos(this.rotation[0]),Math.sin(-this.rotation[0])},{0,Math.sin(this.rotation[0]),Math.cos(this.rotation[0])}};
  double[][] rotY ={{Math.cos(this.rotation[1]),0,Math.sin(-this.rotation[1])},{0,1,0},{Math.sin(this.rotation[1]),0,Math.cos(this.rotation[1])}};
  double[][] rotZ ={{Math.cos(this.rotation[2]),-Math.sin(this.rotation[2]),0},{Math.sin(this.rotation[2]),Math.cos(this.rotation[2]),0},{0,0,1}};
  double[][] rot=matrixProduct(rotX,rotY);
  rot = matrixProduct(rot,rotZ);
  for(int i=0;i<3;i++){
    center[i] = rot[i][0]*center[0]+rot[i][1]*center[1]+rot[i][2]*center[2];
  }
  return center;
}

public boolean checkCollision(Square square){
  square.setRot(-this.rotation[0], -this.rotation[1], -this.rotation[2]);
  double[][] summit = square.calculateSummit();
  double[] center = this.calculateRelCenter();
  for(int i=0;i<8;i++){
    if((summit[i][0]<(center[0]+this.side/2))&&(summit[i][0]>(center[0]-this.side/2))){
      if((summit[i][1]<(center[1]+this.side/2))&&(summit[i][1]>(center[1]-this.side/2))){
        if((summit[i][2]<(center[2]+this.side/2))&&(summit[i][2]>(center[0]-this.side/2))){
          square.setRot(this.rotation[0], this.rotation[1], this.rotation[2]);
          return true;
        }
      }
    }
  }
  return false;
}

}
