package com.example.testshareperference.activity.httpUtil;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WasteHttpUtil {


    //要访问的网址(留下的接口)
    String url ="https://www.mxnzp.com/api/rubbish/type?name=";
    String endURL="&app_id=h0gppttquqmsfkoh&app_secret=bXJDMzZqbldRbTF6c3Z1OWxmRHpxZz09";



    //参数reBack就是要传输的反馈字符串
    public String sendURLRequestForSentence(String thingName) throws InterruptedException {
        final String[] s = new String[1];

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url +thingName+endURL).build();
                    Response response = client.newCall(request).execute();
                    s[0] = response.body().string();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();

        //后端写好的话，这里返回的就是状态码，比如200，404，502这种
        return s[0];
    }
}
