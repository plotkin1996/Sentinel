package sentinel.graphics;
import sentinel.gameplay.Gameplay;

import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.*;
import com.jogamp.newt.event.*;
import com.jogamp.opengl.math.VectorUtil;

public class Graphics implements GLEventListener
  {
    private Gameplay gameplay;
    private GHeightmap heightmap;
    private GTree gTree;
    private GStone gStone;
    GLU glu;
  
    public void attachGameplay(Gameplay gameplay)
      {
        this.gameplay=gameplay;
        heightmap.attach(gameplay.getIHeightmap());
      }
      
    public Graphics()
      {
        this.heightmap=new GHeightmap();
        this.gTree=new GTree();
        this.gStone=new GStone();
      }
    
    public void setOpenGLCaps(GLCapabilities caps)
      {
        caps.setSampleBuffers(true);
        caps.setNumSamples(4);
      }
    
    @Override
    public void init(GLAutoDrawable drawable)
      {
        glu=new GLU();
        GL2 gl = drawable.getGL().getGL2();
        gl.glEnable(GL.GL_MULTISAMPLE);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT,GL2.GL_NICEST);
        gl.glBlendFunc(GL.GL_SRC_ALPHA,GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glEnable(GL.GL_BLEND);
      }
     
    private void setUpFor3D(GL2 gl)
      {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(30.0,aspect,0.1,200.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
      }
    
    
    private void setUpForHUD(GL2 gl)
      {
        gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glOrtho(-1,1,-h,h,-1,1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
      }
     
    @Override
    public void display(GLAutoDrawable drawable)
      {
        gameplay.lock();
        gameplay.runLogic();
        if(gameplay==null) return;
        GL2 gl = drawable.getGL().getGL2();
        setUpFor3D(gl);
        GCamera.applyTransform(gl,gameplay.getICamera());
        heightmap.render(gl);
        GCursor.render(gl,gameplay.getICursor());
        gTree.render(gl,gameplay.getTrees());
        gStone.render(gl,gameplay.getStones());
        GRobot.render(gl,gameplay.getRobots());
        GSentinel.render(gl,gameplay.getSentinels());
        setUpForHUD(gl);
        HeadUpDisplay.render(gl,gameplay,-h);
        gl.glFlush();
        gameplay.unlock();
      }

    @Override
    public void dispose(GLAutoDrawable drawable)
      {
      }
    
    private int width, height;
    private double aspect,h;
    
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
      { 
        this.width=width;
        this.height=height;
        aspect = (double)width/(double)height;
        h = (double)height/(double)width;
        GL2 gl = drawable.getGL().getGL2();
      }
  }
