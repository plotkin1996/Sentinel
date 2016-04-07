package sentinel.util;

public class DDA
  {
    private int mx,my;
    public int getX() {return mx;}
    public int getY() {return my;}
    
    private double rpx,rpy,rdx,rdy,sdx,sdy,ddx,ddy;
    private int sx,sy;
    
    private void init()
      {
        ddx=Math.sqrt(1.0+(rdy*rdy)/(rdx*rdx));
        ddy=Math.sqrt(1.0+(rdx*rdx)/(rdy*rdy));
        
        if(rdx<0) {sx=-1;sdx=(rpx-mx)*ddx;}
        else {sx=1;sdx=(1.0+mx-rpx)*ddx;}
        
        if(rdy<0) {sy=-1;sdy=(rpy-my)*ddy;}
        else {sy=1;sdy=(1.0+my-rpy)*ddy;}
      }
    
    public void step()
      {
        if(sdx<sdy) {sdx+=ddx;mx+=sx;}
        else {sdy+=ddy;my+=sy;}
      }
    
    public DDA(double rpx,double rpy,double rdx,double rdy)
      {
        this.mx=(int)Math.floor(rpx);this.my=(int)Math.floor(rpy);
        this.rdx=rdx;this.rdy=rdy;
        this.rpx=rpx;this.rpy=rpy;
        init();
      }
  }
