package com.example.baymax.ui.tools;

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

import com.example.baymax.FetchData;
import com.example.baymax.R;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    //public static TextView txtView;
    public static TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        textView1=root.findViewById(R.id.w2);
        new FetchData().execute("https://api.myjson.com/bins/10rkkg");

        //txtView=root.findViewById(R.id.textview);

        return root;
    }
}