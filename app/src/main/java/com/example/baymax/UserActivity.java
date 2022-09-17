package com.example.baymax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {

    /*FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        TextView click=findViewById(R.id.clickWebMD);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(UserActivity.this,WebMdActivity.class);
                //i.putExtra("")
                startActivity(i);
            }
        });

        /*mAuthStateListener =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null ){
                    Toast.makeText(UserActivity.this, "You are Logged In",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UserActivity.this,MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(UserActivity.this, "Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };*/


        ImageButton register,login,reminder,misc;
        register=findViewById(R.id.image_reg);
        login=findViewById(R.id.image_log);
        reminder=findViewById(R.id.image_remind);
        misc=findViewById(R.id.image_misc);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, RegisterActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, LoginActivity.class));
            }
        });

        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,ReminderActivity.class ));
            }
        });

        misc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,EmergencyActivity.class ));
            }
        });
    }

   /*@Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }*/
}
