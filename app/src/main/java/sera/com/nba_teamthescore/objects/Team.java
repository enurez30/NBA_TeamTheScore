package sera.com.nba_teamthescore.objects;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import org.greenrobot.greendao.DaoException;

@Entity
public class Team {

    @Id
    private Long id;

    private Long teamId;
    private String teamName;
    private String abbreviation;
    private int teamWins;
    private int teamLosses;

    @ToMany(referencedJoinProperty = "teamId")
    private List<Player> playerList;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1539804063)
    private transient TeamDao myDao;

    @Generated(hash = 2045450142)
    public Team(Long id, Long teamId, String teamName, String abbreviation,
                int teamWins, int teamLosses) {
        this.id = id;
        this.teamId = teamId;
        this.teamName = teamName;
        this.abbreviation = abbreviation;
        this.teamWins = teamWins;
        this.teamLosses = teamLosses;
    }

    @Generated(hash = 882286361)
    public Team() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return this.teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamWins() {
        return this.teamWins;
    }

    public void setTeamWins(int teamWins) {
        this.teamWins = teamWins;
    }

    public int getTeamLosses() {
        return this.teamLosses;
    }

    public void setTeamLosses(int teamLosses) {
        this.teamLosses = teamLosses;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1861065475)
    public List<Player> getPlayerList() {
        if (playerList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlayerDao targetDao = daoSession.getPlayerDao();
            List<Player> playerListNew = targetDao._queryTeam_PlayerList(id);
            synchronized (this) {
                if (playerList == null) {
                    playerList = playerListNew;
                }
            }
        }
        return playerList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1310905913)
    public synchronized void resetPlayerList() {
        playerList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 256592523)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTeamDao() : null;
    }

    public Team(JSONObject singleItem) {

        try {

            setTeamId(singleItem.getLong("id"));
            setTeamName(singleItem.getString("full_name"));
            setTeamWins(singleItem.getInt("wins"));
            setTeamLosses(singleItem.getInt("losses"));
            setAbbreviation(singleItem.getString("abbreviation"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
