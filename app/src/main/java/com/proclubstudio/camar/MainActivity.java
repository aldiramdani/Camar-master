package com.proclubstudio.camar;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    Fragment[] fragments = { new HomeFragment(), new ListGempaFragment()};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(fragments[0]);

        BottomNavigationView  navigationView = (BottomNavigationView)findViewById(R.id.bottomNavigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        loadFragment(fragments[0]);
                        break;
                    case R.id.ic_gempa:
                        loadFragment(fragments[1]);
                        break;
                    case R.id.ic_cepattgp:
                        break;
                }

                return true;
            }
        });

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.home_fragment,new HomeFragment())
//                .commit();



    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}
