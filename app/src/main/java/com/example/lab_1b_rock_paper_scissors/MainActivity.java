package com.example.lab_1b_rock_paper_scissors;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.lab_1b_rock_paper_scissors.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int playerScore = 0, computerScore = 0;
    private String winner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.rockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseWeapon(getString(R.string.rockBtnTxt));
            }
        });
        binding.paperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseWeapon(getString(R.string.paperBtnTxt));
            }
        });
        binding.scissorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseWeapon(getString(R.string.scissorBtnTxt));
            }
        });

        updateScore(playerScore, computerScore);
    }
    @SuppressLint("SetTextI18n")
    public void chooseWeapon(String playerChoice) {
        TextView pwc = binding.playerWeaponLbl;
        pwc.setText(getString(R.string.playerWeaponTxt) + " " + playerChoice);

        Random rand = new Random();
        String computerChoice = Weapon.values()[rand.nextInt(Weapon.values().length)].toString();
        TextView cwc = binding.computerWeaponLbl;
        cwc.setText(getString(R.string.computerWeaponTxt) + " " + computerChoice);

        TextView w = binding.winnerLbl;
        winner = getResult(playerChoice, computerChoice);
        w.setText(winner);
        updateScore(playerScore, computerScore);
    }
    private String getResult(String player, String computer) {
        String tie = "No winner: Same weapon chosen";
        String playerWins = "Player wins:", computerWins = "Computer wins:";
        String r = "Rock breaks scissors!",
                p = "Paper covers rock!",
                s = "Scissors cut paper!";

        if (!player.equalsIgnoreCase(computer)) {
            switch (player.toLowerCase()) {
                case "rock":
                    if ("paper".equalsIgnoreCase(computer)) {
                        computerScore += 1;
                        return computerWins + " " + p;
                    } else if ("scissors".equalsIgnoreCase(computer)) {
                        playerScore += 1;
                        return playerWins + " " + r;
                    }
                    break;
                case "paper":
                    if ("scissors".equalsIgnoreCase(computer)) {
                        computerScore += 1;
                        return computerWins + " " + s;
                    } else if ("rock".equalsIgnoreCase(computer)) {
                            playerScore += 1;
                            return playerWins + " " + p;
                    }
                    break;
                case "scissors":
                    if ("rock".equalsIgnoreCase(computer)) {
                        computerScore += 1;
                        return computerWins + " " + r;
                    } else if ("paper".equalsIgnoreCase(computer)) {
                        playerScore += 1;
                        return playerWins + " " + s;
                    }
                    break;
            }
        }
        return tie;
    }
    public void updateScore(int ps, int cs) {
        TextView score = binding.scoreLbl;
        score.setText(getString(R.string.scoreTxt, String.valueOf(ps), String.valueOf(cs)));
    }
}