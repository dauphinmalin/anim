
import java.util.*;
import java.lang.Math;




public class Collision{
  Particle[] particles;
  ObjectTree collisionTree;
  double sizex;
  double sizey;
  double sizez;


  public Collision(Particle[] particles,double sizex,double sizey,double sizez){
    this.particles=particles.clone();
    this.sizex=sizex;
    this.sizey=sizey;
    this.sizez=sizez;
    int[] tab=new int[particles.length];
    for(int i=0;i<particles.length;i++){
      tab[i]=i;
    }
  }

  public void calculateForce(){
    double[] cm = {0,0,0};
    this.collisionTree.calculateForceObjectTree(0,cm);



  }

  public ArrayList<Integer[]> checkCollision(){
    int[] inside=new int[this.particles.length];
    double[] min = {0,0,0};
    double[] max = {this.sizex,this.sizey,this.sizez};
    double[] cm = {0,0,0};

    this.collisionTree= new ObjectTree(this.particles,min,max,0,cm);
    this.collisionTree.Cluster();
    if(this.collisionTree.elements.length>1){
      ArrayList<Integer[]> list=this.collisionTree.checkCollision();
      Iterator<Integer[]> iterator = list.iterator();
      while (iterator.hasNext()) {
        Integer[] tab=iterator.next();
        if(tab[0]==-1){iterator.remove();}
        else{
          for(int i=0;i<tab.length;i++){
          }
        }
      }
      return list;
    }
    else{
      return null;
    }
  }

  public ArrayList<Integer[]> checkColID(){
    int[] inside=new int[this.particles.length];
    double[] min = {0,0,0};
    double[] max = {this.sizex,this.sizey,this.sizez};
    double[] cm = {0,0,0};

    this.collisionTree= new ObjectTree(this.particles,min,max,0,cm);
    this.collisionTree.Cluster();
    if(this.collisionTree.elements.length>1){
      ArrayList<Integer[]> list=this.collisionTree.checkCollision();
      Iterator<Integer[]> iterator = list.iterator();
      while (iterator.hasNext()) {
        Integer[] tab=iterator.next();
        if(tab[0]==-1){iterator.remove();}
        else{
          for(int i=0;i<tab.length;i++){
          }
        }
      }
      return list;
    }
    else{
      return null;
    }
  }

}
