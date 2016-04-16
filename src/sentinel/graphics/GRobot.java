package sentinel.graphics;

import sentinel.representation.IRobot;
import com.jogamp.opengl.*;
import java.util.Iterator;

class GRobot
  {
    void draw(GL2 gl,IRobot robot)
      {
        
        gl.glBegin(GL2.GL_QUADS);
          gl.glColor3d(1.0,1.0,0.0);
          
          gl.glVertex3d(0.1,0.1,1.6);
          gl.glVertex3d(0.1,-0.1,1.6);
          gl.glVertex3d(-0.1,-0.1,1.6);
          gl.glVertex3d(-0.1,0.1,1.6);
          
          gl.glVertex3d(0.1,0.1,1.3);
          gl.glVertex3d(0.1,-0.1,1.3);
          gl.glVertex3d(-0.1,-0.1,1.3);
          gl.glVertex3d(-0.1,0.1,1.3);
          
          gl.glColor3d(0.8,0.8,0.0);
          
          gl.glVertex3d(0.1,0.1,1.6);
          gl.glVertex3d(-0.1,0.1,1.6);
          gl.glVertex3d(-0.05,0.1,1.3);
          gl.glVertex3d(0.05,0.1,1.3);
          
          gl.glVertex3d(0.1,-0.1,1.6);
          gl.glVertex3d(-0.1,-0.1,1.6);
          gl.glVertex3d(-0.05,-0.1,1.3);
          gl.glVertex3d(0.05,-0.1,1.3);
          
          gl.glColor3d(0.4,0.4,0.0);
          
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
          gl.glColor3d(0.4,0.4,0.0);
          
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
          
          
          gl.glColor3d(0.8,0.8,0.0);
          
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
          
          
          gl.glColor3d(0.6,0.6,0.0);
          
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
        
        if(robot.isActive()) return;
        gl.glTranslated(0.0,0.0,2.0);
        gl.glScaled(0.4,0.4,0.4);
        int longs=8,lats=8;
        gl.glBegin(GL2.GL_QUADS);
        for(int i=0;i<=longs;i++)
          { 
            if((i&1)==0) gl.glColor3d(0.5,0.5,0.0);
            else  gl.glColor3d(0.0,0.0,0.0);
            double lng0=2*Math.PI*(double)(i-1)/longs;
            double lng1=2*Math.PI*(double)(i)/longs;
            double x0=Math.cos(lng0),y0=Math.sin(lng0);
            double x1=Math.cos(lng1),y1=Math.sin(lng1);
            gl.glVertex3d(x0,y0,0.0);
            gl.glVertex3d(x1,y1,0.0);
            gl.glVertex3d(x1/2,y1/2,-0.5);
            gl.glVertex3d(x0/2,y0/2,-0.5);
            
            gl.glVertex3d(x1/2,y1/2,-0.5);
            gl.glVertex3d(x0/2,y0/2,-0.5);
            gl.glVertex3d(x0/4,y0/4,-1.0);
            gl.glVertex3d(x1/4,y1/4,-1.0);
            
            for(int j=lats/2+1;j<=lats;j++)
              {
                double lat0=Math.PI*(-0.5+(double)(j-1)/lats);
                double z0=Math.sin(lat0);
                double zr0=Math.cos(lat0);
                double lat1=Math.PI*(-0.5+(double)j/lats);
                double z1=Math.sin(lat1);
                double zr1=Math.cos(lat1);
                if(((i^j)&1)==0) gl.glColor3d(1.0,1.0,0.0);
                else  gl.glColor3d(0.0,0.0,0.0);
                  
                gl.glVertex3d(x0*zr0,y0*zr0,z0);
                gl.glVertex3d(x0*zr1,y0*zr1,z1);
                  
                gl.glVertex3d(x1*zr1,y1*zr1,z1);
                gl.glVertex3d(x1*zr0,y1*zr0,z0);
              }
          }
        gl.glEnd();
      }
    
    void render(GL2 gl,Iterator<? extends IRobot> things)
      {
        while(things.hasNext())
          {
            IRobot thing=things.next();
            gl.glPushMatrix();
            gl.glTranslated(thing.getX(),thing.getY(),thing.getZ());
            draw(gl,thing);
            gl.glPopMatrix();
          }
      }
  }
