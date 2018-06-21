import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.util.*;
import java.lang.Math;
import org.apache.commons.math3.linear.*;
import org.apache.commons.math3.geometry.euclidean.threed.*;

public class Rope extends PrimitiveObject{
  private double side;
  private Spheric[] summits;
  static private double coefK = 100;
  static private double coefB =1;
  static private double coefI;
  private double[] w;
  RealMatrix Aqq;
  RealMatrix Q;
  RealMatrix qu;
  private  int n;
  private static double beta=0.99;
  float[][] color;



  public ElasticSquare(double m,double[] pos,double[] vel,double[] rotation,double[] f,double dt,double side,double[] w){
    super(m,pos,vel,rotation,f,dt);

    this.side=side;
    this.extremeInf=new double[3];
    this.extremeSup=new double[3];
    for(int j=0;j<3;j++){
      this.extremeInf[j]=pos[j]-this.side/2;
      this.extremeSup[j]=pos[j]+this.side/2;

    }
    this.n=6;
    this.color=new float[(n-1)*(n-1)][3];
    SetColor();
    this.pos0=pos.clone();
    this.w = w.clone();
    this.summits=new Spheric[2*n*2];
    double[][] tab=calculateSummit(side,n);
    for(int i=0;i<this.n*this.n*this.n;i++){
      summits[i]=new Spheric(m/this.n*this.n*this.n,tab[i],vel,rotation,f,dt,0);

    }
    Precompute();
  }

  private void SetColor(){
    Random rand = new Random();
    for(int i=0;i<this.color.length;i++){
      this.color[i][0]=rand.nextFloat();
      this.color[i][1]=rand.nextFloat();
      this.color[i][2]=rand.nextFloat();
    }
  }

  private void Precompute(){
    double[][] q=new double[this.n*this.n*this.n][9];
    this.qu =new BlockRealMatrix(this.n*this.n*this.n,3);
    for(int i=0;i<this.n*this.n*this.n;i++){
      q[i][0]=this.summits[i].pos[0]-this.pos[0];
      q[i][1]=this.summits[i].pos[1]-this.pos[1];
      q[i][2]=this.summits[i].pos[2]-this.pos[2];
      q[i][3]=q[i][0]*q[i][0];
      q[i][4]=q[i][1]*q[i][1];
      q[i][5]=q[i][2]*q[i][2];
      q[i][6]=q[i][0]*q[i][1];
      q[i][7]=q[i][1]*q[i][2];
      q[i][8]=q[i][2]*q[i][0];
      for(int j=0;j<3;j++){
        qu.setEntry(i,j,(this.summits[i].pos[j]-this.pos[j]));
      }
    }
    this.Q=new BlockRealMatrix(q);
    this.Aqq=MatrixUtils.inverse((Q.transpose()).multiply(Q).scalarMultiply(m/this.n*this.n*this.n));
  }

  public void calculateForce(double m,double[] cm){
  }

