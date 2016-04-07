package sentinel.gameplay;
import sentinel.representation.ICursor;

class Cursor implements ICursor
  {
    private Platform platform;
    void pointAt(Platform platform) {this.platform=platform;}
    Platform getPlatform() {return platform;}
    public boolean isEnabled(){return platform!=null;}
    public int getX() {if(isEnabled()) return platform.getX();else return 0;}
    public int getY() {if(isEnabled()) return platform.getY();else return 0;}
    public double getZ() {if(isEnabled()) return platform.getZ();else return 0;}
  }
