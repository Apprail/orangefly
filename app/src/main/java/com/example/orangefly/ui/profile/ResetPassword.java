package com.example.orangefly.ui.profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.orangefly.R;

public class ResetPassword extends Fragment {
    EditText old_password, new_password, confirm_password;
    Button btn_submit;
    Context context;
    ProgressDialog progressDialog;

    Boolean is_valid_input = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_reset_password, container, false);
        context = getActivity();
        old_password = root.findViewById(R.id.old_password);
        new_password = root.findViewById(R.id.new_password);
        confirm_password = root.findViewById(R.id.confirm_password);
        btn_submit = root.findViewById(R.id.btn_reset_submit);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call_submit();
            }
        });

        return root;
    }
    private void call_submit() {
        String str_old_pwd = old_password.getText().toString();
        String str_new_pwd = new_password.getText().toString();
        String str_confirm_pwd = confirm_password.getText().toString();
        if(str_old_pwd.isEmpty()){
            old_password.setError("Old password is required");
            old_password.requestFocus();
            return;
        }
        if (str_new_pwd.isEmpty()){
            new_password.setError("New Password is required");
            new_password.requestFocus();
            return;
        }

        if (str_confirm_pwd.isEmpty()){
            confirm_password.setError("Confirm Password is required");
            confirm_password.requestFocus();
            return;
        }

        if(new_password.equals(confirm_password)){
            //TODO Logic include
        }else {
            confirm_password.setError("Confirm should be same with new password");
            confirm_password.requestFocus();
            return;
        }





    }
}

