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
    public int getMaxHeight() {return 30;}
    public int getStartingEnergy() {return 3;}
    public int getTreeCount() {return 100;}
    public int getTreeEnergy() {return 1;}
    public int getStoneEnergy() {return 2;}
    public int getRobotEnergy() {return 3;}
    public double getCameraHeight() {return 2.0;}
    
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
    
    private RobotBag robotBag;
    public Iterator<? extends IRobot> getRobots()
      {return robotBag.getThings().iterator();}
    
    
    public void look(double dx,double dy)
      {
        player.look(dx,dy);
        cursor.pointAt(heightmap.pick(player.getXPos(),player.getYPos(),player.getZPos(),
          player.getXDir(),player.getYDir(),player.getZDir(),null));
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
    
    private void build(IThingBag bag)
      {
        lock();
        try
          {
            if(player.getEnergy()<bag.getEnergy()) return;
            Platform platform=cursor.getPlatform();
            if(platform==null) return;
            if(!bag.build(platform)) return;
            player.addEnergy(-bag.getEnergy());
          }
        finally {unlock();}
      }
    
    public void buildTree() {build(treeBag);}
    public void buildStone() {build(stoneBag);}
    public void buildRobot() {build(robotBag);}
    
    public void incarnate()
      {
        double bestDist=Double.POSITIVE_INFINITY;
        Robot candidate=null;
        heightmap.pick(player.getXPos(),player.getYPos(),player.getZPos(),
          player.getXDir(),player.getYDir(),player.getZDir(),bestDist);
        for(Robot robot:robotBag.getThings())
          {
            double dist=robot.pick(player.getXPos(),player.getYPos(),player.getZPos(),
              player.getXDir(),player.getYDir(),player.getZDir());
            System.out.println(dist);
            if(dist<bestDist) {dist=bestDist;candidate=robot;}
            
          }
        if(candidate!=null) player.incarnate(candidate);
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
        for(int i=0;i<getTreeCount();i++)
          {
            int n=getRandom()%fpList.size();
            treeBag.build(fpList.get(n));
            fpList.remove(n);
          }
          
        stoneBag=new SimpleThingBag(this,true,0.5,getStoneEnergy());
        robotBag=new RobotBag(this,getCameraHeight()+0.5,getRobotEnergy());
        robotBag.build(fpList.get(0));
        
        player=new Player(getStartingEnergy(),robotBag.getThings().iterator().next());
      }
  }
