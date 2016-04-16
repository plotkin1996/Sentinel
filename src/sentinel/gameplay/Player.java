package sentinel.gameplay;

import sentinel.representation.ICamera;

class Player implements ICamera
  {
    private int energy;
    public int getEnergy() {return energy;}
    public void addEnergy(int delta)
      {energy+=delta;}
    
    private Robot robot;
    public void incarnate(Robot robot)
      {
        if(this.robot!=null) this.robot.deactivate();
        this.robot=robot;
        robot.activate();
      }
    
    Player(int energy,Robot robot)
      {
        this.energy=energy;
        incarnate(robot);
      }
    
    private double pitch=90.0f,yaw;
    public double getPitch(){return pitch;}
    public double getYaw(){return yaw;}
    void look(double dx,double dy)
      {
        if(pitch+dy>15.0f&&pitch+dy<180.0f) pitch+=dy;
        yaw+=dx;
        if(yaw>=360.0f) yaw-=360.0f;
        if(yaw<0) yaw+=360.0f;
      }
    
    //Platform platform;
    public double getXPos()
      {return robot.getX();}
    public double getYPos()
      {return robot.getY();}
    public double getZPos()
      {return robot.getZ()+robot.getHeight();}
    
    //Platform platform;
    public double getXDir()
      {return Math.sin(-yaw*Math.PI/180)*Math.sin(pitch*Math.PI/180);}
    public double getYDir()
      {return Math.cos(yaw*Math.PI/180)*Math.sin(pitch*Math.PI/180);}
    public double getZDir()
      {return -Math.cos(pitch*Math.PI/180);}
  }
