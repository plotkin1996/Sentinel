package sentinel.gameplay;

import sentinel.representation.ICamera;

class Player implements ICamera
  {
    private Gameplay gameplay;
    Player(Gameplay gameplay)
      {this.gameplay=gameplay;}
    
    private double pitch=90.0f,yaw;
    public double getPitch(){return pitch;}
    public double getYaw(){return yaw;}
    void look(double dx,double dy)
      {
        if(pitch+dy>15.0f&&pitch+dy<165.0f) pitch+=dy;
        yaw+=dx;
        if(yaw>=360.0f) yaw-=360.0f;
        if(yaw<0) yaw+=360.0f;
      }
    
    Platform platform;
    public double getXPos()
      {return (double)platform.getX()+0.5f;}
    public double getYPos()
      {return (double)platform.getY()+0.5f;}
    public double getZPos()
      {return platform.getZ()+2.0f;}
    
    void moveToPlatform(Platform platform)
      {this.platform=platform;}
    
  }
