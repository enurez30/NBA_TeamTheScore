package sera.com.nba_teamthescore.datasource;

import java.util.List;

import sera.com.nba_teamthescore.datasource.daoHelper.DaoSessionHolder;
import sera.com.nba_teamthescore.objects.Team;
import sera.com.nba_teamthescore.objects.TeamDao;

public class TeamDataSource extends AbstractDataSource<Team, Long> {

    private static TeamDataSource instance = null;

    public static TeamDataSource getInstance() {
        if (instance == null) {
            synchronized (TeamDataSource.class) {
                if (instance == null) {
                    instance = new TeamDataSource();
                }
            }
        }
        return instance;
    }

    private TeamDataSource() {
        super(DaoSessionHolder.getDaoSession().getTeamDao());
    }


    public List<Team> getTeams() {
        return this.queryBuilder().orderAsc(TeamDao.Properties.TeamName).list();
    }

    public List<Team> getTeamsByWinAcending() {
        return this.queryBuilder().orderAsc(TeamDao.Properties.TeamWins).list();
    }

    public List<Team> getTeamsByWinDescending() {
        return this.queryBuilder().orderDesc(TeamDao.Properties.TeamWins).list();
    }

    public List<Team> getTeamsByWLostAcending() {
        return this.queryBuilder().orderAsc(TeamDao.Properties.TeamLosses).list();
    }

    public List<Team> getTeamsByLostDescending() {
        return this.queryBuilder().orderDesc(TeamDao.Properties.TeamLosses).list();
    }
}
