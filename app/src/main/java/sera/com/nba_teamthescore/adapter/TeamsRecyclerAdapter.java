package sera.com.nba_teamthescore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;

import java.util.List;
import java.util.Objects;

import sera.com.nba_teamthescore.NBA_TeamTheScoreApplication;
import sera.com.nba_teamthescore.R;
import sera.com.nba_teamthescore.datasource.TeamDataSource;
import sera.com.nba_teamthescore.fragment.MainMenuFragment;
import sera.com.nba_teamthescore.objects.Team;

import static sera.com.nba_teamthescore.utils.Utils.SVG_SUFFIX;
import static sera.com.nba_teamthescore.utils.Utils.SVG_URL;

public class TeamsRecyclerAdapter extends RecyclerView.Adapter<TeamsRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Team> teamList;
    private MainMenuFragment parent;

    public TeamsRecyclerAdapter(Context context, MainMenuFragment mainMenuFragment) {
        this.context = context;
        teamList = TeamDataSource.getInstance().getTeams();
        this.parent = mainMenuFragment;
    }


    @NonNull
    @Override
    public TeamsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = Objects.requireNonNull(mLayoutInflater).inflate(R.layout.team_single_line, viewGroup, false);
        return new TeamsRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsRecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView team_logo;
        TextView team_name;
        TextView team_wins;
        TextView team_losses;

        public ViewHolder(final View itemView) {
            super(itemView);
            team_logo = itemView.findViewById(R.id.team_logo);
            team_name = itemView.findViewById(R.id.team_name);
            team_wins = itemView.findViewById(R.id.team_wins);
            team_losses = itemView.findViewById(R.id.team_losses);
        }

        private void bind(int position) {
            team_name.setText(teamList.get(position).getTeamName());
            team_wins.setText(String.valueOf(teamList.get(position).getTeamWins()));
            team_losses.setText(String.valueOf(teamList.get(position).getTeamLosses()));

            SvgLoader.pluck()
                    .with(NBA_TeamTheScoreApplication.getInstance().getMasterActivity())
                    .load(SVG_URL + teamList.get(position).getAbbreviation() + SVG_SUFFIX, team_logo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    parent.moveToNextFragment(teamList.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public void addItems(List<Team> list) {
        teamList.clear();
        teamList.addAll(list);
        notifyDataSetChanged();
    }
}
