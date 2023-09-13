package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    HomePage homePageFragment = new HomePage();
    PlayPage playPageFragment = new PlayPage();
    GameSettingsPage gameSettingsPageFragment = new GameSettingsPage();
    PlayersPage playersPageFragment = new PlayersPage();
    Player1SelectionScreen player1SelectionScreen = new Player1SelectionScreen();
    pvp_or_pve_selection PVPorPVESelectionScreen = new pvp_or_pve_selection();
    Player2SelectionScreen player2SelectionScreen = new Player2SelectionScreen();
    MainActivityData mainActivityDataViewModel;
    String verifyScreenState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("main", "main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityDataViewModel = new ViewModelProvider(this).get(MainActivityData.class);

        String tmpScreenState = "Home";
        if(savedInstanceState != null)
        {
            mainActivityDataViewModel.setDisplayScreen(savedInstanceState.getString("screenValue"));
            tmpScreenState = savedInstanceState.getString("screenValue");
        }
        verifyScreenState = tmpScreenState;

        mainActivityDataViewModel.DisplayScreen.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {
                Log.d("Verify", verifyScreenState);

                if (string.compareTo(verifyScreenState) != 0) {
                    Log.d("String", string);

                    if (string.equals("Home")) {
                        loadHomePageFragment();
                    }
                    if (string.equals("Play")) {
                        loadPlayPageFragment();
                    }
                    if (string.equals("Settings")) {
                        loadGameSettingsPageFragment();
                    }
                    if (string.equals("P1SelectionScreen")) {
                        loadPlayer1SelectionFragment();
                    }
                    if (string.equals("PVPOrPVESelectionScreen")) {
                        loadPVPorPVESelectionFragment();
                    }
                    if (string.equals("P2SelectionScreen")) {
                        loadPlayer2SelectionFragment();
                    }
                    if (string.equals("Statistics")) {
                        loadStatisticsFragment();
                    }
                    verifyScreenState = string;
                }
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("screenValue", mainActivityDataViewModel.getDisplayScreen());
    }

    public void loadHomePageFragment(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragmentContainerView);
        if(frag==null){
            fm.beginTransaction().add(R.id.fragmentContainerView,homePageFragment).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragmentContainerView,homePageFragment).commit();
        }
    }

    public void loadPlayPageFragment(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragmentContainerView);
        if(frag==null){
            fm.beginTransaction().add(R.id.fragmentContainerView,playPageFragment).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragmentContainerView,playPageFragment).commit();
        }
    }

    public void loadGameSettingsPageFragment(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragmentContainerView);
        if(frag==null){
            fm.beginTransaction().add(R.id.fragmentContainerView,gameSettingsPageFragment).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragmentContainerView,gameSettingsPageFragment).commit();
        }
    }


    private void loadPlayer1SelectionFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragmentContainerView);
        if (frag == null) {
            fm.beginTransaction().add(R.id.fragmentContainerView, player1SelectionScreen).commit();
        } else {
            fm.beginTransaction().replace(R.id.fragmentContainerView, player1SelectionScreen).commit();
        }
    }

    private void loadPVPorPVESelectionFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragmentContainerView);
        if (frag == null) {
            fm.beginTransaction().add(R.id.fragmentContainerView, PVPorPVESelectionScreen).commit();
        } else {
            fm.beginTransaction().replace(R.id.fragmentContainerView, PVPorPVESelectionScreen).commit();
        }
    }

    private void loadPlayer2SelectionFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragmentContainerView);
        if (frag == null) {
            fm.beginTransaction().add(R.id.fragmentContainerView, player2SelectionScreen).commit();
        } else {
            fm.beginTransaction().replace(R.id.fragmentContainerView, player2SelectionScreen).commit();
        }
    }

    private void loadStatisticsFragment()
    {
        // TODO marilyn once you hace set up the page, copy the above structures
    }

}