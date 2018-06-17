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
  public RigidObject(PrimitiveObject[] objects,double[] vel,double[] rotation,double[] f,double dt){
    super(0,pos,vel,rotation,f,dt,0,0);
    this.elements=objects.clone();
    int i=0;
    for(int i=0;i<this.elements.length){
      for(int j=0;j<this.elements[i].pos.lenght;j++){
        this.pos[j]+=(this.elements[i].m*this.elements[i].pos[j]+this.pos[j]*this.m)/(this.m+this.elements[i].m);
        this.m=this.m+this.elements[i].m;
      }
    }
    this.calculateBoundingVolume();
    this.TreeofElements=new ObjectTree(this.particles,min,max,m,cm);
    this.TreeofElements.Cluster();

  }

  public void Draw(){
    for(int i=1;i<this.elements.length;i++){
      elements[i].Draw();
    }
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
