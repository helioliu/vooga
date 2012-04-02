package otherSprites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cutscene {
	public List<Action> mySequence;
	public static enum Action {
		STALL,
		JUMP,
		MOVE_RIGHT,
		MOVE_LEFT,
		STAND_STILL
	}
	
	public Cutscene() {
		mySequence = new ArrayList<Action>();
	}
	
	public void addAction(Action a) {
		mySequence.add(a);
	}
	
	public ArrayList<Action> getSequence() {
		return (ArrayList<Action>) Collections.unmodifiableList(mySequence);
	}
}
