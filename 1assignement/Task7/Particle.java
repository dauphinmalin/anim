

public class Particle{

  private double m;//not equal to 0!!!
  private double[] pos;//X,Y,Z
  private double[] previouspos;
  private double[] nextpos;
  private double[] vel;
  private double[] f;//force on X,force Y,force Z
  private double dt;
  private double radius;//radius to display the particle
  private double[] rpos;//center of orbit
  private static double[] posMAX={0,0,0.65};
  private static double[] posMIN={0,0,0.65};
  private static double[] posDown={0,0,4};
  private static double cr=1;
  private static double r = 3.35;//radius of orbit


  public Particle(double m,double[] pos,double[] vel,double[] f,double dt,double radius,double[] rpos){
    if(m>0){
      this.m=m;
      this.dt=dt;
      this.previouspos=new double[3];
      this.nextpos=new double[3];
      this.pos=pos.clone();
      this.rpos=rpos.clone();
      // this.rpos=rpos.clone();
      for(int i=0;i<pos.length;i++){
        this.previouspos[i]=pos[i]-vel[i]*dt;
        this.nextpos[i] = 2*pos[i]-pos[i]-vel[i]*dt+dt*dt*f[i]/m;
      }
      this.vel=vel.clone();
      this.f=f.clone();
      this.radius=radius;


    }
    //else throw "masse equal to 0!!"
  }

  public double getX(){
    return this.pos[0];
  }

  public double getY(){
    return this.pos[1];
  }

  public double getZ(){
    return this.pos[2];
  }

  public double getpX(){
    return this.previouspos[0];
  }

  public double getpY(){
    return this.previouspos[1];
  }

  public double getpZ(){
    return this.previouspos[2];
  }

  public double getRadius(){
    return this.radius;
  }

  public double[] getVel(){
    return this.vel;
  }

  public double[] getRPos(){
    return this.rpos;
  }

  public void setVel(double[] vel){
    this.vel = vel;
  }

  public void setX(double x){
    this.pos[0] = x;
  }

  public void setY(double y){
    this.pos[1] = y;
  }

  public void setZ(double z){
    this.pos[2] = z;
  }

  public void setF(double fx,double fy,double fz){
    this.f[0]=fx;
    this.f[1]=fy;
    this.f[2]=fz;

  }

  public void setPreviousPos(double px,double py,double pz){
    this.previouspos[0]=px;
    this.previouspos[1]=py;
    this.previouspos[2]=pz;

  }

  public void setLimit(double pxmin,double pxmax,double pxdown){
  this.posMIN[0]=pxmin;
  this.posMAX[0]=pxmax;
  this.posDown[0]=pxdown;

}


  public void calculatePos(){
    for(int i=0;i<3;i++){
      this.nextpos[i] = 2*this.pos[i]-this.previouspos[i]+this.dt*this.dt*this.f[i]/this.m;//Verlet for x,y,z
    }
  }

  public void borderResponse(){
    double w = Math.sqrt(Math.pow(this.nextpos[0]-this.rpos[0],2)+Math.pow(this.nextpos[2]-this.rpos[2],2));
    for(int i=0; i<3; i++){
      this.vel[i]=this.nextpos[i]-this.pos[i];
      this.previouspos[i]=this.pos[i];
      this.pos[i]=this.rpos[i]+this.r*(this.nextpos[i]-this.rpos[i])/w;
      
    }
  }

    // for(int i=0;i<3;i++){

    //   if(this.nextpos[i]>(this.posMAX[i]-this.radius)){
    //     this.vel[i]=-this.cr*(this.nextpos[i]-this.pos[i]);
    //     this.previouspos[i]=this.posMAX[i]-this.radius-this.vel[i];
    //     this.pos[i]=this.posMAX[i]-this.radius;
    //   }
    //   else if(this.nextpos[i]<this.radius){
    //     this.vel[i]=-this.cr*(this.nextpos[i]-this.pos[i]);
    //     this.previouspos[i]=this.radius-this.vel[i];
    //     this.pos[i]=this.radius;
    //   }
    //   else{
    //     this.previouspos[i]=this.pos[i];
    //     // this.pos[0]=this.nextpos[0];
    //     // this.pos[1]=this.nextpos[1];
    //     // this.pos[2]=this.nextpos[2];
    //     this.pos[i]=this.nextpos[i];
    //     // this.pos[2]=Math.sqrt(Math.pow(this.r,2)-Math.pow(this.nextpos[0]-this.rpos[0],2))+rpos[2];
    //     this.vel[i]=this.cr*(this.nextpos[i]-this.previouspos[i]);
    //   }
    // }


