package com.example.leafid;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LeafID extends Activity {
    RelativeLayout topLayout;
    ListView listView;
    MyArrayAdapter aa;
    ArrayList<BTree> history;
    Button prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaf_id);
        topLayout = (RelativeLayout) findViewById(R.id.TopLayout);
        listView = (ListView) findViewById(R.id.ListView);
        prev = (Button) findViewById(R.id.BtnPrev);

        // Keeps track of last user BTree.
        history = new ArrayList<BTree>();

        initializeChoices();

        listView.setDivider(null);
        listView.setDividerHeight(0);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
                int selectedIndex = getSelected();
                if (selectedIndex != -1 && selectedIndex != position)
                    aa.getItem(selectedIndex).select();
                aa.getItem(position).select();
            }
        });

        // Handle Button clicks.
        Button btnNext = (Button) findViewById(R.id.BtnNext);
        Button btnPrev = (Button) findViewById(R.id.BtnPrev);
        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedIndex = getSelected();
                if (selectedIndex != -1) {
                    QueryView selectedQV = aa.getItem(selectedIndex);
                    if (selectedQV.isAnswer()) {
                        Toast.makeText(LeafID.this, "Selected an answer!", Toast.LENGTH_SHORT).show();
                    } else {
                        Query selectedQ = (Query) selectedQV
                                        .getQuery();
                        aa.clear();
                        if (history.size() == 0)
                            // If root, return children otherwise
                            // return other children. FOR DEBUGGING.
                            aa.addAll(QueryView.q2QV(
                                            LeafID.this,
                                            BTree.getChildren(selectedQ)));
                        else
                            aa.addAll(QueryView.q2QV(
                                            LeafID.this,
                                            BTree.getChildren1(selectedQ)));
                        // Add selected query to that history stack
                        prev.setEnabled(true);
                    }
                    history.add(0, selectedQV.getQuery());
                }
            }
        });
        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("pOCL", "Prev button clicked");
                if (history.size() == 1) {
                    Log.d("pOCL", "Size = 1. Initializing choices.");
                    history.remove(0);
                    initializeChoices();
                } else if (history.size() > 1) {
                    BTree parent = history.remove(0);
                    aa.clear();
                    aa.addAll(QueryView.q2QV(LeafID.this,
                                    BTree.getChildren(parent)));
                }
            }
        });
        Log.d("LID", "onCreate finished successfully");
    }

    private void initializeChoices() {
        ArrayList<QueryView> init = QueryView.q2QV(this,
                        BTree.initialize());
        aa = new MyArrayAdapter(this, init);
        listView.setAdapter(aa);
        Log.d("LID", "listView successfully set adapter");
        prev.setEnabled(false);
    }

    // Get index of selected QueryView or return -1.
    private int getSelected() {
        for (int i = 0; i < aa.getCount(); i++) {
            if (aa.getItem(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }

    /*
     * // Manage ActionBar (NOT USED RIGHT NOW)
     * 
     * @Override public boolean onCreateOptionsMenu(Menu menu) {
     * getMenuInflater().inflate(R.menu.leaf_id, menu); return true; }
     */
    @Override
    public void onBackPressed() {
        if (history.size() == 0)
            super.onBackPressed();
        else
            new AlertDialog.Builder(this)
                            .setTitle("Really exit?")
                            .setNegativeButton(android.R.string.no,
                                            null)
                            .setPositiveButton(
                                            android.R.string.yes,
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(
                                                                DialogInterface arg0,
                                                                int arg1) {

                                                    LeafID.super.onBackPressed();
                                                }
                                            }).create().show();
    }

    // Applies QueryViews to ListView.
    class MyArrayAdapter extends ArrayAdapter<QueryView> {
        Context context;
        ArrayList<QueryView> list;

        public MyArrayAdapter(Context context,
                        ArrayList<QueryView> list) {
            super(context, android.R.layout.simple_list_item_1, list);
            this.context = context;
            this.list = list;
            Log.d("MAA", "MAA created...");
        }

        public View getView(int position, View convertView,
                        ViewGroup parent) {
            // if (convertView == null) {
            View view = list.get(position);

            // RelativeLayout params for the QueryView cannot be cast to
            // AbsListView params sooooo...
            AbsListView.LayoutParams params1 = new AbsListView.LayoutParams(
                            AbsListView.LayoutParams.MATCH_PARENT,
                            (int) getResources().getDimension(
                                            R.dimen.queryview_height));
            view.setLayoutParams(params1);
            return view;
            // }
            // return convertView;
        }

        @Override
        public QueryView getItem(int position) {
            return list.get(position);
        }
    }
}
