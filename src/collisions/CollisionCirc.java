package collisions;

/**
 * This represents a circle.
 * The references are the top left of the square
 * circumscribing the circle (the circle's center
 * coordinates are calculated and stored as well
 * @author herio rin
 *
 */
public class CollisionCirc implements CollisionShape{
    //X and Y references are the top left of the square
    //circumscribing the circle
    private double myX, myY, myR;
    private static final String myShape = "circle";
    private double myXC, myYC;

    public CollisionCirc(double x, double y, double r){
        myX = x;
        myY = y;
        myR = r;
        updateCenter();
    }
    
    @Override
    public String getShape(){
        return myShape;
    }
    
    @Override
    public CollisionShape clone(){
    	return new CollisionCirc(myX, myY, myR);
    }
    
    @Override
	public boolean intersects(CollisionShape cs){
    	return Intersections.checkIntersect(this, cs);
    }
    
    @Override
	public void setReference(double x, double y) {
        myX = x;
        myY = y;
        updateCenter();
    }

    @Override
	public void move(double dx, double dy) {
        myX += dx;
        myY += dy;
        updateCenter();
    }

    @Override
	public double getX() {
        return myX;
    }
    public double getXCenter(){
        return myXC;
    }

    @Override
	public double getY() {
        return myY;
    }
    public double getYCenter(){
        return myYC;
    }
    
    public double getR(){
        return myR;
    }

    public void setBounds(double x, double y, double r) {
        myX = x;
        myY = y;
        myR = r;
        updateCenter();
    }
    
    public void setX(double x){
        myX = x;
        updateCenter();
    }
    
    public void setY(double y){
        myY = y;
        updateCenter();
    }
    
    public void setR(double r){
        myR = r;
        updateCenter();
    }
    
    /**
     * Updates the coordinates for the center of the circle.
     * We store the center coordinates instead of calculating
     * them on call because we create far fewer circles than
     * we request these coordinates.
     */
    private void updateCenter(){
    	myXC = myX + myR;
    	myYC = myY + myR;
    }


}
