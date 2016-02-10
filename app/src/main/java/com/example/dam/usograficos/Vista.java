package com.example.dam.usograficos;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by 2dam on 02/02/2016.
 */
public class Vista extends View {
    private int alto, ancho;
    static Paint pincel;
    private float x0, y0, x1, y1;
    private Bitmap mapaDeBits;
    private Canvas lienzoFondo;
    private Path rectaPoligonal = new Path();
    private int i=0;

    enum forma{
        circulo,linea,rectangulo,lineaRecta;
    }

    static forma f = forma.linea;


    public Vista(Context context) {
        super(context);
        pincel = new Paint();
        pincel.setColor(Color.RED);
        pincel.setAntiAlias(true);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(10);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mapaDeBits,0,0,null);
        canvas.drawBitmap(mapaDeBits, 0, 0, null);
        canvas.drawPath(rectaPoligonal, pincel);
        float aux;

        switch (f){
            case rectangulo:
                canvas.drawRect(Math.min(x0,x1),Math.min( y0,y1), Math.max(x1,x0), Math.max(y1,y0), pincel);
                break;
            case circulo:
                canvas.drawOval(Math.min(x0,x1),Math.min( y0,y1), Math.max(x1,x0), Math.max(y1,y0), pincel);
                break;
//            case linea:
//                canvas.drawLine(x0,y0,x1,y1,pincel);
//                break;
            case lineaRecta:
                canvas.drawLine(x0,y0,x1,y1,pincel);
        }
//        canvas.drawRect(Math.min(x0,x1),Math.min( y0,y1), Math.max(x1,x0), Math.max(y1,y0), pincel);
//        canvas.drawLine(x0,y0,x1,y1,pincel);
//        canvas.drawOval(Math.min(x0,x1),Math.min( y0,y1), Math.max(x1,x0), Math.max(y1,y0), pincel);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        alto = h;
        ancho = w;
        mapaDeBits = Bitmap.createBitmap(w, h,
                Bitmap.Config.ARGB_8888);
        lienzoFondo = new Canvas(mapaDeBits);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x,y;
        switch (f) {
            case rectangulo:
                x = event.getX();
                y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x0 = x;
                        y0 = y;
                        x1 = x;
                        y1 = y;
                        break;
                    case MotionEvent.ACTION_MOVE:

                        x1 = x;
                        y1 = y;

                        invalidate();


                        break;
                    case MotionEvent.ACTION_UP:
                        x1 = x;
                        y1 = y;
                        lienzoFondo.drawRect(x0, y0, x1, y1, pincel);


                        invalidate();

                        break;
                }
                break;
            case linea:
                x = event.getX();
                y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x0 = x;
                        y0 = y;
                        x1 = x;
                        y1 = y;
                        rectaPoligonal.moveTo(x0, y0);
//                    auxCanvas.drawPoint(x,y,pincel);
                        break;
                    case MotionEvent.ACTION_MOVE:

                        x1 = x;
                        y1 = y;
//                    lienzoFondo.drawLine(x0,y0,x1,y1,pincel);
                        rectaPoligonal.quadTo(x0, y0,
                                (x + x1) / 2, (y + y1) / 2);
//                    rectaPoligonal.quadTo(xi, yi,
//                            (x + xi) / 2, (y + yi)/2);
                        x0 = x1;
                        y0 = y1;
                        invalidate();
//                    auxCanvas.drawPoint(x,y,pincel);
//                    auxCanvas.drawOval(x,y, 25,25, pincel);
                        break;
                    case MotionEvent.ACTION_UP:
                        x1 = x;
                        y1 = y;
                        lienzoFondo.drawPath(rectaPoligonal, pincel);

                        rectaPoligonal.reset();
//                    lienzoFondo.drawLine(x0,y0,x1,y1,pincel);
//                        lienzoFondo.drawOval(Math.min(x0, x1), Math.min(y0, y1), Math.max(x1, x0), Math.max(y1, y0), pincel);
                        invalidate();
//                    auxCanvas.drawPoint(x,y,pincel);
                        break;


                }
            break;

            case circulo:
                x = event.getX();
                y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x0 = x;
                        y0 = y;
                        x1 = x;
                        y1 = y;

                        break;
                    case MotionEvent.ACTION_MOVE:

                        x1 = x;
                        y1 = y;

                        invalidate();

                        break;
                    case MotionEvent.ACTION_UP:
                        x1 = x;
                        y1 = y;

                        lienzoFondo.drawOval(Math.min(x0, x1), Math.min(y0, y1), Math.max(x1, x0), Math.max(y1, y0), pincel);
                        invalidate();

                        break;





                }
                break;

            case lineaRecta:
                x = event.getX();
                y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x0 = x;
                        y0 = y;
                        x1 = x;
                        y1 = y;

                        break;
                    case MotionEvent.ACTION_MOVE:

                        x1 = x;
                        y1 = y;

                        invalidate();

                        break;
                    case MotionEvent.ACTION_UP:
                        x1 = x;
                        y1 = y;

                        lienzoFondo.drawLine(x0,y0,x1,y1,pincel);
                        invalidate();

                        break;





                }
                break;
            }
            return true;

    }


    public static void circulo(){
        f = forma.circulo;
        pincel.setStyle(Paint.Style.FILL);
    }
    public static void linea(){
        f = forma.linea;
        pincel.setStyle(Paint.Style.STROKE);
    }
    public static void rectangulo(){
        f = forma.rectangulo;
        pincel.setStyle(Paint.Style.FILL);
    }

    public static void lineaRecta(){
        f = forma.lineaRecta;
        pincel.setStyle(Paint.Style.STROKE);
    }
    public static void soloBordes(){
        pincel.setStyle(Paint.Style.STROKE);
    }
    public static void conRelleno(){
        pincel.setStyle(Paint.Style.FILL);
    }
}
