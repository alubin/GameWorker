package com.al.gameengine.sprite;

import java.util.LinkedList;

import android.graphics.Canvas;

/**
 * This class manages a series of sprites that are added to it.
 * @author AL
 *
 */
public class SpriteManager
{
   /**
    * List of sprites added.
    */
    private static LinkedList<Sprite> spriteList;
    
    /**
     * Constructor
     */
    public SpriteManager()
    {
	spriteList = new LinkedList<Sprite>();
    }
    
    public void update()
    {
	for(Sprite spriteItem: spriteList)
	{
	    spriteItem.update();
	}
    }
    
    /**
     * Draws the sprites that are in the list.
     * @param canvas
     */
    public void render(Canvas canvas)
    {
	for(Sprite spriteItem: spriteList)
	{
	    spriteItem.draw(canvas);
	}
    }
    
    /**
     * Function used to add sprite to the manager.
     * @param sprite
     */
    public void addSprite(Sprite sprite)
    {
	spriteList.add(sprite);
    }
    
}
