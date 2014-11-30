package com.al.gameengine;

import android.graphics.Bitmap;

/**
 * The class that will represent the background of the game.
 * @author AL
 *
 */
public class Background extends Scene
{
    public Background()
    {
	super();
    }

    public Background(Bitmap image)
    {
	add(image);
    }
    
}
