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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSubmitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       if(v == mSubmitBtn) {
           getPrediction();
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

        // Calculating odds and probabilities
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
        int prediction;

        // Setting Probabilities to TextViews
        mHomeProb.setText(Double.toString(homeProb) + "%");
        mDrawProb.setText(Double.toString(drawProb) + "%");
        mAwayProb.setText(Double.toString(awayProb) + "%");

        // Setting EditTexts to empty
        mTotalHomeGames.setText("");
        mTotalAwayGames.setText("");
        mHomeWins.setText("");
        mAwayLoses.setText("");
        mHomeDraws.setText("");
        mAwayDraws.setText("");
        mAwayHomeWins.setText("");
        mHomeAwayLoses.setText("");
    }
}
