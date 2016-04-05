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
    
    public float getXPos()
      {return 25.5f;}
    public float getYPos()
      {return 25.5f;}
    public float getZPos()
      {return 1.0f;}
    
  }
