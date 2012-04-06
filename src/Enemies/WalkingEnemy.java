package Enemies;

import game.PlatformGame;

import java.awt.image.BufferedImage;

import States.StationaryState;
import States.WalkingRightState;

public class WalkingEnemy extends Enemy {

    public WalkingEnemy(PlatformGame currentGame, BufferedImage image) {
        super(currentGame, image);
        manager.add(new WalkingRightState(this));
        manager.add(new StationaryState(this));
    }

    @Override
    public void update(long elapsedTime) {      
        super.update(elapsedTime);
        
    }
    public static void main(String[] args){
        
    }
}

