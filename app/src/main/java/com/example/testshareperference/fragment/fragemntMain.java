package com.example.testshareperference.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testshareperference.R;
import com.example.testshareperference.activity.DataProvider;

import com.example.testshareperference.activity.adapter.ToDoAdapter;
import com.example.testshareperference.activity.entity.item_EverSummary;
import com.xuexiang.xui.widget.banner.anim.select.ZoomInEnter;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;
import com.xuexiang.xui.widget.banner.widget.banner.base.BaseBanner;
import com.xuexiang.xui.widget.layout.linkage.view.LinkageRecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class fragemntMain extends Fragment {


    Button button = null;
    TextView editText = null;
    EditText editview2;
    com.xuexiang.xui.widget.banner.widget.banner.SimpleImageBanner banner;
    LinkageRecyclerView reclyerViewTodo;
    private List<item_EverSummary> item_everSummaries_list = new ArrayList<>();
    Date kaoYanDate = null;
    Date today ;
    private List<BannerItem> mData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initViewID(view);
        initRecylerViewData();
        initData();
        initKaoYanDate();
        banner.setSource(mData)
                .setSelectAnimClass(ZoomInEnter.class)
                .setOnItemClickListener(new BaseBanner.OnItemClickListener<BannerItem>() {
                    @Override
                    public void onItemClick(View view, BannerItem item, int position) {
                        Toast.makeText(getContext(),mData.get(position).title, Toast.LENGTH_SHORT).show();
                    }
                })
                .startScroll();


        editText.setText("距离考研倒计时："+initReverseTime()+"天");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editview2.getText().toString().equals("")){
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mySummary",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String today = simpleDateFormat.format(date);
                    editor.putString(today,editview2.getText().toString());
                    editor.commit();
                    editview2.setText("");
                }else{
                    Toast.makeText(getContext(),"你还没总结呢，乱按可不好", Toast.LENGTH_SHORT).show();
                }
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        reclyerViewTodo.setLayoutManager(layoutManager);
        ToDoAdapter toDoAdapter = new ToDoAdapter(item_everSummaries_list);
        reclyerViewTodo.setAdapter(toDoAdapter);

        return view;
    }

    private void initRecylerViewData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mySummary",Context.MODE_PRIVATE);
        item_EverSummary item_everSummary = new item_EverSummary();
        Set<String> strings = sharedPreferences.getAll().keySet();

        for(String s:strings){
            item_everSummary.setDate(s);
            item_everSummary.setContent(sharedPreferences.getString(s,"没写东西"));
            item_everSummaries_list.add(item_everSummary);
        }
    }

    private int initReverseTime() {
        SharedPreferences sp=getActivity().getSharedPreferences("Date",Context.MODE_PRIVATE);
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");
        today = new Date();
        try {
            kaoYanDate = simpleFormatter.parse(sp.getString("kaoYanDate", "2020-12-16"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long kaoYanDateNum = kaoYanDate.getTime();
        long todayNum = today.getTime();

        int num = (int) ((kaoYanDateNum-todayNum)/(24*60*60*1000));
        return num;
    }


    private void initData() {
        mData = DataProvider.getBannerList();
    }
    private void initKaoYanDate() {
        SharedPreferences sp=getActivity().getSharedPreferences("Date",Context.MODE_PRIVATE);
        if(sp.getString("kaoYanDate","-1").equals("-1")){
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("kaoYanDate", "2021-12-26");
            editor.commit();
        }
    }
    private void initViewID(View view) {
        button = (Button) view.findViewById(R.id.main_button_add);//+
        editText = (TextView) view.findViewById(R.id.editview);//倒计时
        editview2 = (EditText) view.findViewById(R.id.editview2);

        reclyerViewTodo = (LinkageRecyclerView) view.findViewById(R.id.reclyerView_todo);
        banner = view.findViewById(R.id.sib_the_most_comlex_usage);
    }
}
