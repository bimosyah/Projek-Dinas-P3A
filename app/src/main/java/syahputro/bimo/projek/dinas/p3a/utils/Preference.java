package syahputro.bimo.projek.dinas.p3a.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preference {

    static final String KEY_USER_TEREGISTER = "user", KEY_PASS_TEREGISTER = "pass";
    static final String KEY_USERNAME_SEDANG_LOGIN = "Username_logged_in";
    static final String KEY_STATUS_SEDANG_LOGIN = "Status_logged_in";
    static final String KEY_ID_USER = "";

    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setRegisteredUser(Context context, String username) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_TEREGISTER, username);
        editor.apply();
    }

    public static String getRegisteredUser(Context context) {
        return getSharedPreference(context).getString(KEY_USER_TEREGISTER, "");
    }

    public static void setIdUser(Context context, String id) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_ID_USER, id);
        editor.apply();
    }

    public static String getIdUser(Context context) {
        return getSharedPreference(context).getString(KEY_ID_USER, "");
    }

    public static void setRegisteredPass(Context context, String password) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_PASS_TEREGISTER, password);
        editor.apply();
    }

    public static String getRegisteredPass(Context context) {
        return getSharedPreference(context).getString(KEY_PASS_TEREGISTER, "");
    }

    public static void setLoggedInUser(Context context, String username) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME_SEDANG_LOGIN, username);
        editor.apply();
    }

    public static String getLoggedInUser(Context context) {
        return getSharedPreference(context).getString(KEY_USERNAME_SEDANG_LOGIN, "");
    }

    public static void setLoggedInStatus(Context context, boolean status) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(KEY_STATUS_SEDANG_LOGIN, status);
        editor.apply();
    }

    public static boolean getLoggedInStatus(Context context) {
        return getSharedPreference(context).getBoolean(KEY_STATUS_SEDANG_LOGIN, false);
    }

    public static void clearLoggedInUser(Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USERNAME_SEDANG_LOGIN);
        editor.remove(KEY_STATUS_SEDANG_LOGIN);
        editor.apply();
    }
}