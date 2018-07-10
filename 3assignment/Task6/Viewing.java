import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.Robot;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
public class Viewing implements GLEventListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{
  PrimitiveObject[] objects;
  private GLU glu;
  private double view[];
  double previousX;
  double previousY;
  private static float side=650f;
  public Viewing(PrimitiveObject[] objects){
    super();
    this.objects=objects;
    this.view=new double[3];
    this.view[0]=-1500;
    this.view[1]=325;
    this.view[2]=325;
  }
  @Override
  public void init(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    this.glu = new GLU();
    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    gl.glClearDepth(1.0f);
    gl.glEnable(GL2.GL_DEPTH_TEST);
    gl.glDepthFunc(GL2.GL_LEQUAL);
    gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
    gl.glShadeModel(GL2.GL_SMOOTH);

  }

  @Override
  public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    GL2 gl = drawable.getGL().getGL2();
    if (height == 0) height = 1;
    float aspect = (float)width / height;
    gl.glViewport(0, 0, width, height);
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    this.glu.gluPerspective(45, aspect,1, 2500);
    this.glu.gluLookAt(view[0],view[1],view[2],475,425,125,0,0,1);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
    gl.glLoadIdentity();
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    GL2 gl = drawable.getGL().getGL2();
    float aspect = (float)950 / 650;
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    this.glu.gluPerspective(45, aspect,1, 5000);
    gl.glColor3f(1,1,1);
    this.glu.gluLookAt(view[0],view[1],view[2],325,325,325,0,0,1);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
    gl.glLoadIdentity();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

    //Draw Borders
    gl.glBegin(GL2.GL_LINES);
    gl.glVertex3f(0,0f,0);
    gl.glVertex3f(side,0, 0);
    gl.glEnd();

    gl.glBegin(GL2.GL_LINES);
    gl.glVertex3f(0,0,0);
    gl.glVertex3f(0f,side,0);
    gl.glEnd();

    gl.glBegin(GL2.GL_LINES);
    gl.glVertex3f(0,0f,0);
    gl.glVertex3f(0,0,side);
    gl.glEnd();

    gl.glBegin(GL2.GL_LINES);
    gl.glVertex3f(side,0, 0);
    gl.glVertex3f(side,side,0);
    gl.glEnd();

    gl.glBegin(GL2.GL_LINES);
    gl.glVertex3f(side,0,0);
    gl.glVertex3f(side,0,side);
    gl.glEnd();

    gl.glBegin(GL2.GL_LINES);
    gl.glVertex3f(side,side,0);
    gl.glVertex3f(0,side,0);
    gl.glEnd();

    gl.glBegin(GL2.GL_LINES);
    gl.glVertex3f(side,side, 0);
    gl.glVertex3f(side,side,side);
    gl.glEnd();

    gl.glBegin(GL2.GL_LINES);
    gl.glVertex3f(side,side,side);
    gl.glVertex3f(side,0,side);
    gl.glEnd();

    gl.glBegin(GL2.GL_LINES);
    gl.glVertex3f(side,side,side);
    gl.glVertex3f(0,side,side);
    gl.glEnd();

    gl.glBegin(GL2.GL_LINES);
    gl.glVertex3f(0,side,side);
    gl.glVertex3f(0,side,0);
    gl.glEnd();

    gl.glBegin(GL2.GL_LINES);
    gl.glVertex3f(0,side,side);
    gl.glVertex3f(0,0,side);
    gl.glEnd();

    gl.glBegin(GL2.GL_LINES);
    gl.glVertex3f(0,0,side);
    gl.glVertex3f(side,0,side);
    gl.glEnd();

    for(int i=0;i<this.objects.length;i++){
      this.objects[i].Draw(drawable,this.glu,gl);
    }
  }




private void Zoom(int i){
  double[] tab={view[0]-325,view[1]-325,view[2]-325};
  Vector3D vectorView=new Vector3D(tab);
  vectorView=vectorView.normalize().scalarMultiply(vectorView.getNorm()+i*50);
  this.view[0]=vectorView.getX()+325;
  this.view[1]=vectorView.getY()+325;
  this.view[2]=vectorView.getZ()+325;

}



private void RotateX(double x){
  double theta=-(x)/360*2*Math.PI;
  double view0=view[0];
  this.view[0]=(this.view[0]-325)*Math.cos(theta)-(this.view[1]-325)*Math.sin(theta)+325;
  this.view[1]=(view0-325)*Math.sin(theta)+(this.view[1]-325)*Math.cos(theta)+325;



}


private void RotateY(double y){
  double alpha=(y)/360*2*Math.PI;
  double[] xprimtab={325-view[0],325-view[1],0};
  Vector3D xprim= new Vector3D(xprimtab);
  xprim=xprim.normalize();
  double[] zprimtab={0,0,1};
  Vector3D zprim=new Vector3D(zprimtab);
  double[] vieww={this.view[0]-325,this.view[1]-325,this.view[2]-325};
  Vector3D view=new Vector3D(vieww);
  double norm=view.dotProduct(xprim);
  double view0=(norm)*Math.cos(alpha)+(this.view[2]-325)*Math.sin(alpha);
  xprim=xprim.scalarMultiply(view0);
  double view2=-(norm)*Math.sin(alpha)+(this.view[2]-325)*Math.cos(alpha)+325;
  if(view2<1500 && view2>-side){
    this.view[0]=xprim.getX()+325;
    this.view[1]=xprim.getY()+325;
    this.view[2]=view2;
  }

}

  @Override

  //function to move Camera
  public void mouseDragged(MouseEvent e){
    double x=e.getX();
    double y=e.getY();
    RotateX(x-this.previousX);
    RotateY(y-this.previousY);
    this.previousX=x;
    this.previousY=y;
  }

  @Override

  public void mouseWheelMoved(MouseWheelEvent e) {
    int notches = e.getWheelRotation();

    if (notches < 0) {
      Zoom(-1);
    }
    else{
      Zoom(1);
    }
  }


  @Override public void keyPressed(KeyEvent e){
    int keyCode=e.getKeyCode();
    switch( keyCode ) {
        case KeyEvent.VK_BACK_SPACE:
          Zoom(-1);
        break;
        case KeyEvent.VK_ENTER:
          Zoom(1);
        break;
        case KeyEvent.VK_UP:
          RotateY(3);
        break;
        case KeyEvent.VK_DOWN:
          RotateY(-3);
        break;

        case KeyEvent.VK_LEFT:
          RotateX(3);

        break;
        case KeyEvent.VK_RIGHT :
          RotateX(-3);

        break;
     }

  }

  @Override public void keyReleased(KeyEvent e){

  }

  @Override public void keyTyped(KeyEvent e){

  }

  @Override
  public void dispose(GLAutoDrawable drawable) { }

  @Override
  public void mouseReleased(MouseEvent e){}
    @Override
    public void mousePressed(MouseEvent e){
      this.previousX=e.getX();
      this.previousY=e.getY();
    }
    @Override
    public void mouseClicked(MouseEvent e){

    }
    @Override
    public void mouseEntered(MouseEvent e){

    }
    @Override
    public void mouseExited(MouseEvent e){

    }
    @Override
    public void mouseMoved(MouseEvent e){

    }



  }
