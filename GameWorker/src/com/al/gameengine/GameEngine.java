package com.al.gameengine;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.al.gameengine.sprite.GameView;

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
	this.surfaceHolder = surfaceHolder;
	this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
	Canvas canvas;
	Log.d(TAG, "Starting game loop");

	long beginTime;		// the time when the cycle begun
	long timeDiff;		// the time it took for the cycle to execute
	int sleepTime;		// ms to sleep (<0 if we're behind)
	int framesSkipped;	// number of frames being skipped 

	sleepTime = 0;

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

    public void setRunning(boolean running)
    {
        this.running = running;
    }

}
