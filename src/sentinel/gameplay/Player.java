package sentinel.gameplay;

import sentinel.representation.ICamera;

class Player implements ICamera
  {
    private int energy;
    public int getEnergy() {return energy;}
    public void addEnergy(int delta)
      {energy+=delta;}
    
    private Robot robot;
    public Robot getRobot()
      {return robot;}
      
    public void incarnate(Robot robot)
      {
        if(robot==null) return;
        if(this.robot!=null) this.robot.deactivate();
        this.robot=robot;
        robot.setPlayer(this);
      }
    
    double alertCountdown;
    public boolean isAlert()
      {return alertCountdown>0;}
    public void eliminate()
      {
        energy-=1;
        if(energy<0) gameplay.gameOver();
        alertCountdown=0.5;
      }
    
    void runLogic(double dt){alertCountdown-=dt;}
    
    Gameplay gameplay;
    Player(int energy,Gameplay gameplay,Robot robot)
      {
        this.energy=energy;
        this.gameplay=gameplay;
        incarnate(robot);
        alertCountdown=0;
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
    
    public double getXPos()
      {return robot.getX();}
    public double getYPos()
      {return robot.getY();}
    public double getZPos()
      {return robot.getZ()+robot.getCameraHeight();}
      
    public double getXDir()
      {return Math.sin(-yaw*Math.PI/180)*Math.sin(pitch*Math.PI/180);}
    public double getYDir()
      {return Math.cos(yaw*Math.PI/180)*Math.sin(pitch*Math.PI/180);}
    public double getZDir()
      {return -Math.cos(pitch*Math.PI/180);}
  }
