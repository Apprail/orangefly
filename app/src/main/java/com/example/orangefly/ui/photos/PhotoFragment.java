package com.example.orangefly.ui.photos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.orangefly.AnotherActivity;
import com.example.orangefly.R;

public class PhotoFragment extends Fragment {

    private PhotoViewModel photoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        photoViewModel =
                new ViewModelProvider(this).get(PhotoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_photo, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
//        photoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        Button btn_signin = root.findViewById(R.id.btn_signin);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Intent intent = new Intent(getActivity(), AnotherActivity.class);
                intent.putExtra("item","Login");
                startActivity(intent);
            }
        });
        
        return root;
    }
}