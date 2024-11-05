package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons = new Button[3][3];// 3x3 grid of buttons
    private Button resetButton; // To hold the reset button reference

    private boolean PlayerXTurn = true; // Turn tracking

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // We will create this activity_main.xml next
        initializeButtons();
        resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame(); // Call resetGame method when clicked
            }
        });
    }

    //initialize buttons
    private void initializeButtons() {
        //row 1
        buttons[0][0] = findViewById(R.id.button1);        buttons[0][1] = findViewById(R.id.button2);        buttons[0][2] = findViewById(R.id.button3);
        //row 2
        buttons[1][0] = findViewById(R.id.button4);        buttons[1][1] = findViewById(R.id.button5);        buttons[1][2] = findViewById(R.id.button6);
        //row 3
        buttons[2][0] = findViewById(R.id.button7);        buttons[2][1] = findViewById(R.id.button8);        buttons[2][2] = findViewById(R.id.button9);

        //click listeners
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onButtonClick((Button) view);
                    }
                });
            }
        }
    }

    //Handle Button CLicks
    private void onButtonClick(Button button) {

        //is valid click
        if (!button.getText().toString().equals("")) {
            return;//ignore if already clicked
        }
        // set button dependent on player turn
        if (PlayerXTurn) {
            button.setText("X");
        } else {
            button.setText("O");
        }

        PlayerXTurn = !PlayerXTurn;
        checkForWinner();
    }
    private void checkForWinner() {
        // Check rows for a winner
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().toString().equals(buttons[i][1].getText().toString()) &&
                    buttons[i][0].getText().toString().equals(buttons[i][2].getText().toString()) &&
                    !buttons[i][0].getText().toString().equals("")) {
                // Announce winner
                announceWinner(buttons[i][0].getText().toString());
                return;
            }
        }

        // Check columns for a winner
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().toString().equals(buttons[1][j].getText().toString()) &&
                    buttons[0][j].getText().toString().equals(buttons[2][j].getText().toString()) &&
                    !buttons[0][j].getText().toString().equals("")) {
                // Announce winner
                announceWinner(buttons[0][j].getText().toString());
                return;
            }
        }

        // Check diagonals for a winner
        if (buttons[0][0].getText().toString().equals(buttons[1][1].getText().toString()) &&
                buttons[0][0].getText().toString().equals(buttons[2][2].getText().toString()) &&
                !buttons[0][0].getText().toString().equals("")) {
            // Announce winner
            announceWinner(buttons[0][0].getText().toString());
        } else if (buttons[0][2].getText().toString().equals(buttons[1][1].getText().toString()) &&
                buttons[0][2].getText().toString().equals(buttons[2][0].getText().toString()) &&
                !buttons[0][2].getText().toString().equals("")) {
            // Announce winner
            announceWinner(buttons[0][2].getText().toString());
        }
        //check if tie
        int tie = 0;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (!(buttons[i][j].getText().toString().equals(""))){
                    tie++;
                }
                if (tie == 9){announceWinner("Game Design");}
            }

        }
    }

    // Method to announce the winner
    private void announceWinner(String winner) {
        // You can show a toast message or an alert dialog to announce the winner

        String message = "Player " + winner + " wins!";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        // Optionally, you can disable all buttons after a win
        disableButtons();
    }

    // Method to disable all buttons (optional)
    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    private void resetGame() {
        // Clear the text on each button
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Clear button text
                buttons[i][j].setEnabled(true); // Re-enable buttons
            }
        }
        PlayerXTurn = true; // Reset to player X's turn
    }


}