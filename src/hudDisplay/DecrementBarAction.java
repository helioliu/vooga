package hudDisplay;

import core.EventManager;

public class DecrementBarAction extends HUDAction {

	public DecrementBarAction(HeadsUpDisplay hud) {
		super(hud);
		EventManager em = EventManager.getEventManager();
		em.registerEventListener("got hit", this);
	}

	@Override
	public void actionPerformed(String eventName) {
		for (HUDItem HUDItem : hud.getHUDItems()) {
			int oldScore = HUDItem.getItemScore();
			int newScore = HUDItem.getAssociatedSprite().getScore(
					HUDItem.getScoreID());

			if (oldScore != newScore) {
				HUDItem.adjust(newScore);
			}
		}
	}

	@Override
	public void actionPerformed(Object object) {
		for (HUDItem HUDItem : hud.getHUDItems()) {
			int oldScore = HUDItem.getItemScore();
			int newScore = HUDItem.getAssociatedSprite().getScore(
					HUDItem.getScoreID());

			if (oldScore != newScore) {
				HUDItem.adjust(newScore);
			}
		}
	}

}
