package com.example.testshareperference.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.PopupWindow;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.testshareperference.R;
import com.example.testshareperference.activity.entity.Sentence;
import com.example.testshareperference.activity.entity.Weather;
import com.example.testshareperference.activity.httpUtil.SendRequestWithURLConnect;
import com.example.testshareperference.activity.httpUtil.WasteHttpUtil;
import com.example.testshareperference.activity.httpUtil.WeatherHttpUtil;
import com.example.testshareperference.fragment.fragemntMain;
import com.example.testshareperference.fragment.fragmentLeft;
import com.example.testshareperference.fragment.fragmentSentence;
import com.example.testshareperference.fragment.fragment_porfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.adapter.FragmentAdapter;

import org.json.JSONObject;

import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static com.example.testshareperference.fragment.fragmentSentence.isNetworkAvailable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar = null;
    DrawerLayout drawerLayout = null;
    NavigationView navigationView = null;
    ViewPager viewPager = null;
    MenuItem menuItem = null;
    BottomNavigationView bottomNavigationView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);


        initViewID();

        //主页内容填充
        Fragment[] fragments = new Fragment[]{
                new fragemntMain(),
                new fragmentLeft(),
                new fragmentSentence(),
                new fragment_porfile()
        };

        //中间的内容
        FragmentAdapter<Fragment> adapter = new FragmentAdapter<>(getSupportFragmentManager(), fragments);
        //预加载页面数量
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                bottomNavigationView.setSelectedItemId(0);
            }

            @Override
            public void onPageSelected(int position) {
                //底部栏和viewPager侧滑联动
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //顶部栏
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }


        //底部栏
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.every_sentence_bottom:
                        navigationView.setCheckedItem(R.id.every_sentence);
                        viewPager.setCurrentItem(2, true);
                        Toast.makeText(MainActivity.this, "你点击了每天一句", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.home_page_bottom:
                        navigationView.setCheckedItem(R.id.nav_homepage);
                        viewPager.setCurrentItem(0, true);
                        viewPager.getAdapter().notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "你点击了主页", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.funny_bottom:
                        navigationView.setCheckedItem(R.id.nav_history);
                        viewPager.setCurrentItem(1, true);
                        Toast.makeText(MainActivity.this, "你点击了考研论坛", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.profile_bottom:
                        navigationView.setCheckedItem(R.id.nav_me);
                        viewPager.setCurrentItem(3, true);
                        Toast.makeText(MainActivity.this, "你点击了我的", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        //侧边栏
        navigationView.setCheckedItem(R.id.every_sentence);
        navigationView.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            //监听侧边栏item的点击事件v
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //收起侧边栏
                drawerLayout.closeDrawer(GravityCompat.START);

                switch (item.getItemId()) {
                    case R.id.every_sentence:
                        navigationView.setCheckedItem(R.id.every_sentence);
                        viewPager.setCurrentItem(2, true);
                        Toast.makeText(MainActivity.this, "你点击了每天一句", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_homepage:
                        navigationView.setCheckedItem(R.id.nav_homepage);
                        viewPager.setCurrentItem(0, true);
                        Toast.makeText(MainActivity.this, "你点击了主页", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_history:
                        navigationView.setCheckedItem(R.id.nav_history);
                        viewPager.setCurrentItem(1, true);
                        Toast.makeText(MainActivity.this, "你点击了考研论坛", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_me:
                        navigationView.setCheckedItem(R.id.nav_me);
                        viewPager.setCurrentItem(3, true);
                        Toast.makeText(MainActivity.this, "你点击了我的", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_night:
                        myPopWindow();
                        break;
                    case R.id.nav_laji:
                        myPopWindow_laji();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "什么都没有发生哦", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });


    }

    private void myPopWindow() {
        //初始化布局activity_popupWindow.xml
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View contentView = layoutInflater.inflate(R.layout.fragment_weather, null);
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
        CardView weatherCardView;
        EditText weatherTestView;
        Button weatherButton;
        CardView weatherCardView2;
        MultiAutoCompleteTextView weatherInfo;
        CardView weatherCardView3;
        Button weatherCancel;

        weatherCardView = (CardView) contentView.findViewById(R.id.weather_cardView);
        weatherTestView = (EditText) contentView.findViewById(R.id.weather_testView);
        weatherButton = (Button) contentView.findViewById(R.id.weather_button);
        weatherCardView2 = (CardView) contentView.findViewById(R.id.weather_cardView2);
        weatherInfo = (MultiAutoCompleteTextView) contentView.findViewById(R.id.weather_info);
        weatherCardView3 = (CardView) contentView.findViewById(R.id.weather_cardView3);
        weatherCancel = (Button) contentView.findViewById(R.id.weather_cancel);

        //关闭弹窗
        weatherCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!weatherTestView.getText().toString().equals("")) {
                    String cityName = weatherTestView.getText().toString();
                    String response = null;
                    WeatherHttpUtil weatherHttpUtil = new WeatherHttpUtil();
                    //必须判断网络可用才能使用sendRequestWithURLConnect，否则会卡死进程
                    if (isNetworkAvailable(MainActivity.this) ) {
                        try {
                            response = weatherHttpUtil.sendURLRequestForSentence(cityName);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        try {
                            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh-MM-ss").create();
                            if (response != null && !response.equals("")) {
                                Weather weather = gson.fromJson(response, new TypeToken<Weather>() {
                                }.getType());
                                String text = "";
                                if (1 - weather.getCode() < 1e-6) {
                                    text += "数据获取成功！\n" +
                                            "城市：" + weather.getData().getAddress() + "\n" +
                                            "实时温度：" + weather.getData().getTemp() + "\n" +
                                            "天气描述：" + weather.getData().getWeather() + "\n" +
                                            "风向描述：" + weather.getData().getWinddirection() + "\n" +
                                            "湿度值：" + weather.getData().getHumidity() + "\n" +
                                            "此次天气发布时间：" + weather.getData().getReporttime();

                                } else {
                                    text += "数据获取失败\n";
                                }
                                weatherInfo.setText(text);

                            }
                        } catch (Exception e) {
                            weatherInfo.setText("数据获取失败\n");
                        }
                    }

                } else {
                    Toast.makeText(MainActivity.this, "请输入城市名（东莞市）", Toast.LENGTH_SHORT).show();
                }
            }
        });
//指定位置弹窗
        popupWindow.showAtLocation(contentView, Gravity.LEFT, 0, 0);

    }

    //判断json字符串
    public boolean getJSONType(String str) {

        boolean result = false;

        if (str != "") {

            str = str.trim();

            if (str.startsWith("{") && str.endsWith("}")) {

                result = true;

            } else if (str.startsWith("[") && str.endsWith("]")) {

                result = true;

            }

        }
        return result;
    }

    private void myPopWindow_laji() {
        //初始化布局activity_popupWindow.xml
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View contentView = layoutInflater.inflate(R.layout.fragment_waste, null);
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
        CardView lajiCardView;
        EditText lajiTestView;
        Button lajiButton;
        CardView lajiCardView2;
        MultiAutoCompleteTextView lajiInfo;
        CardView lajiCardView4;
        MultiAutoCompleteTextView lajiSay;
        CardView lajiCardView3;
        Button lajiCancel;

        lajiCardView = (CardView) contentView.findViewById(R.id.laji_cardView);
        lajiTestView = (EditText) contentView.findViewById(R.id.laji_testView);
        lajiButton = (Button) contentView.findViewById(R.id.laji_button);
        lajiCardView2 = (CardView) contentView.findViewById(R.id.laji_cardView2);
        lajiInfo = (MultiAutoCompleteTextView) contentView.findViewById(R.id.laji_info);
        lajiCardView4 = (CardView) contentView.findViewById(R.id.laji_cardView4);
        lajiSay = (MultiAutoCompleteTextView) contentView.findViewById(R.id.laji_say);
        lajiCardView3 = (CardView) contentView.findViewById(R.id.laji_cardView3);
        lajiCancel = (Button) contentView.findViewById(R.id.laji_cancel);


        //关闭弹窗
        lajiCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        lajiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lajiTestView.getText().toString().equals("")) {
                    String cityName = lajiTestView.getText().toString();
                    String response = null;
                    WasteHttpUtil wasteHttpUtil = new WasteHttpUtil();
                    //必须判断网络可用才能使用sendRequestWithURLConnect，否则会卡死进程
                    if (isNetworkAvailable(MainActivity.this)) {
                        try {
                            response = wasteHttpUtil.sendURLRequestForSentence(cityName);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            Gson gson = new Gson();
                            if (response != null && !response.equals("")) {
//                            Weather weather = gson.fromJson(response, new TypeToken<Weather>(){}.getType());
                                lajiInfo.setText("暂时功能不齐，抱歉\n" + response);
                            }
                        }catch (Exception e){
                            lajiInfo.setText("数据访问失败\n");
                        }
                    }

                } else {
                    Toast.makeText(MainActivity.this, "请输入物品名称", Toast.LENGTH_SHORT).show();
                }
            }
        });
//指定位置弹窗
        popupWindow.showAtLocation(contentView, Gravity.LEFT, 0, 0);

    }

    //创建顶部栏的菜单项，从menu文件中获得数据项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        return true;
    }

    //监听菜单item的点击
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home_page:
                navigationView.setCheckedItem(R.id.nav_homepage);
                viewPager.setCurrentItem(3, true);
                Toast.makeText(this, "你点击了主页", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.item_search:
                Toast.makeText(this, "你点击了搜索", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }


    private void initViewID() {

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_bottomView);
        ;
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        //注入字体
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}