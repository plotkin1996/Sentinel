package sentinel.gameplay;

import java.util.HashMap;
import java.util.Collection;

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
        Robot tree=new Robot(this,ctr,height,energy);
        if(!platform.build(tree)) return false;
        things.put(ctr++,tree);
        return true;
      }
    
    public Collection<Robot> getThings()
      {return things.values();}
    
    @Override
    public void remove(int id)
      {things.remove(id);}
    
    Gameplay gameplay;
    RobotBag(Gameplay gameplay,double height,int energy)
      {
        this.gameplay=gameplay;
        this.height=height;
        this.energy=energy;
        things=new HashMap<Integer,Robot>();ctr=0;
      }
  }
