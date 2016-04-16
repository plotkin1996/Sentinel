package sentinel.graphics;
import com.jogamp.opengl.*;

class HeadUpDisplay
  {
    private static void drawCrosshair(GL2 gl)
      {
        gl.glEnable(GL.GL_COLOR_LOGIC_OP);
        gl.glLogicOp(GL.GL_INVERT);
        gl.glBegin(GL.GL_LINES);
          gl.glVertex2d(0.02,0.02);gl.glVertex2d(-0.02,-0.02);
          gl.glVertex2d(0.02,-0.02);gl.glVertex2d(-0.02,0.02);
        gl.glEnd();
        gl.glDisable(GL.GL_COLOR_LOGIC_OP);
      }
      
    static void render(GL2 gl,double pos)
      { 
        drawCrosshair(gl);
        gl.glBegin(GL2.GL_QUADS);
          gl.glVertex2d(-0.9,pos+0.1);
          gl.glVertex2d(-0.8,pos+0.1);
          gl.glVertex2d(-0.8,pos+0.2);
          gl.glVertex2d(-0.9,pos+0.2);
        gl.glEnd();
      }
  }
