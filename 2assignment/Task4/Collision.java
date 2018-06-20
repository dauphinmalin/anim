
import java.util.*;
import java.lang.Math;




public class Collision{
  PrimitiveObject[] objects;
  ObjectTree collisionTree;
  double sizex;
  double sizey;
  double sizez;


  public Collision(PrimitiveObject[] objects,double sizex,double sizey,double sizez){
    this.objects=objects.clone();
    this.sizex=sizex;
    this.sizey=sizey;
    this.sizez=sizez;
    int[] tab=new int[objects.length];
    for(int i=0;i<objects.length;i++){
      tab[i]=i;
    }
  }

  public void calculateForce(){
    double[] cm = {0,0,0};
    this.collisionTree.calculateForceObjectTree(0,cm);



  }

  public ArrayList<Integer[]> checkCollision(){
    int[] inside=new int[this.objects.length];
    double[] min = {0,0,0};
    double[] max = {this.sizex,this.sizey,this.sizez};
    double[] cm = {0,0,0};

    this.collisionTree= new ObjectTree(this.objects,min,max,0,cm);
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
