package sentinel.gameplay;

import sentinel.representation.ISentinel;
import java.util.*;

class Sentinel extends SimpleThing implements ISentinel
  {
    private double yaw;
    public double getYaw() {return yaw;}
    
    private double rotationSpeed;
    private int elimDelay;
    
    ArrayList<Scanpoint> scanlist;
    Iterator<Scanpoint> targetIterator;
    Scanpoint target;
    
    private void toNextTarget()
      {
        if(!targetIterator.hasNext()) targetIterator=scanlist.iterator();
        target=targetIterator.next();
      }
    
    double delay;
    
    void runLogic(double dt)
      {
        if(delay-dt>0) {delay-=dt;return;}
        double dy=target.getYaw()-yaw;
        if(dy<0) dy+=360;
        for(;dy<rotationSpeed*dt;toNextTarget())
          {
            dy=target.getYaw()-yaw;
            if(dy<0) dy+=360;
            int gotEnergy=target.getTarget().eliminate();
            if(gotEnergy!=0)
              {
                delay=elimDelay;
                for(;gotEnergy>0;gotEnergy--) gameplay.spread();
                yaw=target.getYaw();
                return;
              }
          }
        yaw+=rotationSpeed*dt;
        if(yaw>360) yaw-=360;
      }
    
    @Override
    public boolean isProtected() {return true;}
    
    Gameplay gameplay;
    Sentinel(IThingBag bag,int id,ArrayList<Scanpoint> scanlist,Gameplay gameplay)
      {
        super(bag,id,false,0.0,gameplay.getSentinelEnergy());
        this.rotationSpeed=gameplay.getRotationSpeed();
        this.elimDelay=gameplay.getElimDelay();
        this.scanlist=scanlist;
        this.gameplay=gameplay;
        targetIterator=scanlist.iterator();
        toNextTarget();
        delay=0;
      }
  }

