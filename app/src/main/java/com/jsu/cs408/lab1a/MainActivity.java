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

        binding.banner.setText(R.string.banner_text);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart invoked!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop invoked!");
    }

    public void onClickButton(View v) {
        updatePlayersWeapon(v);

        updateComputersWeapon();

        //compareWeapons(); // Returns true if first variable wins

        binding.output.setText("You clicked " + playerWeapon.toString() + "!\nThe computer chose " + computerWeapon.toString() + "!");
        //binding.output.setText("You clicked Rock!");
    }

    private void updateComputersWeapon() {
        int num = (int) Math.floor(Math.random() * 3); // 0 - 2

        Weapon[] weapons = Weapon.values();

        if(num > weapons.length-1) {
            computerWeapon = Weapon.ROCK;
            Log.wtf("MainActivity", "Didn't get a number between 0-2 when trying to choose computers weapon!");
            return;
        }

        computerWeapon = weapons[num];

    }

    private boolean compareWeapons(Weapon w0, Weapon w1) {
        return true;
    }

    private void updatePlayersWeapon(View v) {
        if(v.equals(binding.rockButton)) {
            playerWeapon = Weapon.ROCK;
        } else if (v.equals(binding.paperButton)) {
            playerWeapon = Weapon.PAPER;
        } else if (v.equals(binding.scissorsButton)) {
            playerWeapon = Weapon.SCISSORS;
        }
    }
}