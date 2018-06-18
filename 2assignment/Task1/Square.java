import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.util.*;
import java.lang.Math;

public class Square extends PrimitiveObject{
  private double side;
  private Spheric[] summits;
  private double[][] pos0;

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
this.pos0=calculateSummit();
this.summits=new Spheric[8];
for(int i=0;i<8;i++){
  summits[i]=new Spheric(m/8,pos0[i],vel,rotation,f,dt,0);
}
}
@Override
  public void Draw(GLAutoDrawable drawable,GLU glu,GL2 gl){
    gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)summits[0].pos[0],(float)summits[0].pos[1],(float)summits[0].pos[2]);
     gl.glVertex3f((float)summits[1].pos[0],(float)summits[1].pos[1],(float)summits[1].pos[2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)summits[0].pos[0],(float)summits[0].pos[1],(float)summits[0].pos[2]); // 3 units into the window
     gl.glVertex3f((float)summits[2].pos[0],(float)summits[2].pos[1],(float)summits[2].pos[2]);
     gl.glEnd();

     //top
     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)summits[0].pos[0],(float)summits[0].pos[1],(float)summits[0].pos[2]);
     gl.glVertex3f((float)summits[4].pos[0],(float)summits[4].pos[1],(float)summits[4].pos[2]);
     gl.glEnd();

     // bottom
     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)summits[3].pos[0],(float)summits[3].pos[1],(float)summits[3].pos[2]);
     gl.glVertex3f((float)summits[1].pos[0],(float)summits[1].pos[1],(float)summits[1].pos[2]);
     gl.glEnd();

     // edge 2....
     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)summits[3].pos[0],(float)summits[3].pos[1],(float)summits[3].pos[2]);
     gl.glVertex3f((float)summits[2].pos[0],(float)summits[2].pos[1],(float)summits[2].pos[2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)summits[3].pos[0],(float)summits[3].pos[1],(float)summits[3].pos[2]);
     gl.glVertex3f((float)summits[7].pos[0],(float)summits[7].pos[1],(float)summits[7].pos[2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)summits[6].pos[0],(float)summits[6].pos[1],(float)summits[6].pos[2]);
     gl.glVertex3f((float)summits[2].pos[0],(float)summits[2].pos[1],(float)summits[2].pos[2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)summits[6].pos[0],(float)summits[6].pos[1],(float)summits[6].pos[2]);
     gl.glVertex3f((float)summits[4].pos[0],(float)summits[4].pos[1],(float)summits[4].pos[2]);
     gl.glEnd();

     //Edge 3.............
     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)summits[6].pos[0],(float)summits[6].pos[1],(float)summits[6].pos[2]);
     gl.glVertex3f((float)summits[7].pos[0],(float)summits[7].pos[1],(float)summits[7].pos[2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)summits[5].pos[0],(float)summits[5].pos[1],(float)summits[5].pos[2]);
     gl.glVertex3f((float)summits[1].pos[0],(float)summits[1].pos[1],(float)summits[1].pos[2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)summits[5].pos[0],(float)summits[5].pos[1],(float)summits[5].pos[2]);
     gl.glVertex3f((float)summits[4].pos[0],(float)summits[4].pos[1],(float)summits[4].pos[2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)summits[5].pos[0],(float)summits[5].pos[1],(float)summits[5].pos[2]);
     gl.glVertex3f((float)summits[7].pos[0],(float)summits[7].pos[1],(float)summits[7].pos[2]);
     gl.glEnd();
}

public void calculateBoundingVolume(){
}

public void calculatePos(){
  for(int i=0;i<8;i++){
  this.summits[i].calculatePos();
    }
  for(int i=0;i<3;i++){
    this.nextpos[i] = 2*this.pos[i]-this.previouspos[i]+this.dt*this.dt*this.f[i]/this.m;//Verlet for x,y,z
  }
}

public void borderResponse(){
  for(int i =0;i<8;i++){
    this.summits[i].borderResponse();
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
  double[][] summit={{-this.side/2,-this.side/2,-this.side/2},{-this.side/2,-this.side/2,this.side/2},
  {-this.side/2,this.side/2,-this.side/2},{-this.side/2,this.side/2,this.side/2},
  {this.side/2,-this.side/2,-this.side/2},{this.side/2,-this.side/2,this.side/2},
  {this.side/2,this.side/2,-this.side/2},{this.side/2,this.side/2,this.side/2}};
  double[][] summitbis=new double[8][3];
  double[][] rotX ={{1,0,0},{0,Math.cos(this.rotation[0]),Math.sin(this.rotation[0])},{0,-Math.sin(this.rotation[0]),Math.cos(this.rotation[0])}};
  double[][] rotY ={{Math.cos(this.rotation[1]),0,Math.sin(this.rotation[1])},{0,1,0},{-Math.sin(this.rotation[1]),0,Math.cos(this.rotation[1])}};
  double[][] rotZ ={{Math.cos(this.rotation[2]),Math.sin(this.rotation[2]),0},{-Math.sin(this.rotation[2]),Math.cos(this.rotation[2]),0},{0,0,1}};
  double[][] rot=matrixProduct(rotX,rotY);
  rotX=matrixProduct(rot,rotZ);
  for(int i=0;i<8;i++){
    for(int j=0;j<3;j++){

    summitbis[i][j]=rotX[j][0]*summit[i][0]+rotX[j][1]*summit[i][1]+rotX[j][2]*summit[i][2]+this.pos[j];

  }
  }
  return summitbis;
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
  double[][] rotY ={{Math.cos(this.rotation[1]),0,-Math.sin(this.rotation[1])},{0,1,0},{Math.sin(this.rotation[1]),0,Math.cos(this.rotation[1])}};
  double[][] rotZ ={{Math.cos(this.rotation[2]),-Math.sin(this.rotation[2]),0},{Math.sin(this.rotation[2]),Math.cos(this.rotation[2]),0},{0,0,1}};
  double[][] rot=matrixProduct(rotX,rotY);
  rot = matrixProduct(rot,rotZ);
  for(int i=0;i<3;i++){
    center[i] = rot[i][0]*this.pos[0]+rot[i][1]*this.pos[1]+rot[i][2]*this.pos[2];
  }
  return center;
}

public double[][] summitProj(double[] center,double[][] base){
  double[][] summitP = new double[8][3];
  for(int ii=0;ii<8;ii++){
    for(int jj=0;jj<3;jj++){
      summitP[ii][jj] = (this.pos0[ii][0]-center[0])*base[jj][0]+(this.pos0[ii][1]-center[1])*base[jj][1]+(this.pos0[ii][2]-center[2])*base[jj][2];
    }
  }
  return summitP;
}

public boolean checkCollision(Square square){
  double[][] base = new double[3][3];
  for(int j=0;j<3;j++){
    base[0][j] = (this.pos0[1][j]-this.pos0[0][j])/this.side;
    base[1][j] = (this.pos0[2][j]-this.pos0[0][j])/this.side;
    base[2][j] = (this.pos0[4][j]-this.pos0[0][j])/this.side;
  }
  double[][] summitP = new double[8][3];
  // double[] centerP = {this.side/2,this.side/2,this.side/2};

  summitP = square.summitProj(this.pos, base);
  for(int i=0;i<8;i++){
    if((summitP[i][0]<this.side)&&(summitP[i][0]>0)){
      if((summitP[i][1]<this.side)&&(summitP[i][1]>0)){
        if((summitP[i][2]<this.side)&&(summitP[i][2]>0)){
          return true;
        }
      }
    }
  }

  return false;
}

}
