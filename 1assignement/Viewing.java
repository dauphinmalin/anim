import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Viewing{
  int sizex;
  int sizez;
  SimpleUniverse universe;
  BranchGroup group;







  public void Viewing(int sizex,int sizez){
    this.sizex=sizex;
    this.sizez=sizez;
    this.universe = new SimpleUniverse();
    this.group = new BranchGroup();

    this.group.addChild(new ColorCube(0.3));

   this.universe.getViewingPlatform().setNominalViewingTransform();

   this.universe.addBranchGraph(group);

  }

  public void drawParticles(Particle[] particles){
    for(int i=0;i<particles.length;i++){
      Sphere sphere = new Sphere(0.05f);
      TransformGroup tg = new TransformGroup();
      Transform3D transform = new Transform3D();

Vector3f vector = new Vector3f( x, .0f, .0f);

transform.setTranslation(vector);

tg.setTransform(transform);

tg.addChild(sphere);

group.addChild(tg);


      this.group.addChild(newColorCube)
    }


  }


}
