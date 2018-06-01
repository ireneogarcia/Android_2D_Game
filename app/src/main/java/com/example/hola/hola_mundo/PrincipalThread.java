package com.example.hola.hola_mundo;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class PrincipalThread extends Thread{
    //Usando los FPS del movil limitaremos las llamadas al thread a solo las
    //necesarias
    public static final int MAX_FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private Panel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public void setRunning(boolean running){
        this.running= running;
    }

    public PrincipalThread(SurfaceHolder surfaceHolder, Panel gamePanel){
        //constructor de la clase padre, es decir, constructor Thread
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }


    @Override
    public void run(){
        long startTime;
        long timeMillis = 1000/MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;
        while (running){
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas= this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);

                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (canvas != null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e){e.printStackTrace();}
                }
            }
            timeMillis = (System.nanoTime() - startTime)/1000000;
            /*Trata de de no hacer correr el thread mas de la cuenta, sino que lo haga las veces
            justas en función de los fps*/
            waitTime = targetTime - timeMillis;
            try {
                if (waitTime>0)
                    this.sleep(waitTime);
            } catch (Exception e){e.printStackTrace();}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000); //pasa de nano segundos a milisegundos los (())
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);

            }
        }
    }
}
