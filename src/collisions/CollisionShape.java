package collisions;

/**
 * An interface for Collision Shapes that are used to define
 * boundaries for Sprite-internal Hitboxes.
 * These are distinct from GTGE's internal CollisionShapes in
 * that the interface provides different methods for greater
 * extensibility, and more intersection checking between
 * distinct shapes are supported (twice as many!)
 *
 */
public interface CollisionShape {
    
    /**
     * returns the shape type for collision checking
     */
    public String getShape();
    
    /**
     * returns a copy of the shape for use with intersection checking etc
     */
    public CollisionShape clone();
    
    /**
     * Checks if this shape intersects/overlaps another shape.
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
    
    /**
     * Returns the <code>x</code>-reference of this collision shape.
     */
    public double getX();
    
    /**
     * Returns the <code>y</code>-reference of this collision shape.
     */
    public double getY();
    
}
