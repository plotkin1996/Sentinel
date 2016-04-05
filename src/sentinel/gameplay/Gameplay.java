package sentinel.gameplay;
import sentinel.representation.*;

public class Gameplay
  {
    private int mapXSize=50,mapYSize=50;
    public int getMapXSize() {return mapXSize;}
    public int getMapYSize() {return mapYSize;}
    
    private Heightmap heightmap;
    public IHeightmap getIHeightmap(){return heightmap;}
    private Player player;
    public ICamera getICamera(){return player;}
    
    private int numHills=20;
    public int getNumHills() {return numHills;}
    
    public void look(float dx,float dy)
      {player.look(dx,dy);}
    
    private long rnd=3;
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
        player=new Player(this);
      }
  }
