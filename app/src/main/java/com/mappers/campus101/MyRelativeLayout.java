package com.mappers.campus101;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Kaan Özkara on 27.4.2016.
 * This class created in order to manage Parent Layout of buttons
 */
public class MyRelativeLayout extends RelativeLayout {

    //Constructor
    public MyRelativeLayout(Context context)
    {
        super(context);
    }


    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        final int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            return false; // Do not intercept touch event, let the child handle it
        }

        return false ;
    }

    public boolean onTouchEvent(MotionEvent ev){
        return true ;
    }
}
