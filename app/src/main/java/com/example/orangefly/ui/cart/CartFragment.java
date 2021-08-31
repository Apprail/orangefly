package com.example.orangefly.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.orangefly.AnotherActivity;
import com.example.orangefly.R;
import com.example.orangefly.keypreference.Preferences;
import com.example.orangefly.ui.account.CustomAccountListView;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

public class CartFragment extends Fragment {

    ImageView empty_cart_img;
    TextView empty_cart_txt;
    Button btn_cart_sign_in;
    Button btn_cart_shopping;
    Boolean is_logged_in = false;
    Context context;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        context = getActivity();
        empty_cart_img = (ImageView)root.findViewById(R.id.cart_empty_img);
        empty_cart_txt = (TextView) root.findViewById(R.id.cart_empty_text);
        btn_cart_sign_in = (Button) root.findViewById(R.id.btn_cart_sign_in);
        btn_cart_shopping = (Button)root.findViewById(R.id.btn_cart_shopping);
        is_logged_in = Preferences.readBoolean(context,"logged_in");

        if (is_logged_in){
            btn_cart_sign_in.setVisibility(View.GONE);

        }else{
            btn_cart_sign_in.setVisibility(View.VISIBLE);
        }

        btn_cart_sign_in.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onResume(){
        super.onResume();
        is_logged_in = Preferences.readBoolean(context,"logged_in");
        if (is_logged_in){
            btn_cart_sign_in.setVisibility(View.GONE);

        }else{
            btn_cart_sign_in.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStop(){
        super.onStop();
    }
}