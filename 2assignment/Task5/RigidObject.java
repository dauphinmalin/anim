class RigidObjectTree{
  Volume volume;
  double[] speed;
  double[] force;s
  double[] constraintPosition; //constraint on position for the left and right sons;
  double[] constraintAngle;   //constraint on angle for the left and right sons;
  RigidObject left;
  RigidObject right;



}


public class RigidObject{
  double[] position;
  Volume volume;
  double[] speed;
  double[] rotation;
  double[] force;
  RigidObjectTree volumes;

public RigidObject(PrimitiveObject[] objects,double[] speed,double[] rotation,double[] force){

}

private void CreateTree();

public void calculatePosition();

}
