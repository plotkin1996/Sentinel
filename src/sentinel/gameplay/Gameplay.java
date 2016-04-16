package sentinel.gameplay;
import sentinel.representation.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Gameplay
  {
    private int mapXSize=100,mapYSize=100;
    public int getNumHills() {return 100;}
    public int getMapXSize() {return mapXSize;}
    public int getMapYSize() {return mapYSize;}
    public int getMaxHeight() {return 20;}
    public int getStartingEnergy() {return 50;}
    public int getTreeCount() {return 50;}
    public int getTreeEnergy() {return 1;}
    public int getStoneEnergy() {return 2;}
    
    private Heightmap heightmap;
    public IHeightmap getIHeightmap(){return heightmap;}
    private Player player;
    public ICamera getICamera(){return player;}
    private Cursor cursor;
    public ICursor getICursor(){return cursor;}
    
    private List<Platform> pList,fpList;
    
    private Semaphore sem=new Semaphore(1);
    public void lock()
      {
        try{sem.acquire();}
        catch(Exception e){System.exit(-1);}
      }
    public void unlock() {sem.release();}
    
    private SimpleThingBag treeBag;
    public Iterator<? extends IThing> getTrees()
      {return treeBag.getThings().iterator();}
    
    private SimpleThingBag stoneBag;
    public Iterator<? extends IThing> getStones()
      {return stoneBag.getThings().iterator();}
    
    
    public void look(double dx,double dy)
      {
        player.look(dx,dy);
        cursor.pointAt(heightmap.pick(player.getXPos(),player.getYPos(),player.getZPos(),
          player.getPitch(),player.getYaw(),null));
      }
    
    public void consume()
      {
        lock();
        try
          {
            Platform platform=cursor.getPlatform();
            if(platform==null) return;
            boolean wasFree=cursor.getPlatform().isFree();
            player.addEnergy(platform.consume());
            if(!wasFree&&cursor.getPlatform().isFree()) fpList.add(platform);
          }
        finally {unlock();}
      }
    
    public void buildTree()
      {
        lock();
        try
          {
            if(player.getEnergy()<getTreeEnergy()) return;
            Platform platform=cursor.getPlatform();
            if(platform==null) return;
            if(!treeBag.build(platform)) return;
            player.addEnergy(-getTreeEnergy());
          }
        finally {unlock();}
      }
    
    public void buildStone()
      {
        lock();
        try
          {
            if(player.getEnergy()<getStoneEnergy()) return;
            Platform platform=cursor.getPlatform();
            if(platform==null) return;
            if(!stoneBag.build(platform)) return;
            player.addEnergy(-getStoneEnergy());
          }
        finally {unlock();}
      }
    
    
    public void click()
      {
        if(cursor.isEnabled()) 
          {
            player.moveToPlatform(cursor.getPlatform());
            cursor.pointAt(heightmap.pick(player.getXPos(),player.getYPos(),player.getZPos(),
              player.getPitch(),player.getYaw(),null));
          }
      }
    
    private long rnd=3213;
    public int getRandom()
      {
        //Xorshift.
        rnd^=(rnd<<21);
        rnd^=(rnd>>35);
        rnd^=(rnd<<4);
        int res=(int)rnd;
        return (res>0)?res:-res;
      }
    
    public Gameplay()
      {
        heightmap=new Heightmap(this);
        cursor=new Cursor();
        pList=heightmap.getPList();
        
        fpList=new ArrayList<Platform>(pList);
        
        treeBag=new SimpleThingBag(this,false,1.5,getTreeEnergy());
        for(int i=0;i<getTreeCount();)
          {
            int n=getRandom()%fpList.size();
            if(treeBag.build(fpList.get(n))) i++;
            fpList.remove(n);
          }
          
        stoneBag=new SimpleThingBag(this,true,0.5,getStoneEnergy());
        
        player=new Player(getStartingEnergy());
        player.moveToPlatform(heightmap.getPMap()[1][0]);
      }
  }
