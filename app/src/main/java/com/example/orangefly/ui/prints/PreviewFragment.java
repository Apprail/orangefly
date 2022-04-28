package com.example.orangefly.ui.prints;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.orangefly.DatabaseHelper;
import com.example.orangefly.R;

import java.io.IOException;
import java.util.ArrayList;

public class PreviewFragment extends Fragment {

    Context context;
    private ArrayList<Bitmap> preview_images;
    ImageView imageView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_preview, container, false);
        context = getActivity();
        imageView = (ImageView)root.findViewById(R.id.previewView);
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        try {
            preview_images = databaseHelper.getTheImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("preview_images", String.valueOf(preview_images.size()));
        for (int i = 0; i < preview_images.size(); i++) {
            try {
//                Log.d("preview_images-->", preview_images.get(i));
                imageView.setImageBitmap(preview_images.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.d("ImageURI", String.valueOf(preview_images));
        return root;
    }



}
