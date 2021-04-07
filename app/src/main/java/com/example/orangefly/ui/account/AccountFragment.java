package com.example.orangefly.ui.account;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
import com.example.orangefly.ui.login.LoginFragment;
import com.example.orangefly.ui.upload.UploadViewModel;

import java.util.ArrayList;

public class AccountFragment extends Fragment {

    Context context;
    ListView listView;
    ArrayList<AccountListItems> listItems;
    CustomAccountListView adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        //accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        listView = (ListView)root.findViewById(R.id.account_list);
        listItems = new ArrayList<AccountListItems>();
        listItems.add(new AccountListItems(R.drawable.icon_project,
                                            "Saved Projects",
                                            "Edit and reorder your projects"));
        listItems.add(new AccountListItems(R.drawable.icons_order_history,
                                            "Order History",
                                            "View your orders, tracking, etc"));
        listItems.add(new AccountListItems(R.drawable.icon_address_book,
                                            "Address Book",
                                            "Add and edit your saved addresses"));
        listItems.add(new AccountListItems(R.drawable.icon_connected_accounts,
                                            "Connected Accounts",
                                            "Facebook, Instagram, Google Photos"));
        listItems.add(new AccountListItems(R.drawable.icon_help,
                                            "Help Center & Support",
                                            "Help articles and FAQs"));
        listItems.add(new AccountListItems(R.drawable.icon_info,
                                            "About Orangefly",
                                            "Follow Orangefly and see our guarantee"));
        adapter = new CustomAccountListView(context,listItems);
        listView.setAdapter(adapter);
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

    @Override
    public void onResume(){
        super.onResume();
        adapter = new CustomAccountListView(context,listItems);
        listView.setAdapter(adapter);
    }

    @Override
    public void onStop(){
        super.onStop();
    }
}