import Particle.java;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.BranchGroup;

public class Viewing{
  int sizex;
  int sizez;
  SimpleUniverse universe =;
  BranchGroup group;







  public void Viewing(int sizex,int sizez){
    this.sizex=sizex;
    this.sizez=sizez;
    this.universe = new SimpleUniverse();
    this.group = new BranchGroup();
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
