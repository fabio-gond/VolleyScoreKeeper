package com.example.android.volleyscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout outer = (FrameLayout)findViewById(R.id.team_score_b);


        TextView iv = (TextView)outer.findViewById(R.id.team_score);
        iv.setText("Ciao");
    }
}
