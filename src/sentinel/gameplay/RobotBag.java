package sentinel.gameplay;

import java.util.HashMap;
import java.util.Collection;
import sentinel.util.*;

class RobotBag implements IThingBag
  {
    private double height;
    private int energy;
    
    @Override
    public int getEnergy() {return energy;}
    
    private HashMap<Integer,Robot> things;
    private int ctr;
    
    @Override
    public boolean build(Platform platform)
      {
        Robot robot=new Robot(this,ctr,height,energy);
        if(!platform.build(robot)) return false;
        things.put(ctr++,robot);
        return true;
      }
    
    public Collection<Robot> getThings()
      {return things.values();}
    
    @Override
    public void remove(int id)
      {things.remove(id);}
      
    public Robot pick(double rpx,double rpy,double rpz,
      double rx,double ry,double rz, MutableDouble bestDist)
      {
        Robot result=null;
        for(Robot robot:getThings())
          {
            double dist=robot.pick(rpx,rpy,rpz,rx,ry,rz);
            if(dist<bestDist.get()) {bestDist.put(dist);result=robot;}
          }
        return result;
      }
    
    RobotBag(double height,int energy)
      {
        this.height=height;
        this.energy=energy;
        things=new HashMap<Integer,Robot>();ctr=0;
      }
  }
