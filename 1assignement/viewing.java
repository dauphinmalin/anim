import Particle.java;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.BranchGroup;

public class Viewing{
  int sizex;
  int sizez;
  SimpleUniverse universe = new SimpleUniverse();
  BranchGroup group = new BranchGroup();







  public void Viewing(int sizex,int sizez){
    this.sizex=sizex;
    this.sizez=sizez;
    this.universe = new SimpleUniverse();
    this.group = new BranchGroup();
  }

  public void drawParticles(Particle[] particles){
    for(int i=0;i<particles.length;i++){

    }


  }


}
