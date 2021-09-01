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

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassword extends Fragment {
    EditText email_mobile, otp;
    Button btn_submit;
    Context context;
    ProgressDialog progressDialog;
    Boolean is_valid_input = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_forget_password, container, false);
        context = getActivity();
        email_mobile = root.findViewById(R.id.registered_emailid);
        otp = root.findViewById(R.id.forget_otp);
        btn_submit = root.findViewById(R.id.btn_forget_submit);

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
        String str_uname = email_mobile.getText().toString().trim();
        String str_otp = otp.getText().toString().trim();
        String otp_type = "forget password";
        if(str_uname.isEmpty()){
            email_mobile.setError("Email/Mobile is required");
            email_mobile.requestFocus();
            return;
        }
        if (str_otp.isEmpty() && is_valid_input){
            otp.setError("OTP is required");
            otp.requestFocus();
            return;
        }

        if(is_valid_input){
            // Verify OTP
            Call<DefaultResponse> call =  RetrofitClient
                    .getInstance()
                    .getRetrofitApi()
                    .verifyotp(str_uname,str_otp,otp_type);
            progressDialog.show();
            call.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(@NotNull Call<DefaultResponse> call, @NotNull Response<DefaultResponse> response) {
                    progressDialog.dismiss();
                    try{
                        DefaultResponse dr = response.body();
                        assert dr != null;
                        if (dr.getStatus() == 1){
                            Preferences.writeBoolean(context,"logged_in",true);
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
                }
            });
        }else{
            Call<DefaultResponse> call =  RetrofitClient
                    .getInstance()
                    .getRetrofitApi()
                    .forgetpassword(str_uname,otp_type);

            progressDialog.show();
            call.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(@NotNull Call<DefaultResponse> call, @NotNull Response<DefaultResponse> response) {
                    progressDialog.dismiss();
                    try{
                        DefaultResponse dr = response.body();
                        assert dr != null;
                        if (dr.getStatus() == 1){
                            otp.setVisibility(View.VISIBLE);
                        }else{
                            otp.setVisibility(View.GONE);
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
                }
            });
        }



    }
}
