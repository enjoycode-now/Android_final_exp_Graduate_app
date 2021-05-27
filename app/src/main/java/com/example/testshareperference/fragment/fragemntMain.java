package com.example.testshareperference.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testshareperference.R;
import com.example.testshareperference.activity.DataProvider;

import com.example.testshareperference.activity.MainActivity;
import com.example.testshareperference.activity.adapter.ToDoAdapter;
import com.example.testshareperference.activity.entity.item_EverSummary;
import com.google.gson.Gson;
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
import java.util.TimeZone;

public class fragemntMain extends Fragment {


    Button button = null;
    TextView editText = null;
    EditText editview2;
    com.xuexiang.xui.widget.banner.widget.banner.SimpleImageBanner banner;
    LinkageRecyclerView reclyerViewTodo;
    List<item_EverSummary> item_everSummaries_list = new ArrayList<>();
    Date kaoYanDate = null;
    Date today;
    RecyclerView.LayoutManager layoutManager = null;
    ToDoAdapter toDoAdapter = null;
    NestedScrollView nestedScrollView = null;
    private List<BannerItem> mData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //fragment一开始都是要绑定一个fragemnt类的
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViewID(view);
        initRecylerViewData();
        initData();
        initKaoYanDate();




        //轮播条banner
        banner.setSource(mData)
                .setSelectAnimClass(ZoomInEnter.class)
                .setOnItemClickListener(new BaseBanner.OnItemClickListener<BannerItem>() {
                    @Override
                    public void onItemClick(View view, BannerItem item, int position) {
                        Toast.makeText(getContext(), mData.get(position).title, Toast.LENGTH_SHORT).show();
                    }
                })
                .startScroll();


        editText.setText("距离考研倒计时：" + initReverseTime() + "天");

        //滚动总结在这里注入内容
        layoutManager = new LinearLayoutManager(getContext());
        reclyerViewTodo.setLayoutManager(layoutManager);
        toDoAdapter = new ToDoAdapter(item_everSummaries_list);


        //使用recylerView嵌套scrollview的话，最好还是用android.support.v4.widget.NestedScrollView,而不是原生reclyerView
        //然后这里设置false
        reclyerViewTodo.setNestedScrollingEnabled(false);
        nestedScrollView.setNestedScrollingEnabled(false);
        reclyerViewTodo.setAdapter(toDoAdapter);

