package otherSprites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import States.CutsceneState;
import States.CutsceneState.Action;

import com.golden.gamedev.object.Sprite;

public class CutSceneTrigger extends Sprite {
	public Cutscene myCutscene;
	public CutSceneTrigger(Cutscene c) {
		myCutscene = c;
	}
	
	public void triggerCutscene(Sprite s) {
		s.setState(new CutsceneState(myCutscene));
	}
	
}
