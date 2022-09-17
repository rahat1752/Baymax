package com.example.baymax.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.baymax.R;
import com.example.baymax.UserActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button bLogout;
    private DatabaseReference rootRef;
    FirebaseAuth mFirebaseAuth;
    TextView txt;
    String name1,name2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        mFirebaseAuth=FirebaseAuth.getInstance();

        //txt=root.findViewById(R.id.Username);
        txt=root.findViewById(R.id.text_home);


        rootRef= FirebaseDatabase.getInstance().getReference();

        RetriveUserInfo();



        return root;
    }

   private void RetriveUserInfo(){
   String ID=mFirebaseAuth.getCurrentUser().getUid();
        rootRef.child("Users").child(ID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists() && (dataSnapshot.hasChild("First_name") && dataSnapshot.hasChild("Last_name") && dataSnapshot.hasChild("Age"))){
                            name1=dataSnapshot.child("First_name").getValue().toString();
                            name2=dataSnapshot.child("Last_name").getValue().toString();
                            txt.setText("Welcome "+name1+" "+name2);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}