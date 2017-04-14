package com.dwi.mm.sycure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start_test)
    public void ke_quis(){
        Intent intent = new Intent(this,QuizActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_about)
    public void ktentang(){
        startActivity(new Intent(this,AboutActivity.class));
    }

    @OnClick(R.id.btn_message)
    public void kepesan(){
        startActivity(new Intent(this,MessageActivity.class));
    }

    @OnClick(R.id.btn_thera)
    public void ketera(){
        startActivity(new Intent(this,TherapyActivity.class));
    }
}
