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
        gl.glRotatef(-camera.getPitch(),1.0f,0.0f,0.0f);
        gl.glRotatef(-camera.getYaw(),0.0f,0.0f,1.0f);
        gl.glTranslatef(-camera.getXPos(),-camera.getYPos(),-camera.getZPos());
      }
  }
