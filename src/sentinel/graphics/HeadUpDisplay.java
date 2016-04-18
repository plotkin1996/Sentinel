package sentinel.graphics;
import com.jogamp.opengl.*;
import sentinel.gameplay.Gameplay;

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
      
    static void drawPanel(GL2 gl,Gameplay gameplay,double pos)
      {
        gl.glColor3d(0.0,0.0,0.0);
        gl.glBegin(GL2.GL_QUADS);
          gl.glVertex2d(-1.0,pos);
          gl.glVertex2d(1.0,pos);
          gl.glVertex2d(1.0,pos+0.1);
          gl.glVertex2d(-1.0,pos+0.1);
        gl.glEnd();
        
        gl.glColor3d(0.0,1.0,0.0);
        gl.glBegin(GL2.GL_LINES);
          gl.glVertex2d(1,pos+0.1);
          gl.glVertex2d(-1,pos+0.1);
          gl.glVertex2d(1,pos+0.09);
          gl.glVertex2d(-1,pos+0.09);
        gl.glEnd();
        
        gl.glBegin(GL2.GL_TRIANGLES);
          for(double i=0;i<gameplay.getPlayerEnergy();i++)
            {
              gl.glVertex2d(-0.99+i/20,pos+0.02);
              gl.glVertex2d(-0.99+i/20+0.04,pos+0.02);
              gl.glVertex2d(-0.99+i/20+0.02,pos+0.08);
            }
        gl.glEnd();
      }
      
    static void drawOverlay(GL2 gl,Gameplay gameplay,double pos)
      {
        if(gameplay.isGameOver()) gl.glColor4d(1.0,0.0,0.0,0.7);
        else if(gameplay.isPlayerAlert()) gl.glColor4d(1.0,0.0,0.0,0.5);
        else if(gameplay.isWin()) gl.glColor4d(0.0,1.0,0.0,0.5);
        else if(gameplay.isPaused()) gl.glColor4d(0.5,0.5,0.5,0.5);
        else return;
        gl.glBegin(GL2.GL_QUADS);
          gl.glVertex2d(-1.0,-1.0);
          gl.glVertex2d(1.0,-1.0);
          gl.glVertex2d(1.0,1.0);
          gl.glVertex2d(-1.0,1.0);
        gl.glEnd();
      }
      
    static void render(GL2 gl,Gameplay gameplay,double pos)
      { 
        drawCrosshair(gl);
        drawPanel(gl,gameplay,pos);
        drawOverlay(gl,gameplay,pos);
      }
  }
