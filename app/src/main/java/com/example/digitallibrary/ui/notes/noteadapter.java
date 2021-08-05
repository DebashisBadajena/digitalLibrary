package com.example.digitallibrary.ui.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digitallibrary.R;
import com.example.digitallibrary.ui.book.DownloadBook;
import com.example.digitallibrary.ui.book.model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class noteadapter extends FirebaseRecyclerAdapter<notemodel, noteadapter.noteviewholder> {



    public noteadapter(@NonNull @NotNull FirebaseRecyclerOptions<notemodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull noteviewholder holder, int position, @NonNull @NotNull notemodel model) {
        holder.notename.setText(model.getName());
        holder.authorname.setText(model.getAuthor());
        holder.notesize.setText(model.getSize());


        holder.notename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.notefragment,new downloadnotes(model.getBookurl(),model.getName(),model.getAuthor(),model.getSize()))
                        .addToBackStack(null).commit();

            }
        });


    }

    @NonNull
    @NotNull
    @Override
    public noteviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_note, parent, false);


        return new noteviewholder(view);

    }

    public class noteviewholder extends RecyclerView.ViewHolder {
        TextView notename, authorname, notesize;
        public noteviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            notename = itemView.findViewById(R.id.notename);
            authorname = itemView.findViewById(R.id.authorname);
            notesize = itemView.findViewById(R.id.notesize);


        }
    }


}
