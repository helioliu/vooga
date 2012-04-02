package collisions;

public class CollisionRect implements CollisionShape{
    
    //references are the top left corner
    private double myX, myY, myH, myW;
    public static final String SHAPE = "rectangle";
    
    public CollisionRect(double x, double y, double h, double w){
        myX = x;
        myY = y;
        myH = h;
        myW = w;
    }

    @Override
	public boolean intersects(CollisionShape cs) {
        return Intersections.intersects(this, cs);
    }

    @Override
	public void setReference(double x, double y) {
        myX = x;
        myY = y;
    }

    @Override
	public void move(double dx, double dy) {
        myX += dx;
        myY += dy;
    }

    @Override
	public double getX() {
        return myX;
    }

    @Override
	public double getY() {
        return myY;
    }
    
    public double getH(){
        return myH;
    }
    
    public double getW(){
        return myW;
    }
    
    public void setBounds(double x, double y, double h, double w){
        myX = x;
        myY = y;
        myH = h;
        myW = w;
    }
    
    public void setX(double x){
        myX = x;
    }
    
    public void setY(double y){
        myY = y;
    }
    
    public void setH(double h){
        myH = h;
    }
    
    public void setW(double w){
        myW = w;
    }

}
