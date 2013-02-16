package com.example.leafid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QueryView extends RelativeLayout {
    private Context context;
    private Resources r;
    private BTree nextQuery;
    // Is this the user choice?
    private boolean selected;

    public QueryView(Context context, AttributeSet attrs,
                    BTree treeNode) {
        super(context, attrs);
        this.context = context;
        this.nextQuery = treeNode;
        initialize();
    }

    public QueryView(Context context, BTree treeNode) {
        super(context);
        this.context = context;
        this.nextQuery = treeNode;
        initialize();
    }

    private void initialize() {
        r = getResources();
        selected = true;
        select();
        initializeViews();
    }

    public void select() {
        selected = !selected;
        if (selected)
            setBackgroundColor(r
                            .getColor(android.R.color.holo_blue_dark));
        else
            setBackgroundColor(Color.BLACK);
    }

    // Apply layout parameters and prepare other UI elements.
    private void initializeViews() {
        prepareImageView();
        prepareTextView();
    }

    private void prepareImageView() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        (int) r.getDimension(R.dimen.queryview_height));
        params.setMargins(
                        (int) r.getDimension(R.dimen.activity_horizontal_margin) / 2,
                        (int) r.getDimension(R.dimen.activity_vertical_margin) / 2,
                        (int) r.getDimension(R.dimen.activity_horizontal_margin) / 2,
                        (int) r.getDimension(R.dimen.activity_vertical_margin) / 2);

        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setImageResource(R.drawable.tub);
        iv.setLayoutParams(params);
        addView(iv);
    }

    private void prepareTextView() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(
                        (int) r.getDimension(R.dimen.activity_horizontal_margin) / 2,
                        (int) r.getDimension(R.dimen.activity_vertical_margin) / 2,
                        (int) r.getDimension(R.dimen.activity_horizontal_margin) / 2,
                        (int) r.getDimension(R.dimen.activity_vertical_margin) / 2);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
                        RelativeLayout.TRUE);

        TextView tv = new TextView(context);
        tv.setText(nextQuery.getQuery());
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.BOTTOM);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(r.getDimension(R.dimen.queryview_textsize));
        tv.setBackground(r.getDrawable(R.drawable.gradient));
        addView(tv);
    }
}
