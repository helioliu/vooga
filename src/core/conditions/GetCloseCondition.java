package core.conditions;

import com.golden.gamedev.object.Sprite;

import core.Condition;

public class GetCloseCondition implements Condition {
	Sprite one;
	Sprite two;
	int howFar;
	boolean near;
	
	public GetCloseCondition(Sprite one, Sprite two, int howFar, boolean near) {
		this.one = one;
		this.two = two;
		this.howFar = howFar;
		this.near = near;
	}

	public boolean conditionTrue() {
		if(one.getDistance(two)>howFar){
			return !near;
		}
		return near;
	}

	public void reset() {
	}
}
