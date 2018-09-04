package com.example.mdmuktadir.sqlitelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText email,password,confirmPassword;
    Button btnReg;
    TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeObj();
        databaseHelper=new DatabaseHelper(this);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string1=email.getText().toString();
                String string2=password.getText().toString();
                String string3=confirmPassword.getText().toString();

                if (string1.equals("")||string2.equals("")||string3.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (string2.equals(string3)){
                        Boolean check_email=databaseHelper.checkEmail(string1);
                        if (check_email==true){
                            Boolean insert=databaseHelper.insertData(string1,string2);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Successfully!!!",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Email Already Exists!!!",Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(getApplicationContext(),"Password do not match!",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });







    }

    public void makeObj(){
        email=(EditText)findViewById(R.id.et_email);
        password=(EditText)findViewById(R.id.et_password);
        confirmPassword=(EditText)findViewById(R.id.et_confirmPassword);
        btnReg=(Button)findViewById(R.id.btn_reg);
        tv_register=(TextView)findViewById(R.id.tv_register);

    }
}
