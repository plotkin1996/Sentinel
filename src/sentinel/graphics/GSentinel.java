package sentinel.graphics;

import sentinel.representation.ISentinel;
import com.jogamp.opengl.*;
import java.util.Iterator;

class GSentinel
  {
    static void draw(GL2 gl,ISentinel sentinel)
      {
        Util.drawLegs(gl,0.5,0.5,0.6);
        gl.glRotated(sentinel.getYaw(),0.0,0.0,1.0);
        gl.glTranslated(0.0,0.0,1.6);
        gl.glBegin(GL.GL_TRIANGLES);
        
          gl.glColor3d(0.33,0.33,0.4);
          
          gl.glVertex3d(0.0,0.0,-0.2);
          gl.glVertex3d(0.05,0.05,0.3);
          gl.glVertex3d(-0.05,0.05,0.3);
          
          gl.glVertex3d(0.0,0.0,-0.2);
          gl.glVertex3d(0.05,-0.05,0.3);
          gl.glVertex3d(-0.05,-0.05,0.3);
        
          gl.glColor3d(0.25,0.25,0.3);
          
          gl.glVertex3d(0.0,0.0,-0.2);
          gl.glVertex3d(0.05,0.05,0.3);
          gl.glVertex3d(0.05,-0.05,0.3);
          
          gl.glVertex3d(0.0,0.0,-0.2);
          gl.glVertex3d(-0.05,0.05,0.3);
          gl.glVertex3d(-0.05,-0.05,0.3);
        
          gl.glColor3d(0.33,0.33,0.4);
          
          gl.glVertex3d(0.05,-0.05,0.3);
          gl.glVertex3d(0.05,0.05,0.3);
          gl.glVertex3d(0.0,-0.05,0.5);
          
          gl.glVertex3d(0.0,-0.05,0.5);
          gl.glVertex3d(0.05,0.05,0.3);
          gl.glVertex3d(0.0,0.05,0.5);
          
          gl.glVertex3d(-0.05,-0.05,0.3);
          gl.glVertex3d(-0.05,0.05,0.3);
          gl.glVertex3d(0.0,-0.05,0.5);
          
          gl.glVertex3d(0.0,-0.05,0.5);
          gl.glVertex3d(-0.05,0.05,0.3);
          gl.glVertex3d(0.0,0.05,0.5);
          
          gl.glColor3d(0.25,0.25,0.3);
          gl.glVertex3d(-0.05,-0.05,0.3);
          gl.glVertex3d(0.05,-0.05,0.3);
          gl.glVertex3d(0.0,-0.05,0.5);
          
          gl.glColor3d(0.5,0.5,0.6);
          
          gl.glVertex3d(0.0,0.05,0.5);
          gl.glVertex3d(0.05,0.05,0.3);
          gl.glVertex3d(0.0,0.5,0.3);
          
          gl.glVertex3d(0.0,0.05,0.5);
          gl.glVertex3d(-0.05,0.05,0.3);
          gl.glVertex3d(0.0,0.5,0.3);
          
          gl.glColor3d(0.1,0.1,0.14);
          
          gl.glVertex3d(-0.05,0.05,0.3);
          gl.glVertex3d(0.05,0.05,0.3);
          gl.glVertex3d(0.0,0.5,0.3);
          
        gl.glEnd();
      }
    
    static void render(GL2 gl,Iterator<? extends ISentinel> things)
      {
        while(things.hasNext())
          {
            ISentinel thing=things.next();
            gl.glPushMatrix();
            gl.glTranslated(thing.getX(),thing.getY(),thing.getZ());
            draw(gl,thing);
            gl.glPopMatrix();
          }
      }
  }
