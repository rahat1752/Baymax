package com.example.baymax;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Button Register_btn;
    private EditText Email_edittext_reg,pass_edittext_reg;
    private TextView Login_link;

    private FirebaseAuth mAuth;
    //private DatabaseReference mData;

    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        //mData= FirebaseDatabase.getInstance().getReference();
        loadingbar=new ProgressDialog(this);

        Register_btn=findViewById(R.id.Register_Button);
        Email_edittext_reg=findViewById(R.id.Register_email);
        pass_edittext_reg=findViewById(R.id.Register_password);
        Login_link=findViewById(R.id.Login_link);

        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email=Email_edittext_reg.getText().toString();
                String pass=pass_edittext_reg.getText().toString();

                if(TextUtils.isEmpty(Email)){
                    Email_edittext_reg.setError("Please enter email id");
                    Email_edittext_reg.requestFocus();
                }
                if(pass.isEmpty()){
                    pass_edittext_reg.setError("Please enter password");
                    pass_edittext_reg.requestFocus();
                }
                else{

                    loadingbar.setTitle("Creating New Account");
                    loadingbar.setMessage("Loading...");
                    loadingbar.setCanceledOnTouchOutside(true);
                    loadingbar.show();

                    mAuth.createUserWithEmailAndPassword(Email,pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                                        loadingbar.dismiss();
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "You have Successfully Signed Up",Toast.LENGTH_SHORT).show();
                                        Intent i=new Intent(RegisterActivity.this,SaveDataActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                        loadingbar.dismiss();
                                    }
                                }
                            });
                }

            }
        });

        Login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
}
