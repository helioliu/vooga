package hudDisplay;

import SpriteAction.ChangehealthAction;
import SpriteAction.JumpAction;
import SpriteAction.MoveLeft;
import SpriteAction.MoveRight;
import SpriteAction.StandAction;

public class HUDEvents extends HUDEventManager {
	
	public HUDEvents(HeadsUpDisplay hud){
		super(hud);
		myMap.put("got hit", new DecrementBarAction(hud));
		myMap.put("health change",new ChangehealthAction(hud));
		super.activateAllListeners();
//		myMap.put("score change", new changeScoreAction(s));
	}

}
