class RigidObject extends PrimitiveObject{

  private double[] constraintleft; //constraint on position and rotation for the left son;
  private double[] constraintright;   //constraint on position and angle for right son;
  private ObjectTree TreeofElements;
  private PrimitiveObject[] elements;
  private constraints[][];
public void Draw(){
  for(int i=0;i<objects.length;i++){
    elements[i].Draw();
  }
}
public RigidObject(PrimitiveObject[] objects,double[] pos,double[] vel,double[] rotation,double[] f,double dt){
  super(0,pos,vel,rotation,f,dt,0,0);
  this.elements=objects.clone();
  
  this.TreeofElements=new ObjectTree(this.particles,min,max,m,cm);

}

public void setPos(double[] position,double rotation){
  super(position,rotation);
  double[] leftpos={0,0,0};
  for(i=0;i<3;i++){
    leftpos[i]=position[i]+
  }
  this.left.setPos();
  this.right.calculatePos();
}





private void CreateTree();

public void calculatePosition();

}
