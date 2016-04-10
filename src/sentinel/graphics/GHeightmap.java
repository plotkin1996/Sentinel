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
    private void generateArrays()
      {
        vertices=Buffers.newDirectDoubleBuffer((xSize-1)*(ySize-1)*6*3);
        colors=Buffers.newDirectDoubleBuffer((xSize-1)*(ySize-1)*6*3);
        
        for(int x=0;x<xSize-1;x++) for(int y=0;y<ySize-1;y++)
          {
            int i=(x+(xSize-1)*y)*18;
            double red=
              (vGrid[x][y]==vGrid[x+1][y]&&
              vGrid[x][y+1]==vGrid[x+1][y+1]&&
              vGrid[x+1][y]==vGrid[x][y+1])?
                0.0:1.0;
            double blue=(double)((x^y)&1);
            for(int n=0;n<6;n++)
              {
                int xpos=x+(n==0||n==3||n==4?0:1);
                int ypos=y+(n==0||n==2||n==3?0:1);
                colors.put(i+n*3+0,red);
                colors.put(i+n*3+1,vGrid[xpos][ypos]/iHmap.getMaxHeight());
                colors.put(i+n*3+2,blue);
                vertices.put(i+n*3+0,(double)xpos);
                vertices.put(i+n*3+1,(double)ypos);
                vertices.put(i+n*3+2,vGrid[xpos][ypos]);
              }
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
