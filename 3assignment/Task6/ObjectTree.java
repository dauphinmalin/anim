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

  public Cluster[] Clustering(int[] tab ){

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
    int nb=tab.length/2;
    med1[0]=med1[0]/nb;
    med1[1]=med1[1]/nb;
    med1[2]=med1[2]/nb;
    while(i<tab.length){
      b.add(tab[i]);
      med2[0]=this.elements[tab[i]].getX()+med2[0];
      med2[1]=this.elements[tab[i]].getY()+med2[1];
      med2[2]=this.elements[tab[i]].getZ()+med2[2];


      i++;
    }

    nb=nb+tab.length%2;

    med2[0]=med2[0]/nb;
    med2[1]=med2[1]/nb;
    med2[2]=med2[2]/nb;
    boolean bool=false;
    while(!a.equals(a1) && !b.equals(b1) && !bool){


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
      if(a1.isEmpty() || b1.isEmpty()){
        bool = true;

        a.add(tab[0]);

        for(int ii=0;ii<3;ii++){
          med1[ii] = 0;
        }

        cma[0]+=this.elements[tab[0]].getX();
        cma[1]+=this.elements[tab[0]].getY();
        cma[2]+=this.elements[tab[0]].getZ();
        ma+=this.elements[tab[0]].getM();
        med1[0]=this.elements[tab[0]].getX();
        med1[1]=this.elements[tab[0]].getY();
        med1[2]=this.elements[tab[0]].getZ();

        k1+=1;
        double[] extremeInf=this.elements[tab[0]].getExtremeInf();
        double[] extremeSup=this.elements[tab[0]].getExtremeSup();
        for(int j=0;j<extremeInf.length;j++){
          if(extremeSup[j]>amax[j]){amax[j]=extremeSup[j];}
          if(extremeInf[j]<amin[j]){amin[j]=extremeInf[j];}
        }
        for(int j=0;j<3;j++){
          med2[j] = 0;
        }
        for(i=1;i<tab.length;i++){
          double x=this.elements[tab[i]].getX();
          double y=this.elements[tab[i]].getY();
          double z= this.elements[tab[i]].getZ();
          extremeInf=this.elements[tab[i]].getExtremeInf();
          extremeSup=this.elements[tab[i]].getExtremeSup();
          double m=this.elements[tab[i]].getM();
          b.add(tab[i]);
          med2[0]+=x;
          med2[1]+=y;
          med2[2]+=z;
          k2+=1;
          cmb[0]=(cmb[0]*mb+m*x)/(mb+m);
          cmb[1]=(cmb[1]*mb+m*y)/(mb+m);
          cmb[2]=(cmb[2]*mb+m*z)/(mb+m);
          mb+=m;
          for(int j=0;j<extremeInf.length;j++){
            if(extremeSup[j]>bmax[j]){bmax[j]=extremeSup[j];}
            if(extremeInf[j]<bmin[j]){bmin[j]=extremeInf[j];}
          }

        }

      }
      else{
        for(int jj=0;jj<3;jj++){
          med1[jj] = 0;
          med2[jj] = 0;
        }



      for(i=0;i<tab.length;i++){
        double x=this.elements[tab[i]].getX();
        double y=this.elements[tab[i]].getY();
        double z= this.elements[tab[i]].getZ();
        double[] extremeInf=this.elements[tab[i]].getExtremeInf();
        double[] extremeSup=this.elements[tab[i]].getExtremeSup();
        double m=this.elements[tab[i]].getM();
        if(Math.pow(prevMed1[0]-x,2)+Math.pow(prevMed1[1]-y,2)+Math.pow(prevMed1[2]-z,2)< Math.pow(prevMed2[0]-x,2)+Math.pow(prevMed2[1]-y,2)+Math.pow(prevMed2[2]-z,2)){
          a.add(tab[i]);


          med1[0]+=x;
          med1[1]+=y;
          med1[2]+=z;
          k1+=1;

          cma[0]=(cma[0]*ma+m*x)/(ma+m);
          cma[1]=(cma[1]*ma+m*y)/(ma+m);
          cma[2]=(cma[2]*ma+m*z)/(ma+m);
          ma+=m;

          for(int j=0;j<extremeInf.length;j++){
            if(extremeSup[j]>amax[j]){amax[j]=extremeSup[j];}
            if(extremeInf[j]<amin[j]){amin[j]=extremeInf[j];}
          }
        }
        else{

          b.add(tab[i]);
          med2[0]+=x;
          med2[1]+=y;
          med2[2]+=z;
          k2+=1;
          cmb[0]=(cmb[0]*mb+m*x)/(mb+m);
          cmb[1]=(cmb[1]*mb+m*y)/(mb+m);
          cmb[2]=(cmb[2]*mb+m*z)/(mb+m);
          mb+=m;
          for(int j=0;j<extremeInf.length;j++){
            if(extremeSup[j]>bmax[j]){bmax[j]=extremeSup[j];}
            if(extremeInf[j]<bmin[j]){bmin[j]=extremeInf[j];}
          }
        }
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


  public void calculateForceNode(){

    if(this.cluster.elements.length<60){
      double[] density=new double[this.cluster.elements.length];
      double[] pressure=new double[this.cluster.elements.length];

      for(int i=0;i<density.length;i++){
        density[i]=0;
        for(int j=0;j<density.length;j++){
          double r=Math.sqrt(Math.pow(this.elements[this.cluster.elements[i]].getX()-this.elements[this.cluster.elements[j]].getX(),2)+Math.pow(this.elements[this.cluster.elements[i]].getY()-this.elements[this.cluster.elements[j]].getY(),2)+Math.pow(this.elements[this.cluster.elements[i]].getZ()-this.elements[this.cluster.elements[j]].getZ(),2));
          double K;
          if(r>PrimitiveObject.h){K=0;}
          else{
           K=315/64/Math.PI/Math.pow(PrimitiveObject.h,9)*Math.pow(PrimitiveObject.h*PrimitiveObject.h-r*r,3);
          }
          density[i]+=this.elements[cluster.elements[j]].m*K;
        }
        pressure[i]=PrimitiveObject.p0*PrimitiveObject.cs/7*(Math.pow(density[i]/PrimitiveObject.p0,7)-1);
      }
      for(int i=0;i<density.length;i++){
        double[] viscosity={0,0,0};
        double[] pressureforce={0,0,0};
        for(int j=0;j<density.length;j++){
          double[] vectR={this.elements[this.cluster.elements[i]].getX()-this.elements[this.cluster.elements[j]].getX(),this.elements[this.cluster.elements[i]].getY()-this.elements[this.cluster.elements[j]].getY(),this.elements[this.cluster.elements[i]].getZ()-this.elements[this.cluster.elements[j]].getZ()};
          double r=Math.sqrt(Math.pow(vectR[0],2)+Math.pow(vectR[0],2)+Math.pow(vectR[0],2));
          double visc=-45/Math.PI/Math.pow(PrimitiveObject.h,6)*(PrimitiveObject.h-r);
          if(r<PrimitiveObject.h){
          for(int k=0;k<3;k++){
          pressureforce[k]+=-45/Math.PI/Math.pow(PrimitiveObject.h,6)*Math.pow(PrimitiveObject.h-r,2)*vectR[k];
          viscosity[k]+=visc*vectR[k];
        }}

        }
        for(int k=0;k<3;k++){
        //  System.out.println("pressureforce  "+k+"   :   "+pressureforce[k]);
        //  System.out.println("viscosity  "+k+"   :   "+viscosity[k]);

          this.elements[this.cluster.elements[i]].vel[k]+=this.elements[this.cluster.elements[i]].dt*(pressureforce[k]+viscosity[k]);

        }
      }

    }
    else{

      //System.out.println(m);
      this.left.calculateForceNode();
      this.right.calculateForceNode();
    }
  }
}
public class ObjectTree{
  Node tree;
  protected PrimitiveObject[] elements;
  int[] inside;
  double m;
  double[] cm;
  double[] max;
  double[] min;

  public ObjectTree(PrimitiveObject[] elements,int n,double[] min,double[] max,double m ,double[] cm){
    this.inside=new int[n];
    for(int i=0;i<n;i++){
      inside[i]=i;
    }
    this.elements=elements;
    this.m=m;
    this.cm=cm;
    this.min=min;
    this.max=max;
    Cluster cluster=new Cluster(inside,min,max,m,cm);
    this.tree=new Node(this.elements,cluster);
  }

public void CalculateForce(){
  this.tree.calculateForceNode();
}

  public PrimitiveObject[] getElements(){
    return this.elements;
  }

  public void calculateForceObjectTree(double m,double[] cm){
  }

  ArrayList<Integer[]> checkCollision(){
    return this.tree.RecursionCheck();
  }
  public void Cluster(){

    Cluster cluster=new Cluster(inside,min,max,m,cm);
    this.tree=new Node(this.elements,cluster);
    this.tree.RecursionCluster();

   }



}
