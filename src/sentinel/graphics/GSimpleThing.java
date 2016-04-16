package sentinel.graphics;

import sentinel.representation.IThing;
import java.util.Iterator;
import com.jogamp.opengl.*;

abstract class GSimpleThing
  {
    abstract void draw(GL2 gl,IThing thing);
    
    void render(GL2 gl,Iterator<? extends IThing> things)
      {
        while(things.hasNext())
          {
            IThing thing=things.next();
            gl.glPushMatrix();
            gl.glTranslated(thing.getX(),thing.getY(),thing.getZ());
            draw(gl,thing);
            gl.glPopMatrix();
          }
      }
  }
