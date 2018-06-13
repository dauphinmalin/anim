class RigidObject extends PrimitiveObject{

  private double[] constraintleft; //constraint on position and rotation for the left son;
  private double[] constraintright;   //constraint on position and angle for right son;
  private PrimitiveObject left;
  private PrimitiveObject right;
public void Draw(){
  left.Draw();
  right.Draw();
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



public RigidObject(PrimitiveObject[] objects,double[] pos,double[] vel,double[] rotation,double[] f,double dt){
  super(0,pos,vel,rotation,f,dt,0,0);

}

private void CreateTree();

public void calculatePosition();

}
