package com.hpec.hamedginformdialog;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {
    public static Typeface setTypeface(Context activity) {
        return  Typeface.createFromAsset(activity.getAssets(),  "IRANSansMobile.ttf");
    }
}
