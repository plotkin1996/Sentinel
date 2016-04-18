package sentinel.gameplay;

class MutableDouble
  {
    private double value;
    public void put(double val) {value=val;}
    public double get() {return value;}
    public MutableDouble(double val) {value=val;}
  }
