package com.google.android.material.bottomappbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

class BottomAppBar extends View {
    public BottomAppBar(Context context) {
        this(context, null);
    }

    public BottomAppBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomAppBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
