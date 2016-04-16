package sentinel.gameplay;

import sentinel.representation.IThing;

abstract class Thing implements IThing
  {
    private Platform platform;
    public Platform getPlatform() {return platform;}
    
    public double getX() {return platform.getX()+0.5;}
    public double getY() {return platform.getY()+0.5;}
    private double z;
    public double getZ() {return z;}
    
    void place(Platform platform,double z)
      {this.platform=platform;this.z=z;}
    
    abstract boolean isStackable();
    abstract double getHeight();
    
    public int consume()
      {
        bag.remove(id);
        return 0;
      }
    
    private IThingBag bag;
    private int id;
    Thing(IThingBag bag,int id)
      {this.bag=bag;this.id=id;}
  }
