package core;

import java.awt.Graphics2D;

public interface VoogaManager {

	public void update(long elapsedTime);
	
	public void render(Graphics2D g);
	
	public EventManager getEventManager();
	
}
