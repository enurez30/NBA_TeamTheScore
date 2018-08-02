package sera.com.nba_teamthescore.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import sera.com.nba_teamthescore.NBA_TeamTheScoreApplication;
import sera.com.nba_teamthescore.R;
import sera.com.nba_teamthescore.datasource.PlayerDataSource;
import sera.com.nba_teamthescore.datasource.TeamDataSource;
import sera.com.nba_teamthescore.dialog.ConfirmationAlertDialog;
import sera.com.nba_teamthescore.fragment.MainMenuFragment;
import sera.com.nba_teamthescore.objects.Player;
import sera.com.nba_teamthescore.objects.Team;
import sera.com.nba_teamthescore.utils.AppPref;
import sera.com.nba_teamthescore.utils.DialogUtils;

public class MasterActivity extends AppCompatActivity {

    private FrameLayout container;
    private FragmentManager mFragmentManager;
    private MainMenuFragment mainMenuFragment;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_master);
        NBA_TeamTheScoreApplication.getInstance().setMasterActivity(this);
        container = findViewById(R.id.container);
        if (AppPref.isFirstTime()) {
            updateDataBase();
        } else {
            mainMenuFragment = new MainMenuFragment();
            openFragment(mainMenuFragment, false);
        }
    }


    public void openFragment(Fragment fragment, boolean isAnimate) {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction tr = mFragmentManager.beginTransaction();
        if (isAnimate) {
            tr.setCustomAnimations(R.anim.right_slide_in, R.anim.right_slide_out);
        }
        tr.add(R.id.container, fragment, fragment.getTag())
                .addToBackStack(fragment.getTag())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.reset:
                if (mFragmentManager.getBackStackEntryCount() == 1) {
                    mainMenuFragment.resetList();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void updateDataBase() {
        final List<Team> team_object_list = new ArrayList<>();
        final List<Player> player_object_list = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < arr.length(); i++) {
                JSONObject team_object = arr.getJSONObject(i);
                team_object_list.add(new Team(team_object));
                JSONArray array = team_object.getJSONArray("players");
                for (int j = 0; j < array.length(); j++) {
                    JSONObject player_object = array.getJSONObject(j);
                    player_object_list.add(new Player(player_object, team_object.getLong("id")));
                }
            }

            TeamDataSource.getInstance().insertOrReplaceInTx(team_object_list);
            PlayerDataSource.getInstance().insertOrReplaceInTx(player_object_list);

            AppPref.setFirstTime(false);
            openFragment(new MainMenuFragment(), false);

            Log.d("", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("input.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onBackPressed() {
        if (mFragmentManager.getBackStackEntryCount() > 1) {
            mFragmentManager.popBackStack();
        } else {
            DialogUtils.showConfirmationDialog(
                    getString(R.string.exit_notification),
                    getString(R.string.exit_info),
                    getString(R.string.exit),
                    new ConfirmationAlertDialog.Listener() {
                        @Override
                        public void onPositiveActionSelected() {
                            finish();
                        }

                        @Override
                        public void onNegativeActionSelected() {

                        }
                    }

            );
        }
    }
}
