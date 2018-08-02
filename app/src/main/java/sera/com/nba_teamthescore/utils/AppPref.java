package sera.com.nba_teamthescore.utils;

import android.content.Context;
import android.content.SharedPreferences;

import sera.com.nba_teamthescore.NBA_TeamTheScoreApplication;

public class AppPref {
    private static SharedPreferences getSharedPrefs() {

        return NBA_TeamTheScoreApplication.getInstance().getSharedPreferences("sera.com.nba_teamthescore", Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getPrefsEditor() {
        return getSharedPrefs().edit();
    }

    public static void setFirstTime(boolean status){
        SharedPreferences.Editor editor = getPrefsEditor();
        editor.putBoolean("firstTime", status).apply();
    }

    public static boolean isFirstTime(){
        return getSharedPrefs().getBoolean("firstTime", true);
    }

}