  @Override
  public void Draw(GLAutoDrawable drawable,GLU glu,GL2 gl){

    //Draw Every smaller surface that compose every surface

    int k=0;
    gl.glPushMatrix();

    while(k<n*(n-1)-1){
      if(k%n!=n-1){
        gl.glPushMatrix();
        gl.glColor3f(this.color[k-k/n][0],this.color[k-k/n][1],this.color[k-k/n][2]);

        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f((float)this.summits[k].getX(),(float)this.summits[k].getY(),(float)this.summits[k].getZ());
        gl.glVertex3f((float)this.summits[k+1].getX(),(float)this.summits[k+1].getY(),(float)this.summits[k+1].getZ());
        gl.glVertex3f((float)this.summits[k+n].getX(),(float)this.summits[k+n].getY(),(float)this.summits[k+n].getZ());
        gl.glEnd();
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f((float)this.summits[k+n+1].getX(),(float)this.summits[k+n+1].getY(),(float)this.summits[k+n+1].getZ());
        gl.glVertex3f((float)this.summits[k+1].getX(),(float)this.summits[k+1].getY(),(float)this.summits[k+1].getZ());
        gl.glVertex3f((float)this.summits[k+n].getX(),(float)this.summits[k+n].getY(),(float)this.summits[k+n].getZ());
        gl.glEnd();
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f((float)this.summits[k*n].getX(),(float)this.summits[k*n].getY(),(float)this.summits[k*n].getZ());
        gl.glVertex3f((float)this.summits[(k+1)*n].getX(),(float)this.summits[(k+1)*n].getY(),(float)this.summits[(k+1)*n].getZ());
        gl.glVertex3f((float)this.summits[(k+n)*n].getX(),(float)this.summits[(k+n)*n].getY(),(float)this.summits[(k+n)*n].getZ());
        gl.glEnd();
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f((float)this.summits[(k+1)*n].getX(),(float)this.summits[(k+1)*n].getY(),(float)this.summits[(k+1)*n].getZ());
        gl.glVertex3f((float)this.summits[(k+n)*n].getX(),(float)this.summits[(k+n)*n].getY(),(float)this.summits[(k+n)*n].getZ());
        gl.glVertex3f((float)this.summits[(k+n+1)*n].getX(),(float)this.summits[(k+n+1)*n].getY(),(float)this.summits[(k+n+1)*n].getZ());
        gl.glEnd();

        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f((float)this.summits[k*n+(n-1)].getX(),(float)this.summits[k*n+(n-1)].getY(),(float)this.summits[k*n+(n-1)].getZ());
        gl.glVertex3f((float)this.summits[(k+1)*n+(n-1)].getX(),(float)this.summits[(k+1)*n+(n-1)].getY(),(float)this.summits[(k+1)*n+(n-1)].getZ());
        gl.glVertex3f((float)this.summits[(k+n)*n+(n-1)].getX(),(float)this.summits[(k+n)*n+(n-1)].getY(),(float)this.summits[(k+n)*n+(n-1)].getZ());
        gl.glEnd();
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f((float)this.summits[(k+1)*n+(n-1)].getX(),(float)this.summits[(k+1)*n+(n-1)].getY(),(float)this.summits[(k+1)*n+(n-1)].getZ());
        gl.glVertex3f((float)this.summits[(k+n)*n+(n-1)].getX(),(float)this.summits[(k+n)*n+(n-1)].getY(),(float)this.summits[(k+n)*n+(n-1)].getZ());
        gl.glVertex3f((float)this.summits[(k+n+1)*n+(n-1)].getX(),(float)this.summits[(k+n+1)*n+(n-1)].getY(),(float)this.summits[(k+n+1)*n+(n-1)].getZ());
        gl.glEnd();

        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f((float)this.summits[n*n*(k/n)+k%n].getX(),(float)this.summits[n*n*(k/n)+k%n].getY(),(float)this.summits[n*n*(k/n)+k%n].getZ());
        gl.glVertex3f((float)this.summits[n*n*(k/n)+k%n+1].getX(),(float)this.summits[n*n*(k/n)+k%n+1].getY(),(float)this.summits[n*n*(k/n)+k%n+1].getZ());
        gl.glVertex3f((float)this.summits[n*n*(1+k/n)+k%n].getX(),(float)this.summits[n*n*(1+k/n)+k%n].getY(),(float)this.summits[n*n*(1+k/n)+k%n].getZ());
        gl.glEnd();
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f((float)this.summits[n*n*(1+k/n)+k%n+1].getX(),(float)this.summits[n*n*(1+k/n)+k%n+1].getY(),(float)this.summits[n*n*(1+k/n)+k%n+1].getZ());
        gl.glVertex3f((float)this.summits[n*n*(k/n)+k%n+1].getX(),(float)this.summits[n*n*(k/n)+k%n+1].getY(),(float)this.summits[n*n*(k/n)+k%n+1].getZ());
        gl.glVertex3f((float)this.summits[n*n*(1+k/n)+k%n].getX(),(float)this.summits[n*n*(1+k/n)+k%n].getY(),(float)this.summits[n*n*(1+k/n)+k%n].getZ());
        gl.glEnd();



        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f((float)this.summits[(n-1)*n*n+k].getX(),(float)this.summits[(n-1)*n*n+k].getY(),(float)this.summits[(n-1)*n*n+k].getZ());
        gl.glVertex3f((float)this.summits[(n-1)*n*n+k+1].getX(),(float)this.summits[(n-1)*n*n+k+1].getY(),(float)this.summits[(n-1)*n*n+k+1].getZ());
        gl.glVertex3f((float)this.summits[(n-1)*n*n+k+n].getX(),(float)this.summits[(n-1)*n*n+k+n].getY(),(float)this.summits[(n-1)*n*n+k+n].getZ());
        gl.glEnd();
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f((float)this.summits[(n-1)*n*n+k+n+1].getX(),(float)this.summits[(n-1)*n*n+k+n+1].getY(),(float)this.summits[(n-1)*n*n+k+n+1].getZ());
        gl.glVertex3f((float)this.summits[(n-1)*n*n+k+1].getX(),(float)this.summits[(n-1)*n*n+k+1].getY(),(float)this.summits[(n-1)*n*n+k+1].getZ());
        gl.glVertex3f((float)this.summits[(n-1)*n*n+k+n].getX(),(float)this.summits[(n-1)*n*n+k+n].getY(),(float)this.summits[(n-1)*n*n+k+n].getZ());
        gl.glEnd();
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f((float)this.summits[(n-1+k/n*n)*n+k%n].getX(),(float)this.summits[(n-1+k/n*n)*n+k%n].getY(),(float)this.summits[(n-1+k/n*n)*n+k%n].getZ());
        gl.glVertex3f((float)this.summits[(n-1+k/n*n)*n+k%n+1].getX(),(float)this.summits[(n-1+k/n*n)*n+k%n+1].getY(),(float)this.summits[(n-1+k/n*n)*n+k%n+1].getZ());
        gl.glVertex3f((float)this.summits[(n-1+(1+k/n)*n)*n+k%n].getX(),(float)this.summits[(n-1+(1+k/n)*n)*n+k%n].getY(),(float)this.summits[(n-1+(1+k/n)*n)*n+k%n].getZ());
        gl.glEnd();
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f((float)this.summits[(n-1+k/n*n)*n+k%n+1].getX(),(float)this.summits[(n-1+k/n*n)*n+k%n+1].getY(),(float)this.summits[(n-1+k/n*n)*n+k%n+1].getZ());
        gl.glVertex3f((float)this.summits[(n-1+(1+k/n)*n)*n+k%n].getX(),(float)this.summits[(n-1+(1+k/n)*n)*n+k%n].getY(),(float)this.summits[(n-1+(1+k/n)*n)*n+k%n].getZ());
        gl.glVertex3f((float)this.summits[(n-1+(1+k/n)*n)*n+k%n+1].getX(),(float)this.summits[(n-1+(1+k/n)*n)*n+k%n+1].getY(),(float)this.summits[(n-1+(1+k/n)*n)*n+k%n+1].getZ());
        gl.glEnd();
        gl.glPopMatrix();

      }
      k++;


    }


    gl.glPopMatrix();

  }

