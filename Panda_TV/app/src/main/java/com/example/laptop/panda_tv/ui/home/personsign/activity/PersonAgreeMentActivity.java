package com.example.laptop.panda_tv.ui.home.personsign.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.laptop.panda_tv.R;

/**
 * Created by Laptop on 2017/12/19.
 */
public class PersonAgreeMentActivity extends AppCompatActivity implements View.OnClickListener{
    private Button agree_post_back;
    private TextView agree_post_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_agree_ment);
        initView();
    }

    private void initView() {
        agree_post_back = (Button) findViewById(R.id.agree_post_back);
        agree_post_text = (TextView) findViewById(R.id.agree_post_text);

        agree_post_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.agree_post_back:

                finish();

                break;
        }
    }
}
