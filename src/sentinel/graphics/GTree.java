package sentinel.graphics;

import sentinel.representation.IThing;
import com.jogamp.opengl.*;

class GTree extends GSimpleThing
  {
    @Override
    void draw(GL2 gl,IThing tree)
      {
        gl.glBegin(GL.GL_TRIANGLES);
          gl.glColor3d(0.0,1.0,0.0);
          
          gl.glVertex3d(0.0,0.0,1.5);
          gl.glVertex3d(0.4,-0.4,0.5);
          gl.glVertex3d(-0.4,-0.4,0.5);
          
          gl.glVertex3d(0.0,0.0,1.5);
          gl.glVertex3d(0.4,0.4,0.5);
          gl.glVertex3d(-0.4,0.4,0.5);
          
          gl.glColor3d(0.0,0.5,0.0);
          
          gl.glVertex3d(0.0,0.0,1.5);
          gl.glVertex3d(-0.4,0.4,0.5);
          gl.glVertex3d(-0.4,-0.4,0.5);
          
          gl.glVertex3d(0.0,0.0,1.5);
          gl.glVertex3d(0.4,0.4,0.5);
          gl.glVertex3d(0.4,-0.4,0.5);
          
          gl.glColor3d(0.8,0.4,0.0);
          
          gl.glVertex3d(0.0,0.0,-0.1);
          gl.glVertex3d(0.1,0.1,0.5);
          gl.glVertex3d(-0.1,0.1,0.5);
          
          gl.glVertex3d(0.0,0.0,-0.1);
          gl.glVertex3d(0.1,-0.1,0.5);
          gl.glVertex3d(-0.1,-0.1,0.5);
          
          gl.glColor3d(0.4,0.2,0.0);
          
          gl.glVertex3d(0.0,0.0,-0.1);
          gl.glVertex3d(0.1,0.1,0.5);
          gl.glVertex3d(0.1,-0.1,0.5);
          
          gl.glVertex3d(0.0,0.0,-0.1);
          gl.glVertex3d(-0.1,0.1,0.5);
          gl.glVertex3d(-0.1,-0.1,0.5);
          
        gl.glEnd();
      }
  }
