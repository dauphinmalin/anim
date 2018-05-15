import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;


class Drawing extends JPanel{

  Particle[] particles;
  public Drawing(Particle[] particles){
    this.particles=particles;
  }
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       for(int i=0;i<particles.length;i++){

      ;
      g.setColor(Color.blue);
      g.fillOval((int)Math.round(100*this.particles[i].getX()),(int)Math.round(100*this.particles[i].getZ()),(int)Math.round(100*this.particles[i].getRadius()),(int)Math.round(100*this.particles[i].getRadius()));

    }
  }
  public void move(Particle[] particles){
    this.particles=particles;
    repaint();
  }

}

public class Viewing{
  private int sizex;
  private int sizez;
  private JFrame frame;
  private Drawing panel;





  public Viewing(int sizex,int sizez,Particle[] particles,String name){
    this.sizex=sizex;
    this.sizez=sizez;
    this.frame=new JFrame(name);

    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Handle the CLOSE button
    this.frame.pack();              // Either pack() the components; or setSize()
    this.frame.setVisible(true);    // "super" JFrame show
    this.frame.setSize(sizex,sizez);
    this.panel= new Drawing(particles);
    this.panel.setPreferredSize(new Dimension(this.sizex,this.sizez));
    this.frame.setContentPane(this.panel);
    //this.frame.setLayout(new GridLayout(2, 2));
  }

  public void drawParticles(Particle[] particles){
      //Drawing panel = new Drawing(particles);
    //  panel.setPreferredSize(new Dimension(this.sizex,this.sizez));
      this.panel.move(particles);

      ///this.frame.setContentPane(panel);
      //this.frame.validate();
      //this.frame.repaint();

      SwingUtilities.updateComponentTreeUI(this.frame);

  }


}
