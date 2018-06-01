package com.example.hola.hola_mundo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class RectPlayer implements Object {

    private Rect rectangle;
    private int color;

    public RectPlayer (Rect rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;
    }
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update(){

    }
    //Point is gonna be the center of rectangle
    public void update(Point point) {
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }
    public Rect getRectangle(){
        return rectangle;
    }
}
