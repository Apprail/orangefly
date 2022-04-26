package com.example.orangefly.ui.prints;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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

import com.example.orangefly.R;
import com.example.orangefly.ui.login.SignUpFragment;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_prints_tab, container, false);
        context = getActivity();

        listView = (ListView)root.findViewById(R.id.prints_list);
        listItems = new ArrayList<PrintsListItems>();
        imgArrayUri = new ArrayList<Uri>();
        listItems.add(new PrintsListItems("4X4", R.mipmap.square));
        listItems.add(new PrintsListItems("4X6", R.mipmap.rectangle));
        adapter = new CustomPrintsListView(context,listItems);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = listItems.get(position).getParent_text();
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                }
                Intent intent =  new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode == RESULT_OK){
            List<Bitmap> bitmaps = new ArrayList<>();
            ClipData clipData = data.getClipData();
            if(clipData != null){
                for (int i=0; i<clipData.getItemCount(); i++){
                    Uri imageUri = clipData.getItemAt(i).getUri();
                    imgArrayUri.add(imageUri);
//                    try {
//                        InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        bitmaps.add(bitmap);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
                }
            }else {
                Uri imageUri = data.getData();
                imgArrayUri.add(imageUri);
//                try {
//                    InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    bitmaps.add(bitmap);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("selectedImage", imgArrayUri);
            Fragment previewFragment = new PreviewFragment();
            previewFragment.setArguments(bundle);
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_second_frame, previewFragment ); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
            getActivity().setTitle("Preview");

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for(final Bitmap b : bitmaps){
//                        getActivity().runOnUiThread(new Runnable(){
//
//                            @Override
//                            public void run() {
//
//                            }
//                        });
//                    }
//                }
//            });
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