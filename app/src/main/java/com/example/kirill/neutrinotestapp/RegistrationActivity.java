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


public class RegistrationActivity extends Activity {
    EditText etLogin, etPassword, etVerifyPassword;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        sharedPreferences = getSharedPreferences(getString(R.string.preferences_name), MODE_PRIVATE);
        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etVerifyPassword = (EditText) findViewById(R.id.etVerifyPassword);
        Button btnRegistration = (Button) findViewById(R.id.btnRegistration);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = String.valueOf(etLogin.getText());
                String pass1 = String.valueOf(etPassword.getText());
                String pass2 = String.valueOf(etVerifyPassword.getText());
                if(SupportMethods.stringValidation(login)){
                    if(SupportMethods.stringValidation(pass1)){
                        if(pass2.equals(pass1)){
                            RestClient.getUserService(RegistrationActivity.this).registration(login, pass1, new Callback<UserObject>() {
                                @Override
                                public void success(UserObject userObject, Response response) {
                                    if(userObject.getSuccess()){
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(getString(R.string.user_id), userObject.getId());
                                        editor.commit();
                                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }else {
                                        SupportMethods.showDialog(RegistrationActivity.this, userObject.getMessage());
                                    }
                                }

                                @Override
                                public void failure(RetrofitError error) {

                                }
                            });
                        }else {
                            SupportMethods.showDialog(RegistrationActivity.this, getString(R.string.str_passwords_are_not_equal));
                        }
                    }else {
                        SupportMethods.showDialog(RegistrationActivity.this, getString(R.string.str_incorrect_password));
                    }
                }else {
                    SupportMethods.showDialog(RegistrationActivity.this, getString(R.string.str_incorrect_login));
                }
            }
        });
    }
}
