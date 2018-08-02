package sera.com.nba_teamthescore;

import android.app.Application;
import android.util.Log;

import sera.com.nba_teamthescore.activity.MasterActivity;

public class NBA_TeamTheScoreApplication extends Application {

    static NBA_TeamTheScoreApplication instance;
    private MasterActivity masterActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        NBA_TeamTheScoreApplication.instance = this;
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                // here implementation of uncaught exception
            }
        });
    }

    public void setMasterActivity(MasterActivity masterActivity) {
        this.masterActivity = masterActivity;
    }

    public MasterActivity getMasterActivity() {
        return masterActivity;
    }

    public static NBA_TeamTheScoreApplication getInstance() {
        return instance;
    }

}
