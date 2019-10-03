package com.example.snow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class SnowView extends View {
    private Snow snows[];
    private int Width,Height;
    private Paint paint;
    private Random random;

    public SnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SnowView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //initSnows();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initSnows();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(0,0,0,0);
        for (Snow snow:snows){
            canvas.drawCircle(snow.x, snow.y, snow.radius, paint);
        }
        Update();
        invalidate();
    }

    private void initSnows(){
        Width = getWidth();
        Height = getHeight();
        Log.d("SnowView:","Width = "+Width);
        Log.d("SnowView:","Height = "+Height);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        snows = new Snow[500];
        for(int i=0;i<snows.length;i++) snows[i] = new Snow();

        random = new Random();
        for (Snow snow:snows){
            snow.x = random.nextInt(Width);
            snow.y = random.nextInt(Height);
            snow.vx = random.nextInt(6)-3;
            snow.vy = 3+random.nextInt(1);
            snow.radius = 10+random.nextInt(10);
        }
    }

    private void Update(){
        for(Snow snow:snows){
            snow.x += snow.vx;
            snow.y += snow.vy;
            if(snow.y-snow.radius>Height){
                snow.y=0-random.nextInt(200);
                snow.x = random.nextInt(Width);
            }
        }
    }
    private class Snow{
        int x,y,radius;
        int vx,vy;
    }
}
