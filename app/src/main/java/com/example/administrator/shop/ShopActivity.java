package com.example.administrator.shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shop.database.SQLiteHelper;


public class ShopActivity extends Activity{
    private ListView mListView;
    SQLiteHelper mSQLiteHelper;
    String id;
    TextView Shopping_name,content;
    private String[] titles={
            "荣耀20S","华为Mare30Pro","荣耀9X","华为Mate20Pro","荣耀10青春版","荣耀20","荣耀8X Max","荣耀 nove 4e",
    };
    private  String[] prices={
            "1499元","6399元","1299元","2899元","899元","2099元","999元","1299元"
    };
    private int[] icons={R.drawable.phone1,R.drawable.phone2,R.drawable.
            phone3,R.drawable.phone4,R.drawable.phone5,R.drawable.phone6,R.drawable.phone7,R.drawable.phone8};
    private android.view.LayoutInflater LayoutInflater;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ImageView car=(ImageView) findViewById(R.id.car);
        mListView=(ListView) findViewById(R.id.lv);
        MybaseAdapter mAdapter =new MybaseAdapter();//
        mSQLiteHelper = new SQLiteHelper(this);
        mListView.setAdapter(mAdapter);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShopActivity.this,ShoplistActivity.class);
                startActivityForResult(intent,1);
            }
        });
        initData();
    }
    protected void initData(){
}
    class MybaseAdapter extends BaseAdapter {
            @Override
            public int getCount() {
                return titles.length;
            }
            @Override
            public Object getItem(int position) {
                return titles[position];
            }
            @Override
            public long getItemId(int position) {
                return position;
            }
            class ViewHolder {
                TextView title, price;
                ImageView iv;
                Button addshop;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = View.inflate(ShopActivity.this, R.layout.list_item, null);
                    holder = new ViewHolder();
                    holder.title =  convertView.findViewById(R.id.title);
                    holder.price = convertView.findViewById(R.id.price);
                    holder.iv =  convertView.findViewById(R.id.iv);
                    holder.addshop = convertView.findViewById(R.id.addshop);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.addshop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean n = mSQLiteHelper.insertData(titles[position],prices[position],icons[position]);
                        if(n){
                            Toast.makeText(ShopActivity.this,"加入购物车成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ShopActivity.this,"加入购物车失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                holder.title.setText(titles[position]);
                holder.price.setText(prices[position]);
                holder.iv.setBackgroundResource(icons[position]);
                return convertView;
            }
        }
    }
