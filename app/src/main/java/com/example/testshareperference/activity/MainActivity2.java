package com.example.testshareperference.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.testshareperference.R;
import com.example.testshareperference.fragment.fragmentLeft;
import com.example.testshareperference.fragment.fragmentSentence;

public class MainActivity2 extends AppCompatActivity {

    private FragmentManager fragmentManager =null ;
    private  FragmentTransaction transaction =null ;
    private fragmentLeft f1;
    private fragmentSentence f2;
    Fragment currentFragment;
    Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        button = (Button)findViewById(R.id.button2);
        fragmentManager = getSupportFragmentManager();


        setDefaultFragment();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentFragment instanceof fragmentLeft){
                    fragmentSentence fragmentSentence = new fragmentSentence();
                    switchFragment(fragmentSentence);
                    Log.d("myinfo", "onClick: 1");
                }else{
                    fragmentLeft fragmentLeft = new fragmentLeft();
                    switchFragment(fragmentLeft);

                    Log.d("myinfo", "onClick: 2");
                }

            }
        });


    }
    private void setDefaultFragment()
    {
        transaction = fragmentManager.beginTransaction();
        f1=new fragmentLeft();
        transaction.
                add(R.id.fragment,f1)
                .commit();

        currentFragment = f1;

    }

    //正确的做法
    private void switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.fragment, targetFragment)
                    .commit();
            Log.d("myinfo", "还没添加呢");
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();
            Log.d("myinfo", "添加了( ⊙o⊙ )哇 ");
        }
        currentFragment = targetFragment;
    }
}