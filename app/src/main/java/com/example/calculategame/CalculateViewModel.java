package com.example.calculategame;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.security.Key;
import java.util.Random;
import java.util.logging.Handler;

public class CalculateViewModel extends AndroidViewModel {
    private static final String TAG ="CalculateViewModel" ;
    SavedStateHandle savedStateHandle;
    private String shpName="CalculateGameData";
    private String range="RANGE";
    private String operator="OPERATOR";
    private String best_score="BEST_SCORE";
    private String first_number="FIRST_NUMBER";
    private String second_number="SECOND_NUMBER";
    private String correctAnswer="CORRECT_ANSWER";
    private String currentScore="CURRENT_SCORE";
    private String currentAnswer="CURRENT_ANSWER";
    boolean win_flag=false;
    private int correctScore;
    public CalculateViewModel(@NonNull Application application,SavedStateHandle handle) {
        super(application);

        if ( !handle.contains(best_score) ){
            SharedPreferences sharedPreferences=getApplication().getSharedPreferences(shpName, Context.MODE_PRIVATE);
            handle.set(best_score,sharedPreferences.getInt(best_score,0));
            handle.set(operator,"+");
            handle.set(first_number,0);
            handle.set(second_number,0);
            handle.set(correctAnswer,0);
            handle.set(currentScore,0);
            handle.set(currentAnswer,0);
        }
        this.savedStateHandle=handle;
    }

    public MutableLiveData<Integer> getBest_score() {
        return savedStateHandle.getLiveData(best_score);
    }

    public MutableLiveData<Integer> getFirst_number() {
        return savedStateHandle.getLiveData(first_number);
    }

    public MutableLiveData<Integer> getSecond_number() {
        return savedStateHandle.getLiveData(second_number);
    }


    public MutableLiveData<Integer> getCorrectAnswer() {
        return savedStateHandle.getLiveData(correctAnswer);
    }

    public MutableLiveData<String> getOperator() {
        return savedStateHandle.getLiveData(operator);
    }

    public MutableLiveData<Integer> getCurrentScore() {
        return savedStateHandle.getLiveData(currentScore);
    }

    public MutableLiveData<Integer> getRange() {
        return savedStateHandle.getLiveData(range);
    }

    public MutableLiveData<Integer> getCurrentAnswer() {
        return savedStateHandle.getLiveData(currentAnswer);
    }

    public void operator(int range){

        Random random=new Random();
        int x,y,z;
        x=random.nextInt(range)+1;
        y=random.nextInt(range)+1;
        z=random.nextInt(2);
        Log.d(TAG, "随机数z为: "+z);
        if ( z==0 ){

            getOperator().setValue("+");
            getFirst_number().setValue(x);
            getSecond_number().setValue(y);
            getCorrectAnswer().setValue(x+y);
        }else {
            getOperator().setValue("-");
            if ( x>y )
            {
                getFirst_number().setValue(x);
                getSecond_number().setValue(y);
                getCorrectAnswer().setValue(x-y);
            }else {
                getFirst_number().setValue(y);
                getSecond_number().setValue(x);
                getCorrectAnswer().setValue(y-x);
            }
        }

    }


    public void save(){
        SharedPreferences sharedPreferences=getApplication().getSharedPreferences(shpName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(best_score,getBest_score().getValue());
        editor.apply();
    }

    public void answerCorrect(int range){
        Log.d(TAG, "answerCorrect: 输出range"+range);
        Log.d(TAG, "answerCorrect: 输出currentsCOER"+getCurrentScore().getValue());
        getCurrentScore().setValue((getCurrentScore().getValue() +1));
        if ( getCurrentScore().getValue()>getBest_score().getValue() ){
            getBest_score().setValue(getCurrentScore().getValue());
            win_flag=true;
        }
        operator(range);
    }
}
