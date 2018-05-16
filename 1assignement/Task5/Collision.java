
import java.util.*;
import java.lang.Math;


class Tree{
  private double x;
  private double z;
  private int[] inside;
  private Tree right;
  private Tree left;
  static Particle[] particles=null;



public Tree(int[] inside,double x,double z){
  this.inside=inside;
  this.x=x;
  this.z=z;
  this.left=null;
  this.right=null;
}

public int[] getInside(){
  return this.inside;
}

public double getX(){
  return this.x;
}

public double getZ(){
  return this.z;
}

public setParticles(Particle[] particles){
  this.particles=particles;
}

private void Recursion(){
  int[][] result=this.cluster(inside);
  






}
}

public class Collision{
  Particle[] particles;
  Tree collisionTree;


public Collision(Particle[] particles,double sizex,double sizez){
  this.particles=particles;
  int[] tab=new int[particles.length];
  for(int i=0;i<particles.length;i++){
    tab[i]=i;
  }
  this.collisionTree=new Tree(tab,sizex,sizez);
}
public int[][] Cluster(int[] tab){
    ArrayList<Integer> a=new ArrayList<Integer>();
    ArrayList<Integer> b=new ArrayList<Integer>();
    ArrayList<Integer> a1=new ArrayList<Integer>();
    ArrayList<Integer> b1=new ArrayList<Integer>();
    int i;
    double medx1=0;
    double medz1=0;
    double medx2=0;
    double medz2=0;
    double prevMedx1=0;
    double prevMedz1=0;
    double prevMedx2=0;
    double prevMedz2=0;
    for(i=0;i<tab.length/2;i++){
      a.add(tab[i]);
      medx1=this.particles[tab[i]].getX()+medx1;
      medz1=this.particles[tab[i]].getZ()+medz1;


    }
    medx1=medx1/i;
    medz1=medz1/i;
    while(i<tab.length){
      b.add(tab[i]);
      medx2=this.particles[tab[i]].getX()+medx2;
      medz2=this.particles[tab[i]].getZ()+medz2;
      i++;
      }
      medx2=medx2/(i-tab.length/2-tab.length%2);
      medz2=medz2/(i-tab.length/2-tab.length%2);


    while(!a.equals(a1) && !b.equals(b1)){
      System.out.println("loop");

      a1.clear();
      b1.clear();
      for (int j : a) {
        a1.add(j);
    }
    for (int j : b) {
      b1.add(j);
  }
      a.clear();
      b.clear();

      prevMedx1=medx1;
      prevMedz1=medz1;
      prevMedx2=medx2;
      prevMedz2=medz2;
      System.out.println("prevmedx1: "+prevMedx1+"     prevmedz1: "+prevMedz1);
      System.out.println("prevmedx2: "+prevMedx2+"     prevmedz2: "+prevMedz2);
      medx1=0;
      medz1=0;
      medx2=0;
      medz2=0;
      int k1=0;
      int k2=0;

      for(i=0;i<tab.length;i++){
        double x=this.particles[tab[i]].getX();
        double z= this.particles[tab[i]].getZ();
        if(Math.abs(prevMedx1-x)+Math.abs(prevMedz1-z)<Math.abs(prevMedx2-x)+Math.abs(prevMedz2-z)){
          a.add(tab[i]);
          medx1+=x;
          medz1+=z;
          k1+=1;



        }
        else{
          b.add(tab[i]);
          medx2+=x;
          medz2+=z;
          k2+=1;
        }
      }
      medx1=medx1/k1;
      medz1=medz1/k1;
      medx2=medx2/k2;
      medz2=medz2/k2;
    }
    int[] left = new int[a.size()];
    Iterator<Integer> iteratora = a.iterator();
    for (i = 0; i < left.length; i++)
    {
        left[i] = iteratora.next().intValue();
    }
    int[] right = new int[b.size()];
    Iterator<Integer> iteratorb = b.iterator();
    for (i = 0; i < right.length; i++)
    {
        right[i] = iteratorb.next().intValue();
    }


    int[][] result={left,right};



    return  result;
}


public void checkColision(){

}
}
