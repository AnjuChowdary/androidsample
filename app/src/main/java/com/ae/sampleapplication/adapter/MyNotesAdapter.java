package com.ae.sampleapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ae.sampleapplication.R;
import com.ae.sampleapplication.model.MyNotes;

import java.util.ArrayList;
import java.util.List;

public class MyNotesAdapter extends RecyclerView.Adapter<MyNotesAdapter.MyNotesDataHolder> {

    Context context; //Reference of HomeActivity
    List<MyNotes> myNotes;

    public MyNotesAdapter(Context context, List<MyNotes> myNotes) {
        this.context = context;
        this.myNotes = myNotes;
    }

    @NonNull
    @Override
    public MyNotesAdapter.MyNotesDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Get the reference of ViewHolder
        //LayoutInflator - Service -> It will help you to create instance of xml and creates a View
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.my_notes_row, parent, false);
        return new MyNotesDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNotesAdapter.MyNotesDataHolder holder, int position) {
        //Data to UI
        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You have clicked item: "+(position+1), Toast.LENGTH_SHORT).show();
            }
        });
        holder.date.setText(myNotes.get(position).getDate());
        holder.details.setText(myNotes.get(position).getDescription());
        holder.title.setText(myNotes.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return myNotes.size();
    }

    class MyNotesDataHolder extends RecyclerView.ViewHolder {
        TextView date, title, details;
        ConstraintLayout rowLayout;
        public MyNotesDataHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_textView);
            title = itemView.findViewById(R.id.title_textView);
            details = itemView.findViewById(R.id.details_textView);
            rowLayout = itemView.findViewById(R.id.row_layout);
        }
    }
}
