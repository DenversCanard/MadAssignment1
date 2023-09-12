package com.example.myapplication;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityData extends ViewModel {
    public MutableLiveData<String> P1Name;
    public MutableLiveData<Integer> P1Icon;
    public MutableLiveData<Integer> P1Marker;
    public MutableLiveData<String> P2Name;
    public MutableLiveData<Integer> P2Icon;
    public MutableLiveData<Integer> P2Marker;

    public MutableLiveData<String> DisplayScreen;

    public MutableLiveData<Boolean> P2IsAi;

    public MainActivityData(){
        P1Name = new MediatorLiveData<String>();
        P1Name.setValue("PLayer 2");

        P1Icon = new MediatorLiveData<Integer>();
        P1Icon.setValue(R.drawable.icon1);

        P1Marker = new MediatorLiveData<Integer>();
        P1Marker.setValue(R.drawable.marker1);

        P2Name = new MediatorLiveData<String>();
        P2Name.setValue("Player 2");

        P2Icon = new MediatorLiveData<Integer>();
        P2Icon.setValue(R.drawable.icon2);

        P2Marker = new MediatorLiveData<Integer>();
        P2Marker.setValue(R.drawable.marker2);

        DisplayScreen = new MediatorLiveData<String>();
        DisplayScreen.setValue("Home");

        P2IsAi = new MediatorLiveData<Boolean>();
        P2IsAi.setValue(false);
    }


    public String getP1Name(){
        return P1Name.getValue();
    }
    public void setP1Name(String value){
        P1Name.setValue(value);
    }

    public Integer getP1Icon(){
        return P1Icon.getValue();
    }
    public void setP1Icon(Integer value){
        P1Icon.setValue(value);
    }

    public Integer getP1Marker(){
        return P1Marker.getValue();
    }
    public void setP1Marker(Integer value){
        P1Marker.setValue(value);
    }


    public String getP2Name(){
        return P2Name.getValue();
    }
    public void setP2Name(String value){
        P2Name.setValue(value);
    }

    public Integer getP2Icon(){
        return P2Icon.getValue();
    }
    public void setP2Icon(Integer value){
        P2Icon.setValue(value);
    }

    public Integer getP2Marker(){
        return P2Marker.getValue();
    }
    public void setP2Marker(Integer value){
        P2Marker.setValue(value);
    }


    public String getDisplayScreen(){
        return DisplayScreen.getValue();
    }
    public void setDisplayScreen(String value){
        DisplayScreen.setValue(value);
    }

    public Boolean getP2IsAi(){
        return P2IsAi.getValue();
    }
    public void setP2IsAi(Boolean value){
        P2IsAi.setValue(value);
    }
}
