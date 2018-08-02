package sera.com.nba_teamthescore.datasource;

import java.util.List;

import sera.com.nba_teamthescore.datasource.daoHelper.DaoSessionHolder;
import sera.com.nba_teamthescore.objects.Player;
import sera.com.nba_teamthescore.objects.Team;

public class PlayerDataSource extends AbstractDataSource<Player,Long>{

    private static PlayerDataSource instance = null;

    public static PlayerDataSource getInstance() {
        if (instance == null) {
            synchronized (PlayerDataSource.class) {
                if (instance == null) {
                    instance = new PlayerDataSource();
                }
            }
        }
        return instance;
    }

    private PlayerDataSource() {
        super(DaoSessionHolder.getDaoSession().getPlayerDao());
    }


}
