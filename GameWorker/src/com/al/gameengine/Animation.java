package com.al.gameengine;

import java.util.LinkedList;

import android.graphics.Bitmap;

public class Animation
{
    LinkedList<Bitmap> animationImages;
    int delay;

    public Animation()
    {
	animationImages = new LinkedList<Bitmap>();
	delay = 2;
    }
    
    public void playAnimation()
    {
	
    }
    
    public void addImage(Bitmap img)
    {
	
    }

    public void setDelay(int delay)
    {
        this.delay = delay;
    }
}
