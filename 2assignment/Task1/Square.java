import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.util.*;
import java.lang.Math;
import org.apache.commons.math3.linear.*;
import org.apache.commons.math3.geometry.euclidean.threed.*;

public class Square extends PrimitiveObject{
  private double side;
  private Spheric[] summits;
  private double[][] summitspos;
  private double[] pos0;
  static private double coefK = 100;
  static private double coefB =1;
  static private double coefI;


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
this.summitspos=calculateSummit();

this.summits=new Spheric[8];
for(int i=0;i<8;i++){


  summits[i]=new Spheric(m/8,possummits[i],vel,rotation,f,dt,0);

}
this.coefI = this.m*this.side*this.side/6;
}
@Override
  public void Draw(GLAutoDrawable drawable,GLU glu,GL2 gl){
      /*GLUquadric quad = glu.gluNewQuadric();
      gl.glPushMatrix();
      gl.glColor3d(1,1,1);
      gl.glTranslated(this.pos[0],this.pos[1],this.pos[2]);
      gl.glRotated(this.rotation[0],1,0,0);
      gl.glRotated(this.rotation[1],0,1,0);
      gl.glRotated(this.rotation[2],0,0,1);
      glu.gluCylinder(quad,Math.sqrt(2)*this.side/2,Math.sqrt(2)*this.side/2,this.side,4, 4);
      glu.gluDeleteQuadric(quad);
      gl.glPopMatrix();*/


    gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summitspos[0][0],(float)this.summitspos[0][1],(float)this.summitspos[0][2]);
     gl.glVertex3f((float)this.summitspos[1][0],(float)this.summitspos[1][1],(float)this.summitspos[1][2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summitspos[0][0],(float)this.summitspos[0][1],(float)this.summitspos[0][2]); // 3 units into the window
     gl.glVertex3f((float)this.summitspos[2][0],(float)this.summitspos[2][1],(float)this.summitspos[2][2]);
     gl.glEnd();

     //top
     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summitspos[0][0],(float)this.summitspos[0][1],(float)this.summitspos[0][2]);
     gl.glVertex3f((float)this.summitspos[4][0],(float)this.summitspos[4][1],(float)this.summitspos[4][2]);
     gl.glEnd();

     // bottom
     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summitspos[3][0],(float)this.summitspos[3][1],(float)this.summitspos[3][2]);
     gl.glVertex3f((float)this.summitspos[1][0],(float)this.summitspos[1][1],(float)this.summitspos[1][2]);
     gl.glEnd();

     // edge 2....
     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summitspos[3][0],(float)this.summitspos[3][1],(float)this.summitspos[3][2]);
     gl.glVertex3f((float)this.summitspos[2][0],(float)this.summitspos[2][1],(float)this.summitspos[2][2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summitspos[3][0],(float)this.summitspos[3][1],(float)this.summitspos[3][2]);
     gl.glVertex3f((float)this.summitspos[7][0],(float)this.summitspos[7][1],(float)this.summitspos[7][2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summitspos[6][0],(float)this.summitspos[6][1],(float)this.summitspos[6][2]);
     gl.glVertex3f((float)this.summitspos[2][0],(float)this.summitspos[2][1],(float)this.summitspos[2][2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summitspos[6][0],(float)this.summitspos[6][1],(float)this.summitspos[6][2]);
     gl.glVertex3f((float)this.summitspos[4][0],(float)this.summitspos[4][1],(float)this.summitspos[4][2]);
     gl.glEnd();

     //Edge 3.............
     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summitspos[6][0],(float)this.summitspos[6][1],(float)this.summitspos[6][2]);
     gl.glVertex3f((float)this.summitspos[7][0],(float)this.summitspos[7][1],(float)this.summitspos[7][2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summitspos[5][0],(float)this.summitspos[5][1],(float)this.summitspos[5][2]);
     gl.glVertex3f((float)this.summitspos[1][0],(float)this.summitspos[1][1],(float)this.summitspos[1][2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summitspos[5][0],(float)this.summitspos[5][1],(float)this.summitspos[5][2]);
     gl.glVertex3f((float)this.summitspos[4][0],(float)this.summitspos[4][1],(float)this.summitspos[4][2]);
     gl.glEnd();

     gl.glBegin(GL2.GL_LINES);
     gl.glVertex3f((float)this.summitspos[5][0],(float)this.summitspos[5][1],(float)this.summitspos[5][2]);
     gl.glVertex3f((float)this.summitspos[7][0],(float)this.summitspos[7][1],(float)this.summitspos[7][2]);
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

  /*for(int i=0;i<8;i++){
  this.summits[i].calculatePos();

  for(int k=0;k<3;k++){
  if(this.summits[i].pos[k]<this.extremeInf[k]){this.extremeInf[k]=this.summits[i].pos[k];}
  if(this.summits[i].pos[k]>this.extremeSup[k]){this.extremeSup[k]=this.summits[i].pos[k];}
}
}
    }*/

    for(int i=0;i<3;i++){
    this.vel[i]=this.vel[i]+this.dt*this.f[i]/this.m;
    this.nextpos[i] = this.pos[i]+this.vel[i]*this.dt;
  }
  this.summitspos=calculateSummit();
  borderResponse();
  //RecalculateSummit();

  // shapeMatching();
}


public void borderResponse(){
  for(int i=0;i<3;i++){

    if(this.nextpos[i]>(this.posMAX[i]-this.side/2)){
      this.vel[i]=-(this.nextpos[i]-this.pos[i])/this.dt;
      this.pos[i]=this.posMAX[i]-this.side/2;
      this.extremeSup[i]=this.posMAX[i];
    }
    else if(this.nextpos[i]<this.side/2){
      this.vel[i]=-(this.nextpos[i]-this.pos[i])/this.dt;
      this.pos[i]=this.side/2;
      this.extremeInf[i]=0;
    }
    else{
      this.pos[i]=this.nextpos[i];
      this.extremeInf[i]=this.pos[i]-this.side;
      this.extremeSup[i]=this.pos[i]+this.side;

    }
  }
  /*for(int i =0;i<this.summits.length;i++){
    this.summits[i].borderResponse();
    this.pos[0]=(this.pos[0]*i*m+this.summits[i].pos[0]*m)/((i+1)*m);
    this.pos[1]=(this.pos[1]*i*m+this.summits[i].pos[1]*m)/((i+1)*m);
    this.pos[2]=(this.pos[2]*i*m+this.summits[i].pos[2]*m)/((i+1)*m);
  }*/


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

public void RecalculateSummit(){
  // for(int i=0;i<3;i++){
  //   this.rotation[i] += this.rotation[i];
  //   this.rotation[i] %= 360;
  // }
  double[][] rotX ={{1,0,0},{0,Math.cos(this.rotation[0]),Math.sin(this.rotation[0])},{0,-Math.sin(this.rotation[0]),Math.cos(this.rotation[0])}};
  double[][] rotY ={{Math.cos(this.rotation[1]),0,Math.sin(this.rotation[1])},{0,1,0},{-Math.sin(this.rotation[1]),0,Math.cos(this.rotation[1])}};
  double[][] rotZ ={{Math.cos(this.rotation[2]),Math.sin(this.rotation[2]),0},{-Math.sin(this.rotation[2]),Math.cos(this.rotation[2]),0},{0,0,1}};
  double[][] rot=matrixProduct(rotX,rotY);
  rotX=matrixProduct(rot,rotZ);
  for(int i=0;i<8;i++){
    for(int j=0;j<3;j++){
    this.summits[i].pos[j]=rotX[j][0]*(this.summits[i].pos[0]-this.pos[0])+rotX[j][1]*(this.summits[i].pos[1]-this.pos[1])+rotX[j][2]*(this.summits[i].pos[2]-this.pos[2])+this.pos[j];
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

public double[] rotV(double[] vec){
  Vector3D vecv = new Vector3D(vec);
  vecv = vecv.normalize();
  vec = vecv.toArray();
  double[] result = new double[3];
  double[][] rotX ={{1,0,0},{0,Math.cos(this.rotation[0]),Math.sin(this.rotation[0])},{0,-Math.sin(this.rotation[0]),Math.cos(this.rotation[0])}};
  double[][] rotY ={{Math.cos(this.rotation[1]),0,Math.sin(this.rotation[1])},{0,1,0},{-Math.sin(this.rotation[1]),0,Math.cos(this.rotation[1])}};
  double[][] rotZ ={{Math.cos(this.rotation[2]),Math.sin(this.rotation[2]),0},{-Math.sin(this.rotation[2]),Math.cos(this.rotation[2]),0},{0,0,1}};
  double[][] rot=matrixProduct(rotX,rotY);
  rot = matrixProduct(rot,rotZ);
  for(int i=0;i<3;i++){
    result[i] = rot[i][0]*vec[0]+rot[i][1]*vec[1]+rot[i][2]*vec[2];
  }
  return result;
}

public RealMatrix summitProj(double[] center,RealMatrix base){
  RealMatrix summitP = new BlockRealMatrix(8,3);
  for(int i=0;i<8;i++){
    double[][] vector={{this.summitspos[i][0]-center[0]},{this.summitspos[i][1]-center[1]},{this.summitspos[i][2]-center[2]}};
    RealMatrix vectorm=new BlockRealMatrix(vector);
    summitP.setRow(i,(base.multiply(vectorm)).getColumn(0));
    }

  return summitP;
}

public boolean checkCollision(Square square){
  boolean bool=false;
  double[][] basetab = new double[3][3];
  for(int j=0;j<3;j++){
    basetab[0][j] = (this.summitspos[4][j]-this.summitspos[0][j])/this.side;
    basetab[1][j] = (this.summitspos[2][j]-this.summitspos[0][j])/this.side;
    basetab[2][j] = (this.summitspos[1][j]-this.summitspos[0][j])/this.side;
  }
  RealMatrix base= new BlockRealMatrix(3,3);
  base.setColumn(0,basetab[0]);
  base.setColumn(1,basetab[1]);
  base.setColumn(2,basetab[2]);
  RealMatrix baseop= MatrixUtils.inverse(base);


  //double[][] summitP = new double[8][3];
  // double[] centerP = {this.side/2,this.side/2,this.side/2};

  RealMatrix summitP = square.summitProj(this.summitspos[0], baseop);
  for(int i=0;i<8;i++){
    if((summitP.getEntry(i,0)<=this.side)&&(summitP.getEntry(i,0)>=0)){
      if((summitP.getEntry(i,1)<=this.side)&&(summitP.getEntry(i,1)>=0)){
        if((summitP.getEntry(i,2)<=this.side)&&(summitP.getEntry(i,2)>=0)){
          double[] x = {this.side-summitP.getEntry(i,0),0,0};
          double[] y = {0,this.side-summitP.getEntry(i,1),0};
          double[] z = {0,0,this.side-summitP.getEntry(i,2)};
          double[][] tabCentersquare={{square.pos[0]-this.summitspos[0][0]},{square.pos[1]-this.summitspos[0][1]},{square.pos[2]-this.summitspos[0][2]}};
          RealMatrix Centersquare=new BlockRealMatrix(tabCentersquare);
          double[] Centersquarebis=(baseop.multiply(Centersquare)).getColumn(0);
          double[] distance=new double[6];
          distance[0]=Math.abs(Centersquarebis[0]);
          distance[1]=Math.abs(Centersquarebis[0]-this.side);
          distance[2]=Math.abs(Centersquarebis[1]);
          distance[3]=Math.abs(Centersquarebis[1]-this.side);
          distance[4]=Math.abs(Centersquarebis[2]);
          distance[5]=Math.abs(Centersquarebis[2]-this.side);
          double min=distance[0];
          int indicemin=0;
          for(int k=0;k<6;k++){
            if(min>distance[k]){
              System.out.println(k);
              indicemin=k;
              min=distance[k];
            }
          }
          int direction=(-2*(indicemin%2)+1);
          int axe=indicemin/2;
          double distancei=Math.signum(-(direction)*side*(indicemin%2)+summitP.getEntry(i,axe));
          double[] force={0,0,0};
          double[][] speeds={this.vel};
          RealMatrix speed=new BlockRealMatrix(speeds);
          speeds[0]=square.vel;
          RealMatrix speedsquare=new BlockRealMatrix(speeds);
          speedsquare=speedsquare.subtract(speed);
          RealMatrix speedbis=baseop.multiply(speedsquare.transpose());
          force[axe]=direction*(this.coefK*distancei+direction*this.coefB*speedbis.getEntry(axe,0));
          System.out.println(axe);
          RealMatrix forcem=new BlockRealMatrix(3,1);
          forcem.setColumn(0,force);
          force=(base.multiply(forcem)).getColumn(0);
          for(int k=0;k<3;k++){
            this.vel[k]+=force[k]/this.m*this.dt;
            square.vel[k]-=force[k]/square.m*this.dt;
          }
          /*
          int a = 0;
          int b = 0;
          int c = 0;
          for(int j=0;j<8;j++){
            if(x[0]*(summitP.getEntry(j,0)-summitP.getEntry(i,0))>=0){
              a += 1;
            }
            if(y[1]*(summitP.getEntry(j,1)-summitPcoefBgetEntry(i,1))>=0){
              b += 1;
            }
            if(z[2]*(summitP.getEntry(j,2)-summitP.getEntry(i,2))>=0){
              c += 1;
            }
          }
          if(a>=7){
             double[] pIn = {square.summits[i].getX(),square.summits[i].getY(),square.summits[i].getZ()};
            double[] n = rotV(x);
            //substraction for vector a faire
            double[] vRel = {square.getVel()[0]-this.getVel()[0],square.getVel()[1]-this.getVel()[1],square.getVel()[2]-this.getVel()[2]};
            double[] f = new double[3];

            double[] r1 = {square.summits[i].getX()-square.getX(),square.summits[i].getY()-square.getY(),square.summits[i].getZ()-square.getZ()};
            double[] r2 = {square.summits[i].getX()-this.pos[0],square.summits[i].getY()-this.pos[1],square.summits[i].getZ()-this.pos[2]};
            for(int k=0;k<3;k++){
              f[k] = (-this.coefK*x[0]-this.coefB*(vRel[0]*n[0]+vRel[1]*n[1]+vRel[2]*n[2]))*n[k];
            }

            square.rot(r1, f);
            square.addForceSummit(f);
            for(int j=0;j<3;j++){
              f[j] = - f[j];
            }
            this.rot(r2, f);
            this.addForceSummit(f);
            // System.out.println("f: "+ f[0]+" "+f[1]+" "+f[2]);
            System.out.println("fa: "+ f[0]+" "+f[1]+" "+f[2]);
          }
          if(b>=7){
            // double[] pIn = {square.summits[i].getX(),square.summits[i].getY(),square.summits[i].getZ()};
            double[] n = rotV(y);
            //substraction for vector a faire
            double[] vRel = {square.getVel()[0]-this.getVel()[0],square.getVel()[1]-this.getVel()[1],square.getVel()[2]-this.getVel()[2]};
            double[] f = {0,0,0};

            double[] r1 = {square.summits[i].getX()-square.getX(),square.summits[i].getY()-square.getY(),square.summits[i].getZ()-square.getZ()};
            double[] r2 = {square.summits[i].getX()-this.pos[0],square.summits[i].getY()-this.pos[1],square.summits[i].getZ()-this.pos[2]};
            for(int k=0;k<3;k++){
              f[k] = (-this.coefK*y[1]-this.coefB*(vRel[0]*n[0]+vRel[1]*n[1]+vRel[2]*n[2]))*n[k];
            }

            square.rot(r1, f);
            square.addForceSummit(f);
            for(int j=0;j<3;j++){
              f[j] = - f[j];
            }
            this.rot(r2, f);
            this.addForceSummit(f);
            // System.out.println("f: "+ f[0]+" "+f[1]+" "+f[2]);
            System.out.println("fb: "+ f[0]+" "+f[1]+" "+f[2]);
          }
          if(c>=7){
            // double[] pIn = {square.summits[i].getX(),square.summits[i].getY(),square.summits[i].getZ()};
            double[] n = rotV(z);
            //substraction for vector a faire
            double[] vRel = {square.getVel()[0]-this.getVel()[0],square.getVel()[1]-this.getVel()[1],square.getVel()[2]-this.getVel()[2]};
            double[] f = new double[3];

            double[] r1 = {square.summits[i].getX()-square.getX(),square.summits[i].getY()-square.getY(),square.summits[i].getZ()-square.getZ()};
            double[] r2 = {square.summits[i].getX()-this.pos[0],square.summits[i].getY()-this.pos[1],square.summits[i].getZ()-this.pos[2]};
            for(int k=0;k<3;k++){
              f[k] = (-this.coefK*z[2]-this.coefB*(vRel[0]*n[0]+vRel[1]*n[1]+vRel[2]*n[2]))*n[k];
            }

            square.rot(r1, f);
            square.addForceSummit(f);
            for(int j=0;j<3;j++){
              f[j] = - f[j];
            }
            this.rot(r2, f);
            this.addForceSummit(f);
            // System.out.println("f: "+ f[0]+" "+f[1]+" "+f[2]);
            System.out.println("fc: "+ f[0]+" "+f[1]+" "+f[2]);
          }
          // else {
          //   System.out.println("What happend?");
          // }*/
          bool= true;
        }
      }
    }

  }
  return bool;
}

public void addForceSummit(double[] f){
  for(int i=0;i<3;i++){
    this.f[i] += f[i];
  }
  // this.addForce(f);
  for(int i=0;i<3;i++){
    f[i] /=8;
  }
  // for(int i=0;i<8;i++){
  //   this.summits[i].addForce(f);
  // }
}

public void rot(double[] r,double[] f){
  // System.out.println("f length: "+f.length);
  // System.out.println("f: "+f[0]+"  "+f[1]+"  "+f[2]);
  Vector3D rv = new Vector3D(r);
  // System.out.println("rv: "+rv);
  Vector3D fv = new Vector3D(f[0],f[1],f[2]);
  // System.out.println("fv: "+fv);
  Vector3D crossP = rv.crossProduct(rv,fv);
  crossP.scalarMultiply(this.dt*this.coefI);
  double[] w = crossP.toArray();
  // System.out.println("w: "+ w[0]+" "+w[1]+" "+w[2]);
  for(int i=0;i<3;i++){
    this.rotation[i] += w[i]*180/Math.PI;
    this.rotation[i] %= 5;
  }

  // System.out.println("rot: "+ this.rotation[0]+" "+this.rotation[1]+" "+this.rotation[2]);
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
