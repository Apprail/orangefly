package com.example.orangefly.ui.login;

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

public class SignUpFragment extends Fragment {

    EditText firstname,lastname,email,mobile,password;
    Button signup;
    Context context;
    ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_signup, container, false);
        context = getActivity();
        firstname = root.findViewById(R.id.sign_up_firstname);
        lastname = root.findViewById(R.id.sign_up_lastname);
        mobile = root.findViewById(R.id.sign_up_mobile);
        email = root.findViewById(R.id.sign_up_email);
        password = root.findViewById(R.id.sign_up_password);
        signup = root.findViewById(R.id.btn_sign_up_join);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call_signup();
            }
        });
        return root;
    }

    private void call_signup() {
        String str_firstname = firstname.getText().toString().trim();
        String str_lastname = lastname.getText().toString().trim();
        String str_email = email.getText().toString().trim();
        String str_mobile = mobile.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        if(str_firstname.isEmpty()){
            firstname.setError("First Name is required");
            firstname.requestFocus();
            return;
        }
        if(str_lastname.isEmpty()){
            lastname.setError("Last Name is required");
            lastname.requestFocus();
            return;
        }
        if(str_mobile.isEmpty()){
            mobile.setError("Mobile Number is required");
            mobile.requestFocus();
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
                .sign_up(str_firstname,str_lastname,str_mobile,str_email,pwd);

        progressDialog.show();
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                progressDialog.dismiss();
                try{
                    DefaultResponse dr = response.body();
                    //Log.d("Response",String.valueOf(response.body()));
                    if (dr.getStatus() == 1){
                        Preferences.writeBoolean(context,"logged_in",true);
                        for(int i=0; i<dr.getParams().size(); i++){
                            Preferences.writeString(context,"username",dr.getParams().get(i).getUsername());
                            Preferences.writeString(context,"name",dr.getParams().get(i).getName());
                            Preferences.writeString(context,"salt",dr.getParams().get(i).getSalt());
                            Preferences.writeString(context,"email",dr.getParams().get(i).getEmail());
                        }
                        Toast.makeText(getContext(),dr.getMessage(),Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    }else{
                        Preferences.writeBoolean(context,"logged_in",false);
                        Toast.makeText(getContext(),dr.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Preferences.writeBoolean(context,"logged_in",false);
                    Log.d("Exception-------",String.valueOf(e));
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                progressDialog.dismiss();
                Preferences.writeBoolean(context,"logged_in",false);
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
