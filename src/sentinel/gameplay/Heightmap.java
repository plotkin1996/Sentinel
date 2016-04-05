package sentinel.gameplay;
import sentinel.representation.IHeightmap;

class Heightmap implements IHeightmap
  {
    private Gameplay gameplay;
  
    float[][] vMap;
    @Override
    public float[][] getVMap(){return vMap;}
    
    @Override
    public int getGridXSize() {return gameplay.getMapXSize()+1;}
    @Override
    public int getGridYSize() {return gameplay.getMapYSize()+1;}
    
    private void generateHeightmap()
      {
        for(int i=gameplay.getNumHills();i>0;i--)
          {
            int x0=0,y0=0,x1=getGridXSize(),y1=getGridYSize();
            do{
                int xa=gameplay.getRandom()%(x1-x0)+x0;
                int ya=gameplay.getRandom()%(y1-y0)+y0;
                int xb=gameplay.getRandom()%(y1-y0)+y0;
                int yb=gameplay.getRandom()%(x1-x0)+x0;
                x0=(xa<xb)?xa:xb;x1=(xa>xb)?xa:xb;
                y0=(ya<yb)?ya:yb;y1=(ya>yb)?ya:yb;
                int dh=(gameplay.getRandom()%3==0)?-1:1;
                for(int x=x0;x<x1;x++) for(int y=y0;y<y1;y++)
                    vMap[x][y]+=dh;
            }while(gameplay.getRandom()%2==0&&x1>x0&&y1>y0);
          }
        
        float minh=1000.0f,maxh=-1000.0f;
        for(int x=0;x<getGridXSize();x++) for(int y=0;y<getGridYSize();y++)
          {
            if(minh>vMap[x][y]) minh=vMap[x][y];
            if(maxh<vMap[x][y]) maxh=vMap[x][y];
          }
          
        for(int x=0;x<getGridXSize();x++) for(int y=0;y<getGridYSize();y++)
          vMap[x][y]=(vMap[x][y]-minh)/(maxh-minh);
      }
      
    Heightmap(Gameplay gameplay)
      {
        this.gameplay=gameplay;
        vMap=new float[gameplay.getMapXSize()+1][gameplay.getMapYSize()+1];
        generateHeightmap();
      }
  }
