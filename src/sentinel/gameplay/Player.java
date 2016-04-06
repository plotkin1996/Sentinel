package sentinel.gameplay;

import sentinel.representation.ICamera;

class Player implements ICamera
  {
    private Gameplay gameplay;
    Player(Gameplay gameplay)
      {this.gameplay=gameplay;}
    
    private float pitch=90.0f,yaw;
    public float getPitch(){return pitch;}
    public float getYaw(){return yaw;}
    void look(float dx,float dy)
      {
        if(pitch+dy>15.0f&&pitch+dy<165.0f) pitch+=dy;
        yaw+=dx;
        if(yaw>=360.0f) yaw-=360.0f;
        if(yaw<0) yaw+=360.0f;
      }
    
    Platform platform;
    public float getXPos()
      {return (float)platform.getX()+0.5f;}
    public float getYPos()
      {return (float)platform.getY()+0.5f;}
    public float getZPos()
      {return platform.getZ()+2.0f;}
    
    void moveToPlatform(Platform platform)
      {this.platform=platform;}
    
  }
