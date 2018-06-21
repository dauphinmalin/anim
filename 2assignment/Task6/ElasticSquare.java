import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.util.*;
import java.lang.Math;
import org.apache.commons.math3.linear.*;
import org.apache.commons.math3.geometry.euclidean.threed.*;

public class ElasticSquare extends PrimitiveObject{
  private double side;
  private Spheric[] summits;
  private double[] pos0;
  static private double coefK = 100;
  static private double coefB =1;
  static private double coefI;
  private double[] w;



public ElasticSquare(double m,double[] pos,double[] vel,double[] rotation,double[] f,double dt,double side,double[] w){
  super(m,pos,vel,rotation,f,dt);

  this.side=side;
  this.extremeInf=new double[3];
  this.extremeSup=new double[3];
  for(int j=0;j<3;j++){
    this.extremeInf[j]=pos[j]-this.side/2;
    this.extremeSup[j]=pos[j]+this.side/2;

}
this.pos0=pos.clone();
this.w = w.clone();
this.summits=new Spheric[8];
double[][] tab=calculateSummit();
for(int i=0;i<8;i++){
  summits[i]=new Spheric(m/8,tab[i],vel,rotation,f,dt,0);
}

this.coefI = this.m*this.side*this.side/6;
}

public void calculateForce(double m,double[] cm){
}

@Override
  public void Draw(GLAutoDrawable drawable,GLU glu,GL2 gl){
      gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summits[0].getX(),(float)this.summits[0].getY(),(float)this.summits[0].getZ());
     gl.glVertex3f((float)this.summits[1].getX(),(float)this.summits[1].getY(),(float)this.summits[1].getZ());
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summits[0].getX(),(float)this.summits[0].getY(),(float)this.summits[0].getZ()); // 3 units into the window
     gl.glVertex3f((float)this.summits[2].getX(),(float)this.summits[2].getY(),(float)this.summits[2].getZ());
     gl.glEnd();

     //top
     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summits[0].getX(),(float)this.summits[0].getY(),(float)this.summits[0].getZ());
     gl.glVertex3f((float)this.summits[4].getX(),(float)this.summits[4].getY(),(float)this.summits[4].getZ());
     gl.glEnd();

     // bottom
     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summits[3].getX(),(float)this.summits[3].getY(),(float)this.summits[3].getZ());
     gl.glVertex3f((float)this.summits[1].getX(),(float)this.summits[1].getY(),(float)this.summits[1].getZ());
     gl.glEnd();

     // edge 2....
     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summits[3].getX(),(float)this.summits[3].getY(),(float)this.summits[3].getZ());
     gl.glVertex3f((float)this.summits[2].getX(),(float)this.summits[2].getY(),(float)this.summits[2].getZ());
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summits[3].getX(),(float)this.summits[3].getY(),(float)this.summits[3].getZ());
     gl.glVertex3f((float)this.summits[7].getX(),(float)this.summits[7].getY(),(float)this.summits[7].getZ());
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summits[6].getX(),(float)this.summits[6].getY(),(float)this.summits[6].getZ());
     gl.glVertex3f((float)this.summits[2].getX(),(float)this.summits[2].getY(),(float)this.summits[2].getZ());
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summits[6].getX(),(float)this.summits[6].getY(),(float)this.summits[6].getZ());
     gl.glVertex3f((float)this.summits[4].getX(),(float)this.summits[4].getY(),(float)this.summits[4].getZ());
     gl.glEnd();

     //Edge 3.............
     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summits[6].getX(),(float)this.summits[6].getY(),(float)this.summits[6].getZ());
     gl.glVertex3f((float)this.summits[7].getX(),(float)this.summits[7].getY(),(float)this.summits[7].getZ());
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summits[5].getX(),(float)this.summits[5].getY(),(float)this.summits[5].getZ());
     gl.glVertex3f((float)this.summits[1].getX(),(float)this.summits[1].getY(),(float)this.summits[1].getZ());
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summits[5].getX(),(float)this.summits[5].getY(),(float)this.summits[5].getZ());
     gl.glVertex3f((float)this.summits[4].getX(),(float)this.summits[4].getY(),(float)this.summits[4].getZ());
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summits[5].getX(),(float)this.summits[5].getY(),(float)this.summits[5].getZ());
     gl.glVertex3f((float)this.summits[7].getX(),(float)this.summits[7].getY(),(float)this.summits[7].getZ());
     gl.glEnd();
}

public void calculateBoundingVolume(){
}
private void shapeMatching(){
  RealMatrix p=new BlockRealMatrix(summits.length,3);
  RealMatrix q =new BlockRealMatrix(summits.length,3);
  for(int i=0;i<summits.length;i++){
    for(int j=0;j<3;j++){
      p.setEntry(i,j,this.m/summits.length*(this.summits[i].pos[j]-this.pos[j]));
      q.setEntry(i,j,this.summits[i].pos0[j]-this.pos0[j]);

    }

  }
  RealMatrix A=(p.transpose()).multiply(q);

  RealMatrix G=A.preMultiply(A.transpose());

  EigenDecomposition E=new EigenDecomposition(G);
  RealMatrix D=E.getD();

  for(int i=0;i<3;i++ ){
    D.setEntry(i,i,1/Math.sqrt(D.getEntry(i,i)));
  }

  RealMatrix S=E.getV().multiply(D.multiply(E.getV().transpose()));
  RealMatrix R=A.multiply(S);

  for(int i=0;i<summits.length;i++){
    RealMatrix x0=new BlockRealMatrix(1,3);
    x0.setRow(0,this.summits[i].pos0);
    RealMatrix cm0=new BlockRealMatrix(1,3);
    cm0.setRow(0,this.pos0);
    RealMatrix cm=new BlockRealMatrix(1,3);
    cm.setRow(0,this.pos);
    RealMatrix g=R.multiply(((x0.subtract(cm0))).transpose());
    g=g.add(cm.transpose());
    for(int j=0;j<3;j++){
    this.summits[i].vel[j]+=this.alpha*(g.getEntry(j,0)-this.summits[i].pos[j])/this.dt;
    this.summits[i].pos[j]=g.getEntry(j,0);

  }
  }


}
public void calculatePos(){
    this.pos[0]=0;
    this.pos[1]=0;
    this.pos[2]=0;
    for(int i=0;i<8;i++){
    this.summits[i].calculatePos();
    this.pos[0]=(this.summits[i].getX()+this.pos[0]*i)/(i+1);
    this.pos[1]=(this.summits[i].getY()+this.pos[1]*i)/(i+1);
    this.pos[2]=(this.summits[i].getZ()+this.pos[2]*i)/(i+1);

  }
  //borderResponse();
  shapeMatching();

}


public void borderResponse(){


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


}
