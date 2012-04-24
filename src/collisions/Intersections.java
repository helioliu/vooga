package collisions;

/**
 * Group of static methods to determine intersections between
 * different types of shapes. Going from (n) to (n+1) unique
 * types of shapes necessary adds (n+1) new intersection
 * checks. Shapes represent elements, so visitor is inconvenient,
 * so we just stuff everything in here. At least this way,
 * adding a new shape only means adding a shape class and the
 * (n+1) intersection types into here (and adding to the if
 * tree <_< )
 */
public class Intersections {
    
	/**
	 * We use reflection to check types to call the appropriate
	 * methods (as opposed to the old method of super cool if
	 * statements
	 */
    public static boolean checkIntersect(CollisionShape cs1, CollisionShape cs2){
    	String s1 = cs1.getShape();
    	String s2 = cs2.getShape();
        if(s1=="circle"){
            if(s2=="circle")
                return intersects((CollisionCirc)cs1, (CollisionCirc)cs2);
            if(s2=="rectangle")
                return intersects((CollisionCirc)cs1, (CollisionRect)cs2);
        }
        if(s1=="rectangle"){
            if(s2=="circle")
                return intersects((CollisionCirc)cs2, (CollisionRect) cs1);
            if(s2=="rectangle")
                return intersects((CollisionRect)cs1, (CollisionRect)cs2);
        }
        
        return false;
    }

    /**
     * Checks intersections between a circle and a circle.
     * Checks if the distance between the two centers is less than
     * the sum of the radii of the two circles
     */
	private static boolean intersects(CollisionCirc cc1, CollisionCirc cc2){
        double dist = getDist(cc1.getXCenter(), cc1.getYCenter(), cc2.getXCenter(), cc2.getYCenter());
        return (dist < cc1.getR() + cc2.getR());
    }
	
	/**
	 * Checks intersections between a rectangle and a rectangle.
	 * Checks if each x/y border of a rectangle is between the two
	 * x/y borders of the other
	 */
	private static boolean intersects(CollisionRect cr1, CollisionRect cr2){
	    return (cr1.getX()+cr1.getW()>cr2.getX() && 
	            cr1.getX()<cr2.getX()+cr2.getW() && 
	            cr1.getY()+cr1.getH()>cr2.getY() && 
	            cr1.getY()<cr2.getY()+cr2.getH());
	}

	/**
	 * Checks intersections between a circle and a rectangle.
	 * Checks either if the circle is inside the rectangle or if
	 * any of the corners of the rectangle are inside the circle.
	 */
	private static boolean intersects(CollisionCirc cc, CollisionRect cr){
        //case circle inside rectangle
        if( cc.getXCenter()>cr.getX() && cc.getXCenter()<cr.getX() + cr.getW() &&
            cc.getYCenter()>cr.getY() && cc.getYCenter()<cr.getY() + cr.getH() )
            return true;
        
        //case any side of the circle inside rectangle
        //top or bottom of circle
        if( cc.getXCenter() > cr.getX() && cc.getXCenter() < cr.getX()+cr.getW()){
        	if( cc.getYCenter()-cc.getR() > cr.getY() &&
        			cc.getYCenter()-cc.getR() < cr.getY()+cr.getH())
        		return true;
        	if( cc.getYCenter()+cc.getR() > cr.getY() &&
        			cc.getYCenter()+cc.getR() < cr.getY()+cr.getH())
        		return true;
        }
        //left or right of circle
        if( cc.getYCenter() > cr.getY() && cc.getYCenter() < cr.getY()+cr.getH()){
        	if( cc.getXCenter()-cc.getR() > cr.getX() &&
        			cc.getXCenter()-cc.getR() < cr.getX()+cr.getW())
        		return true;
        	if( cc.getXCenter()+cc.getR() > cr.getX() &&
        			cc.getXCenter()+cc.getR() < cr.getX()+cr.getW())
        		return true;
        }
        
        
        //case any rectangle corner inside circle
        //top left
        double dist1 = getDist(cc.getXCenter(), cc.getYCenter(), cr.getX(), cr.getY());
        if(dist1<cc.getR())
            return true;
        //top right
        double dist2 = getDist(cc.getXCenter(), cc.getYCenter(), cr.getX()+cr.getW(), cr.getY());
        if(dist2<cc.getR())
            return true;        
        //bottom left
        double dist3 = getDist(cc.getXCenter(), cc.getYCenter(), cr.getX(), cr.getY()+cr.getH());
        if(dist3<cc.getR())
            return true;
        //bottom right
        double dist4 = getDist(cc.getXCenter(), cc.getYCenter(), cr.getX()+cr.getW(), cr.getY()+cr.getH());
        if(dist4<cc.getR())
            return true;
        
        return false;
    }
    
	/**
	 * A SQUARED PLUS B SQUARED EQUALS C SQUARED LOLOLOLOLOL
	 */
    private static double getDist(double x1, double y1, double x2, double y2){
        return Math.sqrt( Math.pow(x1-x2,2) + Math.pow(y1-y2,2) );
    }

}
