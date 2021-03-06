
import java.util.*;
import java.lang.Math;

class Cluster{
  public int[] elements;
  public double xMin;
  public double zMin;
  public double xMax;
  public double zMax;


  public Cluster(int[] elements,double xmin,double xmax,double zmin,double zmax){
    this.elements=elements;
    this.xMin=xmin;
    this.xMax=xmax;
    this.zMin=zmin;
    this.zMax=zmax;
  }


  public void Show(){

    for(int i=0;i<elements.length;i++){


    }
  }


  public boolean Col(Cluster c){
    double xmin=this.xMin+c.xMin-c.xMax;
    double xmax=this.xMax;
    double zmin=this.zMin+c.zMin-c.zMax;
    double zmax=this.zMax;
    if(xmin<=c.xMin && c.xMin<=xmax){
      if(zmin<=c.zMin && c.zMin<=zmax){
        return true;

      }
      else{
        return false;
      }
    }
    else{
      return false;
    }
  }
}


class Tree{
  Cluster cluster;
  Tree right;
  Tree left;
  boolean checked;
  private static Particle[] particles=null;


  public Tree(Cluster cluster){
    this.cluster=cluster;
    this.left=null;
    this.right=null;
    this.checked=false;
  }


  public void setParticles(Particle[] particles){
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
    double bxmin=100000000000000.0;
    double azmin=100000000000000.0;
    double axmin=100000000000000.0;
    double bzmin=100000000000000.0;
    double axmax=-11111.000;
    double azmax=-11111.000;
    double bxmax=-11111.000;
    double bzmax=-11111.000;
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

    medx2=medx2/(i-tab.length/2);
    medz2=medz2/(i-tab.length/2);
    while(!a.equals(a1) && !b.equals(b1)){

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
      axmin=1000000000000.0;
      bxmin=1000000000000.0;
      azmin=1000000000000.0;
      bzmin=1000000000000.0;
      axmax=-1111111111;
      azmax=-1111111111;
      azmax=-1111111111;
      bzmax=-1111111111;
      i=0;
      int k1=0;
      int k2=0;
      if(medx1==medx2 && medz1==medz2){
        i=1;
        a.add(tab[0]);
        medx1=0;
        medz1=0;
        medx1+=this.particles[tab[0]].getX();;
        medz1+=this.particles[tab[0]].getZ();;
        k1+=1;
        double radius=this.particles[tab[0]].getRadius();
        if(this.particles[tab[0]].getX()+radius>axmax){axmax=this.particles[tab[0]].getX()+radius;}
        if(this.particles[tab[0]].getX()-radius<axmin){axmin=this.particles[tab[0]].getX()-radius;}
        if(this.particles[tab[0]].getZ()-radius<azmin){azmin=this.particles[tab[0]].getZ()-radius;}
        if(this.particles[tab[0]].getZ()+radius>azmax){azmax=this.particles[tab[0]].getZ()+radius;}

        medx2=0;
        medz2=0;

      }
      else{
      medx1=0;
      medz1=0;
      medx2=0;
      medz2=0;
    }


      for(i=i;i<tab.length;i++){
        double x=this.particles[tab[i]].getX();
        double z= this.particles[tab[i]].getZ();
        double radius=this.particles[tab[i]].getRadius();
        if(Math.pow(prevMedx1-x,2)+Math.pow(prevMedz1-z,2)< Math.pow(prevMedx2-x,2)+Math.pow(prevMedz2-z,2)){
          a.add(tab[i]);
          medx1+=x;
          medz1+=z;
          k1+=1;
          if(x-radius<axmin){axmin=x-radius;}
          if(x+radius>axmax){axmax=x+radius;}
          if(z-radius<azmin){azmin=z-radius;}
          if(z+radius>azmax){azmax=z+radius;}
        }
        else{
          b.add(tab[i]);
          medx2+=x;
          medz2+=z;
          k2+=1;
          if(x-radius<bxmin){bxmin=x-radius;}
          if(x+radius>bxmax){bxmax=x+radius;}
          if(z-radius<bzmin){bzmin=z-radius;}
          if (z+radius>bzmax){bzmax=z+radius;}
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
    Cluster cright=new Cluster(right,bxmin,bxmax,bzmin,bzmax);
    Cluster[] result={cleft,cright};

    return  result;
  }


  public void RecursionCluster(){
    if (this.cluster.elements.length>1){
      Cluster[] result=this.Clustering(this.cluster.elements);
      this.left=new Tree(result[0]);
      this.right=new Tree(result[1]);
      this.left.RecursionCluster();
      this.right.RecursionCluster();
    }
  }


  public ArrayList<Integer[]> RecursionChecklower(Tree bigger){
    boolean col=this.cluster.Col(bigger.cluster);
    ArrayList<Integer[]> list=new ArrayList<Integer[]>();
    if(col){
      if(this.cluster.elements.length>1){
        list.addAll(bigger.RecursionChecklower(this.left));
        list.addAll(bigger.RecursionChecklower(this.right));
      }
      else if(bigger.cluster.elements.length>1){
        list.addAll(this.RecursionChecklower(bigger.left));
        list.addAll(this.RecursionChecklower(bigger.right));
      }
      else{
        Integer[] tab={this.cluster.elements[0],bigger.cluster.elements[0]};
        list.add(tab);
      }
    }
    return list;
  }


  public ArrayList<Integer[]> RecursionCheck(){
    ArrayList<Integer[]> list=new ArrayList<Integer[]>();
    if(!this.checked){
      this.checked=true;
      boolean col=false;
      if(this.cluster.elements.length>1){
        list.addAll(this.left.RecursionCheck());
        list.addAll(this.right.RecursionCheck());
        col=this.left.cluster.Col(this.right.cluster);
        if (col){
          if(this.right.cluster.elements.length>1){
            list.addAll(this.left.RecursionChecklower(this.right.left));
            list.addAll(this.left.RecursionChecklower(this.right.right));
          }
          else if(this.left.cluster.elements.length>1){
            list.addAll(this.right.RecursionChecklower(this.left.left));
            list.addAll(this.right.RecursionChecklower(this.left.right));
          }
          else{
            Integer[] tab={this.left.cluster.elements[0],this.right.cluster.elements[0]};
            list.add(tab);

          }
        }
      }
    }
    return list;
  }
}




public class Collision{
  Particle[] particles;
  Tree collisionTree;
  double sizex;
  double sizez;


  public Collision(Particle[] particles,double sizex,double sizez){
    this.particles=particles;
    this.sizex=sizex;
    this.sizez=sizez;
    int[] tab=new int[particles.length];
    for(int i=0;i<particles.length;i++){
      tab[i]=i;
    }
  }


  public ArrayList<Integer[]> checkCollision(){
    int[] inside=new int[this.particles.length];
    for(int i=0;i<this.particles.length;i++){
      inside[i]=i;
    }
    Cluster cluster=new Cluster(inside,0,0,this.sizex,this.sizez);
    this.collisionTree= new Tree(cluster);
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
