package com.al.gameengine.sprite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.al.gameengine.Background;
import com.al.gameengine.GameEngine;
import com.al.gameengine.Position;
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

    private SpriteManager spriteMan;

    public GameView(Context context)
    {
	super(context);
	WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	Display display = wm.getDefaultDisplay();
	Point size = new Point();
	display.getSize(size);

	//The background that will be used for the game view
	Bitmap backImage = BitmapFactory.decodeResource(getResources(), R.drawable.emptystreet);

	//The Sprites that are rendered onto to the view in front of the background.
	Bitmap vanImage = BitmapFactory.decodeResource(getResources(), R.drawable.inflating1);
	//Sprite to be added to the SpriteManager.
	setBackground(backImage);
	vanSprite = new Sprite(0,0,vanImage);


	vanSprite.setPosition(new Position(0 , size.y - vanImage.getHeight()));
	spriteMan = new SpriteManager();

	//As sprites are added, it will be on the next layer.
	spriteMan.addSprite(vanSprite);

	// adding the callback (this) to the surface holder to intercept events
	getHolder().addCallback(this);

	//Instantiates the game engine
	gEngine = new GameEngine(getHolder(), this);

	// make the game view focusable so it can handle events
	setFocusable(true);
    }

    /**
     * Implement the touch functionality of the view. 
     */
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
	return super.onTouchEvent(event);
    }

    /*
     * (non-Javadoc)
     * @see android.view.View#onDraw(android.graphics.Canvas)
     * When the view is drawn the game objects are rendered onto the screen.
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
	super.onDraw(canvas);
	background.render(canvas);
	spriteMan.render(canvas);
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
	//	spriteMan.update();
    }

    /**
     * Draws the images onto the view.
     * @param canvas
     */
    public void render(Canvas canvas)
    {
	this.onDraw(canvas);
    }

    /**
     * This function sets the background that will be used for the view.
     * @param backgroundImage
     */
    private void setBackground(Bitmap backgroundImage)
    {
	background = new Background(backgroundImage);
    }



}
