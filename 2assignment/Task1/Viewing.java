import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
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


public class Viewing implements GLEventListener{
  PrimitiveObject[] objects;
  private GLU glu;


  public Viewing(PrimitiveObject[] objects){
    super();
    this.objects=objects;


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
      this.glu.gluPerspective(45, aspect,1, 2000); // fovy, aspect, zNear, zFar
      this.glu.gluLookAt(-900,125,725,475,425,125,0,0,1);

      // Enable the model-view transform
      gl.glMatrixMode(GL2.GL_MODELVIEW);
      gl.glLoadIdentity(); // reset
   }

   @Override
   public void display(GLAutoDrawable drawable) {
     GL2 gl = drawable.getGL().getGL2();
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



  public void drawObject(PrimitiveObject[] objects){

    /*this.panel.removeAll();
    for(int i=0;i<objects.length;i++){
    this.panel.add(objects[i].Draw());
    }
    this.panel.revalidate();
    this.panel.repaint();
    Toolkit.getDefaultToolkit().sync();
*/
  }
}
