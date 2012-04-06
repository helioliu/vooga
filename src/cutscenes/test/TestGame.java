package cutscenes.test;

import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.util.List;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

import core.EventListener;
import core.EventManager;

import cutscenes.AutomatedAction;
import cutscenes.BadFileFormatException;
import cutscenes.Cutscene;
import cutscenes.CutsceneTrigger;
import cutscenes.SpriteAutomation;

public class TestGame extends Game {
	static SpriteAutomation automation;
	Cutscene cutscene;
	

	@Override
	public void initResources() {
		EventManager manager = new EventManager();
		Sprite s = new Sprite();
		try {
			automation = new SpriteAutomation(null, "C:/Users/Michael/Workspace/vooga/src/cutscenes/test/testCutsceneScript.script");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BadFileFormatException e) {
			e.displayMessage();
		}
		
		cutscene = new Cutscene();
		cutscene.addSpriteAutomation(automation);
		CutsceneTrigger trigger = new CutsceneTrigger(cutscene);
		trigger.triggerCutscene();
		
		EventListener listener = new TestListener();
		EventManager.getEventManager().registerEventListener("up-key", listener);
		EventManager.getEventManager().registerEventListener("left-key", listener);
		EventManager.getEventManager().registerEventListener("right-key", listener);
		
		trigger.triggerCutscene();
		
	}

	@Override
	public void render(Graphics2D arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long arg0) {
		cutscene.update(arg0);
		EventManager.getEventManager().update(arg0);
	}
}
