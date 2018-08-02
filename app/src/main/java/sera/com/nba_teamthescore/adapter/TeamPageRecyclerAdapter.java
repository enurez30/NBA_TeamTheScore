package sera.com.nba_teamthescore.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import sera.com.nba_teamthescore.R;
import sera.com.nba_teamthescore.objects.Player;
import sera.com.nba_teamthescore.objects.Team;

import static sera.com.nba_teamthescore.utils.Utils.PLAYER_IMG_URL;
import static sera.com.nba_teamthescore.utils.Utils.SEPARATOR;

public class TeamPageRecyclerAdapter extends RecyclerView.Adapter<TeamPageRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Player> playerList;

    public TeamPageRecyclerAdapter(Context context, Team team) {
        this.context = context;
        playerList = team.getPlayerList();
    }


    @NonNull
    @Override
    public TeamPageRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = Objects.requireNonNull(mLayoutInflater).inflate(R.layout.team_page_single_line, viewGroup, false);
        return new TeamPageRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamPageRecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView player_pic;
        TextView player_name;
        TextView player_second_name;
        TextView player_position;
        TextView number;

        public ViewHolder(final View itemView) {
            super(itemView);

            player_pic = itemView.findViewById(R.id.player_pic);
            player_name = itemView.findViewById(R.id.player_name);
            player_second_name = itemView.findViewById(R.id.player_second_name);
            player_position = itemView.findViewById(R.id.position);
            number = itemView.findViewById(R.id.number);
        }

        private void bind(int position) {
            player_name.setText(playerList.get(position).getFirstName());
            player_second_name.setText(playerList.get(position).getLastName());
            player_position.setText(playerList.get(position).getPlayerPosition());
            Typeface custom = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "Android 101.ttf");
            number.setTypeface(custom);
            number.setText(String.valueOf(playerList.get(position).getPlayerNumber()));

            Picasso.with(context)
                    .load(PLAYER_IMG_URL + playerList.get(position).getLastName() + SEPARATOR + playerList.get(position).getFirstName())
                    .error(R.drawable.no_photo_250px_new)
                    .into(player_pic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }

}
