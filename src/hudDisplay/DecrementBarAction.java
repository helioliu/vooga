package hudDisplay;

import sprites.TestCharacterWithStates;

import com.golden.gamedev.object.Sprite;

import core.EventManager;


public class DecrementBarAction extends HUDAction {

	public DecrementBarAction(TestCharacterWithStates s) {
		super(s);
		EventManager em = EventManager.getEventManager();
		em.registerEventListener("got hit", this);
	}

	@Override
	public void actionPerformed(String eventName) {
		HeadsUpDisplay HUD = mySprite.getHUD();
		BarDisplay healthbar = (BarDisplay) HUD.getMyAttributes().get("healthbar");
		healthbar.AdjustBar(1);
		
	}

}
