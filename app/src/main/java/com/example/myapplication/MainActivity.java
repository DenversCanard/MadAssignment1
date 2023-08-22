package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    HomePage homePageFragment = new HomePage();
    PlayPage playPageFragment = new PlayPage();
    GameSettingsPage gameSettingsPageFragment = new GameSettingsPage();
    PlayersPage playersPageFragment = new PlayersPage();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(this)
                .get(MainActivityData.class);
        mainActivityDataViewModel.clickedValue.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch(mainActivityDataViewModel.getClickedValue())
                {
                    case 0:
                        loadHomePageFragment();
                        break;
                    case 1:
                        loadPlayPageFragment();
                        break;
                    case 2:
                        loadGameSettingsPageFragment();
                        break;
                    case 3:
                        loadPlayersPageFragment();
                        break;
                    default:
                        break;
                }
            }
        });
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

    public void loadPlayersPageFragment(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragmentContainerView);
        if(frag==null){
            fm.beginTransaction().add(R.id.fragmentContainerView,playersPageFragment).commit();
        }
        else{
            fm.beginTransaction().replace(R.id.fragmentContainerView,playersPageFragment).commit();
        }
    }

}