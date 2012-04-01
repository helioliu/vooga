package collisions;

//these algorithms have yet to be tested
public class Intersections {
    
    public static boolean intersects(CollisionShape cs1, CollisionShape cs2){
        if(cs1.SHAPE=="circle"){
            if(cs2.SHAPE=="circle")
                return circCirc((CollisionCirc)cs1, (CollisionCirc)cs2);
            if(cs2.SHAPE=="rectangle")
                return circRect((CollisionCirc)cs1, (CollisionRect)cs2);
        }
        if(cs1.SHAPE=="rectangle"){
            if(cs2.SHAPE=="circle")
                return circRect((CollisionCirc)cs2, (CollisionRect) cs1);
            if(cs2.SHAPE=="rectangle")
                return rectRect((CollisionRect)cs1, (CollisionRect)cs2);
        }
        
        return false;
    }
    
    private static boolean rectRect(CollisionRect cr1, CollisionRect cr2){
        return (   cr1.getX()+cr1.getW()>cr2.getX()
                && cr1.getX()<cr2.getX()+cr2.getW()
                && cr1.getY()+cr1.getH()>cr2.getY()
                && cr1.getY()<cr2.getY()+cr2.getH());
    }
    
    private static boolean circCirc(CollisionCirc cc1, CollisionCirc cc2){
        double dist;
        dist = getDist(cc1.getXCenter(), cc1.getYCenter(), cc2.getXCenter(), cc2.getYCenter());
        return (dist < cc1.getR() + cc2.getR());
    }
    
    private static boolean circRect(CollisionCirc cc, CollisionRect cr){
        /*
         * holy crap what do
         */
        return false;
    }
    
    private static double getDist(double x1, double y1, double x2, double y2){
        double dist = Math.sqrt( Math.pow(x1-x2,2) + Math.pow(y1-y2,2) );
        return dist;
    }

}
