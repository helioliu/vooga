package sprites;

import java.util.HashMap;


import hudDisplay.Stat;


import States.RegularMotionState;

import States.State;


@SuppressWarnings("serial")
public class Bens_TestSprite extends StateSprite{
    
    
    public Bens_TestSprite()
    {
        super();

        State s1 = new RegularMotionState(this);
        setGravity(0.000);
        getStateManager().addState(s1);
       
        setMyStats(new HashMap<String, Stat>());    
 
        
    }
    
    

    public void update(long elapsedTime)
    {
        super.update(elapsedTime);
        this.addVerticalSpeed(elapsedTime, getGravity(), 0.5);
    }



    

}
