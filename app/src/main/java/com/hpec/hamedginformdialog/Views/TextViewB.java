package com.hpec.hamedginformdialog.Views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.hpec.hamedginformdialog.Utils;

public class TextViewB extends AppCompatTextView {

    public TextViewB(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public TextViewB(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextViewB(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        if (!isInEditMode())
            setTypeface(Utils.setTypeface(context), Typeface.BOLD);
    }

    public String getString() {
        return this.getText().toString();
    }


    public int getInt() {

        try {
            return Integer.parseInt(this.getText().toString());
        } catch (Exception e) {
            return 0;
        }
    }}