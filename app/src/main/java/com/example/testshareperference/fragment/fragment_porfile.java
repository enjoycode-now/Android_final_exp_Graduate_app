package com.example.testshareperference.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testshareperference.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class fragment_porfile extends Fragment {

    private RadiusImageView rivHeadPic;
    private SuperTextView profileUser;
    private SuperTextView profileNotice;
    private SuperTextView profileSave;
    private SuperTextView profileSugguest;
    private SuperTextView menuSettings;
    private SuperTextView menuAbout;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)   {


        View view = inflater.inflate(R.layout.fragment_profile, container,false);
        rivHeadPic = (RadiusImageView) view.findViewById(R.id.riv_head_pic);
        profileUser = (SuperTextView) view.findViewById(R.id.profile_user);
        profileNotice = (SuperTextView) view.findViewById(R.id.profile_notice);
        profileSave = (SuperTextView) view.findViewById(R.id.profile_save);
        profileSugguest = (SuperTextView) view.findViewById(R.id.profile_sugguest);
        menuSettings = (SuperTextView) view.findViewById(R.id.menu_settings);
        menuAbout = (SuperTextView) view.findViewById(R.id.menu_about);

        rivHeadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"1",Toast.LENGTH_SHORT).show();
            }
        });
        profileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"2",Toast.LENGTH_SHORT).show();
            }
        });
        menuSettings.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClick(SuperTextView superTextView) {
                //初始化布局activity_popupWindow.xml
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View contentView = layoutInflater.inflate(R.layout.fragment_calendar, null);
                //对布局里面的控件进行初始化并进行相应的操作
                //初始化PopupWindow
                PopupWindow popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setTouchable(true);
                popupWindow.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
                popupWindow.showAtLocation(superTextView,0,1,1);
//                popupWindow.showAsDropDown(superTextView);
                CalendarView calendarView2 = (CalendarView) contentView.findViewById(R.id.calendarView);
                TextView textView2 = (TextView) contentView.findViewById(R.id.textView2);

                Button button3 = (Button) contentView.findViewById(R.id.button3);
                Button button4 = (Button) contentView.findViewById(R.id.button4);

                calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView2, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year,month,dayOfMonth);
                        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");
                        String date = simpleFormatter.format(calendar.getTime());
                        textView2.setText(date);
                        //显示用户选择的日期
                        Toast.makeText(getContext(),year + "年" + (month+1) + "月" + dayOfMonth + "日",Toast.LENGTH_SHORT).show();
                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sp=getActivity().getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("kaoYanDate", textView2.getText().toString());
                        editor.commit();
                        popupWindow.dismiss();
                    }
                });
                menuAbout.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
                    @Override
                    public void onClick(SuperTextView superTextView) {
                        //初始化布局activity_popupWindow.xml
                        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                        View contentView = layoutInflater.inflate(R.layout.fragement_about, null);


                        //对布局里面的控件进行初始化并进行相应的操作
                        //初始化PopupWindow
                        PopupWindow popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT,
                                WindowManager.LayoutParams.WRAP_CONTENT, true);
                        popupWindow.setTouchable(true);
                        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                return false;
                            }
                        });
                        WebView webView = (WebView)contentView.findViewById(R.id.web_about);
                        webView.loadUrl("http://8.135.59.161:8088/index.html");
                        popupWindow.showAtLocation(superTextView,0,1,1);
                    }
                });
            }
        });

        return view;
    }




}
