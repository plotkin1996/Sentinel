package sentinel.graphics;
import sentinel.representation.ICursor;
import com.jogamp.opengl.*;

class GCursor
  {
    static void render(GL2 gl,ICursor iCur)
      {
        if(!iCur.isEnabled()) return;
        gl.glEnable(GL.GL_COLOR_LOGIC_OP);
        gl.glLogicOp(GL.GL_INVERT);
        gl.glBegin(GL2.GL_QUADS);
            gl.glColor3d(1.0,1.0,1.0);
            gl.glVertex3d(iCur.getX(),iCur.getY(),iCur.getZ()+0.001);
            gl.glVertex3d(iCur.getX()+1,iCur.getY(),iCur.getZ()+0.001);
            gl.glVertex3d(iCur.getX()+1,iCur.getY()+1,iCur.getZ()+0.001);
            gl.glVertex3d(iCur.getX(),iCur.getY()+1,iCur.getZ()+0.001);
        gl.glEnd();
        gl.glDisable(GL.GL_COLOR_LOGIC_OP);
      }
  }
