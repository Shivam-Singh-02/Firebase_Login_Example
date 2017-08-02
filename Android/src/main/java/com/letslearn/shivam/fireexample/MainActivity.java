package com.letslearn.shivam.fireexample;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    String userName, passWord;
    Button signup,login;
    EditText user, pass;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.userName);
        pass = (EditText) findViewById(R.id.passWord);
        signup = (Button) findViewById(R.id.signup);
        login = (Button) findViewById(R.id.login);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = user.getText().toString();
                passWord = pass.getText().toString();
               createUser(userName,passWord);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = user.getText().toString();
                passWord = pass.getText().toString();
                validateUser(userName,passWord);
            }
        });
    }
    void createUser(String email,String password){
        mAuth.createUserWithEmailAndPassword(userName, passWord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "failed",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }
    void validateUser(String email,String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {



                        if (!task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Invalid Credentials",
                                    Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(MainActivity.this, "Login Successful",
                                Toast.LENGTH_SHORT).show();


                        // ...
                    }
                });

    }
}
