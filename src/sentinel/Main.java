package sentinel;
import sentinel.gameplay.Gameplay;
import sentinel.graphics.Graphics;

import com.jogamp.opengl.*;
import com.jogamp.newt.event.*;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.util.FPSAnimator;
 
public class Main
{
  static {GLProfile.initSingleton();}
  
  public static void main(String[] args) 
    {
      Graphics graphics=new Graphics();
      InputHandler inputHandler=new InputHandler();
      
      Gameplay gameplay=new Gameplay();
      graphics.attachGameplay(gameplay);
      inputHandler.attachGameplay(gameplay);
      
      GLProfile glp=GLProfile.getDefault();
      GLCapabilities caps=new GLCapabilities(glp);
      graphics.setOpenGLCaps(caps);
      GLWindow window=GLWindow.create(caps);
      FPSAnimator animator=new FPSAnimator(window,60,true);
      window.addWindowListener(new WindowAdapter()
        {
          @Override
          public void windowDestroyNotify(WindowEvent arg0)
            {
              if(animator.isStarted()) animator.stop();
              System.exit(0);
            }
        });
      window.setTitle("Sentinel");
      window.setVisible(true); 
      window.setMaximized(true,true);
      window.setPointerVisible(false);
      window.addGLEventListener(graphics);
      inputHandler.attachWindow(window);
      window.addMouseListener(inputHandler);
      window.addKeyListener(inputHandler);
      animator.start();
    }
}
