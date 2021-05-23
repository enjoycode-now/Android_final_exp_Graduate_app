package com.example.testshareperference.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.testshareperference.R;
import com.xuexiang.xui.widget.alpha.XUIAlphaImageView;
import com.xuexiang.xui.widget.layout.linkage.view.LinkageWebView;

public class fragmentLeft extends Fragment {

    private LinearLayout agentweb;
    private XUIAlphaImageView ivBack;
    private View viewLine;
    private XUIAlphaImageView ivFinish;
    private TextView toolbarTitle;
    private XUIAlphaImageView ivMore;
    private LinkageWebView webAbout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_left, container, false);

        initViewID(view);

        ivBack.setOnClickListener(new OnClick());
        ivFinish.setOnClickListener(new OnClick());
        ivMore.setOnClickListener(new OnClick());
        WebSettings settings = webAbout.getSettings();
        //打开内置缩放机制
        settings.setBuiltInZoomControls(true);
        //打开meta标签的支持
        settings.setUseWideViewPort(true);
        //启用概览模式，即缩小内容宽度以适应屏幕
        settings.setLoadWithOverviewMode(true);
        //自适应屏幕，默认为WebSettings.LayoutAlgorithm.NORMAL不做任何渲染处理
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //启用JS支持，这个必须设置True,否则无法使用JS
        settings.setJavaScriptEnabled(true);

        // 确保跳转到另一个网页时仍然在当前 WebView 中显示
        // 而不是调用浏览器打开
        webAbout.setWebViewClient(new WebViewClient());

        // 设置 WebView 的按键监听器，覆写监听器的 onKey 函数，对返回键作特殊处理
        // 当 WebView 可以返回到上一个页面时回到上一个页面
        webAbout.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && webAbout.canGoBack()) {
                    webAbout.goBack();
                    return true;
                }
                return false;
            }
        });


        webAbout.loadUrl("http://bbs.kaoyan.com/");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_more:
                    Toast.makeText(getContext(),"客官稍等，页面加载中...",Toast.LENGTH_SHORT).show();
                    webAbout.loadUrl("https://www.douban.com/group/240355/");
                    break;
                case R.id.iv_finish:
                    Toast.makeText(getContext(),"客官稍等，页面加载中...",Toast.LENGTH_SHORT).show();
                    while(webAbout.canGoBack()){
                        webAbout.goBack();
                    }
                    webAbout.loadUrl("http://8.135.59.161:8088/index2.html");
                    break;
                case R.id.iv_back:
                    if(webAbout.canGoBack()){
                        webAbout.goBack();
                    }else{
                        Toast.makeText(getContext(),"回不去了，我们",Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void initViewID(View view) {
        agentweb = (LinearLayout) view.findViewById(R.id.agentweb);
        ivBack = (XUIAlphaImageView) view.findViewById(R.id.iv_back);
        viewLine = (View) view.findViewById(R.id.view_line);
        ivFinish = (XUIAlphaImageView) view.findViewById(R.id.iv_finish);
        toolbarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        ivMore = (XUIAlphaImageView) view.findViewById(R.id.iv_more);
        webAbout = (LinkageWebView) view.findViewById(R.id.web_about);

    }
}
