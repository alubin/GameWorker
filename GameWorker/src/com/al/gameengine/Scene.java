package com.al.gameengine;

import java.util.LinkedList;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * This class will be used for the back display of each view.
 * @author AL
 *
 */
public class Scene
{
    LinkedList<Bitmap> imgList;
    private Position position;
    private int bgEndMoveX;
    private int bgStartMoveX;

    public Scene()
    {
	imgList = new LinkedList<Bitmap>();
    }

    public Scene(Position pos)
    {
	this.position = pos;
    }

    public void render(Canvas canvas)
    {
	bgEndMoveX--;

	// decrement the near background
	bgStartMoveX = bgStartMoveX - 4;

	for(Bitmap bitmap: imgList)
	{
	    // calculate the wrap factor for matching image draw
	    int newEndX = bitmap.getWidth() - (-bgEndMoveX);


	    // if we have scrolled all the way, reset to start
	    if (newEndX <= 0) {
		bgEndMoveX = 0;
		// only need one draw
		canvas.drawBitmap(bitmap, bgEndMoveX, 0, null);

	    } else {
		// need to draw original and wrap
		canvas.drawBitmap(bitmap, bgEndMoveX, 0, null);
		canvas.drawBitmap(bitmap, newEndX, 0, null);
	    }
	}
    }

    public void add(Bitmap bitmap)
    {
	imgList.add(bitmap);
    }
}
