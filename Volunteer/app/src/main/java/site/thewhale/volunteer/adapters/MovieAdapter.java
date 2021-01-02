package site.thewhale.volunteer.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import site.thewhale.volunteer.R;
import site.thewhale.volunteer.objects.Movies;

public class MovieAdapter extends RecyclerView.Adapter {

    ArrayList<Movies> movieList;
    Context contect;

    public MovieAdapter(ArrayList<Movies> movieList, Context contect) {
        this.movieList = movieList;
        this.contect = contect;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Connecting to special recycle view xml file;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_recycle_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).rate.setText(movieList.get(position).getRate());
        ((ViewHolder) holder).name.setText(movieList.get(position).getName());
        ((ViewHolder) holder).duration.setText(movieList.get(position).getDuration());
        ((ViewHolder) holder).img.setImageResource(movieList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView name;
        public TextView rate;
        public TextView duration;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            img = itemView.findViewById(R.id.moviePic);
            name = itemView.findViewById(R.id.movieName);
            rate = itemView.findViewById(R.id.rate);
            duration = itemView.findViewById(R.id.duration);
        }
    }
}
