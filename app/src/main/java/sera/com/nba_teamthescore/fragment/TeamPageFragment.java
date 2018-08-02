package sera.com.nba_teamthescore.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;

import java.util.Objects;

import sera.com.nba_teamthescore.NBA_TeamTheScoreApplication;
import sera.com.nba_teamthescore.R;
import sera.com.nba_teamthescore.activity.MasterActivity;
import sera.com.nba_teamthescore.adapter.TeamPageRecyclerAdapter;
import sera.com.nba_teamthescore.datasource.TeamDataSource;
import sera.com.nba_teamthescore.objects.Team;
import sera.com.nba_teamthescore.objects.TeamDao;

import static sera.com.nba_teamthescore.utils.Utils.SVG_SUFFIX;
import static sera.com.nba_teamthescore.utils.Utils.SVG_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamPageFragment extends Fragment {

    private MasterActivity masterActivity;
    private ImageView team_logo;
    private TextView team_name;
    private TextView team_wins;
    private TextView team_losses;
    private Long id;
    private Team team;
    private RecyclerView recyclerView;
    private TeamPageRecyclerAdapter adapter;

    public TeamPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getLong("id");
        }
    }

    public static TeamPageFragment newInstance(Long id) {
        TeamPageFragment fragment = new TeamPageFragment();
        Bundle args = new Bundle();
        args.putLong("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team_page, container, false);
        masterActivity = (MasterActivity) getActivity();
        team_logo = v.findViewById(R.id.team_logo);
        team_name = v.findViewById(R.id.team_name);
        team_wins = v.findViewById(R.id.team_wins);
        team_losses = v.findViewById(R.id.team_losses);
        recyclerView = v.findViewById(R.id.recycler_view);
        team = TeamDataSource.getInstance().queryBuilder().where(TeamDao.Properties.Id.eq(id)).unique();
        adapter = new TeamPageRecyclerAdapter(getContext(), team);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.beautiful_green));
        masterActivity.setSupportActionBar(toolbar);
        final ActionBar ab = masterActivity.getSupportActionBar();
        Objects.requireNonNull(ab).setDisplayHomeAsUpEnabled(true);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        team_name.setText(team.getTeamName());
        team_wins.setText(String.valueOf(team.getTeamWins()));
        team_losses.setText(String.valueOf(team.getTeamLosses()));
        SvgLoader.pluck()
                .with(NBA_TeamTheScoreApplication.getInstance().getMasterActivity())
                .load(SVG_URL + team.getAbbreviation() + SVG_SUFFIX, team_logo);
    }
}
