package com.example.testshareperference.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.testshareperference.R;
import com.example.testshareperference.fragment.fragemntMain;
import com.example.testshareperference.fragment.fragmentLeft;
import com.example.testshareperference.fragment.fragmentSentence;
import com.example.testshareperference.fragment.fragment_porfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.adapter.FragmentAdapter;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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
        if(actionBar != null){
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
                        viewPager.setCurrentItem(2,true);
                        Toast.makeText(MainActivity.this, "你点击了每天一句", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.home_page_bottom:
                        navigationView.setCheckedItem(R.id.nav_homepage);
                        viewPager.setCurrentItem(0,true);
                        Toast.makeText(MainActivity.this, "你点击了主页", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.funny_bottom:
                        navigationView.setCheckedItem(R.id.nav_history);
                        viewPager.setCurrentItem(1,true);
                        Toast.makeText(MainActivity.this, "你点击了考研论坛", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.profile_bottom:
                        navigationView.setCheckedItem(R.id.nav_me);
                        viewPager.setCurrentItem(3,true);
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
                switch (item.getItemId()){
                    case R.id.every_sentence:
                        navigationView.setCheckedItem(R.id.every_sentence);
                        viewPager.setCurrentItem(2,true);
                        Toast.makeText(MainActivity.this,"你点击了每天一句",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_homepage:
                        navigationView.setCheckedItem(R.id.nav_homepage);
                        viewPager.setCurrentItem(0,true);
                        Toast.makeText(MainActivity.this,"你点击了主页",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_history:
                        navigationView.setCheckedItem(R.id.nav_history);
                        viewPager.setCurrentItem(1,true);
                        Toast.makeText(MainActivity.this,"你点击了考研论坛",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_me:
                        navigationView.setCheckedItem(R.id.nav_me);
                        viewPager.setCurrentItem(3,true);
                        Toast.makeText(MainActivity.this,"你点击了我的",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this,"什么都没有发生哦",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });


    }


    //创建顶部栏的菜单项，从menu文件中获得数据项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar,menu);
        return true;
    }

    //监听菜单item的点击
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_home_page:
                navigationView.setCheckedItem(R.id.nav_homepage);
                viewPager.setCurrentItem(3,true);
                Toast.makeText(this,"你点击了主页",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.item_search:
                Toast.makeText(this,"你点击了搜索",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }





    private void initViewID(){

        toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView =(NavigationView)findViewById(R.id.nav_view);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav_bottomView); ;
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
        switch (v.getId()){
            default:
                break;
        }
    }
}