package sentinel.gameplay;

import sentinel.representation.ICamera;

class Player implements ICamera
  {
    private Gameplay gameplay;
    Player(Gameplay gameplay)
      {this.gameplay=gameplay;}
    
    private float xAngle,yAngle;
    public float getXAngle(){return xAngle;}
    public float getYAngle(){return yAngle;}
    void look(float dx,float dy) {xAngle+=dx;yAngle+=dy;}
  }
