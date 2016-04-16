package sentinel.gameplay;
import java.util.ArrayList;

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
      
    private ArrayList<Thing> things;
    double stackHeight;
    public int thingCount() {return things.size();}
    public Thing topThing() {return things.get(thingCount()-1);}
    
    public boolean isFree()
      {return thingCount()==0;}
    
    public boolean build(Thing thing)
      {
        if(thingCount()!=0&&!topThing().isStackable()) return false;
        things.add(thing);
        thing.place(this,stackHeight+z);
        stackHeight+=thing.getHeight();
        return true;
      }
    
    public int consume()
      {
        if(thingCount()==0) return 0;
        int energy=topThing().consume();
        if(energy<0) return 0;
        stackHeight-=topThing().getHeight();
        things.remove(thingCount()-1);
        return energy;
      }
    
    Platform(int x,int y,double z)
      {
        this.x=x;this.y=y;this.z=z;
        things=new ArrayList<Thing>();
      }
  }
