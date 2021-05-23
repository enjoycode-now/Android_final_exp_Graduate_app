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


//启动页
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

            //在动画结束后，显式转到MainActivity
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


    }

    private void initView() {
        mimageView = (ImageView)findViewById(R.id.introduceImage);
        lottieAnimationView = (com.airbnb.lottie.LottieAnimationView)
                findViewById(R.id.introduceAnima);
    }

    @Override
    public void onClick(View v) {

    }
}