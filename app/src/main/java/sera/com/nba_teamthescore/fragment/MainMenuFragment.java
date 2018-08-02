package sera.com.nba_teamthescore.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import sera.com.nba_teamthescore.R;
import sera.com.nba_teamthescore.activity.MasterActivity;
import sera.com.nba_teamthescore.adapter.TeamsRecyclerAdapter;
import sera.com.nba_teamthescore.datasource.TeamDataSource;


public class MainMenuFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private TeamsRecyclerAdapter adapter;
    private MasterActivity masterActivity;
    private com.github.clans.fab.FloatingActionButton sortByWins;
    private com.github.clans.fab.FloatingActionButton sortByLosses;
    private int sortByWin = 0;
    private int sortByLost = 0;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_menu, container, false);


        masterActivity = (MasterActivity) getActivity();
        recyclerView = v.findViewById(R.id.recycler_view);
        sortByWins = v.findViewById(R.id.sort_by_wins);
        sortByLosses = v.findViewById(R.id.sort_by_losses);
        adapter = new TeamsRecyclerAdapter(getContext(), this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.beautiful_green));
        masterActivity.setSupportActionBar(toolbar);
        final ActionBar ab = masterActivity.getSupportActionBar();
        Objects.requireNonNull(ab).setDisplayHomeAsUpEnabled(true);

        sortByWins.setOnClickListener(this);
        sortByLosses.setOnClickListener(this);

        return v;
    }

    public void moveToNextFragment(Long id) {
        masterActivity.openFragment(TeamPageFragment.newInstance(id),true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sort_by_wins:
                if (sortByWin == 0) {
                    sortByWin++;
                    adapter.addItems(TeamDataSource.getInstance().getTeamsByWinDescending());
                } else {
                    sortByWin--;
                    adapter.addItems(TeamDataSource.getInstance().getTeamsByWinAcending());
                }
                break;

            case R.id.sort_by_losses:
                if (sortByLost == 0) {
                    sortByLost++;
                    adapter.addItems(TeamDataSource.getInstance().getTeamsByLostDescending());

                } else {
                    sortByLost--;
                    adapter.addItems(TeamDataSource.getInstance().getTeamsByWLostAcending());
                }
                break;
        }
    }

    public void resetList() {
        sortByWins.setRotation(180);
        sortByLosses.setRotation(180);
        adapter.addItems(TeamDataSource.getInstance().getTeams());
    }
}
