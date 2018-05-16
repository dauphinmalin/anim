
import java.util.*;

private class DoubleArray{
  int[] left;
  int[] right;

public DoubleArray(int[] left,int[] right){
  this.left=left;
  this.right=right;
}
}

private class Tree{
  private double x;
  private double z;
  private int[] inside;
  private Tree right;
  private Tree left;



public Tree(int[] inside,x1,z1){
  this.inside=inside;
  this.x1=x;
  this.z1=z;
}

public int[] getInside(){
  return this.inside;
}

public double getX(){
  return this.X;
}

public double getZ(){
  return this.Z;
}
}

public class Collision{
  Particles[] particles;
  Tree collisionTree;


Public Collision(Particles[] particles,sizex,sizez){
  this.particles=particles;
  int[] tab=new int[particles.length];
  for(int i=0;i<particles.length;i++){
    tab[i]=i;
  }
  this.collisionTree=new Tree(tab,sizex,sizez);
}
private DoubleArray Cluster(int[] tab){
    ArrayList<Int> a=new ArrayList<int>();
    ArrayList<Int> b=new ArrayList<Int>();
    ArrayList<Int> b1=new ArrayList<int>();
    ArrayList<Int> b1=new ArrayList<Int>();
    int i;
    double medx1=0;
    double medz1=0;
    double medx2=0;
    double medz2=0;
    double newMedx1=0;
    double newMedz1=0;
    double newMedx2=0;
    double newMedz2=0;
    for(i=0<i<tab.length/2;i++){
      a.add(tab[i]);
      medx1=this.particles[tab[i]].getX()+medx1;
      medz1=this.particles[tab[i]].getZ()+medz1;


    }
    medx1=medx1/i;
    medz1=mezx1/i;
    while(i<tab.length;i++){
      b.add(tab[i]);
      medx2=this.particles[tab[i]].getX()+medx2;
      medz2=this.particles[tab[i]].getZ()+medz2;
      }
      medx1=medx2/(i-tab.length/2-tab.length%2);
      medz1=mezx1/(i-tab.length/2-tab.length%2);


    while(a!=a1 && b !=b1){

      a1.clear();
      b1.clear();
      a1=a.clone();
      b1=a.clone();
      a=a.clear();
      a=a.clear();
      medx1=newMedx1;
      medz1=newMedz1;
      medx2=newMedx2;
      medz2=newMedz2;
      newMedx1=0;
      newMedz1=0;
      newMedx2=0;
      newMedz2=0;
      int k1=0;
      int k2=0;

      for(i=;i<tab.length;i++){
        double x=this.particle[tab[i]].getX();
        double z= this.particle[tab[i]].getZ();
        if(abs(medx1-x)+abs(medz1-z)<abs(medx2-x)+abs(medz2-z)){
          a.add(tab[i]);
          newMedx1+=x;
          newMedz1+=z;
          k1+=1;



        }
        else{
          b.add(tab[i]);
          newMedx2+=x;
          newMed21+=z;
          k2+=1;
        }
      }
      newMedx1=newMedx1/k1;
      newMedz1=newMedz1/k1;
      newMedx2=newMedx2/k2;
      newMedz2=newMedz2/k2;
    }
    Integer[] left = a1.toArray(new Integer[a1.size()]);
    Integer[] right = b1.toArray(new Integer[b1.size()]);
    return new DoubleArray(left,right);
}

private void Recursion(int[] inside){
  int a=int[].length%2;
  int b=int[].length-a;



}
public void checkColision(){

}
}
