/*
 * Copyright (C) 2021 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.testshareperference.activity.httpUtil;

import android.content.Context;
import android.util.Log;

import com.example.testshareperference.activity.MainActivity;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


//这是个网络访问工具类，代表我们去访问一个url,然后得到reponse字符串
public class SendRequestWithURLConnect {

    String endURL="app_id=h0gppttquqmsfkoh&app_secret=bXJDMzZqbldRbTF6c3Z1OWxmRHpxZz09";
    String SentenceUrl ="http://www.mxnzp.com/api/daily_word/recommend?count=1&";
    String response;

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public String sendURLRequestForSentence() throws InterruptedException {
        final String[] s = new String[1];

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(SentenceUrl + endURL).build();
                    Response response = client.newCall(request).execute();
                    s[0] = response.body().string();
                    Log.d("myinfo", "每天一句，http访问");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();

        return s[0];
    }




}