  //     if((this.nextpos[2]>(this.posDown[2]))){
  //       for(int i=0;i<3;i++){
  //         this.previouspos[i]=this.pos[i];
  //       }
  //       System.out.print("ax "+ this.nextpos[0] +"az "+ this.nextpos[2]);
  //       System.out.print("posx "+ this.pos[0] +"posz "+ this.pos[2]);
  //       this.pos[0] = this.nextpos[0];
  //       this.pos[1] = this.nextpos[1];
  //       this.pos[2] = this.rpos[2]+Math.sqrt(Math.pow(this.r,2)-Math.pow(this.nextpos[0]-this.rpos[0],2));
  //       for(int i=0;i<3;i++){
  //         this.vel[i]=this.cr*(this.nextpos[i]-this.previouspos[i]);
          
  //       }
  //     }
  //     else if((this.nextpos[2]<this.posMIN[2])){
  //       System.out.print("b. "+ this.pos[0] );
  //       for(int i=0;i<3;i++){
  //         if(this.pos[0]<=this.rpos[0]){
  //           this.vel[i]=0;
  //           this.previouspos[i]=this.posMIN[i];
  //           this.pos[i]=this.posMIN[i];
  //         }
  //         else{
  //           this.vel[i]=0;
  //           this.previouspos[i]=this.posMAX[i];
  //           this.pos[i]=this.posMAX[i];
  //         }
          
  //       }
  //     }
  //     else{
  //       if(this.vel[2]>=0){
  //         for(int i=0;i<3;i++){
  //           this.previouspos[i]=this.pos[i];
  //         }
  //         if(this.pos[0]<=this.rpos[0]){
  //           this.pos[0]=this.rpos[0]-Math.sqrt(Math.pow(this.r,2)-Math.pow(this.nextpos[2]-this.rpos[2],2));
  //           this.pos[1]=this.nextpos[1];
  //           this.pos[2]=this.nextpos[2];
  //         }
  //         else{
  //           this.pos[0]=this.rpos[0]+Math.sqrt(Math.pow(this.r,2)-Math.pow(this.nextpos[2]-this.rpos[2],2));
  //           this.pos[1]=this.nextpos[1];
  //           this.pos[2]=this.nextpos[2];
  //         }
  //         for(int i=0;i<3;i++){
  //           this.vel[i]=this.cr*(this.nextpos[i]-this.previouspos[i]);
  //           // this.previouspos[i]=this.pos[i];
  //         }

  //       }
  //       else{
  //         for(int i=0;i<3;i++){
  //           this.previouspos[i]=this.pos[i];
  //         }
  //         if(this.pos[0]<=this.rpos[0]){
  //           this.pos[0]=this.nextpos[0];
  //           this.pos[1]=this.nextpos[1];
  //           this.pos[2]=this.rpos[2]+Math.sqrt(Math.pow(this.r,2)-Math.pow(this.nextpos[0]-this.rpos[0],2));
  //         }
  //         else{
  //           this.pos[0]=this.nextpos[0];
  //           this.pos[1]=this.nextpos[1];
  //           this.pos[2]=this.rpos[2]+Math.sqrt(Math.pow(this.r,2)-Math.pow(this.nextpos[0]-this.rpos[0],2));
  //         }
  //         for(int i=0;i<3;i++){
  //           this.vel[i]=this.cr*(this.nextpos[i]-this.previouspos[i]);
  //           // this.previouspos[i]=this.pos[i];
  //         }
  //       }

  //       System.out.print("cx "+ this.pos[0] +"cz "+ this.pos[2] );

  //     }
  // }





}
