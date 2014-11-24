package com.al.gameengine.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.al.gameengine.Animation;
import com.al.gameengine.Direction;
import com.al.gameengine.Position;

public class Sprite
{
    Position pos;
    Bitmap mainImage;
    Direction moveDirection;
    Animation running;
    Animation action;
    Canvas canvas;
    
    public Sprite()
    {
	pos = new Position();
	running = new Animation();
	action = new Animation();
//	canvas = new Canvas();
    }
    
    public Sprite(int initialX, int initialY)
    {
	this();
	this.pos.setX(initialX);
	this.pos.setY(initialY);
	
    }
    
    public void update()
    {
	
    }
    
    public void draw(Canvas canvas)
    {
	setCanvas(canvas);
	render();
    }
    
    public void render()
    {
	if(canvas != null)
	{
	    canvas.drawBitmap(mainImage, pos.getX() - (mainImage.getWidth() / 2), pos.getY() - (mainImage.getHeight() / 2), null);
	}
    }
    
    public void setImage(Bitmap image)
    {
	mainImage = image;
    }
    
    public Bitmap getImage()
    {
	return mainImage;
    }
    
    public Position getPosition()
    {
	return pos;
    }
    
    public void setPosition(Position pos)
    {
	this.pos = pos;
    }
    
    public void setCanvas(Canvas canvas)
    {
	this.canvas = canvas;
    }
   
}
