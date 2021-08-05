package com.example.digitallibrary.ui.book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digitallibrary.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class bookadaptor extends FirebaseRecyclerAdapter<model, bookadaptor.bookviewholder> {



    public bookadaptor(@NonNull @NotNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull bookviewholder holder, int position, @NonNull @NotNull model model) {

        holder.bookname.setText(model.getName());
        holder.authorname.setText(model.getAuthor());
        holder.booksize.setText(model.getSize());

        holder.bookname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.bookfragment,new DownloadBook(model.getBookurl(),model.getName(),model.getAuthor(),model.getSize()))
                        .addToBackStack(null).commit();


            }
        });


    }


    @NonNull
    @NotNull
    @Override
    public bookviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_book, parent, false);
        return new bookviewholder(view);
    }


    public class bookviewholder extends RecyclerView.ViewHolder {
        TextView bookname, authorname, booksize;

        public bookviewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            bookname = itemView.findViewById(R.id.bookname);
            authorname = itemView.findViewById(R.id.authorname);
            booksize = itemView.findViewById(R.id.booksize);


        }
    }
}
