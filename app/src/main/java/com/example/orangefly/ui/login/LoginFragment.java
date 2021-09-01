package com.example.orangefly.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.orangefly.AnotherActivity;
import com.example.orangefly.R;
import com.example.orangefly.keypreference.Preferences;
import com.example.orangefly.models.DefaultResponse;
import com.example.orangefly.api.RetrofitClient;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    EditText username, password;
    TextView forget_pwd;
    Button sign_in, sign_up;
    Context context;
    ProgressDialog progressDialog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);
        context = getActivity();
        username = root.findViewById(R.id.username);
        password = root.findViewById(R.id.password);
        forget_pwd = root.findViewById(R.id.forget_pwd);
        sign_in = root.findViewById(R.id.btn_login);
        sign_up = root.findViewById(R.id.btn_sign_up);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        //Log.d("Shared Preference", String.valueOf(Preferences.readBoolean(context,"logged_in")));
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For test purpose start
                //Preferences.writeBoolean(context,"logged_in",true);//For test purpose end
                call_signin();
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment signUpFragment = new SignUpFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.another_frame, signUpFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
                getActivity().setTitle(R.string.sign_up);
            }
        });

        forget_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment forgetPassword = new ForgetPassword();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.another_frame, forgetPassword); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
                getActivity().setTitle(R.string.forget_pwd);
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
                    assert dr != null;
                    if (dr.getStatus() == 1){
                        Preferences.writeBoolean(context,"logged_in",true);
                        //Log.wtf("dr.getParams().size()", String.valueOf(dr.getParams().size()));
                        for(int i=0; i<dr.getParams().size(); i++){
                            Preferences.writeString(context,"username",dr.getParams().get(i).getUsername());
                            Preferences.writeString(context,"name",dr.getParams().get(i).getName());
                            Preferences.writeString(context,"salt",dr.getParams().get(i).getSalt());
                        }
                        Toast.makeText(getContext(),dr.getMessage(),Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
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