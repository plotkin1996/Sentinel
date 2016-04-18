package sentinel.graphics;
import com.jogamp.opengl.*;

class Util
  {
    static void drawLegs(GL2 gl,double r,double g,double b)
      {
        
        gl.glBegin(GL2.GL_QUADS);
          gl.glColor3d(r,g,b);
          
          gl.glVertex3d(0.1,0.1,1.6);
          gl.glVertex3d(0.1,-0.1,1.6);
          gl.glVertex3d(-0.1,-0.1,1.6);
          gl.glVertex3d(-0.1,0.1,1.6);
          
          gl.glVertex3d(0.1,0.1,1.3);
          gl.glVertex3d(0.1,-0.1,1.3);
          gl.glVertex3d(-0.1,-0.1,1.3);
          gl.glVertex3d(-0.1,0.1,1.3);
          
          gl.glColor3d(r*0.8,g*0.8,b*0.8);
          
          gl.glVertex3d(0.1,0.1,1.6);
          gl.glVertex3d(-0.1,0.1,1.6);
          gl.glVertex3d(-0.05,0.1,1.3);
          gl.glVertex3d(0.05,0.1,1.3);
          
          gl.glVertex3d(0.1,-0.1,1.6);
          gl.glVertex3d(-0.1,-0.1,1.6);
          gl.glVertex3d(-0.05,-0.1,1.3);
          gl.glVertex3d(0.05,-0.1,1.3);
          
          gl.glColor3d(r*0.4,g*0.4,b*0.4);
          
          gl.glVertex3d(0.1,0.1,1.6);
          gl.glVertex3d(0.1,-0.1,1.6);
          gl.glVertex3d(0.1,-0.05,1.3);
          gl.glVertex3d(0.1,0.05,1.3);
          
          gl.glVertex3d(-0.1,0.1,1.6);
          gl.glVertex3d(-0.1,-0.1,1.6);
          gl.glVertex3d(-0.1,-0.05,1.3);
          gl.glVertex3d(-0.1,0.05,1.3);
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLES);
          gl.glColor3d(r*0.4,g*0.4,b*0.4);
          
          gl.glVertex3d(0.1,0.05,1.3);
          gl.glVertex3d(0.05,0.1,1.3);
          gl.glVertex3d(0.2,0.2,-1.0);
          
          gl.glVertex3d(-0.1,0.05,1.3);
          gl.glVertex3d(-0.05,0.1,1.3);
          gl.glVertex3d(-0.2,0.2,-1.0);
          
          gl.glVertex3d(0.1,-0.05,1.3);
          gl.glVertex3d(0.05,-0.1,1.3);
          gl.glVertex3d(0.2,-0.2,-1.0);
          
          gl.glVertex3d(-0.1,-0.05,1.3);
          gl.glVertex3d(-0.05,-0.1,1.3);
          gl.glVertex3d(-0.2,-0.2,-1.0);
          
          
          gl.glColor3d(r*0.8,g*0.8,b*0.8);
          
          gl.glVertex3d(0.1,0.05,1.3);
          gl.glVertex3d(0.1,0.1,1.6);
          gl.glVertex3d(0.2,0.2,-1.0);
          
          gl.glVertex3d(-0.1,0.05,1.3);
          gl.glVertex3d(-0.1,0.1,1.6);
          gl.glVertex3d(-0.2,0.2,-1.0);
          
          gl.glVertex3d(0.1,-0.05,1.3);
          gl.glVertex3d(0.1,-0.1,1.6);
          gl.glVertex3d(0.2,-0.2,-1.0);
          
          gl.glVertex3d(-0.1,-0.05,1.3);
          gl.glVertex3d(-0.1,-0.1,1.6);
          gl.glVertex3d(-0.2,-0.2,-1.0);
          
          
          gl.glColor3d(r*0.6,g*0.6,b*0.6);
          
          gl.glVertex3d(0.05,0.1,1.3);
          gl.glVertex3d(0.1,0.1,1.6);
          gl.glVertex3d(0.2,0.2,-1.0);
          
          gl.glVertex3d(-0.05,0.1,1.3);
          gl.glVertex3d(-0.1,0.1,1.6);
          gl.glVertex3d(-0.2,0.2,-1.0);
          
          gl.glVertex3d(0.05,-0.1,1.3);
          gl.glVertex3d(0.1,-0.1,1.6);
          gl.glVertex3d(0.2,-0.2,-1.0);
          
          gl.glVertex3d(-0.05,-0.1,1.3);
          gl.glVertex3d(-0.1,-0.1,1.6);
          gl.glVertex3d(-0.2,-0.2,-1.0);
        gl.glEnd();
      }
  }
