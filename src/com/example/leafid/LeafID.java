package com.example.leafid;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

public class LeafID extends Activity {

    BTree bTree;
    RelativeLayout topLayout;
    ListView listView;
    MyArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaf_id);

        bTree = BTree.initialize();

        topLayout = (RelativeLayout) findViewById(R.id.TopLayout);
        listView = (ListView) findViewById(R.id.ListView);

        // Display list of QueryViews.
        listView.setDivider(null);
        listView.setDividerHeight(0);
        
        ArrayList<QueryView> d = new ArrayList<QueryView>();
        d.add(new QueryView(this, new Query("12345", "HELLO WORLD")));
        d.add(new QueryView(this, new Query("@#@!", "NIGGAS!")));
        d.add(new QueryView(this, new Query("12345", "HELLO WORLD")));
        d.add(new QueryView(this, new Query("@#@!", "NIGGAS!")));
        
        aa = new MyArrayAdapter(this, d);
        listView.setAdapter(aa);
        
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
                    if (aa.getItem(selectedIndex).isAnswer()) {
                        // If the selected item holds an answer...
                    } else {
                        // Otherwise...

                    }
                }
            }
        });
        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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

    // Manage ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.leaf_id, menu);
        return true;
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
        }

        public View getView(int position, View convertView,
                        ViewGroup parent) {
            if (convertView == null) {
                View view = list.get(position);

                // For RelativeLayout
                AbsListView.LayoutParams params1 = new AbsListView.LayoutParams(
                                AbsListView.LayoutParams.MATCH_PARENT,
                                (int) getResources()
                                                .getDimension(R.dimen.queryview_height));
                view.setLayoutParams(params1);
                return view;
            }
            return convertView;
        }

        @Override
        public QueryView getItem(int position) {
            return list.get(position);
        }
    }
}
