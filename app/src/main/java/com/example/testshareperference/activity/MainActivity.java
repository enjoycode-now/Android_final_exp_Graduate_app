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

        //??????????????????
        Fragment[] fragments = new Fragment[]{
                new fragemntMain(),
                new fragmentLeft(),
                new fragmentSentence(),
                new fragment_porfile()
        };

        //???????????????
        FragmentAdapter<Fragment> adapter = new FragmentAdapter<>(getSupportFragmentManager(), fragments);
        //?????????????????????
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
                //????????????viewPager????????????
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


        //?????????
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }


        //?????????
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.every_sentence_bottom:
                        navigationView.setCheckedItem(R.id.every_sentence);
                        viewPager.setCurrentItem(2, true);
                        Toast.makeText(MainActivity.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.home_page_bottom:
                        navigationView.setCheckedItem(R.id.nav_homepage);
                        viewPager.setCurrentItem(0, true);
                        viewPager.getAdapter().notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.funny_bottom:
                        navigationView.setCheckedItem(R.id.nav_history);
                        viewPager.setCurrentItem(1, true);
                        Toast.makeText(MainActivity.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.profile_bottom:
                        navigationView.setCheckedItem(R.id.nav_me);
                        viewPager.setCurrentItem(3, true);
                        Toast.makeText(MainActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        //?????????
        navigationView.setCheckedItem(R.id.every_sentence);
        navigationView.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            //???????????????item???????????????v
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //???????????????
                drawerLayout.closeDrawer(GravityCompat.START);

                switch (item.getItemId()) {
                    case R.id.every_sentence:
                        navigationView.setCheckedItem(R.id.every_sentence);
                        viewPager.setCurrentItem(2, true);
                        Toast.makeText(MainActivity.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_homepage:
                        navigationView.setCheckedItem(R.id.nav_homepage);
                        viewPager.setCurrentItem(0, true);
                        Toast.makeText(MainActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_history:
                        navigationView.setCheckedItem(R.id.nav_history);
                        viewPager.setCurrentItem(1, true);
                        Toast.makeText(MainActivity.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_me:
                        navigationView.setCheckedItem(R.id.nav_me);
                        viewPager.setCurrentItem(3, true);
                        Toast.makeText(MainActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_night:
                        myPopWindow();
                        break;
                    case R.id.nav_laji:
                        myPopWindow_laji();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });


    }

    private void myPopWindow() {
        //???????????????activity_popupWindow.xml
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View contentView = layoutInflater.inflate(R.layout.fragment_weather, null);
        //???????????????????????????????????????????????????????????????
        //?????????PopupWindow
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

        //????????????
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
                    //????????????????????????????????????sendRequestWithURLConnect????????????????????????
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
                                    text += "?????????????????????\n" +
                                            "?????????" + weather.getData().getAddress() + "\n" +
                                            "???????????????" + weather.getData().getTemp() + "\n" +
                                            "???????????????" + weather.getData().getWeather() + "\n" +
//                                            "???????????????" + weather.getData().getWinddirection() + "\n" +
                                            "????????????" + weather.getData().getHumidity() + "\n" ;
//                                            "???????????????????????????" + weather.getData().getReporttime();

                                } else {
                                    text += "??????????????????\n";
                                }
                                weatherInfo.setText(text);

                            }
                        } catch (Exception e) {
                            weatherInfo.setText("??????????????????\n");
                        }
                    }

                } else {
                    Toast.makeText(MainActivity.this, "?????????????????????????????????", Toast.LENGTH_SHORT).show();
                }
            }
        });
//??????????????????
        popupWindow.showAtLocation(contentView, Gravity.LEFT, 0, 0);

    }

    //??????json?????????
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
        //???????????????activity_popupWindow.xml
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View contentView = layoutInflater.inflate(R.layout.fragment_waste, null);
        //???????????????????????????????????????????????????????????????
        //?????????PopupWindow
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


        //????????????
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
                    //????????????????????????????????????sendRequestWithURLConnect????????????????????????
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
                                lajiInfo.setText("???????????????????????????\n" + response);
                            }
                        }catch (Exception e){
                            lajiInfo.setText("??????????????????\n");
                        }
                    }

                } else {
                    Toast.makeText(MainActivity.this, "?????????????????????", Toast.LENGTH_SHORT).show();
                }
            }
        });
//??????????????????
        popupWindow.showAtLocation(contentView, Gravity.LEFT, 0, 0);

    }

    //?????????????????????????????????menu????????????????????????
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        return true;
    }

    //????????????item?????????
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home_page:
                navigationView.setCheckedItem(R.id.nav_homepage);
                viewPager.setCurrentItem(3, true);
                Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.item_search:
                Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
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
        //????????????
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