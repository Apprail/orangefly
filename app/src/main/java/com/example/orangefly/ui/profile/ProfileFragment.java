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
import com.example.orangefly.api.RetrofitClient;
import com.example.orangefly.keypreference.Preferences;
import com.example.orangefly.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    EditText firstname,lastname,email;
    Context context;
    ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        context = getActivity();
        firstname = root.findViewById(R.id.pf_first_name);
        lastname = root.findViewById(R.id.pf_last_name);
        email = root.findViewById(R.id.pf_email);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        return root;
    }

}
