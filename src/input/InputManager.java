package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import core.EventListener;
import core.EventManager;

public class InputManager implements KeyListener, EventListener {
	private boolean active;
	private static InputManager self;
	private List<String> currentlyPressed;

	private InputManager() {
		currentlyPressed = new ArrayList<String>();
		active = true;
	}

	public static InputManager getInputManager() {
		if (self == null) {
			self = new InputManager();
		}
		return self;
	}

	public void update() {
		for (String event : currentlyPressed)
			EventManager.getEventManager().sendEvent(event);
	}

	public void keyPressed(KeyEvent pressed) {
		currentlyPressed.add(KeyEvent.getKeyText(pressed.getKeyCode()));
	}

	public void keyReleased(KeyEvent released) {
		currentlyPressed.remove(KeyEvent.getKeyText(released.getKeyCode()));
	}

	public void keyTyped(KeyEvent typed) {

	}

	public void actionPerformed(String eventName) {
		if (eventName == "cutscene-begin")
			setActive(false);
		if (eventName == "cutscene-end")
			setActive(true);
	}

	private void setActive(boolean a) {
		active = a;
	}

}
