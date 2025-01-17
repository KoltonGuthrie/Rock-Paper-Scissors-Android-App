package com.jsu.cs408.lab1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jsu.cs408.lab1a.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Weapon playerWeapon, computerWeapon;
    private int playerScore, computerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        computerScore = 0;
        playerScore = 0;

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart invoked!");

        binding.score.setText(getString(R.string.score_message, playerScore, computerScore));

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop invoked!");
    }

    public void onClickButton(View v) {

        playerWeapon = updatePlayersWeapon(v);

        computerWeapon = updateComputersWeapon();

        int comparedValue = compareWeapons(playerWeapon, computerWeapon);

        switch(comparedValue) {
            case 2:
                binding.output.setText(getString(R.string.tied_message, playerWeapon.toString()));
                break;
            case 1:
                binding.output.setText(getString(R.string.lost_message, playerWeapon.toString(), computerWeapon.toString()));
                computerScore++;
                break;
            case 0:
                binding.output.setText(getString(R.string.won_message, playerWeapon.toString(), computerWeapon.toString()));
                playerScore++;
                break;
            case -1:
                binding.output.setText(R.string.compare_error_message);
                break;
            default:

        }

        binding.score.setText(getString(R.string.score_message, playerScore, computerScore));

    }

    private Weapon updateComputersWeapon() {
        Weapon[] weapons = Weapon.values();

        int num = (int) Math.floor(Math.random() * weapons.length); // 0 - weapons.length-1

        if(num > weapons.length-1) {
            Log.e("MainActivity", "Didn't get a number between 0-" + (weapons.length-1) + " when trying to choose computers weapon!");
            return Weapon.ROCK;
        }

        return weapons[num];
    }

    /*
        Returns 0 if first weapon wins, 1 if it loses,
        2 if they tie, & -1 if something goes horribly wrong
    */
    private int compareWeapons(Weapon w0, Weapon w1) {
        if(w0.equals(w1)) return 2;

        if(w0.equals(Weapon.ROCK)) {
            if(w1.equals(Weapon.SCISSORS)) return 0;
            return 1;
        } else if(w0.equals(Weapon.PAPER)) {
            if(w1.equals(Weapon.ROCK)) return 0;
            return 1;
        } else if(w0.equals(Weapon.SCISSORS)) {
            if(w1.equals(Weapon.PAPER)) return 0;
            return 1;
        }

        Log.e("MainActivity", "Was unable to compare weapons properly!");
        return -1;
    }

    private Weapon updatePlayersWeapon(View v) {
        if(v.equals(binding.rockButton)) {
            return Weapon.ROCK;
        } else if (v.equals(binding.paperButton)) {
            return Weapon.PAPER;
        } else if (v.equals(binding.scissorsButton)) {
            return Weapon.SCISSORS;
        }

        Log.e("MainActivity", "Unable to properly update players weapon!");
        return Weapon.ROCK;
    }
}