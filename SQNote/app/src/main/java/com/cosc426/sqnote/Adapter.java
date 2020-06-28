package com.cosc426.sqnote;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Note> notes;

    public Adapter(Context context, List<Note> notes) {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //Use custom view
        View view = inflater.inflate(R.layout.list_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        String title = notes.get(position).getTitle();
        String date = notes.get(position).getDate();
        String time = notes.get(position).getTime();

        holder.noteTitle.setText(title);
        holder.noteDate.setText(date);
        holder.noteTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView noteTitle, noteDate, noteTime;

        public ViewHolder(View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.noteTitle);
            noteDate = itemView.findViewById(R.id.noteDate);
            noteTime = itemView.findViewById(R.id.noteTime);

            //On click listener for notes in view, uses extra for note ID
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent updateActivity = new Intent(v.getContext(), Contents.class);
                    updateActivity.putExtra("ID", notes.get(getAdapterPosition()).getID());
                    v.getContext().startActivity(updateActivity);
                    //Toast.makeText(v.getContext(), "item clicked!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
