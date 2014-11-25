package com.al.gameengine.sprite;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.al.gameengine.Background;
import com.al.gameworker.R;

public class GameView extends SurfaceView implements Callback
{
    private static final String TAG = GameView.class.getSimpleName();
    Background background;
    Sprite vanSprite;

    public GameView(Context context)
    {
	super(context);
	vanSprite = new Sprite();
	background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.emptystreet));
	
	// adding the callback (this) to the surface holder to intercept events
	getHolder().addCallback(this);
	
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
	// TODO Auto-generated method stub
	
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
	// TODO Auto-generated method stub
	
    }
    
    public void update()
    {
	background.update();
	vanSprite.update();
    }
    
    public void render(Canvas canvas)
    {
	
    }


}
