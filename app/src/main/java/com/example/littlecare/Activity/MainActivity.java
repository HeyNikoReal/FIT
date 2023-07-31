package com.example.littlecare.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.littlecare.Fragment.CreateFragment;
import com.example.littlecare.Fragment.GameFragment;
import com.example.littlecare.Fragment.HomeFragment;
import com.example.littlecare.Fragment.SettingFragment;
import com.example.littlecare.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bnvMenu;
    KendaliLogin KL = new KendaliLogin(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!KL.isLogin(KL.keySP_email) == true) {
            startActivity(new Intent(MainActivity.this, BerandaActivity.class));
            finish();
        } else {
            setContentView(R.layout.activity_main);
            bukafragment(new HomeFragment());

            bnvMenu = findViewById(R.id.bnv_navigasi_bawah);

            bnvMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    int itemId = item.getItemId();

                    if (itemId == R.id.menu_home) {
                        bukafragment(new HomeFragment());
                        return true;
                    } else if (itemId == R.id.menu_game) {
                        bukafragment(new GameFragment());
                        return true;
                    } else if (itemId == R.id.menu_create) {
                        bukafragment(new CreateFragment());
                        return true;
                    } else if (itemId == R.id.menu_setting) {
                        bukafragment(new SettingFragment());
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    private void bukafragment(Fragment FR) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_container, FR);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
