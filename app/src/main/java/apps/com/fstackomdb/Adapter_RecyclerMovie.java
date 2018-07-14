package apps.com.fstackomdb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_RecyclerMovie extends RecyclerView.Adapter<Adapter_RecyclerMovie.MyViewHolder>{

    private List<List_Movies> channelsList;
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewMovieName,textViewMovieYear;
        public RoundedImageView roundedImageViewMovie;

        public MyViewHolder(View view) {
            super(view);
            textViewMovieName = (TextView) view.findViewById(R.id.tv_moviename);
            textViewMovieYear = (TextView) view.findViewById(R.id.tv_movieyear);
            roundedImageViewMovie = (RoundedImageView)view.findViewById(R.id.iv_Movie);
        }
    }

    public Adapter_RecyclerMovie(List<List_Movies> moviesList, Context context) {
        this.channelsList = moviesList;
        this.context = context;
    }

    @Override
    public Adapter_RecyclerMovie.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleritem_movie, parent, false);

        return new Adapter_RecyclerMovie.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Adapter_RecyclerMovie.MyViewHolder holder, int position) {
        final List_Movies table = channelsList.get(position);
        holder.textViewMovieName.setText(table.getStringMovieName());
        holder.textViewMovieYear.setText(table.getStringMovieYear());
        Picasso.with(context).load(table.getStringMovieImage()).into(holder.roundedImageViewMovie);
    }

    @Override
    public int getItemCount() {
        return channelsList.size();
    }

    public List<List_Movies> getChannelsList() {
        return channelsList;
    }
}