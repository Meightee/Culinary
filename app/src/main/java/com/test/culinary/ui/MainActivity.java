package com.test.culinary.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.test.culinary.R;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;


        @SuppressLint("NonConstantResourceId")
        BottomNavigationView.OnItemSelectedListener mOnItemSelectedListener
                = item -> {
                    Fragment fragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            fragment = new HomeFragment();
                            break;

                        case R.id.navigation_search:
                            fragment = new  SearchFragment();
                            break;

                        case R.id.navigation_favorite:
                            fragment = new FavoriteFragment();
                            break;
                    }
                    return setFragment(fragment);
                };

    private boolean setFragment(Fragment fragment) {
        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(new HomeFragment());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnItemSelectedListener(mOnItemSelectedListener);

        sharedPreferences = getSharedPreferences("LIKE", MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("0", false).apply();
        sharedPreferences.edit().putBoolean("1", false).apply();
        sharedPreferences.edit().putBoolean("2", false).apply();
        sharedPreferences.edit().putBoolean("3", false).apply();
        sharedPreferences.edit().putBoolean("4", false).apply();
        sharedPreferences.edit().putBoolean("5", false).apply();
        sharedPreferences.edit().putBoolean("6", false).apply();
    }
}