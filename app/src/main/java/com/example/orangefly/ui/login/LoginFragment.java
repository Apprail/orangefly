package com.example.orangefly.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.example.orangefly.keypreference.Preferences;
import com.example.orangefly.models.DefaultResponse;
import com.example.orangefly.api.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    EditText username,password;
    Button signin;
    Context context;
    ProgressDialog progressDialog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);
        context = getActivity();
        username = root.findViewById(R.id.username);
        password = root.findViewById(R.id.password);
        signin = root.findViewById(R.id.btn_login);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        //Log.d("Shared Preference", String.valueOf(Preferences.readBoolean(context,"logged_in")));
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For test purpose start
                //Preferences.writeBoolean(context,"logged_in",true);//For test purpose end
                call_signin();
            }
        });
        return root;
    }

    private void call_signin() {
        String uname = username.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        if(uname.isEmpty()){
            username.setError("Email/Mobile is required");
            username.requestFocus();
            return;
        }
        if (pwd.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        Call<DefaultResponse> call =  RetrofitClient
                .getInstance()
                .getRetrofitApi()
                .login(uname,pwd);

        progressDialog.show();
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                progressDialog.dismiss();
                try{
                    DefaultResponse dr = response.body();
                    Log.d("Response",String.valueOf(response.body()));
                    if (dr.getStatus() == 1){
                        Preferences.writeBoolean(context,"logged_in",true);
                        Toast.makeText(getContext(),dr.getMessage(),Toast.LENGTH_SHORT).show();
                    }else{
                        Preferences.writeBoolean(context,"logged_in",false);
                        Toast.makeText(getContext(),dr.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("Exception-------",String.valueOf(e));
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                Preferences.writeBoolean(context,"logged_in",false);
            }
        });
    }
}