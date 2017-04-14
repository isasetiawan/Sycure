package com.dwi.mm.sycure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TherapyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageView6)
    public void kevid(){
        startActivity(new Intent(this,ListVideoActivity.class));
    }
}
