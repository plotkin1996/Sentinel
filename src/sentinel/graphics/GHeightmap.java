package sentinel.graphics;
import sentinel.representation.IHeightmap;
import com.jogamp.opengl.*;
import java.nio.DoubleBuffer;
import com.jogamp.common.nio.Buffers;

class GHeightmap
  {
    private IHeightmap iHmap;
    void attach(IHeightmap iHmap)
      {
        this.iHmap=iHmap;
        this.iHmap=iHmap;
        xSize=iHmap.getGridXSize();
        ySize=iHmap.getGridYSize();
        vGrid=iHmap.getVGrid();
        generateArrays();
      }
    
    private int xSize,ySize;
    double[][] vGrid;
    
    DoubleBuffer vertices;
    DoubleBuffer colors;
    int buffPos;
    private void addVertex(
        double r,double b,
        int x, int y)
      {
        colors.put(buffPos,r);
        colors.put(buffPos+1,vGrid[x][y]/iHmap.getMaxHeight());
        colors.put(buffPos+2,b);
        vertices.put(buffPos,x);
        vertices.put(buffPos+1,y);
        vertices.put(buffPos+2,vGrid[x][y]);
        buffPos+=3;
      }
    
    private void generateArrays()
      {
        vertices=Buffers.newDirectDoubleBuffer((xSize-1)*(ySize-1)*6*3);
        colors=Buffers.newDirectDoubleBuffer((xSize-1)*(ySize-1)*6*3);
        buffPos=0;
        
        for(int x=0;x<xSize-1;x++) for(int y=0;y<ySize-1;y++)
          {
            double r=
              (vGrid[x][y]==vGrid[x+1][y]&&
              vGrid[x][y+1]==vGrid[x+1][y+1]&&
              vGrid[x+1][y]==vGrid[x][y+1])?
                0.0:1.0;
            double b=(double)((x^y)&1);
            
            int top=0;
            double toph=-1.0;
            for(int n=0;n<4;n++) if(vGrid[x+n%2][y+(n/2)%2]>toph)
              {top=n;toph=vGrid[x+n%2][y+(n/2)%2];}
            
            int topx=top%2,topy=(top/2)%2;
            addVertex(r,b,x+topx,y+topy);
            addVertex(r,b,x+(topx+1)%2,y+topy);
            addVertex(r,b,x+(topx+1)%2,y+(topy+1)%2);
            
            addVertex(r,b,x+topx,y+topy);
            addVertex(r,b,x+topx,y+(topy+1)%2);
            addVertex(r,b,x+(topx+1)%2,y+(topy+1)%2);
          }
      }
    
    void render(GL2 gl)
      {
        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
        gl.glColorPointer(3,GL2.GL_DOUBLE,0,colors);
        gl.glVertexPointer(3,GL2.GL_DOUBLE,0,vertices);
        gl.glDrawArrays(GL.GL_TRIANGLES,0,(xSize-1)*(ySize-1)*2*3);
        gl.glDisableClientState(GL2.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
      }
  }
