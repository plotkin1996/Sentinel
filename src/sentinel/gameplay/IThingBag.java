package sentinel.gameplay;

interface IThingBag
  {
    public boolean build(Platform platform);
    public void remove(int id);
    public int getEnergy();
  }
