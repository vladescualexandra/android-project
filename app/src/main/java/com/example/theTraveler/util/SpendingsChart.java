package com.example.theTraveler.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.theTraveler.R;

import java.util.List;

public class SpendingsChart extends View {

    private Context context;
    private List<Double> source;
    private Paint paint;

    public SpendingsChart(Context context,
                          List<Double> source) {
        super(context);
        this.context = context;
        this.source = source;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (source == null || source.isEmpty()) {
                drawNoGraph(canvas);
        } else if (source.size() < 3) {
            drawNoGraph(canvas);
        } else {

            float distanceBetweenPoints = (float) getWidth() / source.size();

            double maxSpending = source.get(0);
            for (int i = 1; i < source.size() - 1; i++) {
                if (source.get(i) > maxSpending) {
                    maxSpending = source.get(i);
                }

            }

            for (int i = 1; i < source.size() - 1; i++) {
                drawLine(canvas, i - 1, distanceBetweenPoints,
                        maxSpending, source.get(i - 1), source.get(i));
            }

        }
    }

    private void drawNoGraph(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setTextSize(50.0f);
        canvas.drawText(getContext().getString(R.string.statistics_not_enough_visits), getWidth() / 4, getHeight() / 2, paint);
    }

    private void drawLine(Canvas canvas, int position,
                          float distanceBetweenPoints,
                          Double maxSpending, Double start, Double end) {

        float x1 = position * distanceBetweenPoints;
        float y1 = (float) (1 - start / maxSpending) * getHeight();
        float x2 = (position + 1) * distanceBetweenPoints;
        float y2 = (float) (1 - end / maxSpending) * getHeight();

        int color = Color.rgb(205, 21, 21);
        paint.setColor(color);
        paint.setStrokeWidth(5f);

        canvas.drawLine(x1, y1, x2, y2, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(30.0f);
        canvas.drawText(String.valueOf(start), x1, y1, paint);

    }
}
