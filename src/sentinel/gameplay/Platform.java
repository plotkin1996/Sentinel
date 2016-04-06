package sentinel.gameplay;

class Platform implements Comparable<Platform>
  {
    private int x,y;
    public int getX() {return x;}
    public int getY() {return y;}
    private double z;
    public double getZ() {return z;}
    
    @Override
    public int compareTo(Platform o)
      {return (int)Math.signum(getZ()-o.getZ());}
    
    Platform(int x,int y,double z)
      {this.x=x;this.y=y;this.z=z;}
  }
