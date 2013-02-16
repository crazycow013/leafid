package com.example.leafid;

import android.content.Context;
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
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT, getDP(192));
        setLayoutParams(params);
        setBackgroundColor(getResources().getColor(
                        android.R.color.holo_purple));
        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setImageResource(R.drawable.tub);
        iv.setLayoutParams(params);
        addView(iv);
    }
    
    private int getDP(int dps){
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }

}
