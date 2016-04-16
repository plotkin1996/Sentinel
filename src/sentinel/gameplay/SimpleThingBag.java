package sentinel.gameplay;

import java.util.HashMap;
import java.util.Collection;

class SimpleThingBag implements IThingBag
  {
    private HashMap<Integer,SimpleThing> things;
    private int ctr;
    private boolean isStackable;
    private double height;
    private int energy;
    
    public boolean build(Platform platform)
      {
        SimpleThing tree=new SimpleThing(this,ctr,isStackable,height,energy);
        if(!platform.build(tree)) return false;
        things.put(ctr++,tree);
        return true;
      }
    
    public Collection<SimpleThing> getThings()
      {return things.values();}
    
    public void remove(int id)
      {
        things.remove(id);
      }
    
    Gameplay gameplay;
    SimpleThingBag(Gameplay gameplay,boolean isStackable,double height,int energy)
      {
        this.gameplay=gameplay;
        things=new HashMap<Integer,SimpleThing>();ctr=0;
        this.isStackable=isStackable;
        this.height=height;
        this.energy=energy;
      }
  }
