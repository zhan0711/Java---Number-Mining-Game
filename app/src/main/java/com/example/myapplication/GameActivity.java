package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    private HighLowGame game = new HighLowGame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Back button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Submit button
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText valueGuessNumber = findViewById(R.id.valueGuessNumber);
                int guessNumber = Integer.parseInt(valueGuessNumber.getText().toString());
                HighLowGame.Status status = game.checkGuess(guessNumber);
                String historyString = "";
                if (status == HighLowGame.Status.WIN) {
                    // Show WIN
                    Toast.makeText(getApplicationContext(), R.string.win,Toast.LENGTH_LONG).show();
                    Button backButton = findViewById(R.id.backButton);
                    backButton.setVisibility(View.VISIBLE);
                    historyString = String.format("%d WIN%n", guessNumber);
                } else if (status == HighLowGame.Status.LOSE) {
                    // Show LOSE
                    Toast.makeText(getApplicationContext(), R.string.lose,Toast.LENGTH_LONG).show();
                    Button backButton = findViewById(R.id.backButton);
                    backButton.setVisibility(View.VISIBLE);
                    historyString = String.format("%d LOSE%n", guessNumber);
                } else if (status == HighLowGame.Status.LOW) {
                    // Show TOO LOW
                    Toast.makeText(getApplicationContext(), R.string.too_low,Toast.LENGTH_LONG).show();
                    valueGuessNumber.getText().clear();
                    historyString = String.format("%d Too Low%n", guessNumber);
                } else if (status == HighLowGame.Status.HIGH) {
                    // Show TOO HIGH
                    Toast.makeText(getApplicationContext(), R.string.too_high,Toast.LENGTH_LONG).show();
                    valueGuessNumber.getText().clear();
                    historyString = String.format("%d Too High%n", guessNumber);
                }
                TextView labelGuessHistory = findViewById(R.id.labelGuessHistory);
                labelGuessHistory.append(historyString);
                refreshViews();
            }
        });

        // Reset the game.
        game.reset();
        // Refresh the views.
        refreshViews();
    }

    private void refreshViews() {
        TextView valueGuessFuel = findViewById(R.id.valueGuessFuel);
        valueGuessFuel.setText(String.valueOf(game.getFuelAvailable()));
        TextView valueGuessCount = findViewById(R.id.valueGuessCount);
        valueGuessCount.setText(String.valueOf(game.getGuessCount()));
    }
}
