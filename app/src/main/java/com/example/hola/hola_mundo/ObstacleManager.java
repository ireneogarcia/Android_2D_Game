package com.example.hola.hola_mundo;

import android.graphics.Canvas;

import java.util.ArrayList;

import static com.example.hola.hola_mundo.PrincipalThread.canvas;

public class ObstacleManager {
    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;

    private long startTime;

    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int color) {
        this.playerGap = playerGap;
        //obstacle gap es la distancia "y" entre barras
        this.obstacleGap = obstacleGap;
        //obstacle height es el grueso de la barra
        this.obstacleHeight = obstacleHeight;
        this.color = color;

        startTime = System.currentTimeMillis();

        obstacles = new ArrayList<>();

        populateObstacles();
    }

    private void populateObstacles() {
        //higher index = lower on screen = higher y value
        int currY = -5 *Constants.SCREEN_HEIGHT/4;
        //(obstacles.get(obstacles.size() - 1)).getRectangle().bottom <0
        while(currY < 0){
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(new Obstacle(obstacleHeight, color, xStart, currY, playerGap));
            currY += obstacleHeight + obstacleGap;
        }
    }

    public void update(){
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        //quiero que tarde 10 segundos por eso se pone 10000
        float speed = Constants.SCREEN_HEIGHT/10000.0f;
        for(Obstacle ob : obstacles){
            ob.incrementY(speed * elapsedTime);
        }
        if(obstacles.get(obstacles.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT){
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            //ojo la parte ultima: obstacleheight, obstaclegap hay que restarla si no no funciona
            obstacles.add(0, new Obstacle(obstacleHeight, color, xStart, obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap, playerGap));
            obstacles.remove(obstacles.size() - 1);
        }
    }

    public void draw(Canvas canvas){
        for(Obstacle ob : obstacles)
            ob.draw(canvas);
    }
}
