package com.example.leafid;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

// Responsibilities: Display ImageView, TextView; manage whether is selected;
//                   Respond to AnswerHUH; return children of chosen Query
public class QueryView extends RelativeLayout {
    private Context context;
    private Resources r;
    private BTree query;
    // Last digit of held Query's resultID
    private int resultID;
    // Is this the user choice?
    private boolean selected;
    // Manages visuals.
    QueryViewView qvv;

    public static ArrayList<QueryView> queriesToQueryViews(
                    Context context, ArrayList<BTree> list) {
        ArrayList<QueryView> result = new ArrayList<QueryView>();
        for (int i = 0; i < list.size(); i++) {
            result.add(new QueryView(context, list.get(i)));
        }
        return result;
    }

    public QueryView(Context context, AttributeSet attrs, BTree query) {
        super(context, attrs);
        this.context = context;
        this.query = query;
        initialize();
    }

    public QueryView(Context context, BTree query) {
        super(context);
        this.context = context;
        this.query = query;
        initialize();
    }

    private void initialize() {
        r = getResources();
        String queryResultID = query.getResultID();
        resultID = Integer.valueOf(query.getResultID().substring(
                        queryResultID.length() - 1,
                        queryResultID.length()));
        selected = true;
        select();
        qvv = new QueryViewView();
        qvv.initializeViews();
    }

    public void select() {
        qvv.select();
    }

    public boolean isSelected() {
        return selected;
    }

    public ArrayList<BTree> getChildren() {
        return BTree.getChildren((Query) query);
    }

    // Does this have the Query that corresponds with the user's choice?
    public boolean hasQuery(int choice) {
        return resultID == choice;
    }

    // Does this QueryView hold an Answer?
    public boolean isAnswer() {
        return query.isAnswer();
    }

    public int getResultID() {
        return resultID;
    }

    private class QueryViewView {

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
            tv.setText(query.getQuery());
            tv.setLayoutParams(params);
            tv.setGravity(Gravity.BOTTOM);
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(r.getDimension(R.dimen.queryview_textsize));
            tv.setBackground(r.getDrawable(R.drawable.gradient));
            addView(tv);
        }
        
        private void select(){
            selected = !selected;
            if (selected)
                setBackgroundColor(r
                                .getColor(android.R.color.holo_blue_dark));
            else
                setBackgroundColor(Color.BLACK);
        }
    }
}
