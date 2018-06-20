import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.util.*;
import org.apache.commons.math3.linear.*;
import org.apache.commons.math3.geometry.euclidean.threed.*;


public class Wave{
  private RealMatrix height;
  private RealMatrix prevheight;
  private RealMatrix nextheight;
  private double dt;
  private double dx;
  private double v;
  private int length;
  private int width;


public Wave(int x, int y, double dt, double dx, double v){
  this.length = x;
  this.width = y;
  this.height = new BlockRealMatrix(x,y);
  this.prevheight = new BlockRealMatrix(x,y);
  this.nextheight = new BlockRealMatrix(x,y);
  this.dt = dt;
  this.dx = dx;
  this.v = v;
  this.init();
}


public void setBoundary(){
  for(int i=0;i<this.length;i++){
    for(int j=0;j<this.width;j++){
      this.height.setEntry(0,j,325);
      this.height.setEntry(i,0,325);
      this.height.setEntry(this.length-1,j,325);
      this.height.setEntry(i,this.width-1,325);
    }
  }
  this.height.setRow(0,this.height.getRow(1));
  this.height.setRow(this.length-1,this.height.getRow(this.length-2));
  this.height.setColumn(0,this.height.getColumn(1));
  this.height.setColumn(this.width-1,this.height.getColumn(this.width-2));
}
public void init(){
  for(int i=0;i<this.length;i++){
    for(int j=0;j<this.width;j++){
      this.height.setEntry(i,j,325);
      this.prevheight.setEntry(i,j,325);
    }
  }
}

public void scale(){
  double max = this.height.getEntry(0,0);
  double min = this.height.getEntry(0,0);
  for(int i=0;i<this.length;i++){
    for(int j=0;j<this.width;j++){
      if(this.height.getEntry(i,j)>max){
        max = this.height.getEntry(i,j);
      }
      if(this.height.getEntry(i,j)<min){
        min = this.height.getEntry(i,j);
      }
    }
  }
  double scale = 100/(max - min);
  double dummy;
  for(int i=0;i<this.length;i++){
    for(int j=0;j<this.width;j++){
      dummy = scale*(this.height.getEntry(i,j)-(max+min)/2)+325;
      this.height.setEntry(i,j,dummy);
    }
  }
}

public void calculatePos(){
  double cte = this.v*this.v/this.dx/this.dx/this.dt/this.dt;
  double dummy;
  for(int i=1;i<this.length-1;i++){
    for(int j=1;j<this.width-1;j++){
      dummy = cte*(this.height.getEntry(i,j+1)+this.height.getEntry(i+1,j) + this.height.getEntry(i,j-1) + this.height.getEntry(i-1,j) - 4*this.height.getEntry(i,j));
      dummy += 2*this.height.getEntry(i,j) - this.prevheight.getEntry(i,j);
      this.nextheight.setEntry(i,j,dummy);
    }
  }
  this.prevheight.setSubMatrix(this.height.getData(),0,0);
  this.height.setSubMatrix(this.nextheight.getData(),0,0);
  this.setBoundary();
  // this.scale();
}

public void perturbation(int i, int j, double h){
  this.height.setEntry(i,j,h);
}

public void Draw(GLAutoDrawable drawable,GLU glu,GL2 gl){
  /*for(int i=0;i<this.length;i++){
    for(int j=0;j<this.width;j++){
      gl.glBegin(GL2.GL_POINTS);
      gl.glColor3f(((float)this.height.getEntry(i,j)-(325-50))/100,((float)this.height.getEntry(i,j)-(325-50))/100,1);
      gl.glVertex3f((float)i*5,(float)j*5,(float)this.height.getEntry(i,j));
      gl.glEnd();
    }
  }*/
  for(int i=0;i<this.length-1;i++){
    for(int j=0;j<this.width-1;j++){
      gl.glBegin(GL2.GL_TRIANGLES);
      gl.glColor3f(((float)this.height.getEntry(i,j)-(325-50))/100,((float)this.height.getEntry(i,j)-(325-50))/100,1);
      gl.glVertex3f((float)i*5,(float)j*5,(float)this.height.getEntry(i,j));
      gl.glColor3f(((float)this.height.getEntry(i+1,j)-(325-50))/100,((float)this.height.getEntry(i+1,j)-(325-50))/100,1);
      gl.glVertex3f((float)(i+1)*5,(float)j*5,(float)this.height.getEntry(i+1,j));
      gl.glColor3f(((float)this.height.getEntry(i,j+1)-(325-50))/100,((float)this.height.getEntry(i,j+1)-(325-50))/100,1);
      gl.glVertex3f((float)i*5,(float)(j+1)*5,(float)this.height.getEntry(i,j+1));
      gl.glEnd();
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3f(((float)this.height.getEntry(i+1,j+1)-(325-50))/100,((float)this.height.getEntry(i+1,j+1)-(325-50))/100,1);
        gl.glVertex3f((float)(i+1)*5,(float)(j+1)*5,(float)this.height.getEntry(i,j));
        gl.glColor3f(((float)this.height.getEntry(i+1,j)-(325-50))/100,((float)this.height.getEntry(i+1,j)-(325-50))/100,1);
        gl.glVertex3f((float)(i+1)*5,(float)j*5,(float)this.height.getEntry(i+1,j));
        gl.glColor3f(((float)this.height.getEntry(i,j+1)-(325-50))/100,((float)this.height.getEntry(i,j+1)-(325-50))/100,1);
        gl.glVertex3f((float)i*5,(float)(j+1)*5,(float)this.height.getEntry(i,j+1));
        gl.glEnd();
    }
  }/*
  for(int i=0;i<this.length-1;i++){
    for(int j=0;j<this.width-1;j++){
  if(this.height.getEntry(i,j)!=325){
  gl.glPushMatrix();
  gl.glColor3f(((float)this.height.getEntry(i,j)-(325-50))/100,((float)this.height.getEntry(i,j)-(325-50))/100,1);

  gl.glTranslatef((float)i*5,(float)j*5,(float)this.height.getEntry(i,j) );
  GLUquadric quad = glu.gluNewQuadric();
  glu.gluSphere(quad,5, 10, 15);
  glu.gluDeleteQuadric(quad);
  gl.glPopMatrix();
}
else{gl.glBegin(GL2.GL_POINTS);
  gl.glColor3f(((float)this.height.getEntry(i,j)-(325-50))/100,((float)this.height.getEntry(i,j)-(325-50))/100,1);
gl.glVertex3f((float)i*5,(float)j*5,(float)this.height.getEntry(i,j));
gl.glEnd();

gl.glBegin(GL2.GL_TRIANGLES);
gl.glVertex3f((float)i*5,(float)j*5,(float)this.height.getEntry(i,j));
gl.glVertex3f((float)(i+1)*5,(float)j*5,(float)this.height.getEntry(i+1,j));
gl.glVertex3f((float)i*5,(float)(j+1)*5,(float)this.height.getEntry(i,j+1));
gl.glEnd();
  gl.glBegin(GL2.GL_TRIANGLES);
  gl.glVertex3f((float)(i+1)*5,(float)(j+1)*5,(float)this.height.getEntry(i,j));
  gl.glVertex3f((float)(i+1)*5,(float)j*5,(float)this.height.getEntry(i+1,j));
  gl.glVertex3f((float)i*5,(float)(j+1)*5,(float)this.height.getEntry(i,j+1));
  gl.glEnd();}
  }
}*/

}
}
