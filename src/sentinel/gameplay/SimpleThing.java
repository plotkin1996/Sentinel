package sentinel.gameplay;
import sentinel.util.MutableInt;

class SimpleThing extends Thing
  {
    private boolean isStackable;
    @Override
    boolean isStackable() {return isStackable;}
    
    private double height;
    @Override
    double getHeight() {return height;}
    
    private int energy;
    @Override
    public int consume() {super.consume();return getEnergy();}
    @Override
    public int getEnergy() {return energy;}
    @Override
    public boolean isProtected() {return false;}
    
    @Override
    public boolean eliminate(MutableInt e)
      {
        remove();
        e.put(getEnergy());
        return true;
      }
    
    SimpleThing(IThingBag bag,int id,boolean isStackable,double height,int energy)
      {
        super(bag,id);
        this.isStackable=isStackable;
        this.height=height;
        this.energy=energy;
      }
  }
