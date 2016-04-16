package sentinel.gameplay;

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
    public int consume() {super.consume();return energy;}
    
    SimpleThing(IThingBag bag,int id,boolean isStackable,double height,int energy)
      {
        super(bag,id);
        this.isStackable=isStackable;
        this.height=height;
        this.energy=energy;
      }
  }
