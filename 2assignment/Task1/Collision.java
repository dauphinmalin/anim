
import java.util.*;
import java.lang.Math;




public class Collision{
  Particle[] particles;
  ObjectTree collisionTree;
  double sizex;
  double sizez;


  public Collision(Particle[] particles,double sizex,double sizez){
    this.particles=particles.clone();
    this.sizex=sizex;
    this.sizez=sizez;
    int[] tab=new int[particles.length];
    for(int i=0;i<particles.length;i++){
      tab[i]=i;
    }
  }

  public void calculateForce(){

    this.collisionTree.calculateForceObjectTree(0,0,0);



  }

  public ArrayList<Integer[]> checkCollision(){
    int[] inside=new int[this.particles.length];
    for(int i=0;i<this.particles.length;i++){
      inside[i]=i;
    }
    Cluster cluster=new Cluster(inside,0,0,this.sizex,this.sizez,0,0,0);
    this.collisionTree= new ObjectTree(cluster);
    this.collisionTree.setParticles(this.particles);
    this.collisionTree.RecursionCluster();
    if(this.collisionTree.cluster.elements.length>1){
      ArrayList<Integer[]> list=this.collisionTree.RecursionCheck();
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
