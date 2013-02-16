package com.example.leafid;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QueryView extends RelativeLayout {
    Context context;
    Resources r;
    BTree treeNode;

    public QueryView(Context context, AttributeSet attrs,
                    BTree treeNode) {
        super(context, attrs);
        this.context = context;
        this.treeNode = treeNode;
        initialize();
    }

    public QueryView(Context context, BTree treeNode) {
        super(context);
        this.context = context;
        this.treeNode = treeNode;
        initialize();
    }

    private void initialize() {
        initializeViews();
        r = getResources();
    }

    // Apply layout parameters and prepare other UI elements.
    private void initializeViews() {
        // For RelativeLayout
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        (int) r.getDimension(R.dimen.treeview_height));
        setLayoutParams(params1);

        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        (int) r.getDimension(R.dimen.treeview_height));
        params2.setMargins(
                        (int) r.getDimension(R.dimen.activity_horizontal_margin),
                        (int) r.getDimension(R.dimen.activity_vertical_margin),
                        (int) r.getDimension(R.dimen.activity_horizontal_margin),
                        (int) r.getDimension(R.dimen.activity_vertical_margin) / 2);
        prepareImageView(params2);
        prepareTextView(params2);
    }

    private void prepareImageView(RelativeLayout.LayoutParams params) {
        // For ImageView

        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setImageResource(R.drawable.tub);
        iv.setLayoutParams(params);
        addView(iv);
    }

    private void prepareTextView(RelativeLayout.LayoutParams params) {
        TextView tv = new TextView(context);
        tv.setText(treeNode.getQuery());
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.BOTTOM);
        addView(tv);
    }
}
