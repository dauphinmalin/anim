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
  static private double coefK = 100;
  static private double coefB = 10;

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
  //!!!!!!!!the vel in the center is not equal to the vel on ectremity
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

  for(int k=0;k<7;k++){
    this.extremeInf[0]=Math.min(this.summits[k].getX(),this.summits[k+1].getX());
    this.extremeSup[0]=Math.max(this.summits[k].getX(),this.summits[k+1].getX());
    this.extremeInf[1]=Math.min(this.summits[k].getY(),this.summits[k+1].getY());
    this.extremeSup[1]=Math.max(this.summits[k].getY(),this.summits[k+1].getY());
    this.extremeInf[2]=Math.min(this.summits[k].getZ(),this.summits[k+1].getZ());
    this.extremeSup[2]=Math.max(this.summits[k].getZ(),this.summits[k+1].getZ());
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

public double[] rot(double[] vec, double[] center){
  double[] result = new double[3];
  double[][] rotX ={{1,0,0},{0,Math.cos(this.rotation[0]),Math.sin(this.rotation[0])},{0,-Math.sin(this.rotation[0]),Math.cos(this.rotation[0])}};
  double[][] rotY ={{Math.cos(this.rotation[1]),0,Math.sin(this.rotation[1])},{0,1,0},{-Math.sin(this.rotation[1]),0,Math.cos(this.rotation[1])}};
  double[][] rotZ ={{Math.cos(this.rotation[2]),Math.sin(this.rotation[2]),0},{-Math.sin(this.rotation[2]),Math.cos(this.rotation[2]),0},{0,0,1}};
  double[][] rot=matrixProduct(rotX,rotY);
  rot = matrixProduct(rot,rotZ);
  for(int i=0;i<3;i++){
    result[i] = rot[i][0]*vec[0]+rot[i][1]*vec[1]+rot[i][2]*vec[2] + center[i];
  }
  return result;
}

public double[][] summitProj(double[] center,double[][] base){
  double[][] summitP = new double[8][3];
  for(int ii=0;ii<8;ii++){
    for(int jj=0;jj<3;jj++){
      summitP[ii][jj] = (this.summits[ii].getX()-center[0])*base[jj][0]+(this.summits[ii].getY()-center[1])*base[jj][1]+(this.summits[ii].getZ()-center[2])*base[jj][2];
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

  summitP = square.summitProj(this.summits[0].pos, base);
  for(int i=0;i<8;i++){
    if((summitP[i][0]<this.side)&&(summitP[i][0]>0)){
      if((summitP[i][1]<this.side)&&(summitP[i][1]>0)){
        if((summitP[i][2]<this.side)&&(summitP[i][2]>0)){
          double[] x = {this.side-summitP[i][0],0,0};
          double[] y = {0,this.side-summitP[i][1],0};
          double[] z = {0,0,this.side-summitP[i][2]};
          int a = 0;
          int b = 0;
          int c = 0;
          for(int j=0;j<8;j++){
            if(x[0]*(summitP[j][0]-summitP[i][0])>=0){
              a += 1;
            }
            if(y[1]*(summitP[j][1]-summitP[i][1])>=0){
              b += 1;
            }
            if(z[2]*(summitP[j][2]-summitP[i][2])>=0){
              c += 1;
            }
          }
          if(a==8){
            double[] pIn = {square.summits[i].getX(),square.summits[i].getY(),square.summits[i].getZ()};
            double[] n = rot(x,pIn);
            //substraction for vector a faire
            double[] vRel = {square.getVel()[0]-this.getVel()[0],square.getVel()[1]-this.getVel()[1],square.getVel()[2]-this.getVel()[2]};
            double[] f = new double[3];
            for(int k=0;k<3;k++){
              f[k] = (-this.coefK*x[0]-this.coefB*(vRel[0]*n[0]+vRel[1]*n[1]+vRel[2]*n[2]))*n[k];
            }
            square.setF(f[0],f[1],f[2]);
            this.setF(-f[0],-f[1],-f[2]);
            System.out.println("f: "+ f[0]+" "+f[1]+" "+f[2]);
          }
          if(b==8){
            double[] pIn = {square.summits[i].getX(),square.summits[i].getY(),square.summits[i].getZ()};
            double[] n = rot(y,pIn);
            //substraction for vector a faire
            double[] vRel = {square.getVel()[0]-this.getVel()[0],square.getVel()[1]-this.getVel()[1],square.getVel()[2]-this.getVel()[2]};
            double[] f = new double[3];
            for(int k=0;k<3;k++){
              f[k] = (-this.coefK*y[1]-this.coefB*(vRel[0]*n[0]+vRel[1]*n[1]+vRel[2]*n[2]))*n[k];
            }
            square.setF(f[0],f[1],f[2]);
            this.setF(-f[0],-f[1],-f[2]);
            System.out.println("f: "+ f[0]+" "+f[1]+" "+f[2]);
          }
          if(c==8){
            double[] pIn = {square.summits[i].getX(),square.summits[i].getY(),square.summits[i].getZ()};
            double[] n = rot(z,pIn);
            //substraction for vector a faire
            double[] vRel = {square.getVel()[0]-this.getVel()[0],square.getVel()[1]-this.getVel()[1],square.getVel()[2]-this.getVel()[2]};
            double[] f = new double[3];
            for(int k=0;k<3;k++){
              f[k] = (-this.coefK*z[2]-this.coefB*(vRel[0]*n[0]+vRel[1]*n[1]+vRel[2]*n[2]))*n[k];
            }
            square.setF(f[0],f[1],f[2]);
            this.setF(-f[0],-f[1],-f[2]);
            System.out.println("f: "+ f[0]+" "+f[1]+" "+f[2]);
          }
          return true;
        }
      }
    }
  }
  return false;
}

// public void collisionResponse(Square square){
//   double[][] base = new double[3][3];
//   for(int j=0;j<3;j++){
//     base[0][j] = (this.pos0[1][j]-this.pos0[0][j])/this.side;
//     base[1][j] = (this.pos0[2][j]-this.pos0[0][j])/this.side;
//     base[2][j] = (this.pos0[4][j]-this.pos0[0][j])/this.side;
//   }
//   double[][] summitP = new double[8][3];
//   // double[] centerP = {this.side/2,this.side/2,this.side/2};
//
//   summitP = square.summitProj(this.summits[0].pos, base);
//
//   //square in this
//   if(this.checkCollision(square)){
//     double[] x = {this.side-summitP[i][0],0,0};
//     double[] y = {0,this.side-summitP[i][1],0};
//     double[] z = {0,0,this.side-summitP[i][2]};
//     int a = 0;
//     int b = 0;
//     int c = 0;
//     for(int j=0;j<8;j++){
//       if(x[0]*(summitP[j][0]-summitP[i][0])>=0){
//         a += 1;
//       }
//       if(y[1]*(summitP[j][1]-summitP[i][1])>=0){
//         b += 1;
//       }
//       if(z[2]*(summitP[j][2]-summitP[i][2])>=0){
//         c += 1;
//       }
//     }
//     if(a==8){
//       double[] pIn = {square.summits[i].getX(),square.summits[i].getY(),square.summits[i].getZ()};
//       double[] n = rot(x,pIn);
//       //substraction for vector a faire
//       double[] vRel = {square.getVel()[0]-this.getVel()[0],square.getVel()[1]-this.getVel()[1],square.getVel()[2]-this.getVel()[2]};
//       double[] f = new double[3];
//       for(int k=0;k<3;k++){
//         f[k] = -x[0]-(vRel[0]*n[0]+vRel[1]*n[1]+vRel[2]*n[2])*n[k];
//       }
//       square.setF(f[0],f[1],f[2]);
//       this.setF(-f[0],-f[1],-f[2]);
//       System.out.println("f: "+ f[0]+" "+f[1]+" "+f[2]);
//     }
//     if(b==8){
//       double[] pIn = {square.summits[i].getX(),square.summits[i].getY(),square.summits[i].getZ()};
//       double[] n = rot(y,pIn);
//       //substraction for vector a faire
//       double[] vRel = {square.getVel()[0]-this.getVel()[0],square.getVel()[1]-this.getVel()[1],square.getVel()[2]-this.getVel()[2]};
//       double[] f = new double[3];
//       for(int k=0;k<3;k++){
//         f[k] = -y[1]-(vRel[0]*n[0]+vRel[1]*n[1]+vRel[2]*n[2])*n[k];
//       }
//       square.setF(f[0],f[1],f[2]);
//       this.setF(-f[0],-f[1],-f[2]);
//       System.out.println("f: "+ f[0]+" "+f[1]+" "+f[2]);
//     }
//     if(c==8){
//       double[] pIn = {square.summits[i].getX(),square.summits[i].getY(),square.summits[i].getZ()};
//       double[] n = rot(z,pIn);
//       //substraction for vector a faire
//       double[] vRel = {square.getVel()[0]-this.getVel()[0],square.getVel()[1]-this.getVel()[1],square.getVel()[2]-this.getVel()[2]};
//       double[] f = new double[3];
//       for(int k=0;k<3;k++){
//         f[k] = -z[2]-(vRel[0]*n[0]+vRel[1]*n[1]+vRel[2]*n[2])*n[k];
//       }
//       square.setF(f[0],f[1],f[2]);
//       this.setF(-f[0],-f[1],-f[2]);
//       System.out.println("f: "+ f[0]+" "+f[1]+" "+f[2]);
//     }
//   }
//   //this in square
//   else if(square.checkCollision(this)){
//     double[] x = {this.side-summitP[i][0],0,0};
//     double[] y = {0,this.side-summitP[i][1],0};
//     double[] z = {0,0,this.side-summitP[i][2]};
//     int a = 0;
//     int b = 0;
//     int c = 0;
//     for(int j=0;j<8;j++){
//       if(x[0]*(summitP[j][0]-summitP[i][0])>=0){
//         a += 1;
//       }
//       if(y[1]*(summitP[j][1]-summitP[i][1])>=0){
//         b += 1;
//       }
//       if(z[2]*(summitP[j][2]-summitP[i][2])>=0){
//         c += 1;
//       }
//     }
//     if(a==8){
//       double[] pIn = {square.summits[i].getX(),square.summits[i].getY(),square.summits[i].getZ()};
//       double[] n = rot(x,pIn);
//       //substraction for vector a faire
//       double[] vRel = {square.getVel()[0]-this.getVel()[0],square.getVel()[1]-this.getVel()[1],square.getVel()[2]-this.getVel()[2]};
//       double[] f = new double[3];
//       for(int k=0;k<3;k++){
//         f[k] = -x[0]-(vRel[0]*n[0]+vRel[1]*n[1]+vRel[2]*n[2])*n[k];
//       }
//       square.setF(f[0],f[1],f[2]);
//       this.setF(-f[0],-f[1],-f[2]);
//       System.out.println("f: "+ f[0]+" "+f[1]+" "+f[2]);
//     }
//     if(b==8){
//       double[] pIn = {square.summits[i].getX(),square.summits[i].getY(),square.summits[i].getZ()};
//       double[] n = rot(y,pIn);
//       //substraction for vector a faire
//       double[] vRel = {square.getVel()[0]-this.getVel()[0],square.getVel()[1]-this.getVel()[1],square.getVel()[2]-this.getVel()[2]};
//       double[] f = new double[3];
//       for(int k=0;k<3;k++){
//         f[k] = -y[1]-(vRel[0]*n[0]+vRel[1]*n[1]+vRel[2]*n[2])*n[k];
//       }
//       square.setF(f[0],f[1],f[2]);
//       this.setF(-f[0],-f[1],-f[2]);
//       System.out.println("f: "+ f[0]+" "+f[1]+" "+f[2]);
//     }
//     if(c==8){
//       double[] pIn = {square.summits[i].getX(),square.summits[i].getY(),square.summits[i].getZ()};
//       double[] n = rot(z,pIn);
//       //substraction for vector a faire
//       double[] vRel = {square.getVel()[0]-this.getVel()[0],square.getVel()[1]-this.getVel()[1],square.getVel()[2]-this.getVel()[2]};
//       double[] f = new double[3];
//       for(int k=0;k<3;k++){
//         f[k] = -z[2]-(vRel[0]*n[0]+vRel[1]*n[1]+vRel[2]*n[2])*n[k];
//       }
//       square.setF(f[0],f[1],f[2]);
//       this.setF(-f[0],-f[1],-f[2]);
//       System.out.println("f: "+ f[0]+" "+f[1]+" "+f[2]);
//     }
//   }
//
// }

}
