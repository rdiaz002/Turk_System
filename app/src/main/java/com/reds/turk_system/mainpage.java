package com.reds.turk_system;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class mainpage extends AppCompatActivity {
    private BottomNavigationView nav;
    private Fragment main_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        main_frag = new profile();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_view, main_frag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();


        nav = (BottomNavigationView) findViewById(R.id.nav);
        nav.inflateMenu(R.menu.menu1);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.profile:
                        main_frag = new profile();
                        break;
                    case R.id.search:
                        main_frag = new Search();
                        break;
                    case R.id.Bid:
                        main_frag = new Bid();
                        break;
                    case R.id.projects:
                        main_frag = new projects();
                        break;
                    case R.id.Settings:
                        main_frag = new Settings();
                        break;
                    case R.id.Warnings:
                        main_frag = new Warnings();
                        break;
                    case R.id.Authorize:
                        main_frag = new Authorize();
                        break;
                    case R.id.Quit:
                        main_frag= new Quit();
                        break;
                    case R.id.Payment:
                        main_frag = new Payment();
                        break;
                    case R.id.BlackList:
                        main_frag = new Blacklist();
                        break;
                    default:

                        main_frag = new profile();
                        break;

                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_view, main_frag)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                return true;
            }
        });
    }
}
