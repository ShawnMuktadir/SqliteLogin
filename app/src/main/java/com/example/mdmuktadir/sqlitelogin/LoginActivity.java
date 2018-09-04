package com.example.mdmuktadir.sqlitelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText et_loginemail,et_loginpassword;
    Button btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper=new DatabaseHelper(this);
        makeObj();


        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                String email= et_loginemail.getText().toString();
                String password= et_loginpassword.getText().toString();
                Boolean checkEmailPassword=databaseHelper.checkEmailPassword(email,password);
                if (checkEmailPassword==true){
                    Toast.makeText(getApplicationContext(),"Login Successful!!!",Toast.LENGTH_SHORT).show();
                    intent=new Intent(getApplicationContext(),FinalActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Wrong Email or Password!",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void makeObj(){
        et_loginemail=(EditText)findViewById(R.id.et_loginemail);
        et_loginpassword=(EditText)findViewById(R.id.et_loginpassword);
        btn_Login=(Button)findViewById(R.id.btn_Login);
    }
}
