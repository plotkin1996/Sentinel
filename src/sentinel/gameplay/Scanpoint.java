package sentinel.gameplay;

import java.util.*;
import java.util.LinkedList;


class Scanpoint implements Comparable<Scanpoint>
  {
    private Platform target;
    Platform getTarget() {return target;}
    
    private double yaw;
    double getYaw() {return yaw;}
    
    @Override
    public int compareTo(Scanpoint o)
      {return ((Double)getYaw()).compareTo(o.getYaw());}
    
    Scanpoint(Platform target,double yaw)
      {this.target=target;this.yaw=yaw;}
    
    static ArrayList<Scanpoint> buildScanlist(Heightmap heightmap,double rpx,double rpy,double rpz)
      {
        ArrayList<Scanpoint> scanlist=new ArrayList<Scanpoint>();
        for(Platform target:heightmap.getPList())
          {
            double rx=target.getX()+0.5-rpx;
            double ry=target.getY()+0.5-rpy;
            double rz=target.getZ()-rpz;
            Double yaw=Math.atan2(-rx,ry)*180/Math.PI;
            if(yaw<0) yaw+=360;
            if(heightmap.pick(rpx,rpy,rpz,rx,ry,rz,null)==target)
              scanlist.add(new Scanpoint(target,yaw));
          }
        Collections.sort(scanlist);
        return scanlist;
      }
  }
