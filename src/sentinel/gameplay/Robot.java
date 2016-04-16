package sentinel.gameplay;

import sentinel.representation.IRobot;

class Robot extends SimpleThing implements IRobot
  {
    boolean isActive=false;
    public boolean isActive() {return isActive;}
    public void activate() {isActive=true;}
    public void deactivate() {isActive=false;}
    
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
    
    Robot(IThingBag bag,int id,double height,int energy)
      {super(bag,id,false,height,energy);}
  }
