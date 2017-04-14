package com.dwi.mm.sycure;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();

    ArrayList<Integer> gambars = new ArrayList<>();
    ArrayList<String> colors = new ArrayList<>();
    ArrayList<String> questions = new ArrayList<>();
    String[] quests;
    protected static int state,score;

    @BindView(R.id.gambar)
    ImageView gambar;

    @BindView(R.id.latar)
    ImageView latar;

    @BindView(R.id.question_text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        quests = getResources().getStringArray(R.array.questions);

        state = -1;
        score = 0;

        gambars.add(R.drawable.tes1);
        gambars.add(R.drawable.tes2);
        gambars.add(R.drawable.tes3);
        gambars.add(R.drawable.tes4);
        gambars.add(R.drawable.tes5);
        gambars.add(R.drawable.tes6);
        gambars.add(R.drawable.tes7);
        gambars.add(R.drawable.tes8);
        gambars.add(R.drawable.tes9);
        gambars.add(R.drawable.tes10);

        colors.add("#D470E3");
        colors.add("#9700CF");
        colors.add("#CDCA01");
        colors.add("#D2D200");
        colors.add("#9DCB0A");
        colors.add("#D470E3");
        colors.add("#D9D102");
        colors.add("#C1BAB4");
        colors.add("#B7B7B7");
        colors.add("#C9706A");

        updatestate();

    }

    protected void updatestate(){
        if (state<gambars.size()-1){
            state += 1;
            gambar.setImageResource(gambars.get(state));
            latar.setBackgroundColor(Color.parseColor(colors.get(state)));
            ubahwarna(colors.get(state));
            textView.setText(quests[state]);
        } else {
            Intent intent = new Intent(this,ScoreQuizActivity.class);
            intent.putExtra("score",score);
            startActivity(intent);
        }
    }

    protected void ubahwarna(String hex){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(hex));
        }
    }

    @OnClick(R.id.button_ya)
    public void yalanjut(){
        score += 1;
        updatestate();
    }

    @OnClick(R.id.button_tidak)
    public void ytaklanjut(){
        updatestate();
    }
}