  public void calculateBoundingVolume(){
  }
  private void shapeMatching(){
    RealMatrix p=new BlockRealMatrix(this.n*this.n*this.n,3);

    for(int i=0;i<this.n*this.n*this.n;i++){
      for(int j=0;j<3;j++){
        p.setEntry(i,j,this.m/this.n*this.n*this.n*(this.summits[i].pos[j]-this.pos[j]));

      }

    }
    RealMatrix Apq=(p.transpose()).multiply(this.Q);

    RealMatrix A=Apq.multiply(this.Aqq);

    RealMatrix Apqu=(p.transpose()).multiply(this.qu);
    RealMatrix G=Apqu.preMultiply(Apqu.transpose());

    EigenDecomposition E=new EigenDecomposition(G);
    RealMatrix D=E.getD();

    for(int i=0;i<3;i++ ){
      D.setEntry(i,i,1/Math.sqrt(D.getEntry(i,i)));
    }

    RealMatrix S=E.getV().multiply(D.multiply(E.getV().transpose()));
    RealMatrix R=Apqu.multiply(S);
    RealMatrix Rbis=new BlockRealMatrix(3,9);
    double[][] zero={{0,0,0},{0,0,0},{0,0,0}};

    Rbis.setSubMatrix(R.getData(),0,0);
    Rbis.setSubMatrix(zero,0,3);
    Rbis.setSubMatrix(zero,0,6);
    Rbis=(A.scalarMultiply(this.beta)).add(Rbis.scalarMultiply(1-this.beta));
    RealMatrix cm=new BlockRealMatrix(1,3);
    cm.setRow(0,this.pos);
    for(int i=0;i<this.n*this.n*this.n;i++){



      RealMatrix g=Rbis.multiply(Q.getSubMatrix(i,i,0,8).transpose());
      g=g.add(cm.transpose());
      for(int j=0;j<3;j++){
        this.summits[i].vel[j]+=this.alpha*(g.getEntry(j,0)-this.summits[i].pos[j])/this.dt;
        this.summits[i].pos[j]=g.getEntry(j,0);
        //this.summits[i].pos[j]+=this.summits[i].vel[j]*this.dt;

      }
    }
  }/*
  private void shapeMatching(){
  RealMatrix p=new BlockRealMatrix(this.n*this.n*this.n,3);
  RealMatrix q =new BlockRealMatrix(this.n*this.n*this.n,3);
  for(int i=0;i<this.n*this.n*this.n;i++){
  for(int j=0;j<3;j++){
  p.setEntry(i,j,this.m/this.n*this.n*this.n*(this.summits[i].pos[j]-this.pos[j]));
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

for(int i=0;i<this.n*this.n*this.n;i++){
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


}*/
public void calculatePos(){
  this.pos[0]=0;
  this.pos[1]=0;
  this.pos[2]=0;
  for(int i=0;i<this.n*this.n*this.n;i++){
    this.summits[i].calculatePos();
    this.pos[0]=(this.summits[i].getX()+this.pos[0]*i)/(i+1);
    this.pos[1]=(this.summits[i].getY()+this.pos[1]*i)/(i+1);
    this.pos[2]=(this.summits[i].getZ()+this.pos[2]*i)/(i+1);

  }

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


public double[][] calculateSummit(double side,int n){
  double[][] summit=new double[2*n*2][3];
  for(int i=0;i<2*n*2;i++){
    summit[i][0]=side*(i%2)-side/2;
    summit[i][1]=side/(n-1)*(i%n)-side/2;
    summit[i][2]=side/(n-1)*(i/n%n)-side/2;
  }
  /*double[][] summit={{-side/2,-side/2,-side/2},{-side/2,-side/2,side/2},
  {-side/2,side/2,-side/2},{-side/2,side/2,side/2},
  {side/2,-side/2,-side/2},{side/2,-side/2,side/2},
  {side/2,side/2,-side/2},{side/2,side/2,side/2}};*/
  double[][] summitbis=new double[n*n*n][3];
  double[][] rotX ={{1,0,0},{0,Math.cos(this.rotation[0]),Math.sin(this.rotation[0])},{0,-Math.sin(this.rotation[0]),Math.cos(this.rotation[0])}};
  double[][] rotY ={{Math.cos(this.rotation[1]),0,Math.sin(this.rotation[1])},{0,1,0},{-Math.sin(this.rotation[1]),0,Math.cos(this.rotation[1])}};
  double[][] rotZ ={{Math.cos(this.rotation[2]),Math.sin(this.rotation[2]),0},{-Math.sin(this.rotation[2]),Math.cos(this.rotation[2]),0},{0,0,1}};
  double[][] rot=matrixProduct(rotX,rotY);
  rotX=matrixProduct(rot,rotZ);
  for(int i=0;i<n*n*n;i++){
    for(int j=0;j<3;j++){
      summitbis[i][j]=rotX[j][0]*summit[i][0]+rotX[j][1]*summit[i][1]+rotX[j][2]*summit[i][2]+this.pos[j];



    }
  }
  return summitbis;
}


}
