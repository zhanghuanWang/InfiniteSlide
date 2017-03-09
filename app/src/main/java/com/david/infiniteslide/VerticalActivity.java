package com.david.infiniteslide;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.david.infiniteslidelib.view.InfiniteSlideView;
import com.david.infiniteslidelib.view.SlideViewListener;

import java.util.ArrayList;
import java.util.List;

public class VerticalActivity extends AppCompatActivity {

    InfiniteSlideView infiniteSlideView1,infiniteSlideView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);
        infiniteSlideView1 = (InfiniteSlideView) findViewById(R.id.slide_view1);
        infiniteSlideView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        infiniteSlideView2 = (InfiniteSlideView) findViewById(R.id.slide_view1);
        infiniteSlideView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        initdata();
    }

    private void initdata() {

        MyAdapter myAdapter1 = new MyAdapter(getAllData());
        infiniteSlideView1.setAdapter(myAdapter1);
        infiniteSlideView1.setSlideViewListener(new SlideViewListener() {
            @Override
            public void onChange(View v, float offset, boolean left) {
                v.setScaleX(1 - offset * 0.5f);
                v.setScaleY(1 - offset * 0.5f);
                TextView textView = (TextView) v.findViewById(R.id.textview);
                textView.setText(textView.getText().subSequence(0, 9) + " /n" + Float.toString(offset));
            }
        });
        infiniteSlideView1.scrollToPosition(50000-50000 % myAdapter1.getRealSize());
        MyAdapter myAdapter2 = new MyAdapter(getAllData());
        infiniteSlideView2.setAdapter(myAdapter2);
        infiniteSlideView2.setSlideViewListener(new SlideViewListener() {
            @Override
            public void onChange(View v, float offset, boolean left) {
                v.setRotation(left ? -offset * 360 : offset * 360);
                TextView textView = (TextView) v.findViewById(R.id.textview);
                textView.setText(textView.getText().subSequence(0, 9) + " /n" + Float.toString(offset));
            }
        });
        infiniteSlideView2.scrollToPosition(50000-50000 % myAdapter1.getRealSize());

    }

    private List<Bean> getAllData() {
        List<Bean> beenList = new ArrayList<>();
        beenList.add(new Bean(Color.BLUE, "textview1"));
        beenList.add(new Bean(Color.MAGENTA, "textview2"));
        beenList.add(new Bean(Color.GREEN, "textview3"));
        beenList.add(new Bean(Color.RED, "textview4"));
        beenList.add(new Bean(Color.YELLOW, "textview5"));
        return beenList;
    }


    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        List<Bean> beanList;

        public MyAdapter(List<Bean> beanList) {
            this.beanList = beanList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(VerticalActivity.this).inflate(R.layout.item, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            int realposition = position % getRealSize();
            Bean bean = beanList.get(realposition);
            holder.textView.setText(bean.text);
            holder.rootView.setBackgroundColor(bean.backgroundColor);
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        public int getRealSize() {
            return beanList.size();
        }
    }

    class Bean {
        public Bean(int backgroundColor, String text) {
            this.backgroundColor = backgroundColor;
            this.text = text;
        }

        int backgroundColor;
        String text;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ViewGroup rootView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            rootView = (ViewGroup) itemView.findViewById(R.id.root_view);
            textView = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
