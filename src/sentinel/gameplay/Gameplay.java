package sentinel.gameplay;
import sentinel.representation.*;
import java.util.*;
import java.util.concurrent.Semaphore;
import sentinel.util.*;

public class Gameplay
  {
    private int mapXSize=50,mapYSize=50;
    public int getNumHills() {return 80;}
    public int getMapXSize() {return mapXSize;}
    public int getMapYSize() {return mapYSize;}
    public int getMaxHeight() {return 15;}
    public int getStartingEnergy() {return 7;}
    public int getTreeCount() {return 100;}
    public int getTreeEnergy() {return 1;}
    public int getStoneEnergy() {return 2;}
    public int getRobotEnergy() {return 3;}
    public int getSentinelEnergy() {return 4;}
    public double getCameraHeight() {return 2.0;}
    public double getRotationSpeed() {return 10;}
    public int getElimDelay() {return 1;}
    
    private Heightmap heightmap;
    public IHeightmap getIHeightmap(){return heightmap;}
    public Heightmap getHeightmap(){return heightmap;}
    private Player player;
    public ICamera getICamera(){return player;}
    private Cursor cursor;
    public ICursor getICursor(){return cursor;}
    
    private List<Platform> pList,fpList;
    
    void spread()
      {
        for(int n=getRandom()%pList.size();;n=(n+1)%pList.size())
          if(pList.get(n).thingCount()==0)
            {treeBag.build(pList.get(n));return;}
      }
    
    private Semaphore sem=new Semaphore(0);
    public void lock()
      {
        try{sem.acquire();}
        catch(Exception e){System.exit(-1);}
      }
    public void unlock() {sem.release();}
    
    private SimpleThingBag treeBag;
    public Iterator<? extends IThing> getTrees()
      {return treeBag.getThings().iterator();}
    public void buildTree() {build(treeBag);}
    
    private SimpleThingBag stoneBag;
    public Iterator<? extends IThing> getStones()
      {return stoneBag.getThings().iterator();}
    public void buildStone() {build(stoneBag);}
    
    private RobotBag robotBag;
    public Iterator<? extends IRobot> getRobots()
      {return robotBag.getThings().iterator();}
    public void buildRobot() {build(robotBag);}
    
    private SentinelBag sentinelBag;
    public Iterator<? extends ISentinel> getSentinels()
      {return sentinelBag.getThings().iterator();}
    
    
    public void look(double dx,double dy)
      {
        if(isPaused()&&!isWin()) return;
        player.look(dx,dy);
        cursor.pointAt(heightmap.pick(player.getXPos(),player.getYPos(),player.getZPos(),
          player.getXDir(),player.getYDir(),player.getZDir(),null));
      }
    
    public void consume()
      {
        if(isPaused()) return;
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
        if(isPaused()) return;
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
    
    private Platform topPlatform;
    public void incarnate()
      {
        if(isPaused()) return;
        lock();
        try
          {
            MutableDouble dist=new MutableDouble(Double.POSITIVE_INFINITY);
            heightmap.pick(player.getXPos(),player.getYPos(),player.getZPos(),
              player.getXDir(),player.getYDir(),player.getZDir(),dist);
            player.incarnate(robotBag.pick(player.getXPos(),player.getYPos(),player.getZPos(),
              player.getXDir(),player.getYDir(),player.getZDir(),dist));
            if(player.getRobot().getPlatform()==topPlatform) win(); 
          }
        finally {unlock();}
      }
    
    long lastTime;
    long dms;
    public void runLogic()
      {
        if(isPaused()) return;
        dms=System.currentTimeMillis()-lastTime;
        lastTime+=dms;
        double dt=(double)dms/1000;
        sentinelBag.runLogic(dt);
        player.runLogic(dt);
      }
    
    public int getPlayerEnergy() {return player.getEnergy();}
    
    private boolean isGameOver=false;
    public void gameOver() {isGameOver=true;}
    
    private boolean isPaused=false;
    public void togglePause() {isPaused=!isPaused;}
    
    private boolean isWin=false;
    public boolean isWin() {return isWin;}
    public void win() {isWin=true;}
    
    public boolean isGameOver() {return isGameOver;}    
    public boolean isPaused() {return isGameOver||isPaused||isWin;}    
    
    public boolean isPlayerAlert() {return player.isAlert();}
    
    private long rnd;
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
        rnd=System.currentTimeMillis();
      
        heightmap=new Heightmap(this);
        cursor=new Cursor();
        pList=heightmap.getPList();
        
        fpList=new ArrayList<Platform>(pList);
        
        topPlatform=fpList.get(fpList.size()-1);
        sentinelBag=new SentinelBag(this);
        sentinelBag.build(topPlatform);
        fpList.remove(fpList.size()-1);
        
        robotBag=new RobotBag(getCameraHeight(),getRobotEnergy());
        robotBag.build(fpList.get(0));
        fpList.remove(0);
        
        treeBag=new SimpleThingBag(this,false,1.5,getTreeEnergy());
        for(int i=0;i<getTreeCount();i++)
          {
            int n=getRandom()%fpList.size();
            treeBag.build(fpList.get(n));
            fpList.remove(n);
          }
        
        stoneBag=new SimpleThingBag(this,true,0.5,getStoneEnergy());
        
        player=new Player(getStartingEnergy(),this,robotBag.getThings().iterator().next());
        lastTime=System.currentTimeMillis();
        unlock();
      }
  }
