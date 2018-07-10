import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.util.*;
public class Spheric extends PrimitiveObject{


  private double radius;//radius to display the particle


  public Spheric(double m,double[] pos,double[] vel,double[] rotation,double[] f,double dt,double radius){
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
  @Override
    public void Draw(GLAutoDrawable drawable,GLU glu,GL2 gl){
      gl.glPushMatrix();
      gl.glTranslatef((float)pos[0],(float)pos[1],(float)pos[2]  );
      GLUquadric quad = glu.gluNewQuadric();
      glu.gluSphere(quad,this.radius, 10, 15);
      glu.gluDeleteQuadric(quad);
      gl.glPopMatrix();
  }

  public void calculateBoundingVolume(){
  };


  public double getRadius(){
    return this.radius;
  }


  public void calculatePos(){
    for(int i=0;i<3;i++){
    //  System.out.println(vel[i]);
      this.vel[i]=this.vel[i]+this.dt*this.f[i]/this.m;
      this.nextpos[i] = this.pos[i]+this.vel[i]*this.dt;//Verlet for x,y,z
    }
    borderResponse();
  }


  public void borderResponse(){
    for(int i=0;i<3;i++){

      if(this.nextpos[i]>(this.posMAX[i]-this.radius)){
        this.vel[i]=-cf*(this.nextpos[i]-this.pos[i])/this.dt;
        this.pos[i]=this.posMAX[i]-this.radius;
        this.extremeSup[i]=this.posMAX[i];
      }
      else if(this.nextpos[i]<this.radius){
        this.vel[i]=-cf*(this.nextpos[i]-this.pos[i])/this.dt;
        this.pos[i]=this.radius;
        this.extremeInf[i]=0;
      }
      else{
        this.pos[i]=this.nextpos[i];

        this.extremeInf[i]=this.pos[i]-this.radius;
        this.extremeSup[i]=this.pos[i]+this.radius;
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

public boolean checkCollision(Spheric particle){
  		double cr = 1;
  			double deltaX = this.getX()-particle.getX();
        double deltaY = this.getY()-particle.getY();

  			double deltaZ = this.getZ()-particle.getZ();
  			double distance = Math.sqrt(deltaX*deltaX + deltaZ*deltaZ+ deltaY*deltaY);
  			double twoRadius = this.getRadius() + particle.getRadius();
        if(distance==0){distance=1;deltaX=1;deltaY=1;deltaZ=1;};

  			if(distance < twoRadius){
  				double mdm = Math.sqrt(particle.getM()/this.getM());
  				double[] vela = particle.getVel();
  				double[] velb = this.getVel();
  				for(int i=0; i<3; i++){
  					vela[i] *= mdm*cr;
  					velb[i] /= mdm*cr;
  				}
  				this.setVel(vela);
  				particle.setVel(velb);
  				this.setX(this.getX() + deltaX*(twoRadius-distance)*0.5/distance);
          this.setY(this.getY() + deltaY*(twoRadius-distance)*0.5/distance);
  				this.setZ(this.getZ() + deltaZ*(twoRadius-distance)*0.5/distance);
  				particle.setX(particle.getX() - deltaX*(twoRadius-distance)*0.5/distance);
          particle.setY(particle.getY() - deltaY*(twoRadius-distance)*0.5/distance);
  				particle.setZ(particle.getZ() - deltaZ*(twoRadius-distance)*0.5/distance);
  				//previous position
  			}

 return true;


}

public boolean checkCollisionbis(Spheric particle){
  double cr = 0.5;
    double deltaX = this.getX()-particle.getX();
    double deltaY = this.getY()-particle.getY();

    double deltaZ = this.getZ()-particle.getZ();
    double distance = Math.sqrt(deltaX*deltaX + deltaZ*deltaZ+ deltaY*deltaY);
    if(distance==0){deltaX=1;deltaY=1;deltaZ=1;distance=Math.sqrt(deltaX*deltaX + deltaZ*deltaZ+ deltaY*deltaY);};
    double twoRadius = this.getRadius() + particle.getRadius();
    if(distance < this.radius){
      if(distance==0){System.out.println("error");}
      double mdm = Math.sqrt(particle.getM()/this.getM());
      double[] vela = particle.getVel();
      double[] velb = this.getVel();
      for(int i=0; i<3; i++){
        vela[i] *= mdm;
        velb[i] /= mdm;
      }
      this.setVel(vela);
      particle.setVel(velb);
      this.setX(this.getX() + deltaX*(this.radius-distance)*0.5/distance);
      this.setY(this.getY() + deltaY*(this.radius-distance)*0.5/distance);
      this.setZ(this.getZ() + deltaZ*(this.radius-distance)*0.5/distance);
      particle.setX(particle.getX() - deltaX*(this.radius-distance)*0.5/distance);
      particle.setY(particle.getY() - deltaY*(this.radius-distance)*0.5/distance);
      particle.setZ(particle.getZ() - deltaZ*(this.radius-distance)*0.5/distance);
      //previous position
    //  this.setPreviousPos(this.getX()-this.getVel()[0],0,this.getZ()-this.getVel()[2]);
    //  particle.setPreviousPos(particle.getX()-particle.getVel()[0],0,particle.getZ()-particle.getVel()[2]);
    }
    else if(distance < particle.radius){
      double mdm = Math.sqrt(particle.getM()/this.getM());
      double[] vela = particle.getVel();
      double[] velb = this.getVel();
      for(int i=0; i<3; i++){
        vela[i] *= mdm;
        velb[i] /= mdm;
      }
      this.setVel(vela);
      particle.setVel(velb);
      this.setX(this.getX() + deltaX*(particle.radius-distance)*0.5/distance);
      this.setY(this.getY() + deltaY*(particle.radius-distance)*0.5/distance);
      this.setZ(this.getZ() + deltaZ*(particle.radius-distance)*0.5/distance);
      particle.setX(particle.getX() - deltaX*(particle.radius-distance)*0.5/distance);
      particle.setY(particle.getY() - deltaY*(particle.radius-distance)*0.5/distance);
      particle.setZ(particle.getZ() - deltaZ*(particle.radius-distance)*0.5/distance);
      //previous position

    }

return true;




}





}
