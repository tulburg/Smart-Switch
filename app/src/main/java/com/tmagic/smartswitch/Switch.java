package com.tmagic.smartswitch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.InvalidClassException;

/**
 * Created by tulburg on 7/23/15.
 */
public class Switch extends LinearLayout{

        static String TAG = Switch.class.getSimpleName();

        private int mStrokeWidth = 4;
        private float mRadius = 5.0f;
        private int mSelectedIndex = 0;
        private int mInActiveColor = Color.parseColor("#ffffff");
        private int mActiveColor = Color.parseColor("#0000cc");
        private int mActiveTextColor = mInActiveColor;
        private int mInactiveTextColor = mActiveColor;
        private STYLE mStyle = STYLE.BOX;
        private onSelectChangeListener changeListener;

        public Switch(Context context) {
            super(context);
            init(context, null);
        }

        public Switch(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context, attrs);
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public Switch(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context, attrs);
        }

        private void init(Context context, AttributeSet attributeSet){
            changeListener = null;
            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.Switch, 0, 0);
            mActiveColor = typedArray.getColor(R.styleable.Switch_activeColor, R.color.blue);
            mInActiveColor = typedArray.getColor(R.styleable.Switch_inActiveColor, R.color.black);
            mActiveTextColor = typedArray.getColor(R.styleable.Switch_activeTextColor, R.color.white);
            mInactiveTextColor = typedArray.getColor(R.styleable.Switch_inactiveTextColor, R.color.white);
            mStrokeWidth = typedArray.getInt(R.styleable.Switch_strokeWidth, 4);
            mRadius = typedArray.getFloat(R.styleable.Switch_cornerRadius, 6.0f);
            typedArray.recycle();

            setOrientation(HORIZONTAL);
            setWeightSum(getChildCount());

            generateViews();
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);
        }

        private void generateViews(){
            setUpParent();

        }

        @Override
        protected void onFinishInflate() {
            super.onFinishInflate();
            start();
        }

        private void start(){
            for(int i=getChildCount() -1;i>=0;i--) {
                if (!getChildAt(i).getClass().getSimpleName().equalsIgnoreCase("Button")) {
                    try {
                        throw new InvalidClassException(TAG, "Only Buttons are allowed as a child of this view not "+getChildAt(i).getClass().getSimpleName());
                    } catch (InvalidClassException e) {
                        e.printStackTrace();
                    }
                }
                Button button = (Button) getChildAt(i);
                button.setTextColor(mActiveColor);
                setUpButton(button, i);

            }
        }

        private void setUpButton(final Button button, final int position){

            final float radius = mRadius * 4;
            final float[] corners;

            float[] uniqueCorners = {radius, radius, radius, radius, radius, radius, radius, radius};
            if(position == 0){
                corners = new float[]{radius,radius, 0, 0, 0, 0, radius,radius};
                ((LayoutParams) button.getLayoutParams()).setMargins(0, 0, mStrokeWidth*4, 0);
            }else if(position == (getChildCount() - 1)){
                corners = new float[]{0,0,radius,radius,radius,radius, 0,0};
            }else{
                corners = new float[]{0,0,0,0,0,0,0,0};
                ((LayoutParams) button.getLayoutParams()).setMargins(0, 0, mStrokeWidth*4, 0);
            }

            final RectF rectF = new RectF();


            ShapeDrawable shapeDrawable = null;
            if(mStyle == STYLE.BOX){
                shapeDrawable = new ShapeDrawable(new RoundRectShape(corners, rectF, null));
            }else{
                shapeDrawable = new ShapeDrawable(new RoundRectShape(uniqueCorners, rectF, null));
            }
            final Paint drawablePaint = shapeDrawable.getPaint();

            if(position == mSelectedIndex){
                drawablePaint.setColor(mActiveColor);
                button.setTextColor(mActiveTextColor);
            }else{
                button.setTextColor(mInactiveTextColor);
                drawablePaint.setColor(mInActiveColor);
            }

            final ShapeDrawable finalShapeDrawable = shapeDrawable;
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedIndex = position;

                    float[] uniqueCorners = {radius, radius, radius, radius, radius, radius, radius, radius};

                    for (int i = getChildCount() - 1; i >= 0; i--) {

                        float[] newCorners;
                        if (i == 0) {
                            newCorners = new float[]{radius, radius, 0, 0, 0, 0, radius, radius};
                        } else if (i == (getChildCount() - 1)) {
                            newCorners = new float[]{0, 0, radius, radius, radius, radius, 0, 0};
                        } else {
                            newCorners = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
                        }

                        ShapeDrawable newShapeDrawable;
                        if (mStyle == STYLE.BOX) {
                            newShapeDrawable = new ShapeDrawable(new RoundRectShape(newCorners, rectF, null));
                        } else {
                            newShapeDrawable = new ShapeDrawable(new RoundRectShape(uniqueCorners, rectF, null));
                        }
                        Paint newPaint = newShapeDrawable.getPaint();
                        newPaint.setColor(mInActiveColor);
                        newPaint.setAntiAlias(true);
                        Button child = (Button) getChildAt(i);
                        mSetBackgroundDrawable(child, newShapeDrawable);
                        child.setTextColor(mInactiveTextColor);
                    }

                    Button button1 = (Button) v;
                    if (changeListener != null) {
                        changeListener.onSelectChange(position, button1.getText().toString());
                    }
                    button1.setTextColor(mActiveTextColor);
                    drawablePaint.setColor(mActiveColor);
                    mSetBackgroundDrawable(button1, finalShapeDrawable);
                }

            });

            drawablePaint.setAntiAlias(true);
            mSetBackgroundDrawable(button, shapeDrawable);
        }

        private void setUpParent(){
            float radius = (mRadius * 4) + (mStrokeWidth * 4);
            int strokeWidth = mStrokeWidth * 4;
            float[] corners = new float[]{radius,radius, radius,radius, radius,radius, radius,radius};
            RectF rectF = new RectF();
            setPadding(strokeWidth, strokeWidth, strokeWidth, strokeWidth);
            ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(corners, rectF, null));
            Paint drawablePaint = shapeDrawable.getPaint();
            drawablePaint.setColor(mActiveColor);
            drawablePaint.setAntiAlias(true);
            mSetBackgroundDrawable(this, shapeDrawable);
            setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        public void setOnSelectChangeListener(onSelectChangeListener listener){
            changeListener = listener;
        }

        public int getSelectedIndex(){
            return mSelectedIndex;
        }

        public void setSelectedIndex(int index){
            if(index >= getChildCount()){
                try {
                    throw new Exception("Invalid selected index, index out of range");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                mSelectedIndex = index;
                start();
            }
        }

        public void setStrokeWidth(int width){
            mStrokeWidth = width;
            start();
        }

        public void setActiveColor(int color){
            mActiveColor = color;
            start();
            setUpParent();
        }

        public void setInActiveColor(int color){
            mInActiveColor = color;
            start();
        }

        public void setActiveTextColor(int color){
            mActiveTextColor = color;
            start();
        }

        public void setInactiveTextColor(int color){
            mInactiveTextColor = color;
            start();
        }

        public void setRadius(float radius){
            mRadius = radius;
            start();
        }

        public enum STYLE{
            ROUND, BOX
        }

        public void setStyle(STYLE style){
            this.mStyle = style;
            start();
        }

        private void mSetBackgroundDrawable(View v, Drawable drawable){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                v.setBackground(drawable);
            }else{
                v.setBackgroundDrawable(drawable);
            }
        }


        public interface onSelectChangeListener{
            void onSelectChange(int index, String selectedText);
        }

}

