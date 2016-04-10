package sentinel.gameplay;
import sentinel.representation.*;
import java.util.*;

public class Gameplay
  {
    private int mapXSize=100,mapYSize=100;
    public int getMapXSize() {return mapXSize;}
    public int getMapYSize() {return mapYSize;}
    public int getMaxHeight() {return 5;}
    
    private Heightmap heightmap;
    public IHeightmap getIHeightmap(){return heightmap;}
    private Player player;
    public ICamera getICamera(){return player;}
    private Cursor cursor;
    public ICursor getICursor(){return cursor;}
    
    List<Platform> pList;
    
    public int getNumHills() {return 20;}
    
    public void look(double dx,double dy)
      {
        player.look(dx,dy);
        cursor.pointAt(heightmap.pick(player.getXPos(),player.getYPos(),player.getZPos(),
          player.getPitch(),player.getYaw(),null));
      }
    
    public void click()
      {
        if(cursor.isEnabled()) player.moveToPlatform(cursor.getPlatform());
      }
    
    private long rnd=1;
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
        player=new Player(this);
        player.moveToPlatform(heightmap.getPMap()[2][2]);//pList.get(0));//pList.size()-1));
      }
  }
