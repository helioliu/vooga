package hudDisplay;

import java.awt.Graphics2D;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.golden.gamedev.object.GameFont;

public class TimerItem extends TextItem {

	public TimerItem(GameFont scoreFont, int X, int Y, Stat stat) {
		super(scoreFont, X, Y, stat);
	}

	@Override
	public void render(Graphics2D g) {
		if (isHUDactive()) {
			Date date = new Date((long) getMyStat().getMyValue());
			DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
			String dateFormatted = formatter.format(date);
			getMyScoreFont().drawString(g, dateFormatted,getHUDX() + getMyX(), getHUDY() + getMyY());
		}
	}

}
