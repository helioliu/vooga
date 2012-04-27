package hudDisplay;
import sprites.BryanSprite;

import core.EventListener;



public abstract class HUDAction implements EventListener{
	protected HeadsUpDisplay hud;
	
	public HUDAction( HeadsUpDisplay hud)
	{
		this.hud = hud;
		
	}
	
	public abstract void actionPerformed(String event);

}