package com.example.orangefly.ui.account;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.orangefly.AnotherActivity;
import com.example.orangefly.R;
import com.example.orangefly.api.RetrofitClient;
import com.example.orangefly.keypreference.Preferences;
import com.example.orangefly.models.DefaultResponse;
import com.example.orangefly.ui.login.LoginFragment;
import com.example.orangefly.ui.upload.UploadViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {

    Context context;
    ListView listView;
    TextView username;
    TextView sign_out;
    LinearLayout signed_layout;
    ArrayList<AccountListItems> listItems;
    CustomAccountListView adapter;
    Boolean is_logged_in = false;
    ProgressDialog progressDialog;
    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        //accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        username = (TextView)root.findViewById(R.id.logged_username);
        sign_out = (TextView)root.findViewById(R.id.sign_out);
        signed_layout = (LinearLayout)root.findViewById(R.id.signed_layout);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        listView = (ListView)root.findViewById(R.id.account_list);
        listItems = new ArrayList<AccountListItems>();
        listItems.add(new AccountListItems(R.drawable.outline_account_circle_24,
                                            "Profile",
                                            "Edit your details"));
        listItems.add(new AccountListItems(R.drawable.outline_list_alt_24,
                                            "Order History",
                                            "View your orders, tracking, etc."));
        listItems.add(new AccountListItems(R.drawable.outline_my_location_24,
                                            "My Address",
                                            "Add and edit your saved addresses"));
//        listItems.add(new AccountListItems(R.drawable.icon_connected_accounts,
//                                            "Connected Accounts",
//                                            "Facebook, Instagram, Google Photos"));
        listItems.add(new AccountListItems(R.drawable.outline_help_outline_24,
                                            "Help Center & Support",
                                            "Help articles and FAQs"));
        listItems.add(new AccountListItems(R.drawable.outline_info_24,
                                            "About Orangefly",
                                            "Follow Orangefly and see our guarantee"));
        adapter = new CustomAccountListView(context,listItems);
        listView.setAdapter(adapter);

        is_logged_in = Preferences.readBoolean(context,"logged_in");

        if (is_logged_in){
            signed_layout.setVisibility(View.VISIBLE);
            username.setText(" "+Preferences.readString(context,"name"));
        }else{
            signed_layout.setVisibility(View.GONE);
            username.setText("");
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_item = listItems.get(i).getParent_text();
                Intent intent = new Intent(getActivity(), AnotherActivity.class);
                intent.putExtra("item",selected_item);
                startActivity(intent);
            }
        });

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = Preferences.readString(context,"username");
                String salt = Preferences.readString(context,"salt");

                Call<DefaultResponse> call =  RetrofitClient
                        .getInstance()
                        .getRetrofitApi()
                        .logout(uname,salt);
                progressDialog.show();
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        progressDialog.dismiss();
                        try{
                            DefaultResponse dr = response.body();
                            assert dr != null;
                            if (dr.getStatus() == 1){
                                Preferences.writeBoolean(context,"logged_in",false);
                                Preferences.writeString(context,"username","");
                                Preferences.writeString(context,"name","");
                                Preferences.writeString(context,"salt","");
                                Toast.makeText(getContext(),dr.getMessage(),Toast.LENGTH_SHORT).show();
                                signed_layout.setVisibility(View.GONE);
                                username.setText("");
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
        });
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        Button btn_signin = root.findViewById(R.id.btn_signin);
//
//        btn_signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                Intent intent = new Intent(getActivity(), AnotherActivity.class);
//                intent.putExtra("item","Login");
//                startActivity(intent);
//            }
//        });



        return root;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onResume(){
        super.onResume();
        adapter = new CustomAccountListView(context,listItems);
        listView.setAdapter(adapter);
//        Log.wtf("is_logged_in_onResume", String.valueOf(is_logged_in));
//        Log.wtf("preference_onResume",Preferences.readString(context,"username"));
        if (is_logged_in){
            signed_layout.setVisibility(View.VISIBLE);
            username.setText(" "+Preferences.readString(context,"name"));
        }else{
            signed_layout.setVisibility(View.GONE);
            username.setText("");
        }
    }

    @Override
    public void onStop(){
        super.onStop();
    }
}