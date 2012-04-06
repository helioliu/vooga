package hudDisplay;

public abstract class HUDDecorator implements HUDInterface {

	private HUDInterface myHUDDisplay;

	public HUDDecorator(HUDInterface hd) {
		this.myHUDDisplay = hd;
	}

}
