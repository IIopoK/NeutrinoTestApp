package com.example.kirill.neutrinotestapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kirill.neutrinotestapp.objects.UserObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends Activity {

    EditText etLogin, etPassword;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(getString(R.string.preferences_name), MODE_PRIVATE);
        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Button btnRegistration = (Button) findViewById(R.id.btnRegistration);
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = String.valueOf(etLogin.getText());
                String pass = String.valueOf(etPassword.getText());
                if(SupportMethods.stringValidation(login)){
                    if(SupportMethods.stringValidation(pass)){
                        RestClient.getUserService(LoginActivity.this).login(login, pass, new Callback<UserObject>() {
                            @Override
                            public void success(UserObject userObject, Response response) {
                                if(userObject.getSuccess()){
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(getString(R.string.user_id), userObject.getId());
                                    editor.commit();
                                    Intent intent = new Intent(LoginActivity.this, SearchAdvertisementActivity.class);
                                    startActivity(intent);
                                }else {
                                    SupportMethods.showDialog(LoginActivity.this, userObject.getMessage());
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });
                    }else {
                        SupportMethods.showDialog(LoginActivity.this, getString(R.string.str_incorrect_password));
                    }
                }else{
                    SupportMethods.showDialog(LoginActivity.this, getString(R.string.str_incorrect_login));
                }
            }
        });
    }



}
