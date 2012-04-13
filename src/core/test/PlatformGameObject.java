package core.test;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.golden.gamedev.Game;

import core.EventManager;

public class PlatformGameObject {

	Game game;
    private EventManager myEventManager;

	public void handleInput ()
    {
        if (game.keyPressed(KeyEvent.VK_SPACE))
            myEventManager.fireEvent(this, "Input.User.Pause");
        
        if (game.click())
            myEventManager.fireEvent(this, "Input.Mouse.LeftClick");

        if (game.rightClick())
            myEventManager.fireEvent(this, "Input.User.Swap");
        
        if(game.bsInput.isKeyDown(KeyEvent.VK_T))
            myEventManager.fireEvent(this, "Input.User.Cheat");
    }
	
	
}
