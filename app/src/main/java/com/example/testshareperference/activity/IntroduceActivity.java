package com.example.testshareperference.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.testshareperference.R;

public class IntroduceActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView mimageView = null;
    com.airbnb.lottie.LottieAnimationView lottieAnimationView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

        initView();
        mimageView.animate().translationY(2200).setDuration(1000).setStartDelay(1000);
        lottieAnimationView.animate().translationY(1600).setDuration(1000).setStartDelay(1000);
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(IntroduceActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        SharedPreferences sp=this.getPreferences(MODE_PRIVATE);
        if(sp.getString("kaoYanDate","-1").equals("-1")){
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("kaoYanDate", "2021-12-26");
            editor.commit();
        }

    }

    private void initView() {
        mimageView = (ImageView)findViewById(R.id.introduceImage);
        lottieAnimationView = (com.airbnb.lottie.LottieAnimationView)
                findViewById(R.id.introduceAnima);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            default:
                break;
        }
    }
}