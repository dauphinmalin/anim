
import java.util.*;
import java.lang.Math;




public class Collision{
  PrimitiveObject[] objects;
  ObjectTree collisionTree;
  double sizex;
  double sizey;
  double sizez;


  public Collision(PrimitiveObject[] objects,double sizex,double sizey,double sizez){
    this.objects=objects;
    this.sizex=sizex;
    this.sizey=sizey;
    this.sizez=sizez;

  }

  public void calculateForce(){
    this.collisionTree.CalculateForce();



  }

  public ArrayList<Integer[]> checkCollision(int n){
    int[] inside=new int[n];
    double[] min = {0,0,0};
    double[] max = {this.sizex,this.sizey,this.sizez};
    double[] cm = {0,0,0};

    this.collisionTree= new ObjectTree(this.objects,n,min,max,0,cm);
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

  // public ArrayList<Integer[]> checkColID(){
  //   int[] inside=new int[this.objects.length];
  //   double[] min = {0,0,0};
  //   double[] max = {this.sizex,this.sizey,this.sizez};
  //   double[] cm = {0,0,0};
  //
  //   this.collisionTree= this.objects[0].TreeofElements;
  //   this.collisionTree.Cluster();
  //   if(this.collisionTree.elements.length>1){
  //     ArrayList<Integer[]> list=this.collisionTree.checkCollision();
  //     Iterator<Integer[]> iterator = list.iterator();
  //     while (iterator.hasNext()) {
  //       Integer[] tab=iterator.next();
  //       if(tab[0]==-1){iterator.remove();}
  //       else{
  //         for(int i=0;i<tab.length;i++){
  //         }
  //       }
  //     }
  //     return list;
  //   }
  //   else{
  //     return null;
  //   }
  // }

}
