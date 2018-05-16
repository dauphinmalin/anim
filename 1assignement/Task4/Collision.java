
import java.util.*;
import java.lang.Math;

class Cluster{
  public int[] elements;
  public double xMin;
  public double zMin;
  public double xMax;
  public double zMax;

Public Cluster(int[] elements,xmin,zmin,xmax,zmax){
  this.elements=elements;
  this.xMin=xmin;
  this.xMax=xmax;
  this.zMin=zmin;
  this.zMax=zmax;
}

}

class Tree{
  Cluster cluster;
  private Tree right;
  private Tree left;
  static Particle[] particles=null;



public Tree(Cluster cluster){
  this.cluster=cluster;
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

public Cluster[] Clustering(int[] tab){
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
    double axmin=1000000000000000000000;
    double bxmin=1000000000000000000000;
    double azmin=1000000000000000000000;
    double bzmin=1000000000000000000000;
    double axmax=0;
    double azmax=0;
    double azmax=0;
    double bzmax=0;
    for(i=0;i<tab.length/2;i++){
      a.add(tab[i]);
      medx1=this.particles[tab[i]].getX()+medx1;
      medz1=this.particles[tab[i]].getZ()+medz1;
      if


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
      axmin=1000000000000000;
      bxmin=1000000000000000;
      azmin=1000000000000000;
      bzmin=1000000000000000;
      axmax=0;
      azmax=0;
      azmax=0;
      bzmax=0;


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
          if{x<axmin}{axmin=x;}
          else if {x>axmax}{axmax=x}
          if{x<azmin}{azmin=z;}
          else if {z>azmax}{azmax=z}



        }
        else{
          b.add(tab[i]);
          medx2+=x;
          medz2+=z;
          k2+=1;
          if{x<bxmin}{bxmin=x;}
          else if {x>bxmax}{bxmax=x}
          if{x<bzmin}{bzmin=z;}
          else if {z>bzmax}{bzmax=z}
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

    Cluster cleft=new Cluster(left,axmin,axmax,azmin,azmax);
    Cluster cleft=new Cluster(left,bxmin,bxmax,bzmin,bzmax);
    Cluster[] result={left,right};



    return  result;
}



public void Recursion(){
  if (this.cluster.elements.length>1){


  Cluster[] result=this.cluster(this.inside);
  this.left=new Tree(Cluster[0]);
  this.right=new Tree(Cluster[1]);
  this.left.Recursion();
  this.right.Recursion();
  }









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



public void checkColision(){
  int[] inside=new int[this.particles.length];
  for(int i=0;i<this.particles.length;i++){
    inside[i]=i;
  }
  Cluster cluster=new Cluster(inside,0,0,this.sizex,this.sizez);
  Tree tree= new Tree(cluster);
  tree.Collision();

}
}
