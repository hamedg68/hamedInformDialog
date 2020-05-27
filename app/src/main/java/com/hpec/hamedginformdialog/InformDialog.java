package com.hpec.hamedginformdialog;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hamed on 5/27/18.
 */

public class InformDialog extends AlertDialog {

    private ImageView image;
    private Button positiveButton, negativeButton;
    private TextView dialogTitle;

    private OnEventListeners onEventListeners;

    public InformDialog(@NonNull Activity activity) {
        super(activity);
        setView(activity);
        setListeners();
        show();
        setDimensions(activity);
    }

    public InformDialog(@NonNull Activity activity, ANIMATION animation) {
        super(activity);
        setView(activity);
        setAnimation(animation);
        setListeners();
        show();
        setDimensions(activity);
    }

    private void setView(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.popup_inform_dialog, null);

        this.setView(view);

        this.setCancelable(false);

        positiveButton = (Button) view.findViewById(R.id.positiveBtn);
        negativeButton = (Button) view.findViewById(R.id.negativeBtn);
        dialogTitle = (TextView) view.findViewById(R.id.title);
        image = (ImageView) view.findViewById(R.id.imgDialog);

        dialogTitle.setSelected(true);
    }


    private void setListeners() {

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onEventListeners != null)
                    onEventListeners.onYesClick();

                dismiss();
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onEventListeners != null)
                    onEventListeners.onNoClick();

                dismiss();
            }
        });
    }

    public InformDialog setTitle(String title) {
        dialogTitle.setText(title);

        return this;
    }

    public InformDialog setTitleSize(float size) {
        dialogTitle.setTextSize(size);

        return this;
    }

    public InformDialog setPositiveBtn(String s) {
        positiveButton.setText(s);

        return this;
    }

    public InformDialog setNegativeBtn(String s) {
        negativeButton.setText(s);

        return this;
    }

    public InformDialog setPositiveBtnBackground(Drawable drawable) {

        positiveButton.setBackground(drawable);

        return this;
    }

    public InformDialog setNegativeBtnBackground(Drawable drawable) {

        negativeButton.setBackground(drawable);

        return this;
    }

    public InformDialog cancelable(boolean cancelable) {
        this.setCancelable(cancelable);
        return this;
    }

    private void setDimensions(Activity context) {
        if (this.getWindow() != null)
            this.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.rounded_linear));

        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        width = (int) ((width) * ((double) 18 / 20));

        if (this.getWindow() != null)
            this.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public InformDialog registerListener(OnEventListeners listeners) {
        this.onEventListeners = listeners;
        return this;
    }

    public interface OnEventListeners {
        void onYesClick();

        void onNoClick();

    }

    private void setAnimation(ANIMATION animation) {
        if (getWindow() != null)
            getWindow().getAttributes().windowAnimations = animation.getAnim();
    }

    public InformDialog setDialogType(TYPE TYPE) {
        this.image.setImageResource(TYPE.getImage());
        return this;
    }

    public InformDialog setImage(int icon) {
        this.image.setImageResource(icon);
        return this;
    }

    public InformDialog setButtons(BUTTON button) {

        int id = button.getId();

        if (id == BUTTON.one_button.getId()) {
            positiveButton.setVisibility(View.VISIBLE);
            negativeButton.setVisibility(View.GONE);
            positiveButton.setText(R.string.ok);
        } else if (id == BUTTON.two_button.getId()) {
            positiveButton.setVisibility(View.VISIBLE);
            negativeButton.setVisibility(View.VISIBLE);
            positiveButton.setText(R.string.yes);
            negativeButton.setText(R.string.no);
        }

        return this;
    }

    public enum ANIMATION {
        bottomToTop(R.style.dialog_bottom_to_top),
        topToBottom(R.style.dialog_top_to_bottom),
        leftToRight(R.style.dialog_left_to_right),
        rightToLeft(R.style.dialog_right_to_left),
        fade(R.style.dialog_fade);

        private int anim;

        ANIMATION(int anim) {
            this.anim = anim;

        }

        @SuppressLint("UseSparseArrays")
        private static Map<Integer, ANIMATION> map = new HashMap<>();

        static {
            for (ANIMATION type : ANIMATION.values()) {
                map.put(type.anim, type);
            }
        }

        public static ANIMATION valueOf(int type) {
            return map.get(type);
        }

        public int getAnim() {
            return anim;
        }

    }

    public enum BUTTON {
        one_button(1),
        two_button(2);

        private int id;

        BUTTON(int id) {
            this.id = id;

        }

        @SuppressLint("UseSparseArrays")
        private static Map<Integer, BUTTON> map = new HashMap<>();

        static {
            for (BUTTON type : BUTTON.values()) {
                map.put(type.id, type);
            }
        }

        public static BUTTON valueOf(int type) {
            return map.get(type);
        }

        public int getId() {
            return id;
        }

    }

    public enum TYPE {
        confirm(R.drawable.confirm),
        warning(R.drawable.warning),
        info(R.drawable.inform),
        error(R.drawable.error);

        private int image;

        TYPE(int kind) {
            this.image = kind;
        }

        @SuppressLint("UseSparseArrays")
        private static Map<Integer, TYPE> map = new HashMap<>();

        static {
            for (TYPE type : TYPE.values()) {
                map.put(type.image, type);
            }
        }

        public static TYPE valueOf(int type) {
            return map.get(type);
        }

        public int getImage() {
            return image;
        }

    }

}