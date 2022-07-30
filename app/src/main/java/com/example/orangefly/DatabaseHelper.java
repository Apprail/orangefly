package com.example.orangefly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
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
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final Context context;
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "orangefly_db   ";

    // Table Names
    private static final String CART_TABLE_NAME = "cart";
    // column names
    private static final String CART_COLUMN_ID = "cid";
    private static final String CART_COLUMN_IMAGE_NAME = "image_name";
    private static final String CART_COLUMN_QTY = "qty";
    private static final String CART_COLUMN_PRICE = "price";
    private static final String CART_COLUMN_TOTAL = "total";

    // Table create statement
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + CART_TABLE_NAME + "("+
            CART_COLUMN_ID + " INTEGER PRIMARY KEY," +
            CART_COLUMN_IMAGE_NAME + " TEXT UNIQUE," +
            CART_COLUMN_QTY + " INTEGER,"+
            CART_COLUMN_PRICE + " INTEGER,"+
            CART_COLUMN_TOTAL + " INTEGER);";

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
        db.execSQL("DROP TABLE IF EXISTS " + CART_TABLE_NAME);
        // create new table
        onCreate(db);
    }

    public void addEntry(String image, int qty, int price, int total) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CART_COLUMN_IMAGE_NAME, image);
        cv.put(CART_COLUMN_QTY, qty);
        cv.put(CART_COLUMN_PRICE, price);
        cv.put(CART_COLUMN_TOTAL, total);
        database.insert(CART_TABLE_NAME, null, cv );
        database.close();
    }

    public void updateEntry(int id, String image, int qty, int price, int total) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CART_COLUMN_IMAGE_NAME, image);
        cv.put(CART_COLUMN_QTY, qty);
        cv.put(CART_COLUMN_PRICE, price);
        cv.put(CART_COLUMN_TOTAL, total);
        database.update( CART_TABLE_NAME, cv, "id = ? ", new String[] { Integer.toString(id) }  );
        database.close();
    }

    public ArrayList<Bitmap> getTheImage() throws IOException {
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + CART_TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null, null);
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()) {
                String uri = cursor.getString(cursor.getColumnIndex("image_name"));
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(uri));
                bitmaps.add(bitmap);
            }
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }

        return bitmaps;
    }

    public int getCartTotal() {
        int total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + CART_TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null, null);
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()) {
                int cart_total = cursor.getInt(cursor.getColumnIndex("total"));
                total += cart_total;
            }
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }

        return total;
    }

    public ArrayList<List> getAllCart() {
        ArrayList<List> array_list = new ArrayList<List>();
        int cart_id = 0;
        String image_name = "";
        int qty =0;
        int price = 0;
        int total=0;
        String count = "";

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select *,COUNT(cid) AS count from cart GROUP BY cid ORDER BY cid ", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            ArrayList<String> list = new ArrayList<String>();
            list.add(res.getString(res.getColumnIndex(CART_COLUMN_ID)));
            list.add(res.getString(res.getColumnIndex(CART_COLUMN_IMAGE_NAME)));
            list.add(res.getString(res.getColumnIndex(CART_COLUMN_QTY)));
            list.add(res.getString(res.getColumnIndex(CART_COLUMN_PRICE)));
            list.add(res.getString(res.getColumnIndex(CART_COLUMN_TOTAL)));
            list.add(res.getString(res.getColumnIndex("count")));
            array_list.add(list);
            res.moveToNext();
        }
        return array_list;
    }

    public void deleteAllRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+CART_TABLE_NAME);
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, CART_TABLE_NAME);
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from cart where id="+id+"", null );
    }

}


