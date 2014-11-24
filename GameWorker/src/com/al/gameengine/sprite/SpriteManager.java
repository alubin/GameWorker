package com.al.gameengine.sprite;

import java.util.LinkedList;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class SpriteManager
{
   
    private static LinkedList<Sprite> spriteList;
    
    public SpriteManager()
    {
	spriteList = new LinkedList<Sprite>();
    }
    
    public static void update()
    {
	for(Sprite spriteItem: spriteList)
	{
	    spriteItem.update();
	}
    }
    
    public static void draw(Canvas canvas)
    {
	for(Sprite spriteItem: spriteList)
	{
	    spriteItem.draw(canvas);
	}
    }
    
}
