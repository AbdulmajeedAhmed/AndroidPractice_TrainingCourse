package com.alyafei.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alyafei.myapplication.others.Item;

import java.util.ArrayList;

public class GridViewEx extends AppCompatActivity {
    TextView myTextView;
    private ArrayList<Item> itemsList;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_ex);

        GridView gridView=(GridView)findViewById(R.id.gridViewItems);

        // create new list and new adapter
        itemsList= new ArrayList<Item>();
        adapter= new Adapter(itemsList);

        // add new items
        itemsList.add(new Item("Banana",2,R.drawable.search));
        itemsList.add(new Item("Orange",3,R.drawable.images));
        itemsList.add(new Item("Strawberry",23,R.drawable.setting));
        gridView.setAdapter(adapter);
    }

    public void addImage(View view) {
        itemsList.add(new Item("Strawberry",23,R.mipmap.ic_launcher));
        adapter.notifyDataSetChanged();
    }

    // This class needs to be here not in seprate class because it need the inheritance AppCompatActivity
    private class Adapter extends BaseAdapter {

        private ArrayList<Item> listOfItems;


        public Adapter(ArrayList<Item> listOfItems){
            this.listOfItems=listOfItems;
        }
        @Override
        public int getCount() {
            return listOfItems.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //link with the layout you design
            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.gridviewdesign,null);

            // use each item
            final Item item=listOfItems.get(position);
            //get the views
            TextView frute=(TextView)view.findViewById(R.id.frute);
            TextView price=(TextView)view.findViewById(R.id.price);
            //put in them the values of the item
            frute.setText(item.getFrute());
            price.setText(""+item.getPrice());


            ImageView ImageView=(ImageView)view.findViewById(R.id.imageView);
            ImageView.setImageResource(item.getImage());

            // to delete any item
            ImageView ImageView2=(ImageView)view.findViewById(R.id.deleteImage);
            ImageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listOfItems.remove(item);
                    // to refresh the list.
                    adapter.notifyDataSetChanged();
                }
            });
            return view;


        }
    }




}

  /*  public void changeName(View view) {
        myTextView =(TextView)findViewById(R.id.myTextView);
        myTextView.setText("fuck you!");
        Intent intent= new Intent(this,Main2Activity.class);


        intent.putExtra("name","Abdulmajid");
        intent.putExtra("age",24);
        startActivity(intent);
    }*/


//GridViewEx