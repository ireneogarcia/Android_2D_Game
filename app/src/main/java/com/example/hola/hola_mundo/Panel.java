package com.example.hola.hola_mundo;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback{
    private PrincipalThread thread;
    private RectPlayer player;
    private Point playerPoint;
    public Panel(Context context) {
        super(context); // igual a SurfaceView surfaceView(context)

        getHolder().addCallback(this);

        thread = new PrincipalThread(getHolder(), this);

        player = new RectPlayer(new Rect(100,100,200,200),  Color.rgb(0,230,0));
        playerPoint = new Point(150,150);
        // focus event on touch mode and keypadmode
        setFocusable(true);

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new PrincipalThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        boolean retry = true;
        while(true){
            try {
                thread.setRunning(false);
                thread.join();
            }catch (Exception e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    /*detecta que se esta tocando la pantalla ahora se pondrá return true porque siempre queremos eso
    pero habrá que ver para otros proyectos*/
    public boolean onTouchEvent (MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:

                playerPoint.set((int)event.getX(), (int)event.getY());
        }
             //return super.onTouchEvent(event);
        return true;
    }

    public void update(){
        player.update(playerPoint);

    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        //color del fondo
        canvas.drawColor(Color.WHITE);
        player.draw(canvas);


    }


}
