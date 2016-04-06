package sentinel.gameplay;
import sentinel.representation.*;
import java.util.*;

public class Gameplay
  {
    private int mapXSize=50,mapYSize=50;
    public int getMapXSize() {return mapXSize;}
    public int getMapYSize() {return mapYSize;}
    public int getMaxHeight() {return 5;}
    
    private Heightmap heightmap;
    public IHeightmap getIHeightmap(){return heightmap;}
    private Player player;
    public ICamera getICamera(){return player;}
    
    List<Platform> pList;
    
    public int getNumHills() {return 20;}
    
    public void look(double dx,double dy)
      {player.look(dx,dy);}
    
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
        pList=heightmap.getPList();
        player=new Player(this);
        player.moveToPlatform(pList.get(0));//pList.size()-1));
      }
  }
