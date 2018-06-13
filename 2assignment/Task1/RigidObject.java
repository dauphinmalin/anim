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

  }

  public void Draw(){
    for(int i=1;i<this.elements.length;i++){
      elements[i].Draw();
    }
  }

  public void calculateBoundingVolume(){
    for(int j=0;j<this.elements[i].lenght;j++){
      this.extremeInf[j]=this.elements[0].extremeInf[j];
      this.extremeSup[j]=this.elements[0].extremeSup[j];
    }
    for(int i=1;i<this.elements.length;i++){
      for(int j=0;j<this.elements[i].lenght;j++){
        if(this.elements[i].extremeInf[j]<this.extremeInf[j]){
          this.extremeInf[j]=this.elements[i].extremeInf[j];
        }
        if(this.elements[i].extremeSup[j]>this.extremeSup[j]){
          this.extremeSup[j]=this.elements[i].extremeSup[j];
        }
      }
    }
  }






  private void CreateTree();

  public void calculatePosition();

}
