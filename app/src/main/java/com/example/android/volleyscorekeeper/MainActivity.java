package com.example.android.volleyscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int scoreA=0;
    int scoreB=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn= findViewById(R.id.btn_set1a);
    }
    /*
        Set btn state of the set button
     */
    public void setBtnState (Button button, Boolean active){
        int sdk = android.os.Build.VERSION.SDK_INT;

        if(active){
            button.setBackgroundResource(R.drawable.set_btn_active);
        }else{
            button.setBackgroundResource(R.drawable.set_btn);
        }
    }
    public void clickSet(View view){
        int numSet=0;
        char team='A';
        Button [] buttonsA= {findViewById(R.id.btn_set1a),findViewById(R.id.btn_set2a),findViewById(R.id.btn_set3a)};
        Button [] buttonsB= {findViewById(R.id.btn_set1b),findViewById(R.id.btn_set2b),findViewById(R.id.btn_set3b)};

        switch (view.getId()){
            case R.id.btn_set1a:numSet=1;break;
            case R.id.btn_set2a:numSet=2;break;
            case R.id.btn_set3a:numSet=3;break;
            case R.id.btn_set1b:numSet=1;team='B';break;
            case R.id.btn_set2b:numSet=2;team='B';break;
            case R.id.btn_set3b:numSet=3;team='B';break;
        }
        if(team=='A'){
            for (int i=0;i<3;i++){
                if(i<numSet){
                    setBtnState(buttonsA[i],true);
                }else  setBtnState(buttonsA[i],false);
            }
        }else{
            for (int i=0;i<3;i++){
                if(i<numSet){
                    setBtnState(buttonsB[i],true);
                }else  setBtnState(buttonsB[i],false);
            }
        }
    }
}
