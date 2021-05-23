package com.example.testshareperference.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
import com.xuexiang.xui.widget.alpha.XUIAlphaImageView;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class fragment_porfile extends Fragment {

    private RadiusImageView rivHeadPic;
    private SuperTextView rivHead;
    private SuperTextView profileUser;
    private SuperTextView profileNotice;
    private SuperTextView profileSave;
    private SuperTextView profileSugguest;
    private SuperTextView menuSettings;
    private SuperTextView menuAbout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        rivHeadPic = (RadiusImageView) view.findViewById(R.id.riv_head_pic);
        rivHead = (SuperTextView) view.findViewById(R.id.riv_head);
        profileUser = (SuperTextView) view.findViewById(R.id.profile_user);
        profileNotice = (SuperTextView) view.findViewById(R.id.profile_notice);
        profileSave = (SuperTextView) view.findViewById(R.id.profile_save);
        profileSugguest = (SuperTextView) view.findViewById(R.id.profile_sugguest);
        menuSettings = (SuperTextView) view.findViewById(R.id.menu_settings);
        menuAbout = (SuperTextView) view.findViewById(R.id.menu_about);


        //头像
        rivHeadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "哟，这是个小彩蛋", Toast.LENGTH_SHORT).show();
            }
        });
        //头像
        rivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "你点啊点啊，没啥用的，别生气，下个版本也许有", Toast.LENGTH_SHORT).show();
            }
        });
        //研招网
        profileNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //初始化布局activity_popupWindow.xml
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View contentView = layoutInflater.inflate(R.layout.fragment_agentweb, null);


                //对布局里面的控件进行初始化并进行相应的操作
                //初始化PopupWindow
                PopupWindow popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setTouchable(true);
                popupWindow.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });

                //
                XUIAlphaImageView ivBack;
                XUIAlphaImageView ivFinish;

                ivBack = (XUIAlphaImageView) contentView.findViewById(R.id.iv_back);
                ivFinish = (XUIAlphaImageView) contentView.findViewById(R.id.iv_finish);
                ivBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                ivFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                WebView webView = (WebView) contentView.findViewById(R.id.web_about);
                webView.loadUrl("https://yz.chsi.com.cn/");
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
            }
        });


        //账号
        profileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_DayNight);
                builder.setTitle("你真的要删除全部用户数据？").setMessage("请谨慎确认！");
                builder.setPositiveButton("真的，我很确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sp = getActivity().getSharedPreferences("mySummary", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sp.edit();
                        edit.clear();
                        edit.commit();

                        sp = getActivity().getSharedPreferences("Date", Context.MODE_PRIVATE);
                        edit = sp.edit();
                        edit.clear();
                        edit.commit();


                        Toast.makeText(getActivity(), "我已经删了，呜呜", Toast.LENGTH_SHORT).show();
                    }
                }).setNeutralButton("算了,下次吧", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "嗯，迷途知返", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("以后我也不删除！", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "你真是个棒极了的孩子", Toast.LENGTH_SHORT).show();
                    }
                }).show();

            }
        });
        //致谢名单
        profileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "致谢名单", Toast.LENGTH_SHORT).show();

                //初始化布局activity_popupWindow.xml
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View contentView = layoutInflater.inflate(R.layout.fragment_agentweb, null);


                //对布局里面的控件进行初始化并进行相应的操作
                //初始化PopupWindow
                PopupWindow popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setTouchable(true);
                popupWindow.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });

                //
                XUIAlphaImageView ivBack;
                XUIAlphaImageView ivFinish;

                ivBack = (XUIAlphaImageView) contentView.findViewById(R.id.iv_back);
                ivFinish = (XUIAlphaImageView) contentView.findViewById(R.id.iv_finish);
                ivBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                ivFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                WebView webView = (WebView) contentView.findViewById(R.id.web_about);
                webView.loadUrl("http://8.135.59.161:8088/index2.html");
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
            }
        });
        //意见反馈
        profileSugguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "意见反馈", Toast.LENGTH_SHORT).show();


                //初始化布局activity_popupWindow.xml
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View contentView = layoutInflater.inflate(R.layout.fragemnt_reback_suggestion, null);
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
                popupWindow.showAsDropDown(v, 10, 10, 10);

                Button button = (Button) contentView.findViewById(R.id.reback_button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        Toast.makeText(getActivity(), "非常感谢你此次的反馈", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        //设置考研日期
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
                popupWindow.showAtLocation(superTextView, Gravity.CENTER, -1, -1);
//                popupWindow.showAsDropDown(superTextView);
                CalendarView calendarView2 = (CalendarView) contentView.findViewById(R.id.calendarView);
                TextView textView2 = (TextView) contentView.findViewById(R.id.textView2);

                Button button3 = (Button) contentView.findViewById(R.id.button3);
                Button button4 = (Button) contentView.findViewById(R.id.button4);

                calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView2, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");
                        String date = simpleFormatter.format(calendar.getTime());
                        textView2.setText(date);
                        //显示用户选择的日期
                        Toast.makeText(getContext(), year + "年" + (month + 1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
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
                        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("kaoYanDate", textView2.getText().toString());
                        editor.commit();
                        popupWindow.dismiss();
                    }
                });


            }
        });


        //关于
        menuAbout.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClick(SuperTextView superTextView) {
                //初始化布局activity_popupWindow.xml
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View contentView = layoutInflater.inflate(R.layout.fragment_agentweb, null);


                //对布局里面的控件进行初始化并进行相应的操作
                //初始化PopupWindow
                PopupWindow popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setTouchable(true);
                popupWindow.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });

                //
                XUIAlphaImageView ivBack;
                XUIAlphaImageView ivFinish;

                ivBack = (XUIAlphaImageView) contentView.findViewById(R.id.iv_back);
                ivFinish = (XUIAlphaImageView) contentView.findViewById(R.id.iv_finish);
                ivBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                ivFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                WebView webView = (WebView) contentView.findViewById(R.id.web_about);
                webView.loadUrl("http://8.135.59.161:8088/index.html");
                popupWindow.showAtLocation(superTextView, Gravity.CENTER, 0, 0);


            }
        });
        return view;
    }


}
