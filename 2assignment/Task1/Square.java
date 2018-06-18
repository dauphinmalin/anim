import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.util.*;
import java.lang.Math;
import org.apache.commons.math3.linear.*;

public class Square extends PrimitiveObject{
  private double side;
  private Spheric[] summits;


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
double[][] possummits=calculateSummit();
this.pos0=pos.clone();
this.summits=new Spheric[8];
for(int i=0;i<8;i++){
  summits[i]=new Spheric(m/8,possummits[i],vel,rotation,f,dt,0);
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

  for(int i=0;i<8;i++){
  this.summits[i].calculatePos();
  for(int k=0;k<3;k++){
  if(this.summits[i].pos[k]<this.extremeInf[k]){this.extremeInf[k]=this.summits[i].pos[k];}
  if(this.summits[i].pos[k]>this.extremeSup[k]){this.extremeSup[k]=this.summits[i].pos[k];}
}
    }
  borderResponse();

  shapeMatching();
}


public void borderResponse(){
  this.pos[0]=0;
  this.pos[1]=0;
  this.pos[2]=0;
  for(int i =0;i<this.summits.length;i++){
    this.summits[i].borderResponse();
    this.pos[0]=(this.pos[0]*i*m+this.summits[i].pos[0]*m)/((i+1)*m);
    this.pos[1]=(this.pos[1]*i*m+this.summits[i].pos[1]*m)/((i+1)*m);
    this.pos[2]=(this.pos[2]*i*m+this.summits[i].pos[2]*m)/((i+1)*m);
  }


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
  RealMatrix mrotX=new BlockRealMatrix(rotX);
  RealMatrix mrotY=new BlockRealMatrix(rotY);
  RealMatrix mrotZ=new BlockRealMatrix(rotZ);
  RealMatrix mrot=mrotX.multiply(mrotY);
  mrot=mrot.multiply(mrotZ);
  for(int i=0;i<8;i++){
    for(int j=0;j<3;j++){

    summitbis[i][j]=mrot.getEntry(j,0)*summit[i][0]+mrot.getEntry(j,1)*summit[i][1]+mrot.getEntry(j,2)*summit[i][2]+this.pos[j];

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
  RealMatrix mrotX=new BlockRealMatrix(rotX);
  RealMatrix mrotY=new BlockRealMatrix(rotY);
  RealMatrix mrotZ=new BlockRealMatrix(rotZ);
  RealMatrix mrot=mrotX.multiply(mrotY);
  mrot=mrot.multiply(mrotZ);
  for(int i=0;i<3;i++){
    center[i] = mrot.getEntry(i,0)*this.pos[0]+mrot.getEntry(i,1)*this.pos[1]+mrot.getEntry(i,2)*this.pos[2];
  }
  return center;
}

public double[][] summitProj(double[] center,double[][] base){
  double[][] summitP = new double[8][3];
  for(int i=0;i<8;i++){
    for(int j=0;j<3;j++){
      summitP[i][j] = (this.summits[i].pos0[0]-center[0])*base[j][0]+(this.summits[i].pos0[1]-center[1])*base[j][1]+(this.summits[i].pos0[2]-center[2])*base[j][2];
    }
  }
  return summitP;
}

public boolean checkCollision(Square square){
  double[][] base = new double[3][3];
  for(int j=0;j<3;j++){
    base[0][j] = (this.summits[1].pos0[j]-this.summits[0].pos0[j])/this.side;
    base[1][j] = (this.summits[2].pos0[j]-this.summits[0].pos0[j])/this.side;
    base[2][j] = (this.summits[4].pos0[j]-this.summits[0].pos0[j])/this.side;
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
