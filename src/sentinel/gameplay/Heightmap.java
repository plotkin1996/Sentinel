package sentinel.gameplay;
import sentinel.representation.IHeightmap;
import sentinel.util.DDA;
import java.util.*;

class Heightmap implements IHeightmap
  {
    private Gameplay gameplay;
    
    public int getMapXSize() {return gameplay.getMapXSize();}
    public int getMapYSize() {return gameplay.getMapYSize();}
    public double getMaxHeight() {return gameplay.getMaxHeight();};
    
    @Override
    public int getGridXSize() {return getMapXSize()+1;}
    @Override
    public int getGridYSize() {return getMapYSize()+1;}
  
    double[][] vGrid;
    @Override
    public double[][] getVGrid(){return vGrid;}
    
    private void generateVGrid()
      {
        for(int i=gameplay.getNumHills();i>0;i--)
          {
            int x0=1,y0=1,x1=getMapXSize(),y1=getMapYSize();
            do{
                int xa=gameplay.getRandom()%(x1-x0)+x0;
                int ya=gameplay.getRandom()%(y1-y0)+y0;
                int xb=gameplay.getRandom()%(y1-y0)+y0;
                int yb=gameplay.getRandom()%(x1-x0)+x0;
                x0=(xa<xb)?xa:xb;x1=(xa>xb)?xa:xb;
                y0=(ya<yb)?ya:yb;y1=(ya>yb)?ya:yb;
                int dh=(gameplay.getRandom()%3==0)?-1:1;
                for(int x=x0;x<x1;x++) for(int y=y0;y<y1;y++)
                    vGrid[x][y]+=dh;
            }while(gameplay.getRandom()%2==0&&x1>x0&&y1>y0);
          }
        normalizeVGrid();
      }
    
    private void normalizeVGrid()
      {
        double minh=Double.POSITIVE_INFINITY,maxh=Double.NEGATIVE_INFINITY;
        int maxX=0,maxY=0;
        for(int x=0;x<getMapXSize();x++) for(int y=0;y<getMapYSize();y++)
          {
            if(minh>vGrid[x][y]) minh=vGrid[x][y];
            if(maxh<vGrid[x][y]) {maxh=vGrid[x][y];maxX=x;maxY=y;}
          }
        for(int x=0;x<getGridXSize();x++) for(int y=0;y<getGridYSize();y++)
          vGrid[x][y]=(vGrid[x][y]-minh)/(maxh-minh)*getMaxHeight()*0.9f;
        vGrid[maxX][maxY]=vGrid[maxX+1][maxY]=
          vGrid[maxX][maxY+1]=vGrid[maxX+1][maxY+1]=
            getMaxHeight();
      }
    
    List<Platform> pList;
    List<Platform> getPList() {return pList;}
    Platform[][] pMap;
    Platform[][] getPMap() {return pMap;}
    
    private void generatePlatforms()
      {
        pList=new ArrayList<Platform>();
        pMap=new Platform[getMapXSize()][getMapYSize()];
        for(int x=0;x<getMapXSize();x++) for(int y=0;y<getMapYSize();y++)
          if(vGrid[x][y]==vGrid[x+1][y]&&
               vGrid[x][y+1]==vGrid[x+1][y+1]&&
               vGrid[x+1][y]==vGrid[x][y+1])
            {
              Platform platform=new Platform(x,y,vGrid[x][y]);
              pList.add(platform);pMap[x][y]=platform;
            }
        Collections.sort(pList);
      }  
    
    private double checkQuadIntersection(int x,int y,
        double rpx,double rpy,double rpz,
        double rx,double ry,double rz)
      {
        double h0=vGrid[x][y],hx=vGrid[x+1][y],hy=vGrid[x][y+1];
        double ldotn=rx*(h0-hx)+ry*(h0-hy)+rz;
        
        if(ldotn>=0) return Double.POSITIVE_INFINITY;
        double dist=-((rpx-x)*(h0-hx)+(rpy-y)*(h0-hy)+(rpz-h0))/ldotn;
        double px=rpx+dist*rx-x,py=rpy+dist*ry-y;
        if(px<0||px>1||py<0||py>1) return Double.POSITIVE_INFINITY;
        return dist;
      }
    
    Platform pick(double rpx,double rpy,double rpz,double rx,double ry,double rz,Double dist)
      {
        if(dist==null) dist=new Double(0.0);
        for(DDA dda=new DDA(rpx,rpy,rx,ry);
            dda.getX()>=0&&dda.getX()<getMapXSize()&&
            dda.getY()>=0&&dda.getY()<getMapYSize();
          dda.step())
          {
            dist=checkQuadIntersection(dda.getX(),dda.getY(),rpx,rpy,rpz,rx,ry,rz);
            if(dist!=Double.POSITIVE_INFINITY) return pMap[dda.getX()][dda.getY()];
          }
        return null;
      }
    
    Heightmap(Gameplay gameplay)
      {
        this.gameplay=gameplay;
        vGrid=new double[getGridXSize()][getGridYSize()];
        generateVGrid();
        generatePlatforms();
      }
  }
