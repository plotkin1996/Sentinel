package sentinel.graphics;

import sentinel.representation.IThing;
import com.jogamp.opengl.*;

class GStone extends GSimpleThing
  {
    @Override
    void draw(GL2 gl,IThing tree)
      {
        gl.glBegin(GL.GL_TRIANGLES);
          gl.glColor3d(0.2,0.6,1.0);
          
          gl.glVertex3d(-0.5,0.0,0.5);
          gl.glVertex3d(0.0,-0.5,0.5);
          gl.glVertex3d(0.0,0.5,0.5);
          
          gl.glVertex3d(0.5,0.0,0.5);
          gl.glVertex3d(0.0,-0.5,0.5);
          gl.glVertex3d(0.0,0.5,0.5);
          
          gl.glColor3d(0.0,0.0,0.1);
          
          gl.glVertex3d(0.3,0.3,0.0);
          gl.glVertex3d(-0.3,0.3,0.0);
          gl.glVertex3d(-0.3,-0.3,0.0);
          
          gl.glVertex3d(0.3,0.3,0.0);
          gl.glVertex3d(0.3,-0.3,0.0);
          gl.glVertex3d(-0.3,-0.3,0.0);
          
          gl.glColor3d(0.1,0.3,0.5);
          
          gl.glVertex3d(0.0,-0.5,0.5);
          gl.glVertex3d(0.3,-0.3,0.0);
          gl.glVertex3d(-0.3,-0.3,0.0);
          
          gl.glVertex3d(0.0,0.5,0.5);
          gl.glVertex3d(0.3,0.3,0.0);
          gl.glVertex3d(-0.3,0.3,0.0);
          
          gl.glVertex3d(0.5,0.0,0.5);
          gl.glVertex3d(0.3,0.3,0.0);
          gl.glVertex3d(0.3,-0.3,0.0);
          
          gl.glVertex3d(-0.5,0.0,0.5);
          gl.glVertex3d(-0.3,0.3,0.0);
          gl.glVertex3d(-0.3,-0.3,0.0);
          
          gl.glColor3d(0.05,0.15,0.25);
          
          gl.glVertex3d(0.5,0.0,0.5);
          gl.glVertex3d(0.0,0.5,0.5);
          gl.glVertex3d(0.3,0.3,0.0);
          
          gl.glVertex3d(-0.5,0.0,0.5);
          gl.glVertex3d(0.0,0.5,0.5);
          gl.glVertex3d(-0.3,0.3,0.0);
          
          gl.glVertex3d(0.5,0.0,0.5);
          gl.glVertex3d(0.0,-0.5,0.5);
          gl.glVertex3d(0.3,-0.3,0.0);
          
          gl.glVertex3d(-0.5,0.0,0.5);
          gl.glVertex3d(0.0,-0.5,0.5);
          gl.glVertex3d(-0.3,-0.3,0.0);
          
        gl.glEnd();
      }
  }
