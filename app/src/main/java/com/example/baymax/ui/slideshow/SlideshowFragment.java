package com.example.baymax.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baymax.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final EditText editText1, editText2,e3;
        Button cal;
        final TextView result;
        ImageView mImageView;

            editText1 =  root.findViewById(R.id.feet);
            editText2 =  root.findViewById(R.id.inch);
            e3 =  root.findViewById(R.id.weight);
            cal =  root.findViewById(R.id.add);
            result =  root.findViewById(R.id.result);
            mImageView =  root.findViewById(R.id.imageView);


            cal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {

                        String s1 = editText1.getText().toString();
                        String s2 = editText2.getText().toString();
                        String s3 = e3.getText().toString();
                        double num1 = Double.parseDouble(s1);
                        double num2 = Double.parseDouble(s2);
                        double num3 = Double.parseDouble(s3);
                        if (view.getId() == R.id.add) {
                            double m = (num1*0.3048) + (num2*0.0254);
                            double re=num3/(m*m);
                            result.setText("BMI=" + re);


                        }


                    } catch (Exception e) {



                    }
                }
            });
        return root;
    }
}