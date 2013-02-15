package com.example.leafid;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class LeafID extends Activity {

    BTree bTree;
    RelativeLayout topLayout;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaf_id);

        topLayout = (RelativeLayout) findViewById(R.id.TopLayout);
        listView = (ListView) findViewById(R.id.ListView);
        ArrayList<String> d = new ArrayList<String>();
        for(int i = 0; i < 30; i++){
            d.add(String.valueOf(i));
        }
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, d);
        listView.setAdapter(aa);
        bTree = BTree.initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.leaf_id, menu);
        return true;
    }

}
