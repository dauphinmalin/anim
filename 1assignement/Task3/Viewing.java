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
      int radius=(int)Math.round(100*this.particles[i].getRadius());
      g.setColor(Color.blue);
      g.fillOval((int)Math.round(100*this.particles[i].getX())-radius,(int)Math.round(100*this.particles[i].getZ())-radius,2*radius,2*radius);

      //draw the line segment
      g.drawLine(20,30,900,500);
      double b = 5 + 88.0/47*9;
      int y0 = (int)((b-88.0/47*8.5)*100);
      int y1 = (int)((b-88.0/47*9.5)*100);
      g.drawLine(850,y0,950,y1);



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
  private double d;
  private double t;




  public Viewing(int sizex,int sizez,Particle[] particles,String name){

    this.t = 47.0/88.0;
    this.d = (30.0 - this.t*20.0)/100;
    this.sizex=sizex;
    this.sizez=sizez;
    this.frame=new JFrame(name);

    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.pack();
    this.frame.setVisible(true);
    this.frame.setSize(sizex,sizez+22);
    this.panel= new Drawing(particles);
    this.panel.setPreferredSize(new Dimension(this.sizex,this.sizez));
    this.frame.setContentPane(this.panel);
    SwingUtilities.updateComponentTreeUI(this.frame);

    

  }

  public void drawParticles(Particle[] particles){

      this.panel.move(particles);

  }

  public double getT(){
    return this.t;
}

  public double getD(){
    return this.d;
}


}
