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
  private double[][] summits;
  private double[] pos0;
  static private double coefK = 150;
  static private double coefB =1;
  static private double coefI;
  private double[] w;
  private float[][] color;


  public Square(double m,double[] pos,double[] vel,double[] rotation,double[] f,double dt,double side,double[] w){
    super(m,pos,vel,rotation,f,dt);

    this.side=side;
    this.extremeInf=new double[3];
    this.extremeSup=new double[3];
    this.color=new float[6][3];
    SwapColor();
    for(int j=0;j<3;j++){
      this.extremeInf[j]=pos[j]-this.side/2;
      this.extremeSup[j]=pos[j]+this.side/2;

    }
    this.pos0=pos.clone();
    this.w = w.clone();
    this.summits=new double[8][3];
    calculateSummit();



    this.coefI = 30*this.m*this.side*this.side/6;
  }

  public void SwapColor(){
    Random rand = new Random();

    for(int j=0;j<6;j++){
      this.color[j][0]=rand.nextFloat();
      this.color[j][1]=rand.nextFloat();
      this.color[j][2]=rand.nextFloat();


    }
  }
  @Override
  public void Draw(GLAutoDrawable drawable,GLU glu,GL2 gl){
    gl.glPushMatrix();
    gl.glColor3f(this.color[0][0],this.color[0][1],this.color[0][2]);

    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex3f((float)this.summits[0][0],(float)this.summits[0][1],(float)this.summits[0][2]);
    gl.glVertex3f((float)this.summits[1][0],(float)this.summits[1][1],(float)this.summits[1][2]);
    gl.glVertex3f((float)this.summits[2][0],(float)this.summits[2][1],(float)this.summits[2][2]);

    gl.glEnd();

    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex3f((float)this.summits[1][0],(float)this.summits[1][1],(float)this.summits[1][2]);
    gl.glVertex3f((float)this.summits[2][0],(float)this.summits[2][1],(float)this.summits[2][2]);
    gl.glVertex3f((float)this.summits[3][0],(float)this.summits[3][1],(float)this.summits[3][2]);
    gl.glEnd();
    gl.glColor3f(this.color[1][0],this.color[1][1],this.color[1][2]);

    //top
    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex3f((float)this.summits[0][0],(float)this.summits[0][1],(float)this.summits[0][2]);
    gl.glVertex3f((float)this.summits[4][0],(float)this.summits[4][1],(float)this.summits[4][2]);
    gl.glVertex3f((float)this.summits[2][0],(float)this.summits[2][1],(float)this.summits[2][2]);
    gl.glEnd();

    // bottom
    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex3f((float)this.summits[4][0],(float)this.summits[4][1],(float)this.summits[4][2]);
    gl.glVertex3f((float)this.summits[2][0],(float)this.summits[2][1],(float)this.summits[2][2]);
    gl.glVertex3f((float)this.summits[6][0],(float)this.summits[6][1],(float)this.summits[6][2]);

    gl.glEnd();
    gl.glColor3f(this.color[2][0],this.color[2][1],this.color[2][2]);

    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex3f((float)this.summits[4][0],(float)this.summits[4][1],(float)this.summits[4][2]);
    gl.glVertex3f((float)this.summits[1][0],(float)this.summits[1][1],(float)this.summits[1][2]);
    gl.glVertex3f((float)this.summits[0][0],(float)this.summits[0][1],(float)this.summits[0][2]);

    gl.glEnd();
    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex3f((float)this.summits[4][0],(float)this.summits[4][1],(float)this.summits[4][2]);
    gl.glVertex3f((float)this.summits[1][0],(float)this.summits[1][1],(float)this.summits[1][2]);
    gl.glVertex3f((float)this.summits[5][0],(float)this.summits[5][1],(float)this.summits[5][2]);

    gl.glEnd();

    gl.glColor3f(this.color[3][0],this.color[3][1],this.color[3][2]);


    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex3f((float)this.summits[2][0],(float)this.summits[2][1],(float)this.summits[2][2]);
    gl.glVertex3f((float)this.summits[3][0],(float)this.summits[3][1],(float)this.summits[3][2]);
    gl.glVertex3f((float)this.summits[6][0],(float)this.summits[6][1],(float)this.summits[6][2]);

    gl.glEnd();

    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex3f((float)this.summits[7][0],(float)this.summits[7][1],(float)this.summits[7][2]);
    gl.glVertex3f((float)this.summits[3][0],(float)this.summits[3][1],(float)this.summits[3][2]);
    gl.glVertex3f((float)this.summits[6][0],(float)this.summits[6][1],(float)this.summits[6][2]);

    gl.glEnd();

    gl.glColor3f(this.color[4][0],this.color[4][1],this.color[4][2]);
    

    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex3f((float)this.summits[7][0],(float)this.summits[7][1],(float)this.summits[7][2]);
    gl.glVertex3f((float)this.summits[5][0],(float)this.summits[5][1],(float)this.summits[5][2]);
    gl.glVertex3f((float)this.summits[6][0],(float)this.summits[6][1],(float)this.summits[6][2]);

    gl.glEnd();

    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex3f((float)this.summits[4][0],(float)this.summits[4][1],(float)this.summits[4][2]);
    gl.glVertex3f((float)this.summits[5][0],(float)this.summits[5][1],(float)this.summits[5][2]);
    gl.glVertex3f((float)this.summits[6][0],(float)this.summits[6][1],(float)this.summits[6][2]);

    gl.glEnd();

    gl.glColor3f(this.color[5][0],this.color[5][1],this.color[5][2]);


    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex3f((float)this.summits[1][0],(float)this.summits[1][1],(float)this.summits[1][2]);
    gl.glVertex3f((float)this.summits[5][0],(float)this.summits[5][1],(float)this.summits[5][2]);
    gl.glVertex3f((float)this.summits[3][0],(float)this.summits[3][1],(float)this.summits[3][2]);

    gl.glEnd();

    gl.glBegin(GL2.GL_TRIANGLES);
    gl.glVertex3f((float)this.summits[7][0],(float)this.summits[7][1],(float)this.summits[7][2]);
    gl.glVertex3f((float)this.summits[5][0],(float)this.summits[5][1],(float)this.summits[5][2]);
    gl.glVertex3f((float)this.summits[3][0],(float)this.summits[3][1],(float)this.summits[3][2]);

    gl.glEnd();


    gl.glPopMatrix();

  }

  public void calculateBoundingVolume(){
  }


  public void calculatePos(){
    for(int i=0;i<3;i++){
      this.vel[i]=this.vel[i]+this.dt*this.f[i]/this.m;
      this.pos[i] = this.pos[i]+this.vel[i]*this.dt;
      this.rotation[i] += this.w[i]*this.dt;
    }
    calculateSummit();
    borderResponse();
  }
  private void responseForce(double[] force){
    for(int i=0;i<3;i++){
      this.vel[i]+=force[i]*this.dt/m;
    }
  }
  public void borderResponse(){
    for(int j=0;j<8;j++){
      boolean bool=false;
      double[] f={0,0,0};
      for(int i=0;i<3;i++){

        if(this.summits[j][i]>(this.posMAX[i])){
          bool=true;
          f[i]=-(this.coefK*(this.summits[j][i]-this.posMAX[i])+this.coefB*this.vel[i]);
        }
        else if(this.summits[j][i]<0){
          bool=true;
          f[i]= -(this.coefK*this.summits[j][i]+this.coefB*this.vel[i]);
        }
      }
      if(bool){
        this.responseForce(f);
        double[] r = {this.summits[j][0]-this.pos[0],this.summits[j][1]-this.pos[1],this.summits[j][2]-this.pos[2]};
        this.rot(r,f);
      }
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

  public void calculateSummit(){
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

        this.summits[i][j]=rotX[j][0]*summit[i][0]+rotX[j][1]*summit[i][1]+rotX[j][2]*summit[i][2]+this.pos[j];
        if(this.summits[i][j]<this.extremeInf[j]){this.extremeInf[j]=this.summits[i][j];}
        if(this.summits[i][j]>this.extremeSup[j]){this.extremeSup[j]=this.summits[i][j];}


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
      double[][] vector={{this.summits[i][0]-center[0]},{this.summits[i][1]-center[1]},{this.summits[i][2]-center[2]}};
      RealMatrix vectorm=new BlockRealMatrix(vector);
      summitP.setRow(i,(base.multiply(vectorm)).getColumn(0));
    }

    return summitP;
  }

  public boolean checkCollision(Square square){
    boolean bool=false;
    double[][] basetab = new double[3][3];
    for(int j=0;j<3;j++){
      basetab[0][j] = (this.summits[4][j]-this.summits[0][j])/this.side;
      basetab[1][j] = (this.summits[2][j]-this.summits[0][j])/this.side;
      basetab[2][j] = (this.summits[1][j]-this.summits[0][j])/this.side;
    }
    RealMatrix base= new BlockRealMatrix(3,3);
    base.setColumn(0,basetab[0]);
    base.setColumn(1,basetab[1]);
    base.setColumn(2,basetab[2]);
    RealMatrix baseop= MatrixUtils.inverse(base);
    RealMatrix summitP = square.summitProj(this.summits[0], baseop);
    for(int i=0;i<8;i++){
      if((summitP.getEntry(i,0)<=this.side)&&(summitP.getEntry(i,0)>=0)){
        if((summitP.getEntry(i,1)<=this.side)&&(summitP.getEntry(i,1)>=0)){
          if((summitP.getEntry(i,2)<=this.side)&&(summitP.getEntry(i,2)>=0)){
            double[] x = {this.side-summitP.getEntry(i,0),0,0};
            double[] y = {0,this.side-summitP.getEntry(i,1),0};
            double[] z = {0,0,this.side-summitP.getEntry(i,2)};
            double[][] tabCentersquare={{square.pos[0]-this.summits[0][0]},{square.pos[1]-this.summits[0][1]},{square.pos[2]-this.summits[0][2]}};
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
                // System.out.println(k);
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
            // System.out.println(axe);
            RealMatrix forcem=new BlockRealMatrix(3,1);
            forcem.setColumn(0,force);
            force=(base.multiply(forcem)).getColumn(0);

            double[] possquare = new double[3];
            switch(indicemin){
              case 0: possquare[0]=0;
              possquare[1]=summitP.getEntry(i,1);
              possquare[2]=summitP.getEntry(i,2);
              break;
              case 1: possquare[0]=this.side;
              possquare[1]=summitP.getEntry(i,1);
              possquare[2]=summitP.getEntry(i,2);
              break;
              case 2: possquare[0]=summitP.getEntry(i,0);
              possquare[1]=0;
              possquare[2]=summitP.getEntry(i,2);
              break;
              case 3: possquare[0]=summitP.getEntry(i,0);
              possquare[1]=this.side;
              possquare[2]=summitP.getEntry(i,2);
              break;
              case 4: possquare[0]=summitP.getEntry(i,0);
              possquare[1]=summitP.getEntry(i,1);
              possquare[2]=0;
              break;
              case 5: possquare[0]=summitP.getEntry(i,0);
              possquare[1]=summitP.getEntry(i,1);
              possquare[2]=this.side;
              break;
            }

            double[] r1= {square.getSummit(i)[0]-square.getX(),square.getSummit(i)[1]-square.getY(),square.getSummit(i)[2]-square.getZ()};
            double[] r2= {square.getSummit(i)[0]-this.pos[0],square.getSummit(i)[1]-this.pos[1],square.getSummit(i)[2]-this.pos[2]};
            double[] minusforce = {-force[0],-force[1],-force[2]};
            square.rot(r1,minusforce);
            square.responseForce(minusforce);
            this.responseForce(force);
            this.rot(r2,force);
            bool= true;
          }
        }
      }

    }
    if(bool){
      this.SwapColor();
      square.SwapColor();
    }
    return bool;
  }



  public void rot(double[] r,double[] f){
    Vector3D rv = new Vector3D(r);
    Vector3D fv = new Vector3D(f);
    Vector3D crossP = rv.crossProduct(fv);
    crossP = crossP.scalarMultiply(this.dt/this.coefI);
    double[] w = crossP.toArray();
    for(int i=0;i<3;i++){
      this.w[i] += w[i];
    }

  }

  public double[] getSummit(int i){
    return this.summits[i];
  }

}
