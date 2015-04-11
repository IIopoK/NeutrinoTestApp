package com.example.kirill.neutrinotestapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by iiopok on 10.04.2015.
 */
public class SupportMethods {

    public static void showDialog(Context context, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.str_caution))
                .setMessage(message)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setCancelable(false)
                .setNegativeButton(context.getString(R.string.str_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static boolean stringValidation(String s){
        if(s.length()>0){
            for(char c: s.toCharArray()){
                if(c != ' '){
                    return true;
                }
            }
            return false;
        }else {
            return false;
        }
    }
}
