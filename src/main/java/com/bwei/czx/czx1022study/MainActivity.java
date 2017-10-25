package com.bwei.czx.czx1022study;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView all_price;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buy = (Button) findViewById(R.id.buy);
        checkBox = (CheckBox) findViewById(R.id.ck_all);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        all_price = (TextView) findViewById(R.id.all_price);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        //添加死数据
        final List<UserBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new UserBean("商品" + i, false, 100, 1));
        }
        final RvAdapter adapter = new RvAdapter(this, list);
        recyclerView.setAdapter(adapter);


        adapter.setCheckListener(new RvAdapter.CheckListener() {
            @Override
            public void check(boolean check, int position) {
                //监听全选
                boolean all_check = true;
                //价格
                float price = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isB()) {
                        price += list.get(i).getPrice() * list.get(i).getCount();
                    }
                }
                all_price.setText(price + "元");
                for (int i = 0; i < list.size(); i++) {
                    if (!list.get(i).isB()) {
                        all_check = false;
                        break;
                    }
                }

                if (all_check) {
                    checkBox.setChecked(true);
                } else {
                    checkBox.setChecked(false);
                }
            }
        });


        //全选和反选
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = checkBox.isChecked();
                float price = 0;
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setB(check);
                    if (list.get(i).isB()) {
                        price += list.get(i).getPrice() * list.get(i).getCount();
                    }
                }
                adapter.notifyDataSetChanged();
                all_price.setText(price + "元");
            }
        });
    }
}
