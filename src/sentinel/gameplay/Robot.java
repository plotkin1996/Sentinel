package sentinel.gameplay;

import sentinel.representation.IRobot;
import sentinel.util.MutableInt;

class Robot extends SimpleThing implements IRobot
  {
    private Player player;
    public boolean isActive() {return player!=null;}
    public void setPlayer(Player player) {this.player=player;}
    public void deactivate() {this.player=null;}
    
    private double cameraHeight;
    public double getCameraHeight() {return cameraHeight;}
    
    public double pick
        (double rpx,double rpy,double rpz,
        double rx,double ry,double rz)
      {
        if(isActive()) return Double.POSITIVE_INFINITY;
        double dpx=getX()-rpx;
        double dpy=getY()-rpy;
        double dpz=getZ()+2.0-rpz;
        double lpdp=rx*dpx+ry*dpy+rz*dpz;
        double D=lpdp*lpdp-(dpx*dpx+dpy*dpy+dpz*dpz)+0.16;
        if(D<0) return Double.POSITIVE_INFINITY;
        double dist=-Math.min(-lpdp-Math.sqrt(D),-lpdp+Math.sqrt(D));
        if(dist<0) return Double.POSITIVE_INFINITY;
        if(rpz+rz*dist-getZ()>=2.0) return dist;
        dist=-Math.max(-lpdp-Math.sqrt(D),-lpdp+Math.sqrt(D));
        if(dist<0) return Double.POSITIVE_INFINITY;
        if(rpz+rz*dist-getZ()>=2.0) return dist;
        return Double.POSITIVE_INFINITY;
      }
    
    @Override
    public int consume()
      {
        if(isActive()) return -1;
        return super.consume();
      }
    
    @Override
    public boolean eliminate(MutableInt e)
      {
        if(!isActive()) return super.eliminate(e);
        player.eliminate();
        e.put(-1);
        return false;
      }
    
    Robot(IThingBag bag,int id,double cameraHeight,int energy)
      {super(bag,id,false,0.0,energy);this.cameraHeight=cameraHeight;}
  }
