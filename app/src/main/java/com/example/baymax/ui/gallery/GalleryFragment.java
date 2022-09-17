package com.example.baymax.ui.gallery;

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

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

            final EditText editText1, editText2;
            Button cal;
            final TextView result;
            ImageView mImageView;


                editText1 = root.findViewById(R.id.feet);
                editText2 = root.findViewById(R.id.inch);
                cal = root.findViewById(R.id.add);
                result = root.findViewById(R.id.result);



                cal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {

                            String s1 = editText1.getText().toString();
                            String s2 = editText2.getText().toString();
                            double num1 = Double.parseDouble(s1);
                            double num2 = Double.parseDouble(s2);
                            if (view.getId() == R.id.add) {
                                double m = (num1 * 0.3048) + (num2 * 0.0254);
                                double in=m*39.37;
                                double re = 50+2.3* (in-60);
                                result.setText("IBW= " + re+"kg");


                            }


                        } catch (Exception e) {


                        }
                    }
                });




        return root;
    }
}
