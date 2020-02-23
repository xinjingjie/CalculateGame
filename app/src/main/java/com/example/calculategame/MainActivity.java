package com.example.calculategame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.style.BulletSpan;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController= Navigation.findNavController(this,R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this,navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        if ( navController.getCurrentDestination().getId()==R.id.questionFragment ){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.quit_dialog_message));
            builder.setPositiveButton(R.string.dialog_positive_message, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    navController.navigateUp();
                }
            });
            builder.setNegativeButton(getString(R.string.dialog_negative_message), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();
        }


        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        switch (navController.getCurrentDestination().getId()){
            case R.id.questionFragment:
                onSupportNavigateUp();
                break;
            case R.id.winFragment:
                navController.navigate(R.id.action_winFragment_to_welcomeFragment);
                break;
            case R.id.loseFragment:
                navController.navigate(R.id.action_loseFragment_to_welcomeFragment);
                break;
            default:
                super.onBackPressed();
        }



    }
}
