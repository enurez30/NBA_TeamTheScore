package sera.com.nba_teamthescore.objects;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Player {

    @Id
    private Long id;

    private Long playerId;
    private String firstName;
    private String lastName;
    private String playerPosition;
    private int playerNumber;
    private Long teamId;

    @Generated(hash = 1052960648)
    public Player(Long id, Long playerId, String firstName, String lastName,
                  String playerPosition, int playerNumber, Long teamId) {
        this.id = id;
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerPosition = playerPosition;
        this.playerNumber = playerNumber;
        this.teamId = teamId;
    }

    @Generated(hash = 30709322)
    public Player() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPlayerPosition() {
        return this.playerPosition;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Long getTeamId() {
        return this.teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Player(JSONObject singleItem, Long teamId) {

        try {

            setPlayerId(singleItem.getLong("id"));
            setFirstName(singleItem.getString("first_name"));
            setLastName(singleItem.getString("last_name"));
            setPlayerPosition(singleItem.getString("position"));
            setPlayerNumber(singleItem.getInt("number"));
            setTeamId(teamId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
