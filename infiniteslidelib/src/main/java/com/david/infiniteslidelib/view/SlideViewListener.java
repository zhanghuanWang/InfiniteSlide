package com.david.infiniteslidelib.view;

import android.view.View;

/**
 * Created by zhumingwei on 17/3/9.
 */

public interface SlideViewListener {
    void onChange(View v, float offset,boolean left);
}