        //+
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editview2.getText().toString().equals("")) {
                    final int[] position = {0};
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mySummary", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                    String today = simpleDateFormat.format(date);

                    if (sharedPreferences.contains(today)) {
                        //AlertDialog中的监听器中的代码逻辑，不知道为什么无法允许
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.Theme_AppCompat_DayNight_Dialog);
//                        builder.setTitle("你今天已经写总结了哦！")
//                                .setMessage("继续增加的话会覆盖原先的")
//                                .setNegativeButton("算了", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        }).setPositiveButton("我坚持", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                contain[0] = 1;
//                            }
//                        }).show();
                        Toast.makeText(getContext(), "你操作太频繁了", Toast.LENGTH_SHORT).show();
//                        editor.putString(today, editview2.getText().toString());
//                        editor.commit();
//                        position[0] = item_everSummaries_list.size() - 1;
//                        item_EverSummary item = new item_EverSummary(today, editview2.getText().toString());
//                        toDoAdapter.notifyItemChanged(position[0]);
//                        item_everSummaries_list.remove(position[0]);
//                        item_everSummaries_list.add(position[0], item);
//                        toDoAdapter.notifyItemRangeChanged(position[0], item_everSummaries_list.size());


                    } else {
                        editor.putString(today, editview2.getText().toString());
                        editor.commit();
                        position[0] = item_everSummaries_list.size();
//                        toDoAdapter.notifyItemChanged(position[0]);
                        item_everSummaries_list.add(item_everSummaries_list.size(), new item_EverSummary(today, editview2.getText().toString()));
                        toDoAdapter.notifyDataSetChanged();
//                        toDoAdapter.notifyItemRangeChanged(position[0], item_everSummaries_list.size());
                    }

                    editview2.setText("");
                } else {
                    Toast.makeText(getContext(), "你还没总结呢，乱按可不好", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    //初始化即将要注入recylerView的内容
    private void initRecylerViewData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mySummary", Context.MODE_PRIVATE);
        Set<String> strings = sharedPreferences.getAll().keySet();
        item_everSummaries_list.clear();

        for (String s : strings) {
            //这个实例化不能放在for的外面，否则会出现每个item都重复为最后一个
            item_EverSummary item_everSummary = new item_EverSummary();
            item_everSummary.setDate(s);
            item_everSummary.setContent(sharedPreferences.getString(s, "内容好像走丢了"));
            Log.d("myinfo", s+"|"+sharedPreferences.getString(s, "内容好像走丢了"));
            item_everSummaries_list.add(item_everSummary);
        }

//        Set<? extends Map.Entry<String, ?>> entrySet = sharedPreferences.getAll().entrySet();
//        for(Map.Entry<String, ?> entry : entrySet){
//            item_everSummary.setDate(entry.getKey());
//            item_everSummary.setContent((String) entry.getValue());
//            item_everSummaries_list.add(item_everSummary);
//        }
    }

    //计算倒计天数
    private int initReverseTime() {
        SharedPreferences sp = getActivity().getSharedPreferences("Date", Context.MODE_PRIVATE);
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");
        today = new Date();
        try {
            kaoYanDate = simpleFormatter.parse(sp.getString("kaoYanDate", "2020-12-16"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long kaoYanDateNum = kaoYanDate.getTime();
        long todayNum = today.getTime();

        int num = (int) ((kaoYanDateNum - todayNum) / (24 * 60 * 60 * 1000));
        return num;
    }

    //初始化banner的数据
    private void initData() {
        mData = DataProvider.getBannerList();
    }
    //初始化考研日期
    private void initKaoYanDate() {
        SharedPreferences sp = getActivity().getSharedPreferences("Date", Context.MODE_PRIVATE);
        if (sp.getString("kaoYanDate", "-1").equals("-1")) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("kaoYanDate", "2021-12-26");
            editor.commit();
        }
    }

    //每个组件要绑定变量
    private void initViewID(View view) {
        button = (Button) view.findViewById(R.id.main_button_add);//+
        editText = (TextView) view.findViewById(R.id.editview);//倒计时
        editview2 = (EditText) view.findViewById(R.id.editview2);//输入总结文字框

        reclyerViewTodo = (LinkageRecyclerView) view.findViewById(R.id.reclyerView_todo);//以及保持的总结
        banner = view.findViewById(R.id.sib_the_most_comlex_usage);//轮播条
        nestedScrollView = view.findViewById(R.id.fragment_main_scrollerView);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        item_everSummaries_list.clear();
        toDoAdapter.notifyDataSetChanged();
    }

    //重新回到这个fragment的操作，更新一下倒计时，以免之前改动发生的不正确
    @Override
    public void onResume() {
        super.onResume();
        editText.setText("距离考研倒计时：" + initReverseTime() + "天");
//        initRecylerViewData();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position;
        position =toDoAdapter.getContextMenuPosition();
        switch (item.getItemId()) {
            case 0://查看
            {
                String content = toDoAdapter.getList().get(position).getContent();
                Toast.makeText(getContext(), ""+content, Toast.LENGTH_SHORT).show();

            }
            break;
            case 1://删除
                if(-1<position&&position<item_everSummaries_list.size()) {
                    String date = item_everSummaries_list.get(position).getDate();
//                    toDoAdapter.notifyItemChanged(position);
                    item_everSummaries_list.remove(position);
                    toDoAdapter.notifyDataSetChanged();
//                    toDoAdapter.notifyItemRangeChanged(position, item_everSummaries_list.size());
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mySummary", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(date);
                    editor.commit();
                    Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "删除失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2://修改
                Toast.makeText(getContext(), "暂时还不支持，抱歉", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }
}

