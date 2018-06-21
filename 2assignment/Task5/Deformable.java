import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.util.*;
import java.lang.Math;
import org.apache.commons.math3.linear.*;
import org.apache.commons.math3.geometry.euclidean.threed.*;




public class Deformable extends PrimitiveObject{
  private double side;
  private Cluster[] clusters;
  static private double coefK = 100;
  static private double coefB =1;
  static private double coefI;
  private double[] w;
  RealMatrix Aqq;
  RealMatrix Q;
  RealMatrix qu;
  int c;
  private  int n;
  private static double beta=0.99;
  float[] color;
  PrimitiveObject[] summits;



  public Deformable(double m,double[] pos,double[] vel,double[] rotation,double[] f,double dt,double side,double[] w){
    super(m,pos,vel,rotation,f,dt);

    this.side=side;
    this.extremeInf=new double[3];
    this.extremeSup=new double[3];
    for(int j=0;j<3;j++){
      this.extremeInf[j]=pos[j]-this.side/2;
      this.extremeSup[j]=pos[j]+this.side/2;

    }
    this.c=50;
    this.color=new float[3];
    Random rand=new Random();
    color[0]= rand.nextFloat();
    color[1]= rand.nextFloat();
    color[2]= rand.nextFloat();

    this.n=3;
    this.pos0=pos.clone();
    this.w = w.clone();
    this.clusters=new Cluster[c];

    double[][] tab=calculateSummit(side,n,c);
    this.summits=new PrimitiveObject[tab.length];
    for(int i=0;i<tab.length;i++){
      this.summits[i]=new Spheric(m/(this.n*this.n*this.n*this.c),tab[i],vel,rotation,f,dt,0);

    }
    for(int i=0;i<c;i++){
      this.clusters[i]=new Cluster(n,m/c,dt,this.color);
      for(int j=0;j<n*n*n;j++){
        int k=i*n*n*(n-1)+j;
      clusters[i].summits[j]=this.summits[k];
    }
    this.clusters[i].Precompute();
    }
    this.clusters[0].summits[n+1]=new Square(m/4,tab[n+1],vel,rotation,f,dt,50,w);
    this.clusters[0].Precompute();
    this.clusters[c-1].summits[n+1]=new Square(m/4,tab[(c-1)*n*n*(n-1)+n+1],vel,rotation,f,dt,50,w);
    this.clusters[c-1].Precompute();



  }





  public void calculateForce(double m,double[] cm){
  }

  @Override
  public void Draw(GLAutoDrawable drawable,GLU glu,GL2 gl){
    for(int i=0;i<this.c;i++){
      this.clusters[i].Draw(drawable,glu,gl);
    }
    this.clusters[0].summits[n+1].Draw(drawable,glu,gl);
    this.clusters[c-1].summits[n+1].Draw(drawable,glu,gl);

    //Draw Every smaller surface that compose every surface


  }

  public void calculateBoundingVolume(){
  }

public void calculatePos(){
  for(int i=0;i<this.clusters.length;i++){
    clusters[i].calculatePos();
  }
  if(((Square)this.clusters[0].summits[n+1]).checkCollision(((Square)this.clusters[c-1].summits[n+1]))){
   		}
   		else {((Square)this.clusters[c-1].summits[n+1]).checkCollision((Square)this.clusters[0].summits[n+1]);

}
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


public double[][] calculateSummit(double side,int n,int c){
  double[][] summit=new double[n*n*n+n*n*(n-1)*(c-1)][3];
  for(int i=0;i<n*n*n+n*n*(n-1)*(c-1);i++){
    summit[i][0]=side/(n-1)*(i/n/n)-side/2;
    summit[i][1]=side/(n-1)*(i%n)-side/2;
    summit[i][2]=side/(n-1)*(i/n%n)-side/2;
  }

  double[][] summitbis=new double[n*n*n+n*n*(n-1)*(c-1)][3];
  double[][] rotX ={{1,0,0},{0,Math.cos(this.rotation[0]),Math.sin(this.rotation[0])},{0,-Math.sin(this.rotation[0]),Math.cos(this.rotation[0])}};
  double[][] rotY ={{Math.cos(this.rotation[1]),0,Math.sin(this.rotation[1])},{0,1,0},{-Math.sin(this.rotation[1]),0,Math.cos(this.rotation[1])}};
  double[][] rotZ ={{Math.cos(this.rotation[2]),Math.sin(this.rotation[2]),0},{-Math.sin(this.rotation[2]),Math.cos(this.rotation[2]),0},{0,0,1}};
  double[][] rot=matrixProduct(rotX,rotY);
  rotX=matrixProduct(rot,rotZ);
  for(int i=0;i<n*n*n+n*n*(n-1)*(c-1);i++){
    for(int j=0;j<3;j++){
      summitbis[i][j]=rotX[j][0]*summit[i][0]+rotX[j][1]*summit[i][1]+rotX[j][2]*summit[i][2]+this.pos[j];



    }
  }
  return summitbis;
}


}
