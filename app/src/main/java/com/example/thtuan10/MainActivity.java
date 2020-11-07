package com.example.thtuan10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private List<Product> productList;
    private int currentViewMode = 0;

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stubList = (ViewStub) findViewById(R.id.stub_list);
        stubGrid = (ViewStub) findViewById(R.id.stub_grid);

        //Inflate ViewStub before get view

        stubList.inflate();
        stubGrid.inflate();

        listView = (ListView) findViewById(R.id.myListView);
        gridView = (GridView) findViewById(R.id.myGridView);

        getProductList();

        //Get current view mode in share references

        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);

        //Register item lick

        listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);
        switchView();

    }

    private void switchView(){
        if(VIEW_MODE_LISTVIEW == currentViewMode){
            //Display Listview
        stubList.setVisibility(View.VISIBLE);
        //Hide gridView
        stubGrid.setVisibility(View.GONE);
        }else{
            //Hide Listview
            stubList.setVisibility(View.GONE);
            //Display gridview
            stubGrid.setVisibility(View.VISIBLE);
        }
        setAdapters();
    }
    private void setAdapters()
    {
        if(VIEW_MODE_LISTVIEW == currentViewMode){
            listViewAdapter = new ListViewAdapter(this, R.layout.list_item, productList);
            listView.setAdapter(listViewAdapter);
        }else{
            gridViewAdapter = new GridViewAdapter(this, R.layout.grid_item, productList);
            gridView.setAdapter(gridViewAdapter);
        }

    }
    public List<Product> getProductList(){
        //pseudo code to get product, replace your code to get real product here
        productList = new ArrayList<>();
        productList.add(new Product(R.drawable.ic_baseline_android_24,"Dưỡng","18T2 1"));
        productList.add(new Product(R.drawable.ic_baseline_android_24,"Duy","18T2 2"));
        productList.add(new Product(R.drawable.ic_baseline_android_24,"Long","18T2 3"));
        productList.add(new Product(R.drawable.ic_baseline_android_24,"Khoa","18T2 4"));
        productList.add(new Product(R.drawable.ic_baseline_android_24,"vương","18T2 5"));
        productList.add(new Product(R.drawable.ic_baseline_android_24,"Dũng","18T2 6"));
        productList.add(new Product(R.drawable.ic_baseline_android_24,"Hoàng","18T2 7"));
        productList.add(new Product(R.drawable.ic_baseline_android_24,"Tiến","18T2 8"));
        productList.add(new Product(R.drawable.ic_baseline_android_24,"Tuấn", "18T2 9"));


        return productList;

    }
    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Do any thing when user click to item
            Toast.makeText(getApplicationContext(),productList.get(position).getTitle() + " - "+productList.get(position).getDescription(), Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_menu_1:
                if(VIEW_MODE_LISTVIEW == currentViewMode){
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                }else{
                    currentViewMode = VIEW_MODE_LISTVIEW;
                }
                //Switch view
                switchView();
                //Save view mode in share references
                SharedPreferences sharedPreferences = getSharedPreferences("ViewMode",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentViewMode",currentViewMode);
                editor.commit();

                break;
        }
        return true;
    }
}
























