package sentinel.gameplay;

import java.util.HashMap;
import java.util.Collection;

class TreeBag implements IThingBag
  {
    private HashMap<Integer,Tree> things;
    private int ctr;
    
    public boolean build(Platform platform)
      {
        Tree tree=new Tree(this,ctr);
        if(!platform.build(tree)) return false;
        things.put(ctr++,tree);
        return true;
      }
    
    public Collection<Tree> getThings()
      {return things.values();}
    
    public void remove(int id)
      {
        things.remove(id);
      }
    
    Gameplay gameplay;
    TreeBag(Gameplay gameplay)
      {
        this.gameplay=gameplay;
        things=new HashMap<Integer,Tree>();ctr=0;
      }
  }
