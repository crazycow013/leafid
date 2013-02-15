package com.example.leafid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LeafID extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_leaf_id);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.leaf_id, menu);
    return true;
  }

}
