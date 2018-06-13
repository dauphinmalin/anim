import java.util.*;
import java.lang.Math;



class Cluster{
  public int[] elements;
  public double[] Min;
  public double[] Max;
  double m;
  double[] cm;


  public Cluster(int[] elements,double[] Min, double[] Max, double m, double[] cm){
    this.elements=elements;
    this.Min=new double[3];
    this.Max=new double[3];
    this.cm=new double[3];

    for(int i=0;i<3;i++){
      this.Min[i]=Min[i];
      this.Max[i]=Max[i];
      this.cm[i]=cm[i];
    }
    this.m=m;

  }


  // public void Show(){
  //
  //   for(int i=0;i<elements.length;i++){
  //
  //
  //   }
  // }


  public boolean Col(Cluster c){
    double xmin=this.Min[0]+c.Min[0]-c.Max[0];
    double xmax=this.Max[0];
    double ymin=this.Min[1]+c.Min[1]-c.Max[1];
    double ymax=this.Max[1];
    double zmin=this.Min[2]+c.Min[2]-c.Max[2];
    double zmax=this.Max[2];
    if(xmin<=c.Min[0] && c.Min[0]<=xmax){
      if(ymin<=c.Min[1] && c.Min[1]<=ymax){
        if(zmin<=c.Min[2] && c.Min[2]<=zmax){
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
    double[] med1={0.0,0.0,0.0};
    double[] med2={0.0,0.0,0.0};
    double[] prevMed1={0.0,0.0,0.0};
    double[] prevMed2={0.0,0.0,0.0};
    double[] bmin={100000000000000.0,100000000000000.0,100000000000000.0};
    double[] amin={100000000000000.0,100000000000000.0,100000000000000.0};
    double[] bmax={-11111.000,-11111.000,-11111.000};
    double[] amax={-11111.000,-11111.000,-11111.000};
    double[] cma={0.0, 0.0, 0.0};
    double[] cmb={0.0, 0.0, 0.0};
    double ma=0;
    double mb=0;

    for(i=0;i<tab.length/2;i++){
      a.add(tab[i]);
      med1[0]=this.elements[tab[i]].getX()+med1[0];
      med1[1]=this.elements[tab[i]].getY()+med1[1];
      med1[2]=this.elements[tab[i]].getZ()+med1[2];
    }
    med1[0]=med1[0]/i;
    med1[1]=med1[1]/i;
    med1[2]=med1[2]/i;
    while(i<tab.length){
      b.add(tab[i]);
      med2[0]=this.elements[tab[i]].getX()+med2[0];
      med2[1]=this.elements[tab[i]].getY()+med2[1];
      med2[2]=this.elements[tab[i]].getZ()+med2[2];
      i++;
    }

    med2[0]=med2[0]/(i-tab.length/2);
    med2[1]=med2[1]/(i-tab.length/2);
    med2[2]=med2[2]/(i-tab.length/2);
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
      prevMed1[0]=med1[0];
      prevMed1[1]=med1[1];
      prevMed1[2]=med1[2];
      prevMed2[0]=med2[0];
      prevMed2[1]=med2[1];
      prevMed2[2]=med2[2];
      for(int k=0;k<3;k++){
        amin[k] = 1000000000000.0;
        bmin[k] = 1000000000000.0;
        cma[k] = 0.0;
        cmb[k] = 0.0;
        amax[k] = -1111111111;
        bmax[k] = -1111111111;
      }
      ma=0;
      mb=0;
      i=0;
      int k1=0;
      int k2=0;
      if(med1[0]==med2[0] && med1[1]==med2[1] && med1[2]==med2[2]){
        i=1;
        a.add(tab[0]);

        for(int ii=0;ii<3;ii++){
          med1[ii] = 0;
        }

        cma[0]+=this.elements[tab[0]].getX();
        cma[1]+=this.elements[tab[0]].getY();
        cma[2]+=this.elements[tab[0]].getZ();
        ma+=this.elements[tab[0]].getM();
        med1[0]+=this.elements[tab[0]].getX();
        med1[1]+=this.elements[tab[0]].getY();
        med1[2]+=this.elements[tab[0]].getZ();

        k1+=1;
        double[] length=this.elements[tab[0]].getLength();
        double[] width=this.elements[tab[0]].getWidth();
        double[] height=this.elements[tab[0]].getHeight();
        if(this.elements[tab[0]].getX()+length[0]>amax[0]){amax[0]=this.elements[tab[0]].getX()+length[0];}
        if(this.elements[tab[0]].getX()+length[1]<amin[0]){amin[0]=this.elements[tab[0]].getX()+length[1];}
        if(this.elements[tab[0]].getY()+width[0]>amax[1]){amax[1]=this.elements[tab[0]].getX()+width[0];}
        if(this.elements[tab[0]].getY()+width[1]<amin[1]){amin[1]=this.elements[tab[0]].getX()+width[1];}
        if(this.elements[tab[0]].getZ()+height[1]<amin[2]){amin[2]=this.elements[tab[0]].getZ()+height[1];}
        if(this.elements[tab[0]].getZ()+height[0]>amax[2]){amax[2]=this.elements[tab[0]].getZ()+height[0];}

        for(int j=0;j<3;j++){
          med2[j] = 0;
        }

      }
      else{
        for(int jj=0;jj<3;jj++){
          med1[jj] = 0;
          med2[jj] = 0;
        }
    }


      for(i=i;i<tab.length;i++){
        double x=this.elements[tab[i]].getX();
        double y=this.elements[tab[i]].getY();
        double z= this.elements[tab[i]].getZ();
        double[] length=this.elements[tab[i]].getLength();
        double[] width=this.elements[tab[i]].getWidth();
        double[] height=this.elements[tab[i]].getHeight();
        double m=this.elements[tab[i]].getM();
        if(Math.pow(prevMed1[0]-x,2)+Math.pow(prevMed1[1]-y,2)+Math.pow(prevMed1[2]-z,2)< Math.pow(prevMed2[0]-x,2)+Math.pow(prevMed2[1]-y,2)+Math.pow(prevMed2[2]-z,2)){
          a.add(tab[i]);
          med1[0]+=x;
          med1[1]+=y;
          med1[2]+=z;
          k1+=1;

          cma[0]=(cma[0]*ma+m*x)/(ma+m);
          cma[1]=(cma[1]*ma+m*x)/(ma+m);
          cma[2]=(cma[2]*ma+m*z)/(ma+m);
          ma+=m;

          if(x+length[0]<amin[0]){amin[0]=x+length[0];}
          if(x+length[1]>amax[0]){amax[0]=x+length[1];}
          if(y+width[0]<amin[1]){amin[1]=y+width[0];}
          if(y+width[1]>amax[1]){amax[1]=y+width[1];}
          if(z+height[0]<amin[2]){amin[2]=z+height[0];}
          if(z+height[1]>amax[2]){amax[2]=z+height[1];}
        }
        else{
          b.add(tab[i]);
          med2[0]+=x;
          med2[1]+=y;
          med2[2]+=z;
          k2+=1;
          cmb[0]=(cmb[0]*mb+m*x)/(mb+m);
          cmb[1]=(cmb[1]*mb+m*x)/(mb+m);
          cmb[2]=(cmb[2]*mb+m*z)/(mb+m);
          mb+=m;
          if(x+length[0]<bmin[0]){bmin[0]=x+length[0];}
          if(x+length[1]>bmax[0]){bmax[0]=x+length[1];}
          if(y+width[0]<bmin[1]){bmin[1]=y+width[0];}
          if(y+width[1]>bmax[1]){bmax[1]=y+width[1];}
          if(z+height[0]<bmin[2]){bmin[2]=z+height[0];}
          if(z+height[1]>bmax[2]){bmax[2]=z+height[1];}
        }
      }
      med1[0]=med1[0]/k1;
      med1[1]=med1[1]/k1;
      med1[2]=med1[2]/k1;
      med2[0]=med2[0]/k2;
      med2[1]=med2[1]/k2;
      med2[2]=med2[2]/k2;
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
    Cluster cleft=new Cluster(left,amin,amax,ma,cma);
    Cluster cright=new Cluster(right,bmin,bmax,mb,cmb);
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


public void calculateForceNode(double m,double[] cm){
  if(this.cluster.elements.length>1){
    double m1=this.right.cluster.m;
    double[] cm1 = {(cm[0]*m+m1*this.right.cluster.cm[0])/(m+m1), (cm[1]*m+m1*this.right.cluster.cm[1])/(m+m1), (cm[2]*m+m1*this.right.cluster.cm[2])/(m+m1)};
    this.left.calculateForceNode(m+m1,cm1);
    m1=this.left.cluster.m;
    double[] cm2 = {(cm[0]*m+m1*this.left.cluster.cm[0])/(m+m1), (cm[1]*m+m1*this.left.cluster.cm[1])/(m+m1), (cm[2]*m+m1*this.left.cluster.cm[2])/(m+m1)};
    this.right.calculateForceNode(m+m1,cm2);

  }
  else{
    //System.out.println(m);
    this.elements[this.cluster.elements[0]].calculateForce(m,cm);

  }
}
}
public class ObjectTree{
  Node tree;
  protected PrimitiveObject[] elements;


  public ObjectTree(PrimitiveObject[] elements,double[] min,double[] max,double m ,double[] cm){
    int[] inside=new int[elements.length];
    for(int i=0;i<elements.length;i++){
      inside[i]=i;
    }
  this.elements=elements.clone();
  Cluster cluster=new Cluster(inside,min,max,m,cm);
  this.tree=new Node(this.elements,cluster);
}



  public PrimitiveObject[] getElements(){
    return this.elements;
  }

  public void calculateForceObjectTree(double m,double[] cm){
    this.tree.calculateForceNode(m,cm);
  }

  ArrayList<Integer[]> checkCollision(){
    return this.tree.RecursionCheck();
  }
  public void Cluster(){
    this.tree.RecursionCluster();
  }



}
