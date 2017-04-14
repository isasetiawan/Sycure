package com.dwi.mm.sycure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScoreQuizActivity extends AppCompatActivity {
    @BindView(R.id.score_text)
    TextView score_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_quiz);
        ButterKnife.bind(this);

        int score  = getIntent().getIntExtra("score",0);
        score_text.setText(score+"");
    }

    @OnClick(R.id.goto_treatment)
    public void traet(){
        Intent intent = new Intent(this,TreatmentActivity.class);
        startActivity(intent);
    }

}
