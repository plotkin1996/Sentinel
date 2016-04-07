package sentinel.graphics;
import sentinel.representation.ICursor;
import com.jogamp.opengl.*;

class GCursor
  {
    private ICursor iCur;
    void attach(ICursor iCur) {this.iCur=iCur;}
    void render(GL2 gl)
      {
        if(iCur.isEnabled())
          {
            gl.glBegin(GL2.GL_QUADS);
            gl.glColor4d(1.0,1.0,1.0,0.5);
            gl.glVertex3d(iCur.getX(),iCur.getY(),iCur.getZ()+0.1);
            gl.glVertex3d(iCur.getX()+1,iCur.getY(),iCur.getZ()+0.1);
            gl.glVertex3d(iCur.getX()+1,iCur.getY()+1,iCur.getZ()+0.1);
            gl.glVertex3d(iCur.getX(),iCur.getY()+1,iCur.getZ()+0.1);
            gl.glEnd();
          }
      }
  }
