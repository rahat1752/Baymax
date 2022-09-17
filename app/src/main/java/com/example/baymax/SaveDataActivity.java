package com.example.baymax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SaveDataActivity extends AppCompatActivity {

    private EditText Name1_ET,Age_Et,Name2_ET;
    private Button Save_btn;

    private FirebaseAuth mAuth;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        mAuth=FirebaseAuth.getInstance();
        mData= FirebaseDatabase.getInstance().getReference();

        Name1_ET=findViewById(R.id.name_ET1);
        Name2_ET=findViewById(R.id.name_ET2);
        Age_Et=findViewById(R.id.Age_ET);
        Save_btn=findViewById(R.id.button_save);

        Save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1=Name1_ET.getText().toString().trim();
                String name2=Name2_ET.getText().toString().trim();
                String age=Age_Et.getText().toString().trim();

                user u=new user(name1,name2,age);
                String ID=mAuth.getCurrentUser().getUid();
                mData.child("Users").child(ID).setValue(u);

                Toast.makeText(SaveDataActivity.this, "You have Successfully Saved Data",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(SaveDataActivity.this,MainActivity.class));

            }
        });

    }
}

