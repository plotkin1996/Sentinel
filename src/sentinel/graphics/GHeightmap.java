package sentinel.graphics;
import sentinel.representation.IHeightmap;
import com.jogamp.opengl.*;
import com.jogamp.opengl.math.VectorUtil;
import java.nio.FloatBuffer;
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
        vMap=iHmap.getVMap();
        generateArrays();
      }
    
    private int xSize,ySize;
    float[][] vMap;
    
    FloatBuffer vertices;
    FloatBuffer colors;
    private void generateArrays()
      {
        vertices=Buffers.newDirectFloatBuffer((xSize-1)*(ySize-1)*4*3);
        colors=Buffers.newDirectFloatBuffer((xSize-1)*(ySize-1)*4*3);
        
        for(int x=0;x<xSize-1;x++) for(int y=0;y<ySize-1;y++)
          {
            int i=(x+(xSize-1)*y)*12;
            float red=
              (vMap[x][y]==vMap[x+1][y]&&
              vMap[x][y+1]==vMap[x+1][y+1]&&
              vMap[x+1][y]==vMap[x][y+1])?
                0.0f:1.0f;
            float blue=(float)((x^y)&1);
            for(int n=0;n<4;n++)
              {
                colors.put(i+n*3+0,red);
                colors.put(i+n*3+1,vMap[x+((n&1)^(n>>1))][y+(n>>1)]);
                colors.put(i+n*3+2,blue);
                vertices.put(i+n*3+0,(float)(x+((n&1)^(n>>1))));
                vertices.put(i+n*3+1,(float)(y+(n>>1)));
                vertices.put(i+n*3+2,vMap[x+((n&1)^(n>>1))][y+(n>>1)]*20);
              }
          }
      }
    
    void render(GL2 gl)
      {
        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
        gl.glColorPointer(3,GL.GL_FLOAT,0,colors);
        gl.glVertexPointer(3,GL.GL_FLOAT,0,vertices);
        gl.glDrawArrays(gl.GL_QUADS,0,(xSize-1)*(ySize-1)*4);
        gl.glDisableClientState(GL2.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
      }
  }
