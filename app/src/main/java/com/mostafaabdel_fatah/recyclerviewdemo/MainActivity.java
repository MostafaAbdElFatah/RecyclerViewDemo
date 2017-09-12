package com.mostafaabdel_fatah.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    GridLayoutManager gridLayoutManager;
    int [] images = {R.drawable.image1,R.drawable.image2, R.drawable.image3,
            R.drawable.image4,R.drawable.image5,R.drawable.image6,R.drawable.image7,
            R.drawable.image8,R.drawable.image9,R.drawable.paths1,R.drawable.paths2,
            R.drawable.paths3,R.drawable.paths4,R.drawable.paths5,R.drawable.paths6,
            R.drawable.paths7,R.drawable.paths8,R.drawable.paths9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        ArrayList<Item> arrayList = new ArrayList<Item>();
        /*******/
        for(int i = 0 ; i < 40 ; i++) {
            Item item = new Item();
            item.setText("image " + i);
            item.setImage(images[i%images.length]);
            arrayList.add(item);
        }
        /********/
        recyclerViewAdapter = new RecyclerViewAdapter(this,arrayList,gridLayoutManager);
        recyclerViewAdapter.lisneter = new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Item item) {
                Intent intent = new Intent(MainActivity.this,detailsActivity.class);
                intent.putExtra("name",item.getText());
                intent.putExtra("image",item.getImage());
                startActivity(intent);
            }
        };
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item)
        {
            switchLayout();
            switchIcon(item);
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    private  void  switchLayout(){
        switch (gridLayoutManager.getSpanCount()) {
            case 1:
                gridLayoutManager.setSpanCount(2);
                break;
            case 2:
                gridLayoutManager.setSpanCount(1);
                break;
            default:
                gridLayoutManager.setSpanCount(1);
        }
        recyclerViewAdapter.notifyItemRangeChanged(0,recyclerViewAdapter.getItemCount());
    }
    private  void  switchIcon(MenuItem item){
        if(gridLayoutManager.getSpanCount() == 1 ){
            item.setIcon(R.drawable.ic_span_1);
        }else {
            item.setIcon(R.drawable.ic_span_3);
        }
    }
    // end of class

}
