package com.al.gameengine.sprite;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.al.gameengine.Background;
import com.al.gameengine.GameEngine;
import com.al.gameworker.R;

/**
 * View used to display the game objects.
 * @author AL
 *
 */
public class GameView extends SurfaceView implements Callback
{
    private static final String TAG = GameView.class.getSimpleName();
    //Pointer the game engine to be used by the view.
    private GameEngine gEngine;
    //The background image.
    private Background background;
    //Objects to be displayed in the game, in front of the background.
    private Sprite vanSprite;

    public GameView(Context context)
    {
	super(context);
	//Sprite to be added to the SpriteManager
	vanSprite = new Sprite(0,0,BitmapFactory.decodeResource(getResources(), R.drawable.inflating1));
	background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.emptystreet));
	
	SpriteManager.addSprite(vanSprite);

	// adding the callback (this) to the surface holder to intercept events
	getHolder().addCallback(this);

	//Instantiates the game engine
	gEngine = new GameEngine(getHolder(), this);

	// make the game view focusable so it can handle events
	setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
	return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
	Log.d(TAG, "Entered On Draw");
	super.onDraw(canvas);
	background.render(canvas);
//	SpriteManager.draw(canvas);
	Log.d(TAG, "Exiting On Draw");
    }

    public void setSprite(Sprite sprite)
    {
//	vanSprite = sprite;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
	gEngine = new GameEngine(getHolder(), this);
	// at this point the surface is created and
	// we can safely start the game loop
	gEngine.setRunning(true);
	gEngine.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
	    int height)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
	boolean retry = true;
	while(retry)
	{
	    try{
		//End the game
		gEngine.setRunning(false);
		gEngine.join();
		retry = false;
	    }
	    catch (Exception e) {
		Log.e(TAG, "There was an error trying to destroy the game engine");
		gEngine.stop();
	    }
	}
	Log.d(TAG, "Thread was shut down cleanly");

    }

    public void update()
    {
	//	background.update();
//	vanSprite.update();
    }

    /**
     * Draws the images onto the view.
     * @param canvas
     */
    public void render(Canvas canvas)
    {
	this.onDraw(canvas);
    }



}
