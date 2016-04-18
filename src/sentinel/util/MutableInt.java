package sentinel.util;

public class MutableInt
  {
    private int value;
    public void put(int val) {value=val;}
    public int get() {return value;}
    public MutableInt(int val) {value=val;}
  }
