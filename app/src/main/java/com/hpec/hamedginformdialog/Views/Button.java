package com.hpec.hamedginformdialog.Views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.hpec.hamedginformdialog.Utils;

public class Button extends androidx.appcompat.widget.AppCompatButton {
    public Button(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Button(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        if (!isInEditMode()) {
            setTypeface(Utils.setTypeface(context), Typeface.NORMAL);
        }

    }
}
