package com.example.coffee_order;

import android.gesture.GestureLibraries;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class myAdapter extends FirebaseRecyclerAdapter<Model,holder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    FirebaseRecyclerOptions<Model> options;
    public myAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
        this.options=options;
    }

    @Override
    protected void onBindViewHolder(@NonNull holder holder, int position, @NonNull Model model) {
        holder.course.setText(model.getCourse());
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new holder(view);
    }
}
