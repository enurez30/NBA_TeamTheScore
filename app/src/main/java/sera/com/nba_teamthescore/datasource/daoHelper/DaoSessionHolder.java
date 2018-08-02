package sera.com.nba_teamthescore.datasource.daoHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import sera.com.nba_teamthescore.NBA_TeamTheScoreApplication;
import sera.com.nba_teamthescore.objects.DaoMaster;
import sera.com.nba_teamthescore.objects.DaoSession;

public class DaoSessionHolder {
    private static DaoSession daoSession;

    private static DaoSession createDaoSession(Context context){
        SQLiteOpenHelper helper = new UpgradeHelper(context, "nba_teamTheScore");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        return daoMaster.newSession();
    }

    public static synchronized DaoSession getDaoSession(){
        if(daoSession == null){
            daoSession = createDaoSession(NBA_TeamTheScoreApplication.getInstance());
        }
        return daoSession;
    }
}
