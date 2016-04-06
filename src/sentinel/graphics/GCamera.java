package sentinel.graphics;

import sentinel.representation.ICamera;
import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.*;

class GCamera
  {
    private ICamera camera;
    
    void attach(ICamera camera)
      {this.camera=camera;}
      
    void applyTransform(GL2 gl)
      {
        gl.glRotated(-camera.getPitch(),1.0,0.0,0.0);
        gl.glRotated(-camera.getYaw(),0.0,0.0,1.0);
        gl.glTranslated(-camera.getXPos(),-camera.getYPos(),-camera.getZPos());
      }
  }
