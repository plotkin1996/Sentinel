package sentinel;

import com.jogamp.newt.event.*;
import sentinel.gameplay.Gameplay;
import com.jogamp.newt.opengl.GLWindow;

class InputHandler implements MouseListener, KeyListener
  {
    private Gameplay gameplay;
    void attachGameplay(Gameplay gameplay)
      {this.gameplay=gameplay;}
      
    private GLWindow window;
    void attachWindow(GLWindow window)
      {
        this.window=window;
        window.warpPointer(window.getWidth()/2,window.getHeight()/2);
      }

//==============================================================
    public void keyPressed(KeyEvent e) {}
    
    public void keyReleased(KeyEvent e)
      { 
        switch(e.getKeyCode())
          {
            case KeyEvent.VK_ESCAPE:window.destroy();break;
            case KeyEvent.VK_SPACE:gameplay.consume();break;
            case KeyEvent.VK_1:gameplay.buildTree();break;
            case KeyEvent.VK_2:gameplay.buildStone();break;
            case KeyEvent.VK_3:gameplay.buildRobot();break;
            case KeyEvent.VK_P:gameplay.togglePause();break;
          }
      }

//==============================================================
    public void mouseClicked(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {} 
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {gameplay.incarnate();}
       
    public void mouseReleased(MouseEvent e) {}
    public void mouseWheelMoved(MouseEvent e) {}
    
    public void mouseMoved(MouseEvent e)
      {
        double dx=-(double)(e.getX()-window.getWidth()/2)/10;
        double dy=-(double)(e.getY()-window.getHeight()/2)/10;
        window.warpPointer(window.getWidth()/2,window.getHeight()/2);
        gameplay.look(dx,dy);
      }
  }
