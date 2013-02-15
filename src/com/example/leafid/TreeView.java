package com.example.leafid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TreeView extends RelativeLayout {

    public TreeView(Context context, AttributeSet attrs,
                    String resultID) {
        super(context, attrs);
        ImageView iv = new ImageView(context);
        iv.setImageResource(R.drawable.tub);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
        params.height = R.dimen.imageview_height;
        iv.setLayoutParams(params);
    }

}
