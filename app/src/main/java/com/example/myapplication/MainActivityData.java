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

    public MutableLiveData<Integer> P1Wins;

    public MutableLiveData<Integer> P2Wins;
    public MutableLiveData<Integer> Draw;



    public MutableLiveData<String> DisplayScreen;

    public MutableLiveData<Boolean> P2IsAi;
    public MutableLiveData<Integer> BoardColour;
    public MutableLiveData<Integer> WinCon;
    public MutableLiveData<Integer> BoardSize;

    public MainActivityData(){
        P1Name = new MediatorLiveData<String>();
        P1Name.setValue("PLayer 1");

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

        P2IsAi = new MediatorLiveData<Boolean>();
        P2IsAi.setValue(false);

        P1Wins = new MediatorLiveData<Integer>();
        P1Wins.setValue(0);

        P2Wins = new MediatorLiveData<Integer>();
        P2Wins.setValue(0);

        Draw = new MutableLiveData<Integer>();
        Draw.setValue(3);

        DisplayScreen = new MediatorLiveData<String>();
        DisplayScreen.setValue("Home");

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
    public Integer getP1Icon(){
        return P1Icon.getValue();
    }
    public void setP1Icon(Integer value){
        P1Icon.setValue(value);             //
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

    public void setP1Wins(int wins) { P1Wins.setValue(wins);}
    public void addP1Win(){P1Wins.setValue(P1Wins.getValue()+1);}
    public void clearP1Wins(){P1Wins.setValue(0);}
    public Integer getP1wins(){return P1Wins.getValue();}

    //P2 WINS
    public void setP2Wins(int wins) { P2Wins.setValue(wins);}
    public void addP2Win(){P2Wins.setValue(P2Wins.getValue()+1);}
    public void clearP2Wins(){P2Wins.setValue(0);}
    public Integer getP2wins(){return P2Wins.getValue();}

    public void setDraw(int wins) { Draw.setValue(wins);}
    public void addDraw(){Draw.setValue(Draw.getValue()+1);}
    public void clearDraw(){Draw.setValue(0);}
    public Integer getDraw(){return Draw.getValue();}

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
