package com.al.gameengine;

/**
 * Class that holds the position value for each sprite.
 * @author AL
 *
 */
public class Position
{
    //The X variable of the position.
    private int x;
    //The Y variable of the position.
    private int y;
    
    public Position()
    {
	x = 0;
	y = 0;
    }
    
    public Position(int x, int y)
    {
	this.x = x;
	this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

}
