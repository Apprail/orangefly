package com.example.orangefly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final Context context;
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "orangefly";

    // Table Names
    private static final String DB_TABLE = "preview_image";

    // column names
    private static final String KEY_NAME = "image_name";
    private static final String KEY_IMAGE = "image_data";

    // Table create statement
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + DB_TABLE + "("+
            KEY_NAME + " TEXT," +
            KEY_IMAGE + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating table
        db.execSQL(CREATE_TABLE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);

        // create new table
        onCreate(db);
    }

    public void addEntry(String image) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_IMAGE, image);
        database.insert( DB_TABLE, null, cv );
        database.close();
    }

    public ArrayList<Bitmap> getTheImage() throws IOException {
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        ArrayList<String> test = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE;
        Cursor cursor = db.rawQuery(sql, null, null);
        Log.d("Cursor_count", String.valueOf(cursor.getCount()));
        if (cursor != null && cursor.getCount() > 0 ){
            while (cursor.moveToNext()) {
                String uri = cursor.getString(cursor.getColumnIndex("image_data"));
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(uri));
                bitmaps.add(bitmap);
            }
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return bitmaps;
    }

    public void deleteAllRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+DB_TABLE);
    }

}


