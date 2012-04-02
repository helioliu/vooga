package collisions;

public interface CollisionShape {
    
    /**
     * returns the shape type for collision checking
     */
    public String getShape();
    
    /**
     * Checks if this shape intersects/overlaps another shape
     */
    public boolean intersects(CollisionShape cs);
    
    /**
     * Set the reference point for the object
     */
    public void setReference(double x, double y);
    
    
    /**
     * shifts the position of the reference point by the given values
     */
    public void move(double dx, double dy);
    
    /**
     * Sets the boundary of this collision shape to specified boundary.
     */
    //public void setBounds(double x1, double y1, int w1, int h1);
    
    /**
     * Returns the <code>x</code>-reference of this collision shape.
     */
    public double getX();
    
    /**
     * Returns the <code>y</code>-reference of this collision shape.
     */
    public double getY();
    
    //setBounds, getDimentions

}
