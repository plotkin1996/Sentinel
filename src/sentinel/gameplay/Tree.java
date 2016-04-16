package sentinel.gameplay;

class Tree extends Thing
  {
    @Override
    boolean isStackable() {return false;}
    
    @Override
    double getHeight() {return 1.5;}
    
    @Override
    public int consume() {super.consume();return 1;}
    
    Tree(IThingBag bag,int id)
      {super(bag,id);}
  }
