package com.example.lab05;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter<HelperAdapter.MyViewClass>  {
    private ArrayList<String> username;
    private ArrayList<Integer> score;
    private ArrayList<Player> players;
    private Context context;

    public class MyViewClass extends RecyclerView.ViewHolder{
        TextView username;
        TextView score;
        public MyViewClass(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            score = itemView.findViewById(R.id.score);
        }
    }

    public HelperAdapter(ArrayList<String> username, ArrayList<Integer> score,  ArrayList<Player> players, Context context) {
        this.username = username;
        this.score = score;
        this.players = players;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        MyViewClass myViewClass=new MyViewClass(view);
        return myViewClass;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewClass holder, @SuppressLint("RecyclerView") int position) {
        holder.username.setText(username.get(position));
        holder.score.setText(String.valueOf(score.get(position)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra("rank", players.get(position).getRank());
                intent.putExtra("username", players.get(position).getUsername());
                intent.putExtra("rating", players.get(position).getRating());
                intent.putExtra("score", players.get(position).getScore());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return username.size();
    }
}
