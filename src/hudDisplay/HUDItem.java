package hudDisplay;

import java.awt.Graphics2D;

public abstract class HUDItem {

	public Stat myStat;

	public abstract void render(Graphics2D g);

	public abstract void update(int HUDX, int HUDY,long elapsedTime);

}
