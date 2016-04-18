package sentinel.gameplay;

import java.util.*;

class SentinelBag implements IThingBag
  {
    private double cameraHeight;
    
    @Override
    public int getEnergy() {return gameplay.getSentinelEnergy();}
    
    private HashMap<Integer,Sentinel> things;
    private int ctr;
    
    @Override
    public boolean build(Platform platform)
      {
        Sentinel sentinel=new Sentinel(this,ctr,Scanpoint.buildScanlist(
          heightmap,platform.getX()+0.5,platform.getY()+0.5,platform.getZ()+cameraHeight),gameplay);
        if(!platform.build(sentinel)) return false;
        things.put(ctr++,sentinel);
        return true;
      }
    
    public Collection<Sentinel> getThings()
      {return things.values();}
    
    @Override
    public void remove(int id)
      {things.remove(id);}

    void runLogic(double dt)
      {
        for(Sentinel sentinel:getThings()) sentinel.runLogic(dt);
      }

    Heightmap heightmap;
    Gameplay gameplay;
    SentinelBag(Gameplay gameplay)
      {
        this.gameplay=gameplay;
        this.heightmap=gameplay.getHeightmap();
        this.cameraHeight=gameplay.getCameraHeight();
        things=new HashMap<Integer,Sentinel>();ctr=0;
      }
  }
