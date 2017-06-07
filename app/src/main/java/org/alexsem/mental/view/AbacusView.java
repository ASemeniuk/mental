package org.alexsem.mental.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * View used to display flashcards
 */

public class AbacusView extends View {

    private int numberOfRows;
    private int maxValueBound;
    private int currentValue;

    private boolean isMeasurementChanged = false;
    private Paint mPaint;

    private final int COLOR_LINE = 0xff505050; //TODO change
    private final int COLOR_BEAD = 0xffb38d41; //TODO change

    private float WIDTH_LINE = 3f;
    private float WIDTH_OUTLINE = 2f;
    private float WIDTH_DELIMITER = 6f;
    private float WIDTH_BEAD = 48f;
    private float HEIGHT_BEAD = 30f;

    private float MARGIN_ROW_VERT = 10f;
    private float MARGIN_ROW_HORZ = 4f;

    private int mCanvasWidth = 1;
    private int mCanvasHeight = 1;

    public AbacusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        int dpi = getResources().getDisplayMetrics().densityDpi;
        WIDTH_LINE = WIDTH_LINE * dpi / 160;
        WIDTH_OUTLINE = WIDTH_OUTLINE * dpi / 160;
        WIDTH_DELIMITER = WIDTH_DELIMITER * dpi / 160;
        WIDTH_BEAD = WIDTH_BEAD * dpi / 160;
        HEIGHT_BEAD = HEIGHT_BEAD * dpi / 160;
        MARGIN_ROW_VERT = MARGIN_ROW_VERT * dpi / 160;
        MARGIN_ROW_HORZ = MARGIN_ROW_HORZ * dpi / 160;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(WIDTH_LINE);
    }

    /**
     * Set data to be displayed
     * @param numberOfDigits Number of digits to display
     */
    public void initialize(int numberOfDigits) {
        this.numberOfRows = numberOfDigits;
        this.maxValueBound = 1;
        for (int i = 0; i < numberOfDigits; i++) {
            maxValueBound *= 10;
        }
        requestLayout();
    }

    /**
     * Update currently displayed value
     * @param newValue Value to display
     */
    public void updateValue(int newValue) {
        this.currentValue = newValue;
        if (currentValue >= maxValueBound) {
            currentValue %= maxValueBound;
        }
        invalidate();
    }

    //--------------------------------------------------------------------------------------------------------------------

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int newWidth = Math.round(numberOfRows * calculateRowWidth() + WIDTH_OUTLINE);
        int newHeight = Math.round(calculateRowHeight() + WIDTH_OUTLINE);
        if (newWidth != mCanvasWidth || newHeight != mCanvasHeight) {
            mCanvasWidth = newWidth;
            mCanvasHeight = newHeight;
            isMeasurementChanged = true;
        }
        setMeasuredDimension(newWidth, newHeight);
    }


    private float calculateRowWidth() {
        return WIDTH_BEAD + MARGIN_ROW_HORZ * 2;
    }

    private float calculateRowHeight() {
        return HEIGHT_BEAD * 7 + MARGIN_ROW_VERT * 2 + WIDTH_DELIMITER;
    }

    //---------------------------------------------------------------------------------------------------------------------

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isMeasurementChanged) {
            isMeasurementChanged = false;
        }

        if (numberOfRows < 1) { //Data not yet set
            return;
        }

        float canvasWidth = getMeasuredWidth();
        float canvasHeight = getMeasuredHeight();

        mPaint.setStrokeWidth(WIDTH_OUTLINE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(COLOR_LINE);
        canvas.drawRect(WIDTH_OUTLINE / 2, WIDTH_OUTLINE / 2, canvasWidth - WIDTH_OUTLINE / 2, canvasHeight - WIDTH_OUTLINE / 2, mPaint);


        int numberToProcess = currentValue;
        float currentLeft = canvasWidth - WIDTH_OUTLINE / 2 - calculateRowWidth();
        for (int i = 0; i < numberOfRows; i++) {
            drawRow(numberToProcess % 10, canvas, currentLeft, WIDTH_OUTLINE / 2, mPaint);
            currentLeft -= calculateRowWidth();
            numberToProcess /= 10;
        }
    }

    /**
     * Draw exactly one row of beads
     * @param value  Value to represent by the row (from 0 to 9)
     * @param canvas Canvas to draw to
     * @param left   Horizontal offset
     * @param top    Vertical offset
     * @param paint  Paint to draw with
     */
    private void drawRow(int value, Canvas canvas, float left, float top, Paint paint) {
        float currentTop = top + MARGIN_ROW_VERT;
        float width = calculateRowWidth();
        float height = calculateRowHeight();

        //Row spike
        paint.setStrokeWidth(WIDTH_OUTLINE);
        paint.setColor(COLOR_LINE);
        canvas.drawLine(left + width / 2, top, left + width / 2, top + height, paint);

        //Sky bead
        if (value / 5 == 1) {
            currentTop += HEIGHT_BEAD;
        }
        drawHexagon(canvas, left + MARGIN_ROW_HORZ, currentTop, paint);
        if (value / 5 == 0) {
            currentTop += HEIGHT_BEAD;
        }
        currentTop += HEIGHT_BEAD;

        //Delimiter
        paint.setStrokeWidth(WIDTH_DELIMITER);
        canvas.drawLine(left, currentTop + WIDTH_DELIMITER / 2, left + width, currentTop + WIDTH_DELIMITER / 2, paint);
        currentTop += WIDTH_DELIMITER;

        //Earth beads
        for (int i = 0; i < 4; i++) {
            if (i == value % 5) {
                currentTop += HEIGHT_BEAD;
            }
            drawHexagon(canvas, left + MARGIN_ROW_HORZ, currentTop, paint);
            currentTop += HEIGHT_BEAD;
        }
    }

    /**
     * Draws hexagon of specified size
     * @param canvas Canvas to draw to
     * @param left   Horizontal position
     * @param top    Vertical position
     * @param paint  Paint to draw with
     */
    private void drawHexagon(Canvas canvas, float left, float top, Paint paint) {
        Path path = new Path();
        path.moveTo(left, top + HEIGHT_BEAD / 2);
        path.moveTo(left + WIDTH_LINE, top + HEIGHT_BEAD / 2);
        path.lineTo(left + WIDTH_BEAD / 4, top + WIDTH_LINE);
        path.lineTo(left + WIDTH_BEAD * 3 / 4, top + WIDTH_LINE);
        path.lineTo(left + WIDTH_BEAD - WIDTH_LINE, top + HEIGHT_BEAD / 2);
        path.lineTo(left + WIDTH_BEAD * 3 / 4, top + HEIGHT_BEAD - WIDTH_LINE);
        path.lineTo(left + WIDTH_BEAD / 4, top + HEIGHT_BEAD - WIDTH_LINE);
        path.close();

        paint.setStrokeWidth(WIDTH_LINE);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(COLOR_BEAD);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(COLOR_LINE);
        canvas.drawPath(path, paint);
        canvas.drawLine(left + WIDTH_LINE, top + HEIGHT_BEAD / 2, left + WIDTH_BEAD - WIDTH_LINE, top + HEIGHT_BEAD / 2, paint);
    }


}
