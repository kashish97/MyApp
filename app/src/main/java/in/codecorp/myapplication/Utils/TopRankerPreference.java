package in.codecorp.myapplication.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Html;

import static android.util.Patterns.EMAIL_ADDRESS;

public class TopRankerPreference {
    public static final String PREFERENCE = "TBPreference";
    public static final String USER_ID = "USER_ID";
    public static final String TOKEN_ID = "TOKEN_ID";

    private static Context mContext;
    private static TopRankerPreference tafelBossPreference;
    private SharedPreferences sharedPreferences;

    private TopRankerPreference() {
        sharedPreferences = mContext.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
    }

    public static TopRankerPreference getInstance() {
        if (tafelBossPreference == null) {
            tafelBossPreference = new TopRankerPreference();
        }
        return tafelBossPreference;
    }

    public static Context getmContext() {
        return mContext;
    }

    public static void setmContext(Context mContext) {
        TopRankerPreference.mContext = mContext;
    }

    public static String getUDID() {
        String id = android.provider.Settings.System.getString(mContext.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        return id;
    }

    public static boolean isTextEmpty(String text) {
        return (text == null || text.isEmpty());
    }

    public static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        } else {
            return EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public void saveStringData(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public void saveIntData(String key, int value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(key, value);
        prefsEditor.commit();
    }

    public String getStringData(String key) {
        String data = null;
        if (sharedPreferences != null) {
            data = sharedPreferences.getString(key, "");
        }
        return data;
    }

    public String getStringData(String key, String defaultValue) {
        String data = null;
        if (sharedPreferences != null) {
            data = sharedPreferences.getString(key, defaultValue);
        }
        if (data.equals(defaultValue)) {
            return defaultValue;
        }
        return data;
    }

    public int getIntData(String key) {
        int data = 0;
        if (sharedPreferences != null) {
            data = sharedPreferences.getInt(key, 0);
        }
        return data;
    }

    public void saveBooleanData(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public boolean getBooleanData(String key) {
        boolean data = false;
        if (sharedPreferences != null) {
            data = sharedPreferences.getBoolean(key, false);
        }
        return data;
    }

    public static String stripHtml(String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(html).toString();
        }
    }
}