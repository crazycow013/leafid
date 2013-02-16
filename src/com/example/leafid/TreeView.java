package com.example.leafid;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TreeView extends RelativeLayout {
    Context context;

    public TreeView(Context context, AttributeSet attrs,
                    String resultID) {
        super(context, attrs);
        this.context = context;
        initializeViews();
    }

    public TreeView(Context context, String resultID) {
        super(context);
        this.context = context;
        initializeViews();
    }

    private void initializeViews() {
        Resources r = getResources();
        // For RelativeLayout
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        (int) r.getDimension(R.dimen.treeview_height));
        setLayoutParams(params1);
        
        // For ImageView
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        (int) r.getDimension(R.dimen.treeview_height));
        params2.setMargins(16, 16, 16, 8);
        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setImageResource(R.drawable.tub);
        iv.setLayoutParams(params2);
        addView(iv);
    }
}
