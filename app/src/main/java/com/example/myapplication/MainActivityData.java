package com.example.myapplication;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityData extends ViewModel {
    public MutableLiveData<String> P1Name;
    public MutableLiveData<String> P1Icon;
    public MutableLiveData<String> P1Marker;
    public MutableLiveData<String> P2Name;
    public MutableLiveData<String> P2Icon;
    public MutableLiveData<String> P2Marker;
    public MutableLiveData<String> DisplayScreen;
    public MutableLiveData<Boolean> P2IsAi;
    public MutableLiveData<Integer> BoardColour;
    public MutableLiveData<Integer> WinCon;
    public MutableLiveData<Integer> BoardSize;

    public MainActivityData(){
        P1Name = new MediatorLiveData<String>();
        P1Name.setValue("Player 1");

        P1Icon = new MediatorLiveData<String>();
        P1Icon.setValue("icon1 ");

        P1Marker = new MediatorLiveData<String>();
        P1Marker.setValue("marker1 ");

        P2Name = new MediatorLiveData<String>();
        P2Name.setValue("Player 2");

        P2Icon = new MediatorLiveData<String>();
        P2Icon.setValue("icon2 ");

        P2Marker = new MediatorLiveData<String>();
        P2Marker.setValue("marker2 ");

        DisplayScreen = new MediatorLiveData<String>();
        DisplayScreen.setValue("Home");

        P2IsAi = new MediatorLiveData<Boolean>();
        P2IsAi.setValue(false);

        BoardColour = new MediatorLiveData<Integer>();
        BoardColour.setValue(R.color.defaultBlue);

        BoardSize = new MutableLiveData<Integer>();
        BoardSize.setValue(3);

        WinCon = new MutableLiveData<Integer>();
        WinCon.setValue(3);
    }


    public String getP1Name(){
        return P1Name.getValue();
    }
    public void setP1Name(String value){
        P1Name.setValue(value);
    }

    public String getP1Icon(){
        return P1Icon.getValue();
    }
    public void setP1Icon(String value){
        P1Icon.setValue(value);
    }

    public String getP1Marker(){
        return P1Marker.getValue();
    }
    public void setP1Marker(String value){
        P1Marker.setValue(value);
    }

    public String getP2Name(){
        return P2Name.getValue();
    }
    public void setP2Name(String value){
        P2Name.setValue(value);
    }

    public String getP2Icon(){
        return P2Icon.getValue();
    }
    public void setP2Icon(String value){
        P2Icon.setValue(value);
    }

    public String getP2Marker(){
        return P2Marker.getValue();
    }
    public void setP2Marker(String value){
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

    public int getBoardColour(){
        return BoardColour.getValue();
    }

    public void setBoardColour(int colour){
        BoardColour.setValue(colour);
    }

    public int getBoardSize(){
        return BoardSize.getValue();
    }

    public void setBoardSize(int size){
        BoardSize.setValue(size);
    }

    public int getWinCondition(){
        return WinCon.getValue();
    }

    public void setWinCondition(int newWinCon){
        WinCon.setValue(newWinCon);
    }

}
