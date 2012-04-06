package collisions;

public class CollisionCirc implements CollisionShape{
    //X and Y references are the top left of the square
    //circumscribing the circle
    private double myX, myY, myR;
    private static final String myShape = "rectangle";

    public CollisionCirc(double x, double y, double r){
        myX = x;
        myY = y;
        myR = r;
    }
    
    @Override
    public String getShape(){
        return myShape;
    }
    
    @Override
	public boolean intersects(CollisionShape cs){
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
    public double getXCenter(){
        return myX + myR;
    }

    @Override
	public double getY() {
        return myY;
    }
    public double getYCenter(){
        return myY + myR;
    }
    
    public double getR(){
        return myR;
    }

    public void setBounds(double x, double y, double r) {
        myX = x;
        myY = y;
        myR = r;
    }
    
    public void setX(double x){
        myX = x;
    }
    
    public void setY(double y){
        myY = y;
    }
    
    public void setR(double r){
        myR = r;
    }


}
