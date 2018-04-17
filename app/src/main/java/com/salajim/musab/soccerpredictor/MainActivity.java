package com.salajim.musab.soccerpredictor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.totalGames) EditText mTotalGames;
    @Bind({R.id.homeWins}) EditText mHomeWins;
    @Bind(R.id.awayLoses) EditText mAwayLoses;
    @Bind(R.id.draws) EditText mDraws;
    @Bind(R.id.awayHomeWins) EditText mAwayHomeWins;
    @Bind(R.id.homeAwayLoses) EditText mHomeAwayLoses;
    @Bind(R.id.submitBtn) Button mSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSubmitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getPrediction();
    }

    private void getPrediction() {
    }
}
