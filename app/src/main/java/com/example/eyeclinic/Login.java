package com.example.eyeclinic;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Activity {

    private Button LoginButton;
    private EditText UserEmail, UserPassword;
    private TextView NeedNewAccountLink;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();

        NeedNewAccountLink = (TextView)findViewById(R.id.register_account_link);
        UserEmail = (EditText)findViewById(R.id.login_email);
        UserPassword = (EditText)findViewById(R.id.login_password);
        LoginButton = (Button) findViewById(R.id.login);
        loadingBar = new ProgressDialog(this);

        NeedNewAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUserToRegistration();


            }
        });


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AllowingUserToLogin();


            }
        });



    }





   //@Override
   // protected void onRe()
  // {
   //     super.onStart();

   //     FirebaseUser currentUser = mAuth.getCurrentUser();
    //   if (currentUser == null)
      //  {
      //      SendUserToMainActivity();
     //  }
  // }

    private void AllowingUserToLogin()
    {
        String email =UserEmail.getText().toString();
        String password = UserPassword.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login");
            loadingBar.setMessage("Please wait, while we are allowing you to login into your Account...");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);


            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                SendUserToMainActivity();

                                Toast.makeText(Login.this, "You are Logged In successfully.", Toast.LENGTH_SHORT).show();
                                SendUserToMainActivity();
                                loadingBar.dismiss();
                            }
                            else
                            {
                                String message = task.getException().getMessage();
                                Toast.makeText(Login.this, "Error Occured:", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    private void SendUserToMainActivity()
    {
        Intent mainactivityIntent = new Intent(Login.this, MainActivity.class );
        mainactivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainactivityIntent);
        finish();

    }

    private void SendUserToRegistration()
    {
        Intent registerIntent = new Intent(Login.this, Registration.class);
        startActivity(registerIntent);
    }
}
