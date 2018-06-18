

public class Particle extends PrimitiveObject{


  private double radius;//radius to display the particle
  private static double[] posMAX={950,0,650};
  private static double cr=1;
  private static double gcst = 6.67e-3;

  public Particle(double m,double[] pos,double[] vel,double[] rotation,double[] f,double dt,double radius){
    super(m,pos,vel,rotation,f,dt);
    for(int j=0;j<3;j++){

    }
    this.radius=radius;
    this.extremeInf=new double[3];
    this.extremeSup=new double[3];
    for(int j=0;j<3;j++){
      this.extremeInf[j]=pos[j]-this.radius;
      this.extremeSup[j]=pos[j]+this.radius;
    }
  }
    public void Draw(){
  }

  public void calculateBoundingVolume(){
  };


  public double getRadius(){
    return this.radius;
  }



  // public void calculatePos(){
  //   double inter;
  //   for(int i=0;i<3;i++){
  //     inter=2*this.pos[i]-this.previouspos[i]+this.dt*this.dt*this.f[i]/this.m;//Verlet for x,y,z


  //     if(inter>(this.posMAX[i]-this.radius)){
  //       this.vel[i]=-this.cr*(inter-this.pos[i]);
  //       this.previouspos[i]=this.posMAX[i]-this.radius-this.vel[i];
  //       this.pos[i]=this.posMAX[i]-this.radius;
  //     }
  //     else if(inter<this.radius){
  //       this.vel[i]=-this.cr*(inter-this.pos[i]);
  //       this.previouspos[i]=this.radius-this.vel[i];
  //       this.pos[i]=this.radius;
  //     }
  //     else{
  //     this.previouspos[i]=this.pos[i];
  //     this.pos[i]=inter;
  //     this.vel[i]=this.cr*(this.pos[i]-this.previouspos[i]);
  //     }
  //   }
  // }

  public void calculatePos(){
    for(int i=0;i<3;i++){
      this.nextpos[i] = 2*this.pos[i]-this.previouspos[i]+this.dt*this.dt*this.f[i]/this.m;//Verlet for x,y,z
    }
  }
  @Override
  public void borderResponse(){

    for(int i=0;i<3;i++){

      if(this.nextpos[i]>(this.posMAX[i]-this.radius)){
        this.vel[i]=-20000*this.cr*(this.nextpos[i]-this.pos[i]);
        this.previouspos[i]=this.posMAX[i]-this.radius-this.vel[i];
        this.pos[i]=this.posMAX[i]-this.radius;
        this.extremeSup[i]=this.posMAX[i];
      }
      else if(this.nextpos[i]<this.radius){
        this.vel[i]=-20000*this.cr*(this.nextpos[i]-this.pos[i]);
        this.previouspos[i]=this.radius-this.vel[i];
        this.pos[i]=this.radius;
        this.extremeInf[i]=0;
      }
      else{
        this.previouspos[i]=this.pos[i];
        this.pos[i]=this.nextpos[i];
        this.extremeInf[i]=this.pos[i]-this.radius;
        this.extremeSup[i]=this.pos[i]+this.radius;
    //    this.vel[i]=this.cr*(this.pos[i]-this.previouspos[i]);
      }
    }
  }

  public void calculateForce(double m,double[] cm){
    this.f[0]=0;
    this.f[1]=0;
    this.f[2]=0;
    double x = cm[0] - this.pos[0];
    double y = cm[1] - this.pos[1];
    double z = cm[2] - this.pos[2];
    double distance = Math.sqrt(x*x+y*y+z*z);
    if(distance!=0){
      double force = this.gcst*this.m*m/(distance*distance*distance);
      this.f[0] += force*x;
      this.f[1] += force*y;
      this.f[2] += force*z;


    }
  }





}
