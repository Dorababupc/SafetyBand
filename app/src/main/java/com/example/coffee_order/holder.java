package com.example.coffee_order;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class holder extends RecyclerView.ViewHolder {
    CircleImageView img;
    TextView name,course,email;
    public holder(@NonNull View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.profile_image);
        name=itemView.findViewById(R.id.name);
        course=itemView.findViewById(R.id.course);
        email=itemView.findViewById(R.id.email);
    }
}
