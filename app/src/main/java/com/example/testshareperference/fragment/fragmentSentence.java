package com.example.testshareperference.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testshareperference.activity.entity.Sentence;
import com.google.gson.Gson;

import com.example.testshareperference.R;
import com.example.testshareperference.activity.httpUtil.SendRequestWithURLConnect;

import com.xuexiang.xui.widget.toast.XToast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//每天一句的fragment类
public class fragmentSentence extends Fragment {
    TextView textView;
    TextView autohor_textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_sentence, container,false);

        textView = (TextView) view.findViewById(R.id.sentence_textview);
        autohor_textView = (TextView) view.findViewById(R.id.sentence_author_textview);
        String response = null;

        //必须判断网络可用才能使用sendRequestWithURLConnect，否则会卡死进程
        if(isNetworkAvailable(getActivity())) {
            SendRequestWithURLConnect sendRequestWithURLConnect = new SendRequestWithURLConnect();
            try {
                response = (String) sendRequestWithURLConnect.sendURLRequestForSentence();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            Gson gson = new Gson();
            if(response!=null&&!response.equals("")) {
                Sentence sentence = gson.fromJson(response, Sentence.class);
                textView.setText(sentence.getData().get(0).getContent());
                if (!sentence.getData().get(0).getAuthor().equals("")) {
                    autohor_textView.setText("作者：" + sentence.getData().get(0).getAuthor());
                }
            }
        }
        return  view;
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //NetWorkState, Unavailabel
        if (connectivity == null) {
        return false;
    }
        else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    //NetWorkState, Availabel
                    return true;
            }
            }
        }
    }
        return false;
    }

    public String sendURLRequestForSentence_now() throws InterruptedException {
        String endURL="app_id=h0gppttquqmsfkoh&app_secret=bXJDMzZqbldRbTF6c3Z1OWxmRHpxZz09";
        String SentenceUrl ="http://www.mxnzp.com/api/daily_word/recommend?count=1&";
        String response;
        final String[] s = new String[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(SentenceUrl + endURL).build();
                    Response response = client.newCall(request).execute();
                    s[0] = response.body().string();
//                    showResponse(s[0]);
                    Log.d("myinfo", "进来了");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        return s[0];
    }

//    public void showResponse(final String s){
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                textView.setText(s);
//            }
//        });
//    }

}
