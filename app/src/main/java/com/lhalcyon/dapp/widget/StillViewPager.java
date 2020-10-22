package com.lhalcyon.dapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * <pre>
 * Create by  :    L
 * Create Time:    2018/4/16
 * Brief Desc :   不可滑动ViewPager
 * </pre>
 */
public class StillViewPager extends ViewPager {

    public StillViewPager(Context context) {
        super(context);
    }

    public StillViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
