package com.example.orangefly.ui.prints;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.orangefly.ActivitySecond;
import com.example.orangefly.CustomProgressDialog;
import com.example.orangefly.DatabaseHelper;
import com.example.orangefly.R;
import com.example.orangefly.Utility;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class PrintTabFragment  extends Fragment {
    Context context;
    ListView listView;
    ArrayList<PrintsListItems> listItems;
    CustomPrintsListView adapter;
    ArrayList<Uri> imgArrayUri;
    CustomProgressDialog customProgressDialog;
    DatabaseHelper databaseHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_prints_tab, container, false);
        context = getActivity();
        databaseHelper = new DatabaseHelper(context);
        //databaseHelper.deleteAllRecords();
        customProgressDialog = new CustomProgressDialog(context);
        listView = (ListView)root.findViewById(R.id.prints_list);
        listItems = new ArrayList<PrintsListItems>();
        imgArrayUri = new ArrayList<Uri>();
        listItems.add(new PrintsListItems("4X4", R.mipmap.square));
        listItems.add(new PrintsListItems("4X6", R.mipmap.rectangle));
        adapter = new CustomPrintsListView(context,listItems);
        listView.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = listItems.get(position).getParent_text();

//                Intent intent =  new Intent(Intent.ACTION_GET_CONTENT);
//                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                intent.setType("image/*");
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
        return root;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode == RESULT_OK && data != null){
            customProgressDialog.show();
            List<Bitmap> bitmaps = new ArrayList<>();
            ClipData clipData = data.getClipData();
            if(clipData.getItemCount() > 10) {
                customProgressDialog.dismiss();
                Log.e("APP_TAG", "Greater than THRESHOLD.");
                Toast.makeText(context, "You can select only 10 picture",
                        Toast.LENGTH_LONG).show();
                return;
            }
            if(clipData != null){
                for (int i=0; i<clipData.getItemCount(); i++){
                    Uri imageUri = clipData.getItemAt(i).getUri();
                    databaseHelper.addEntry(String.valueOf(imageUri),1,1,1);
                }
            }else {
                Uri imageUri = data.getData();
                databaseHelper.addEntry(String.valueOf(imageUri),1,1,1);
            }
            customProgressDialog.dismiss();
            Intent intent = new Intent(getActivity(), ActivitySecond.class);
            intent.putExtra("item","Cart");
            startActivity(intent);
        }
        else {
            Toast.makeText(context, "You haven't picked image", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        adapter = new CustomPrintsListView(context,listItems);
        listView.setAdapter(adapter);
    }

    @Override
    public void onStop(){
        super.onStop();
    }
}