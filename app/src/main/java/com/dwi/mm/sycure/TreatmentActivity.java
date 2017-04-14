package com.dwi.mm.sycure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TreatmentActivity extends AppCompatActivity {

    @BindView(R.id.treat_text)
    TextView treat_text;

    @BindView(R.id.picts_treat)
    ImageView imageView;

    @BindView(R.id.curr_page)
    TextView currText;

    protected static int currpage ;

    String[] treats_test;
    int[] picts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);
        ButterKnife.bind(this);
        treat_text.setMovementMethod(new ScrollingMovementMethod());
        treats_test = getResources().getStringArray(R.array.treats);
        currpage = -1;

        picts = new int[]{
                R.drawable.tr1,
                R.drawable.tr2,
                R.drawable.tr3,
                R.drawable.tr4,
                R.drawable.tr5,
                R.drawable.tr6,
                R.drawable.tr7,
                R.drawable.tr8,
                R.drawable.tr9,
                R.drawable.tr9,
        };

        updatestate();
    }

    public void updatestate(){
        if (currpage < treats_test.length - 1) {
            currpage+=1;
            treat_text.setText(treats_test[currpage]);
            imageView.setImageResource(picts[currpage]);
            int a = treats_test.length -1;
            currText.setText(currpage+"/"+a);
        } else {
            Intent intent = new Intent(this,MainMenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    @OnClick(R.id.next_btn)
    public void nxt(){
        updatestate();
    }
}
