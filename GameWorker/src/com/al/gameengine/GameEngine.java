package com.al.gameengine;

import com.al.gameengine.sprite.SpriteManager;

public class GameEngine extends Thread
{

    @Override
    public void run()
    {
	super.run();
	SpriteManager.update();
    }

}
