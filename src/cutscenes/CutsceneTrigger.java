package cutscenes;

import com.golden.gamedev.object.Sprite;

import core.EventManager;

public class CutsceneTrigger extends Sprite {
	private Cutscene myCutscene;
	
	public CutsceneTrigger(Cutscene cutscene) {
		myCutscene = cutscene;
	}
	
	public Cutscene getCutscene() {
		return myCutscene;
	}
	
	public void triggerCutscene() {
		EventManager.getEventManager().sendEvent("cutscene-begin");
	}
	
	
}
