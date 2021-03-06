package com.al.gameengine;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.al.gameengine.sprite.GameView;

/**
 * Game Loop for the game view, this will update the position of the images in the view.
 * @author AL
 *
 */
public class GameEngine extends Thread
{

    private static final String TAG = GameEngine.class.getSimpleName();
    private boolean running;
    private SurfaceHolder surfaceHolder;
    private GameView gamePanel;

    // desired fps
    private final static int 	MAX_FPS = 50;
    // maximum number of frames to be skipped
    private final static int	MAX_FRAME_SKIPS = 5;
    // the frame period
    private final static int	FRAME_PERIOD = 1000 / MAX_FPS;	

    //    @Override
    //    public void run()
    //    {
    //	super.run();
    //	SpriteManager.update();
    //    }

    public GameEngine()
    {
	running = false;
    }

    public GameEngine(SurfaceHolder surfaceHolder, GameView gamePanel)
    {
	super();
	//Lock for the canvas.
	this.surfaceHolder = surfaceHolder;
	//Pointer to the game view, where the images will be displayed.
	this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
	//Pointer to the canvas where things will be displayed.
	Canvas canvas;
	Log.d(TAG, "Starting game loop");

	// the time when the cycle begun
	long beginTime;
	// the time it took for the cycle to execute
	long timeDiff;
	// ms to sleep (<0 if we're behind)
	int sleepTime;
	// number of frames being skipped 
	int framesSkipped;

	sleepTime = 0;

	//Loops the game if the running flag is true.
	while (running) {
	    canvas = null;
	    // try locking the canvas for exclusive pixel editing
	    // in the surface
	    try {
		canvas = this.surfaceHolder.lockCanvas();
		synchronized (surfaceHolder) {
		    beginTime = System.currentTimeMillis();
		    // resetting the frames skipped
		    framesSkipped = 0;	
		    // update game state
		    this.gamePanel.update();
		    // render state to the screen
		    // draws the canvas on the panel
		    this.gamePanel.render(canvas);
		    // calculate how long did the cycle take
		    timeDiff = System.currentTimeMillis() - beginTime;
		    // calculate sleep time
		    sleepTime = (int)(FRAME_PERIOD - timeDiff);

		    if (sleepTime > 0) {
			// if sleepTime > 0 we're OK
			try {
			    // send the thread to sleep for a short period
			    // very useful for battery saving
			    Thread.sleep(sleepTime);
			} catch (InterruptedException e) {}
		    }

		    while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
			// we need to catch up
			// update without rendering
			this.gamePanel.update();
			// add frame period to check if in next frame
			sleepTime += FRAME_PERIOD;
			framesSkipped++;
		    }
		}
	    } finally {
		// in case of an exception the surface is not left in
		// an inconsistent state
		if (canvas != null) {
		    surfaceHolder.unlockCanvasAndPost(canvas);
		}
	    }	// end finally
	}
    }

    public boolean isRunning()
    {
        return running;
    }

    /**
     * Sets the running state of the engine.
     * @param running
     */
    public void setRunning(boolean running)
    {
        this.running = running;
    }

}
