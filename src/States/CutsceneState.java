package States;

import java.util.ArrayList;

import otherSprites.Cutscene;
import otherSprites.Cutscene.Action;

import com.golden.gamedev.object.Sprite;

public class CutsceneState implements State {
	private ArrayList<Action> mySequence;
	private Cutscene myCutscene;
	private int currentPosition;
	private Sprite mySprite;

	public CutsceneState(Cutscene c) {
		currentPosition = 0;

	}

	public void UpKey(Sprite s) {
	}

	public void DownKey(Sprite s) {
	}

	public void RightKey(Sprite s) {
	}

	public void LeftKey(Sprite s) {
	}

	public void SpaceKey(Sprite s) {
	}

	public void collide() {
		// Do collide function here
	}

	public void update(Sprite s) {

	}

}
