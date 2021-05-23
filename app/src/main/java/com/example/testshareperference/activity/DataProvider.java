package com.example.testshareperference.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;

import java.util.ArrayList;
import java.util.List;


//本地保存的banner数据
public class DataProvider {
    public static String[] titles = new String[]{
            "考研的路上，我们都不是孤单的",
            "凛冬将至，我从今开始复习，至考方休。",
            "我是唤醒黎明的闹钟，闪耀午夜的台灯。",
            "我是图书馆的雕像，自习室的幽灵。",
            "我将悬梁刺股，生死於斯。",
    };

    public static String[] urls_lanscape = new String[]{
            "https://pic.imgdb.cn/item/60a916256ae4f77d35c74ee7.jpg",
            "https://pic.imgdb.cn/item/60a916256ae4f77d35c74f04.jpg",
            "https://pic.imgdb.cn/item/60a916256ae4f77d35c74edc.jpg",
            "https://pic.imgdb.cn/item/60a916306ae4f77d35c7ae4e.jpg",
            "https://pic.imgdb.cn/item/60a916256ae4f77d35c74ed2.jpg",
    };
    public static String[] urls_inspiration = new String[]{
            "https://pic.imgdb.cn/item/60a9177b6ae4f77d35d3ef0e.jpg",
            "https://pic.imgdb.cn/item/60a9177b6ae4f77d35d3ef45.jpg",
            "https://pic.imgdb.cn/item/60a9177b6ae4f77d35d3ef6e.jpg",
            "https://pic.imgdb.cn/item/60a9177b6ae4f77d35d3efb1.jpg",
            "https://pic.imgdb.cn/item/60a9177b6ae4f77d35d3efeb.jpg",
    };

    public static List<BannerItem> getBannerList() {
        ArrayList<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < urls_inspiration.length; i++) {
            BannerItem item = new BannerItem();
            item.imgUrl = urls_inspiration[i];
            item.title = titles[i];

            list.add(item);
        }

        return list;
    }



    private static List<AdapterItem> getGridItems(Context context, int titleArrayId, int iconArrayId) {
        List<AdapterItem> list = new ArrayList<>();
        String[] titles = ResUtils.getStringArray(titleArrayId);
        Drawable[] icons = ResUtils.getDrawableArray(context, iconArrayId);
        for (int i = 0; i < titles.length; i++) {
            list.add(new AdapterItem(titles[i], icons[i]));
        }
        return list;
    }
}
