package com.example.android.volleyscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    int scoreA=0;
    int scoreB=0;
    int setsA =0;
    int setsB=0;
    boolean needReset=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn= findViewById(R.id.btn_set1a);
        // recovering the instance state
        if (savedInstanceState != null) {
            scoreA = savedInstanceState.getInt("team_a_score");
            scoreB = savedInstanceState.getInt("team_b_score");
            setsA = savedInstanceState.getInt("team_a_sets");
            setsB = savedInstanceState.getInt("team_b_sets");
            Button btnScore=findViewById(R.id.team_score_a);
            btnScore.setText(String.valueOf(scoreA));
            btnScore=findViewById(R.id.team_score_b);
            btnScore.setText(String.valueOf(scoreB));
            setSets('A',setsA);
            setSets('B',setsB);
        }
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

        switch (view.getId()){
            case R.id.btn_set1a:numSet=1;break;
            case R.id.btn_set2a:numSet=2;break;
            case R.id.btn_set3a:numSet=3;break;
            case R.id.btn_set1b:numSet=1;team='B';break;
            case R.id.btn_set2b:numSet=2;team='B';break;
            case R.id.btn_set3b:numSet=3;team='B';break;
        }
        setSets(team,numSet);
    }
    /*
     Change set buttons color and set number of winning sets
     */
    private void setSets(char team,int numSet){
        Button [] buttonsA= {findViewById(R.id.btn_set1a),findViewById(R.id.btn_set2a),findViewById(R.id.btn_set3a)};
        Button [] buttonsB= {findViewById(R.id.btn_set1b),findViewById(R.id.btn_set2b),findViewById(R.id.btn_set3b)};
        if(team=='A'){
            setsA =numSet;
            for (int i=0;i<3;i++){
                if(i<numSet){
                    setBtnState(buttonsA[i],true);
                }else  setBtnState(buttonsA[i],false);
            }
        }else{
            setsB=numSet;
            for (int i=0;i<3;i++){
                if(i<numSet){
                    setBtnState(buttonsB[i],true);
                }else  setBtnState(buttonsB[i],false);
            }
        }
    }

    /*
        Increment Team Score on tap
     */
    public void increment(View view){
        if(needReset){
            resetScores();
            needReset=false;
        }else {
            Button btnScore = findViewById(view.getId());
            if (view.getId() == R.id.team_score_a) {
                scoreA++;
                btnScore.setText(String.valueOf(scoreA));
            } else {
                scoreB++;
                btnScore.setText(String.valueOf(scoreB));
            }
            checkWin();
        }
    }

    /*
    Reset Team Scores but not the set scores
     */
    public void resetScores(View view){
        scoreA=0;
        scoreB=0;
        Button btnScore=findViewById(R.id.team_score_a);
        btnScore.setText("0");
        btnScore=findViewById(R.id.team_score_b);
        btnScore.setText("0");
    }
    private void resetScores(){
        scoreA=0;
        scoreB=0;
        Button btnScore=findViewById(R.id.team_score_a);
        btnScore.setText("0");
        btnScore=findViewById(R.id.team_score_b);
        btnScore.setText("0");
    }

    /*
    Reset Sets but not the team scores
     */
    public void resetSets(View view){
        setSets('A',0);
        setSets('B',0);
    }

    /* Save scores */
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt("team_a_score", scoreA);
        savedInstanceState.putInt("team_b_score", scoreB);
        savedInstanceState.putInt("team_a_sets", setsA);
        savedInstanceState.putInt("team_b_sets", setsB);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    /* Check if a team wins a set or the match */
    private void checkWin(){
        char winner=' ';
        if (scoreA>=25){
            if ((scoreA-scoreB)>1){
                winner='A';
            }
        }
        if (scoreB>=25){
            if ((scoreB-scoreA)>1){
                winner='B';
            }
        }
        if ((winner=='A') && (setsA<3)){
            setSets(winner,setsA+1);
            needReset=true;
        }else if ((winner=='B') && (setsB<3)){
            setSets(winner,setsB+1);
            needReset=true;
        }
    }

}
