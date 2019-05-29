import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.a2i.tpeea.sliderapp.R;

public class Slider extends View {
    /* Variables */
    //Cursor variables
    private float ratio, value, minValue, maxValue, cursorDiameter;

    //Slider variables
    private float sliderHeight, sliderWidth, sliderPaddingTop, sliderPaddingLeft, sliderPaddingRight, sliderPaddingBottom;
    private boolean enableSlider;

    //Bar variables
    private float barLength, barWidth;

    //Color variables
    private Paint cursorPaint = null;
    private Paint valuePaint = null;
    private Paint barPaint = null;
    private int cursorColor, valueColor, barColor, disabledColor = 0;

    /* Constants */
    final float DEFAULT_BAR_HEIGHT = 100;
    final float DEFAULT_BAR_WIDTH = 10;
    final float DEFAULT_CURSOR_DIAMETER = 20;

    /* Getters & setters */
    //Cursors
    public float getMinValue() {
        return minValue;
    }
    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }
    public float getMaxValue() {
        return maxValue;
    }
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }
    public float getRatio() {
        return ratio;
    }
    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
    public float getValue() {
        return value;
    }
    public void setValue(float value) {
        this.value = value;
    }
    public float getCursorDiameter() {
        return cursorDiameter;
    }
    public void setCursorDiameter(float cursorDiameter) {
        this.cursorDiameter = cursorDiameter;
    }

    //Slider
    public float getSliderHeight() {
        return sliderHeight;
    }
    public void setSliderHeight(float sliderHeight) {
        this.sliderHeight = sliderHeight;
    }
    public float getSliderWidth() {
        return sliderWidth;
    }
    public void setSliderWidth(float sliderWidth) {
        this.sliderWidth = sliderWidth;
    }
    public float getSliderPaddingTop() {
        return sliderPaddingTop;
    }
    public void setSliderPaddingTop(float sliderPaddingTop) {
        this.sliderPaddingTop = sliderPaddingTop;
    }
    public float getSliderPaddingLeft() {
        return sliderPaddingLeft;
    }
    public void setSliderPaddingLeft(float sliderPaddingLeft) {
        this.sliderPaddingLeft = sliderPaddingLeft;
    }
    public float getSliderPaddingRight() {
        return sliderPaddingRight;
    }
    public void setSliderPaddingRight(float sliderPaddingRight) {
        this.sliderPaddingRight = sliderPaddingRight;
    }
    public float getSliderPaddingBottom() {
        return sliderPaddingBottom;
    }
    public void setSliderPaddingBottom(float sliderPaddingBottom) {
        this.sliderPaddingBottom = sliderPaddingBottom;
    }
    public boolean isEnableSlider() {
        return enableSlider;
    }
    public void setEnableSlider(boolean enableSlider) {
        this.enableSlider = enableSlider;
    }

    //Bar
    public float getBarLength() {
        return barLength;
    }
    public void setBarLength(float barLength) {
        this.barLength = barLength;
    }
    public float getBarWidth() {
        return barWidth;
    }
    public void setBarWidth(float barWidth) {
        this.barWidth = barWidth;
    }

    //Color
    public Paint getCursorPaint() {
        return cursorPaint;
    }

    public void setCursorPaint(Paint cursorPaint) {
        this.cursorPaint = cursorPaint;
    }

    public Paint getValuePaint() {
        return valuePaint;
    }

    public void setValuePaint(Paint valuePaint) {
        this.valuePaint = valuePaint;
    }

    public Paint getBarPaint() {
        return barPaint;
    }

    public void setBarPaint(Paint barPaint) {
        this.barPaint = barPaint;
    }

    public void setCursorColor(int cursorColor) {
        this.cursorColor = cursorColor;
    }

    public void setValueColor(int valueColor) {
        this.valueColor = valueColor;
    }

    public void setBarColor(int barColor) {
        this.barColor = barColor;
    }

    //Constants
    public float getDEFAULT_BAR_HEIGHT() {
        return DEFAULT_BAR_HEIGHT;
    }

    public float getDEFAULT_BAR_WIDTH() {
        return DEFAULT_BAR_WIDTH;
    }

    public float getDEFAULT_CURSOR_DIAMETER() {
        return DEFAULT_CURSOR_DIAMETER;
    }



    /* Methods */
    private float dpToPixel(float valueInDp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, getResources().getDisplayMetrics());
    }

    private float valueToRatio(float cursorValue) {
        return cursorValue / (getMaxValue() - getMinValue());
    }

    private float ratioToValue(float cursorRatio) {
        return cursorRatio * (getMaxValue() - getMinValue());
    }

    private Point toPos(float value) {
        int posX = (int) Math.round(getPaddingLeft() + (getCursorDiameter() / 2));
        int posY = (int) Math.round(valueToRatio(value)*getBarLength());

        return new Point(posX, posY);
    }

    private float toValue(Point curseur){
        return ratioToValue(curseur.x / getBarLength());
    }

    private void init(Context context, AttributeSet attrs) {
        /* Instanciation des Paint */
        Paint cursorPaint = new Paint();
        Paint valuePaint = new Paint();
        Paint barPaint = new Paint();

        /* Anti alias */
        cursorPaint.setAntiAlias(true);
        valuePaint.setAntiAlias(true);
        barPaint.setAntiAlias(true);

        /* Style */
        barPaint.setStyle(Paint.Style.STROKE);
        valuePaint.setStyle(Paint.Style.STROKE);
        cursorPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        barPaint.setStrokeCap(Paint.Cap.ROUND);

        barColor = ContextCompat.getColor(context, R.color.barColor);
        cursorColor = ContextCompat.getColor(context, R.color.cursorColor);
        valueColor = ContextCompat.getColor(context, R.color.valueColor);
        disabledColor = ContextCompat.getColor(context, R.color.disabledColor);


        if(enableSlider) {
            barPaint.setColor(barColor);
            cursorPaint.setColor(cursorColor);
            valuePaint.setColor(valueColor);
        } else {
            barPaint.setColor(disabledColor);
            cursorPaint.setColor(disabledColor);
            valuePaint.setColor(disabledColor);
        }

        barWidth = dpToPixel(DEFAULT_BAR_WIDTH);
        barLength = dpToPixel(DEFAULT_BAR_HEIGHT);
        cursorDiameter = dpToPixel(DEFAULT_CURSOR_DIAMETER);

        barPaint.setStrokeWidth(barWidth);
        valuePaint.setStrokeWidth(barWidth);
    }

    private void onDraw(Canvas canvas) {

    }

    public Slider(Context context) {
       super(context);
       init(context, null);

    }

    //attrs : ensemble d'attributs provenant du xml
    public Slider(Context context, AttributeSet attrs) {
       super(context, attrs);
       init(context, attrs);
    }
}
