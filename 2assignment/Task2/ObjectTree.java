import java.util.*;
import java.lang.Math;


class Cluster{
  public int[] elements;
  public double xMin;
  public double zMin;
  public double xMax;
  public double zMax;
  double m;
  double cmx;
  double cmz;


  public Cluster(int[] elements,double xmin,double xmax,double zmin,double zmax,double m,double cxm,double czm){
    this.elements=elements;
    this.xMin=xmin;
    this.xMax=xmax;
    this.zMin=zmin;
    this.zMax=zmax;
    this.m=m;
    this.cmx=cxm;
    this.cmz=czm;
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

class Node{
  Cluster cluster;
  Node right;
  Node left;
  boolean checked;
  PrimitiveObject[] elements;


  public Node(PrimitiveObject[] elements,Cluster cluster){
    this.cluster=cluster;
    this.left=null;
    this.right=null;
    this.checked=false;
    this.elements=elements;
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
    double cmxa=0;
    double cmxb=0;
    double cmzb=0;
    double cmza=0;
    double ma=0;
    double mb=0;

    for(i=0;i<tab.length/2;i++){
      a.add(tab[i]);
      medx1=this.elements[tab[i]].getX()+medx1;
      medz1=this.elements[tab[i]].getZ()+medz1;
    }
    medx1=medx1/i;
    medz1=medz1/i;
    while(i<tab.length){
      b.add(tab[i]);
      medx2=this.elements[tab[i]].getX()+medx2;
      medz2=this.elements[tab[i]].getZ()+medz2;
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
      cmxa=0;
      cmxb=0;
      cmzb=0;
      cmza=0;
      ma=0;
      mb=0;
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

        cmxa+=this.elements[tab[0]].getX();
        cmza+=this.elements[tab[0]].getZ();
        ma+=this.elements[tab[0]].getM();
        medx1+=this.elements[tab[0]].getX();
        medz1+=this.elements[tab[0]].getZ();

        k1+=1;
        double width=this.elements[tab[0]].getWidth();
        double height=this.elements[tab[0]].getHeight();
        if(this.elements[tab[0]].getX()+width>axmax){axmax=this.elements[tab[0]].getX()+width;}
        if(this.elements[tab[0]].getX()-width<axmin){axmin=this.elements[tab[0]].getX()-width;}
        if(this.elements[tab[0]].getZ()-height<azmin){azmin=this.elements[tab[0]].getZ()-height;}
        if(this.elements[tab[0]].getZ()+height>azmax){azmax=this.elements[tab[0]].getZ()+height;}

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
        double x=this.elements[tab[i]].getX();
        double z= this.elements[tab[i]].getZ();
        double width=this.elements[tab[i]].getWidth();
        double height=this.elements[tab[i]].getHeight();
        double m=this.elements[tab[i]].getM();
        if(Math.pow(prevMedx1-x,2)+Math.pow(prevMedz1-z,2)< Math.pow(prevMedx2-x,2)+Math.pow(prevMedz2-z,2)){
          a.add(tab[i]);
          medx1+=x;
          medz1+=z;
          k1+=1;

          cmxa=(cmxa*ma+m*x)/(ma+m);
          cmza=(cmza*ma+m*z)/(ma+m);
          ma+=m;

          if(x-width<axmin){axmin=x-width;}
          if(x+width>axmax){axmax=x+width;}
          if(z-height<azmin){azmin=z-height;}
          if(z+height>azmax){azmax=z+height;}
        }
        else{
          b.add(tab[i]);
          medx2+=x;
          medz2+=z;
          k2+=1;
          cmxb=(cmxb*mb+m*x)/(mb+m);
          cmzb=(cmzb*mb+m*z)/(mb+m);
          mb+=m;
          if(x-width<bxmin){bxmin=x-width;}
          if(x+width>bxmax){bxmax=x+width;}
          if(z-height<bzmin){bzmin=z-height;}
          if (z+height>bzmax){bzmax=z+height;}
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
    Cluster cleft=new Cluster(left,axmin,axmax,azmin,azmax,ma,cmxa,cmza);
    Cluster cright=new Cluster(right,bxmin,bxmax,bzmin,bzmax,mb,cmxb,cmzb);
    Cluster[] result={cleft,cright};

    return  result;
  }


  public void RecursionCluster(){
    if (this.cluster.elements.length>1){
      Cluster[] result=this.Clustering(this.cluster.elements);
      this.left=new Node(this.elements,result[0]);
      this.right=new Node(this.elements,result[1]);
      this.left.RecursionCluster();
      this.right.RecursionCluster();
    }
  }


  public ArrayList<Integer[]> RecursionChecklower(Node bigger){
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


public void calculateForceNode(double m,double cmx,double cmz){
  if(this.cluster.elements.length>1){
    double m1=this.right.cluster.m;
    this.left.calculateForceNode(m+m1,(cmx*m+m1*this.right.cluster.cmx)/(m+m1),(cmz*m+m1*this.right.cluster.cmz)/(m+m1));
    m1=this.left.cluster.m;
    this.right.calculateForceNode(m+m1,(cmx*m+m1*this.left.cluster.cmx)/(m+m1),(cmz*m+m1*this.left.cluster.cmz)/(m+m1));

  }
  else{
    //System.out.println(m);
    this.elements[this.cluster.elements[0]].calculateForce(m,cmx,cmz);

  }
}
}
public class ObjectTree{
  Node tree;
  protected PrimitiveObject[] elements;


  public ObjectTree(PrimitiveObject[] elements,int xmin,double zmin,double xmax,double zmax,double m ,double cmx,double cmz){
    int[] inside=new int[elements.length];
    for(int i=0;i<elements.length;i++){
      inside[i]=i;
    }
  this.elements=elements.clone();
  Cluster cluster=new Cluster(inside,xmin,xmax,zmin,zmax,m,cmx,cmz);
  this.tree=new Node(this.elements,cluster);
}



  public PrimitiveObject[] getElements(){
    return this.elements;
  }

  public void calculateForceObjectTree(double x,double y,double z){
    this.tree.calculateForceNode(x,y,z);
  }

  ArrayList<Integer[]> checkCollision(){
    return this.tree.RecursionCheck();
  }
  public void Cluster(){
    this.tree.RecursionCluster();
  }



}
