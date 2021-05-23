package com.example.testshareperference.activity.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class MyAdapter extends PagerAdapter {

    List<View> mListView;


    public MyAdapter(List<View> mListView) {
        this.mListView = mListView;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mListView.get(position));
//        super.destroyItem(container, position, object);
    }



    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //这个方法用来实例化页卡
        container.addView(mListView.get(position), 0);//添加页卡
        return mListView.get(position);
//        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return mListView.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
//        return false;
    }

    public List<View> getmListView() {
        return mListView;
    }

    public void setmListView(List<View> mListView) {
        this.mListView = mListView;
    }
}
