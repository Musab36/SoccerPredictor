package com.salajim.musab.soccerpredictor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.totalHomeGames) EditText mTotalHomeGames;
    @Bind(R.id.totalAwayGames) EditText mTotalAwayGames;
    @Bind({R.id.homeWins}) EditText mHomeWins;
    @Bind(R.id.awayLoses) EditText mAwayLoses;
    @Bind(R.id.homeDraws) EditText mHomeDraws;
    @Bind(R.id.awayDraws) EditText mAwayDraws;
    @Bind(R.id.awayHomeWins) EditText mAwayHomeWins;
    @Bind(R.id.homeAwayLoses) EditText mHomeAwayLoses;
    @Bind(R.id.submitBtn) Button mSubmitBtn;
    @Bind(R.id.homeProb) TextView mHomeProb;
    @Bind(R.id.drawProb) TextView mDrawProb;
    @Bind(R.id.awayProb) TextView mAwayProb;
    @Bind(R.id.predictionTextView) TextView mPrediction;
    @Bind(R.id.dcTextView) TextView mDc;
    @Bind(R.id.clearBtn) Button mClearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSubmitBtn.setOnClickListener(this);
        mClearBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       if(v == mSubmitBtn) {
           getPrediction();
       }
       if(v == mClearBtn) {
           // Setting EditTexts to empty
           mTotalHomeGames.setText("");
           mTotalAwayGames.setText("");
           mHomeWins.setText("");
           mAwayLoses.setText("");
           mHomeDraws.setText("");
           mAwayDraws.setText("");
           mAwayHomeWins.setText("");
           mHomeAwayLoses.setText("");
           mHomeProb.setText("");
           mDrawProb.setText("");
           mAwayProb.setText("");
           mPrediction.setText("");
           mDc.setText("");
       }
    }

    private void getPrediction() {
        // Getting user input
        int totalHomeGames = Integer.parseInt(mTotalHomeGames.getText().toString());
        int totalAwayGames = Integer.parseInt(mTotalAwayGames.getText().toString());
        int homeWins = Integer.parseInt(mHomeWins.getText().toString());
        int awayLoses = Integer.parseInt(mAwayLoses.getText().toString());
        int homeDraws = Integer.parseInt(mHomeDraws.getText().toString());
        int awayDraws = Integer.parseInt(mAwayDraws.getText().toString());
        int awayHomeWins = Integer.parseInt(mAwayHomeWins.getText().toString());
        int homeAwayLoses = Integer.parseInt(mHomeAwayLoses.getText().toString());

        // Calculating the odds and the probabilities
        double totalGames = totalHomeGames + totalAwayGames;
        double homeWin = homeWins + awayLoses;
        double homeOdds = homeWin / totalGames;
        double homeProb = homeOdds * 100;
        double draw = homeDraws + awayDraws;
        double drawOdds = draw / totalGames;
        double drawProb = drawOdds * 100;
        double awayWin = awayHomeWins + homeAwayLoses;
        double awayOdds = awayWin / totalGames;
        double awayProb = awayOdds * 100;

        // Rounding off the doubles to two decimals places
        double hRoundOff = Math.round(homeProb * 100.0)/100.0;
        double dRoundOff = Math.round(drawProb * 100.0)/100.0;
        double aRoundOff = Math.round(awayProb * 100.0)/100.0;
        int prediction;

        // Setting Probabilities to TextViews
        mHomeProb.setText(Double.toString(hRoundOff) + "%");
        mDrawProb.setText(Double.toString(dRoundOff) + "%");
        mAwayProb.setText(Double.toString(aRoundOff) + "%");

        // Prediction
        if(hRoundOff > dRoundOff && (hRoundOff > aRoundOff)) {
            mPrediction.setText("1");
        } else if(dRoundOff > hRoundOff && (dRoundOff > aRoundOff)) {
            mPrediction.setText("X");
        } else if(hRoundOff == dRoundOff && (hRoundOff == aRoundOff) && (dRoundOff == aRoundOff)){
            mPrediction.setText("1");
        } else {
            mPrediction.setText("2");
        }

        // Double chance prediction
        if(hRoundOff > dRoundOff && (dRoundOff > aRoundOff)) {
            mDc.setText("1X");
        } else if(hRoundOff > dRoundOff && (aRoundOff > dRoundOff)) {
            mDc.setText("12");
        } else if(aRoundOff > dRoundOff && (dRoundOff > hRoundOff)){
            mDc.setText("X2");
        } else if(hRoundOff == dRoundOff && (aRoundOff > hRoundOff)) {
            mDc.setText("X2");
        } else if(aRoundOff == dRoundOff && (hRoundOff > aRoundOff)) {
            mDc.setText("1X");
        } else if(hRoundOff == aRoundOff && (dRoundOff > hRoundOff) && (dRoundOff > aRoundOff)) {
            mDc.setText("1X");
        } else if(hRoundOff == aRoundOff && (dRoundOff < hRoundOff) && (dRoundOff < aRoundOff)) {
            mDc.setText("12");
        } else if(aRoundOff == dRoundOff && (aRoundOff > hRoundOff)){
            mDc.setText("X2");
        } else {
            mDc.setText("1X");
        }
    }
}
