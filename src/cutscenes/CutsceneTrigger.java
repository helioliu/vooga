package cutscenes;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import core.EventManager;

public class CutsceneTrigger extends Sprite {
	
	public CutsceneTrigger(BufferedImage i) {
		super(i);
	}
	
	public void triggerCutscene() {
		EventManager.getEventManager().sendEvent("cutscene-begin");
	}
	
	
}
