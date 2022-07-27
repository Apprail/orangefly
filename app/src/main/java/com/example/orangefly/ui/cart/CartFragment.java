package com.example.orangefly.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orangefly.AnotherActivity;
import com.example.orangefly.DatabaseHelper;
import com.example.orangefly.R;
import com.example.orangefly.keypreference.Preferences;
import com.example.orangefly.ui.account.AccountListItems;
import com.example.orangefly.ui.account.CustomAccountListView;
import com.example.orangefly.ui.photos.Pager;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

public class CartFragment extends Fragment {

    ImageView empty_cart_img;
    TextView empty_cart_txt;
    Button btn_cart_sign_in;
    Button btn_cart_shopping;
    Boolean is_logged_in = false;
    Context context;
    ArrayList<CartItems> listItems;
    CartAdapter cartAdapter;
    ListView listView;
    ArrayList<Bitmap> preview_images;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        context = getActivity();
        empty_cart_img = (ImageView)root.findViewById(R.id.cart_empty_img);
        empty_cart_txt = (TextView) root.findViewById(R.id.cart_empty_text);
        btn_cart_sign_in = (Button) root.findViewById(R.id.btn_cart_sign_in);
        btn_cart_shopping = (Button)root.findViewById(R.id.btn_cart_shopping);
        assert context != null;
        is_logged_in = Preferences.readBoolean(context,"logged_in");
        listItems = new ArrayList<CartItems>();
        listView = (ListView) root.findViewById(R.id.recycler_view);
        DatabaseHelper databaseHelper = new DatabaseHelper(context);


        if (is_logged_in){
            btn_cart_sign_in.setVisibility(View.GONE);

        }else{
            btn_cart_sign_in.setVisibility(View.VISIBLE);
        }

        try {
            preview_images = databaseHelper.getTheImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < preview_images.size(); i++) {
            //imageView.setImageBitmap(preview_images.get(i));
            listItems.add(new CartItems(preview_images.get(i), "Item", 120, 1, 1));

        }
        cartAdapter = new CartAdapter(context, listItems);
        listView.setAdapter(cartAdapter);


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
        cartAdapter = new CartAdapter(context, listItems);
        listView.setAdapter(cartAdapter);
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