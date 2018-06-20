import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import java.awt.event.MouseEvent;
import java.awt.Robot;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
/*class Drawing extends JPanel{
  PrimitiveObject[] objects;


  public Drawing(PrimitiveObject[] objects){
    this.objects=objects;
  }


  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for(int i=0;i<objects.length;i++){
      int radius=(int)Math.round(this.objects[i].getRadius());
      g.setColor(Color.blue);
      g.fillOval((int)Math.round(this.objects[i].getX())-radius,(int)Math.round(this.objects[i].getZ())-radius,2*radius,2*radius);
    }
  }


  public void move(PrimitiveObject[] objects){
    this.objects=objects;
    repaint();
  }
}

*/


public class Viewing implements GLEventListener, MouseListener, MouseMotionListener{
  PrimitiveObject[] objects;
  private GLU glu;
  private double view[];
  double previousX;
  double previousY;


  public Viewing(PrimitiveObject[] objects){
    super();
    this.objects=objects;
    this.view=new double[3];
    this.view[0]=-900;
    this.view[1]=325;
    this.view[2]=325;


  }
 @Override
  public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
      this.glu = new GLU();                         // get GL Utilities
      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
      gl.glClearDepth(1.0f);      // set clear depth value to farthest
      gl.glEnable(GL2.GL_DEPTH_TEST); // enables depth testing
      gl.glDepthFunc(GL2.GL_LEQUAL);  // the type of depth test to do
      gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
      gl.glShadeModel(GL2.GL_SMOOTH); // blends colors nicely, and smoothes out lighting

      // ----- Your OpenGL initialization code here -----
   }

   @Override
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
      if (height == 0) height = 1;   // prevent divide by zero
      float aspect = (float)width / height;

      // Set the view port (display area) to cover the entire window
      gl.glViewport(0, 0, width, height);

      // Setup perspective projection, with aspect ratio matches viewport
      gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
      gl.glLoadIdentity();             // reset projection matrix
      this.glu.gluPerspective(45, aspect,1, 2500); // fovy, aspect, zNear, zFar
      this.glu.gluLookAt(view[0],view[1],view[2],475,425,125,0,0,1);

      // Enable the model-view transform
      gl.glMatrixMode(GL2.GL_MODELVIEW);
      gl.glLoadIdentity(); // reset
   }

   @Override
   public void display(GLAutoDrawable drawable) {
     GL2 gl = drawable.getGL().getGL2();
     float aspect = (float)950 / 650;

     gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
     gl.glLoadIdentity();             // reset projection matrix
     this.glu.gluPerspective(45, aspect,1, 2500); // fovy, aspect, zNear, zFar
     gl.glColor3f(1,1,1);

     this.glu.gluLookAt(view[0],view[1],view[2],325,325,325,0,0,1);

     // Enable the model-view transform
     gl.glMatrixMode(GL2.GL_MODELVIEW);
     gl.glLoadIdentity(); // reset
     gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

     gl.glBegin(GL2.GL_LINES);
      gl.glVertex3f(0,0f,0);
      gl.glVertex3f(650,0, 0);
      gl.glEnd();

      gl.glBegin(GL2.GL_LINES);
      gl.glVertex3f(0,0,0); // 3 units into the window
      gl.glVertex3f(0f,650,0);
      gl.glEnd();

      //top
      gl.glBegin(GL2.GL_LINES);
      gl.glVertex3f(0,0f,0);
      gl.glVertex3f(0,0,650);
      gl.glEnd();

      // bottom
      gl.glBegin(GL2.GL_LINES);
      gl.glVertex3f(650,0, 0);
      gl.glVertex3f(650,650,0);
      gl.glEnd();

      // edge 2....
      gl.glBegin(GL2.GL_LINES);
      gl.glVertex3f(650,0,0);
      gl.glVertex3f(650,0,650);
      gl.glEnd();

      gl.glBegin(GL2.GL_LINES);
      gl.glVertex3f(650,650,0);
      gl.glVertex3f(0,650,0);
      gl.glEnd();

      gl.glBegin(GL2.GL_LINES);
      gl.glVertex3f(650,650, 0);
      gl.glVertex3f(650,650,650);
      gl.glEnd();

      gl.glBegin(GL2.GL_LINES);
      gl.glVertex3f(650,650,650);
      gl.glVertex3f(650,0,650);
      gl.glEnd();

      //Edge 3.............
      gl.glBegin(GL2.GL_LINES);
      gl.glVertex3f(650,650,650);
      gl.glVertex3f(0,650,650);
      gl.glEnd();

      gl.glBegin(GL2.GL_LINES);
      gl.glVertex3f(0,650,650);
      gl.glVertex3f(0,650,0);
      gl.glEnd();

      gl.glBegin(GL2.GL_LINES);
      gl.glVertex3f(0,650,650);
      gl.glVertex3f(0,0,650);
      gl.glEnd();

      gl.glBegin(GL2.GL_LINES);
      gl.glVertex3f(0,0,650);
      gl.glVertex3f(650,0,650);
      gl.glEnd();



      for(int i=0;i<this.objects.length;i++){
        this.objects[i].Draw(drawable,this.glu,gl);
      }
   }

   /**
    * Called back before the OpenGL context is destroyed. Release resource such as buffers.
    */
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
         public void mouseClicked(MouseEvent e){}
         @Override
         public void mouseEntered(MouseEvent e){}
         @Override
         public void mouseExited(MouseEvent e){}
     @Override
    public void mouseMoved(MouseEvent e){}
   @Override
   public void mouseDragged(MouseEvent e){
     double x=e.getX();
     double y=e.getY();
     double view0=this.view[0];
     double theta=(x-this.previousX)/360*2*Math.PI;
     double alpha=(y-this.previousY)/360*2*Math.PI;
     this.view[0]=(this.view[0]-325)*Math.cos(theta)-(this.view[1]-325)*Math.sin(theta)+325;
     this.view[1]=(view0-325)*Math.sin(theta)+(this.view[1]-325)*Math.cos(theta)+325;
     double[] xprimtab={325-view[0],325-view[1],0};
     Vector3D xprim= new Vector3D(xprimtab);

     xprim=xprim.normalize();

     double[] zprimtab={0,0,1};
     Vector3D zprim=new Vector3D(zprimtab);
     Vector3D yprim=Vector3D.crossProduct(zprim,xprim);
     double[] vieww={this.view[0]-325,this.view[1]-325,this.view[2]-325};
     Vector3D view=new Vector3D(vieww);
     double norm=view.dotProduct(xprim);




     view0=(norm)*Math.cos(alpha)+(this.view[2]-325)*Math.sin(alpha);
     xprim=xprim.scalarMultiply(view0);

     double view2=-(norm)*Math.sin(alpha)+(this.view[2]-325)*Math.cos(alpha)+325;

     if(view2<1500 && view2>-200){
     this.view[0]=xprim.getX()+325;
     this.view[1]=xprim.getY()+325;

     this.view[2]=view2;
   }

     this.previousX=x;
     this.previousY=y;




   }




}
