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

public class GameView extends SurfaceView implements Callback
{
    private static final String TAG = GameView.class.getSimpleName();
    private GameEngine gEngine;
    Background background;
    Sprite vanSprite;

    public GameView(Context context)
    {
	super(context);
	vanSprite = new Sprite();
	background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.emptystreet));
	
		// adding the callback (this) to the surface holder to intercept events
	getHolder().addCallback(this);
	
	gEngine = new GameEngine(getHolder(), this);
	
	// make the GamePanel focusable so it can handle events
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
	SpriteManager.draw(canvas);
	Log.d(TAG, "Exiting On Draw");
    }
    
    public void setSprite(Sprite sprite)
    {
	vanSprite = sprite;
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
			gEngine.setRunning(false);
			gEngine.join();
			retry = false;
		}
		catch (Exception e) {
			Log.e(TAG, "There was an error try to destroy the game engine");
			gEngine.stop();
		}
	}
	Log.d(TAG, "Thread was shut down cleanly");
	
    }
    
    public void update()
    {
//	background.update();
	vanSprite.update();
    }
    
    public void render(Canvas canvas)
    {
	this.onDraw(canvas);
    }
    


}
