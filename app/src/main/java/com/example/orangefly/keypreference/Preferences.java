package com.example.orangefly.keypreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {

    public static SharedPreferences mPrefs;

    public static Boolean readBoolean(Context context, String key) {
        mPrefs = context.getSharedPreferences(key,Context.MODE_PRIVATE);
        return mPrefs.getBoolean(key, false);
    }

    public static void writeBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(key, value);
        mEditor.apply();
        mEditor.commit();
    }

    public static String readString(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

    public static void writeString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.apply();
    }
}
