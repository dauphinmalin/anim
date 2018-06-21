import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.util.*;
import java.lang.Math;
import org.apache.commons.math3.linear.*;
import org.apache.commons.math3.geometry.euclidean.threed.*;


class Cluster{
  public PrimitiveObject[] summits;
  float[] color;
  RealMatrix Aqq;
  RealMatrix Q;
  RealMatrix qu;
  double m;
  double[] pos;
  double[] pos0;
  private  int n;
  private static double beta=0.5;
  private double side;
  protected static double alpha=1;
  double dt;


Cluster(int n,double m,double dt,float color[]){
  this.color=color;
  this.summits=new PrimitiveObject[n*n*n];
  this.n=n;
  this.m=m;
  this.dt=dt;
  this.pos=new double[3];


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
}



public void calculatePos(){
  this.pos[0]=0;
  this.pos[1]=0;
  this.pos[2]=0;
  double m=0;
  for(int i=0;i<this.n*this.n*this.n;i++){
    this.summits[i].calculatePos();
    this.pos[0]=(this.summits[i].getX()*this.summits[i].m+this.pos[0]*m)/(m+this.summits[i].m);
    this.pos[1]=(this.summits[i].getY()*this.summits[i].m+this.pos[1]*m)/(m+this.summits[i].m);
    this.pos[2]=(this.summits[i].getZ()*this.summits[i].m+this.pos[2]*m)/(m+this.summits[i].m);
    m=m+this.summits[i].m;

  }

  shapeMatching();

}


public void Precompute(){
  double[][] q=new double[this.n*this.n*this.n][9];
  this.qu =new BlockRealMatrix(this.n*this.n*this.n,3);
  m=0;
  this.pos[0]=0;
  this.pos[1]=0;
  this.pos[2]=0;
  for(int i=0;i<this.summits.length;i++){
    this.summits[i].calculatePos();
    this.pos[0]=(this.summits[i].getX()*this.summits[i].m+this.pos[0]*m)/(m+this.summits[i].m);
    this.pos[1]=(this.summits[i].getY()*this.summits[i].m+this.pos[1]*m)/(m+this.summits[i].m);
    this.pos[2]=(this.summits[i].getZ()*this.summits[i].m+this.pos[2]*m)/(m+this.summits[i].m);
    m=m+this.summits[i].m;


  }
  this.pos0=pos.clone();
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


public void Draw(GLAutoDrawable drawable,GLU glu,GL2 gl){
  int k=0;
  gl.glPushMatrix();

  while(k<n*(n-1)-1){
    if(k%n!=n-1){
      gl.glPushMatrix();
      gl.glColor3f(this.color[0],this.color[1],this.color[2]);

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
}
