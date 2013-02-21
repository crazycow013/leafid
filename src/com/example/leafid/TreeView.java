package com.example.leafid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class TreeView extends RelativeLayout {
    Context context;
    Answer answer;
    Resources r;
    float dpWidth;

    public TreeView(Context context, Answer answer) {
        super(context);
        this.context = context;
        this.answer = answer;
        dpWidth = getScreenWidth();
        Log.d("TV", "dpWidth: " + dpWidth);
        r = getResources();
        initializeViews();
    }

    private float getScreenWidth() {
        WindowManager wm = (WindowManager) context
                        .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        int pxWidth = size.x;

        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        return (float) (pxWidth / outMetrics.density);
    }

    private void initializeViews() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        prepareImageView();
    }

    private void prepareImageView() {
        RelativeLayout.LayoutParams params;
        if (dpWidth < r.getDimension(R.dimen.max_tree_size)) {
            // If screen size is less than max size in dimensions file...
            params = new RelativeLayout.LayoutParams((int) dpWidth,
                            (int) dpWidth);
            params.setMargins(
                            (int) r.getDimension(R.dimen.activity_horizontal_margin) / 2,
                            (int) r.getDimension(R.dimen.activity_vertical_margin) / 2,
                            (int) r.getDimension(R.dimen.activity_horizontal_margin) / 2,
                            (int) r.getDimension(R.dimen.activity_vertical_margin) / 2);
        } else {
            params = new RelativeLayout.LayoutParams(
                            (int) r.getDimension(R.dimen.max_tree_size),
                            (int) r.getDimension(R.dimen.max_tree_size));
        }

    }
}
