package com.test.coursech.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.test.coursech.R;
import com.test.coursech.domain.interactor.AuthInteractor;

public class AuthActivity extends AppCompatActivity {

    private EditText login;
    private EditText pass;
    private AuthInteractor authInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_screen);
        authInteractor = new AuthInteractor();
        login = findViewById(R.id.login);
        pass = findViewById(R.id.pass);
        View.OnClickListener oclBtnOk = v -> {
            if (check()) {
                if (authInteractor.checkAuth(login.getText().toString(), pass.getText().toString())) {
                    Intent intent = new Intent(this, ProductsActivity.class);
                    startActivity(intent);
                }
            }
        };
        findViewById(R.id.logIn).setOnClickListener(oclBtnOk);
    }

    private boolean check() {
        boolean res = true;
        if (login.getText().toString().equals("")) {
            login.setBackgroundResource(R.drawable.edit_text_warning_stroke);
            res = false;
        } else {
            login.setBackgroundResource(R.drawable.edit_text_black_stroke);
        }
        if (pass.getText().toString().equals("")) {
            pass.setBackgroundResource(R.drawable.edit_text_warning_stroke);
            res = false;
        } else {
            login.setBackgroundResource(R.drawable.edit_text_black_stroke);
        }
        return res;
    }

}