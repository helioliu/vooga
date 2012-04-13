package collisions;

/**
 * Associates a shape and an event
 * May end up just being a wrapper for a shape once we move the events
 * LET IT BE KNOWN THAT HITBOX REGIONS OVERRIDE NORMAL SPRITE REGIONS
 * (or something...)
 */
public class Hitbox {
    
   /**
    * The shape of the hitbox relative to its owner sprite
    */
   private CollisionShape myShape;
   
   /**
    * The event triggered by collisions with this hitbox
    * This will be removed in the future once the event system
    * is finalized, and events will be generated upon collisions
    */
   private String myEvent;
   
   /**
    * The identifier of the hitbox (intuitively the name of the
    * region the hitbox covers, such as "eyes" or "chocolate")
    * Or like, it can just be a number corresponding to its index
    * in its owner sprite's hitbox list
    */
   private String myID;
   
   public Hitbox(CollisionShape shape, String group, String ID){
       myShape = shape;
       myEvent = group;
       myID = ID;
   }
   
   /**
    * Changes the shape of the hitbox from its previous
    */
   public void setShape(CollisionShape shape){
       myShape = shape;
   }
   /**
    * Changes the triggered event
    * Will be removed upon finalization of the event system
    */
   public void setEvent(String event){
       myEvent = event;
   }
   
   /**
    * Retrieves the shape
    */
   public CollisionShape getShape(){
       return myShape;
   }
   /**
    * Retrieves the triggered event
    */
   public String getEvent(){
       return myEvent;
   }
   
   /**
    * Get an identifier for the hitbox
    */
   public String getID(){
	   return myID;
   }

}
