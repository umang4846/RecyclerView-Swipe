package com.appprocessors.recyclerview_swipe;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appprocessors.recyclerview_swipe.adapter.MyAdapter;
import com.appprocessors.recyclerview_swipe.helper.MyButtonClickListener;
import com.appprocessors.recyclerview_swipe.helper.MySwipeHelper;
import com.appprocessors.recyclerview_swipe.model.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    MyAdapter adapter;

    LinearLayoutManager linearLayoutManager;

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);

        MySwipeHelper mySwipeHelper = new MySwipeHelper(this, recycler, 200) {
            @Override
            protected void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {
                buffer.add(new MyButton(MainActivity.this,
                        "Delete",
                        45,
                        0,
                        Color.parseColor("#CD0000"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                Toast.makeText(MainActivity.this, "Delete Clicked " + pos, Toast.LENGTH_SHORT).show();
                            }
                        }));

                buffer.add(new MyButton(MainActivity.this,
                        "Update",
                        45,
                        R.drawable.ic_edit_white_24dp,
                        Color.parseColor("#FF9502"),
                        pos -> Toast.makeText(MainActivity.this, "Update Clicked " + pos, Toast.LENGTH_SHORT).show()));
            }
        };

        generateItem();


    }

    private void generateItem() {

        List<Item> itemList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            itemList.add(new Item("Pie 0" + (++i),
                    "1000 Rs.",
                    "https://www.tablefortwoblog.com/wp-content/uploads/2019/01/buffalo-chicken-pizza-recipe-photos-tablefortwoblog-3-400x400.jpg"));
        }

        adapter = new MyAdapter(this, itemList);
        recycler.setAdapter(adapter);

    }
}
