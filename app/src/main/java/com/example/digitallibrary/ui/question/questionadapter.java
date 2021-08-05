package com.example.digitallibrary.ui.question;

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
import com.example.digitallibrary.ui.notes.downloadnotes;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class questionadapter extends FirebaseRecyclerAdapter<questionmodel,questionadapter.questionViewHolder>
{



    public questionadapter(@NonNull @NotNull FirebaseRecyclerOptions<questionmodel> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull @NotNull questionViewHolder holder, int position, @NonNull @NotNull questionmodel model) {
    holder.year.setText(model.getYear());
    holder.sem.setText(model.getSem());

        holder.year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentQuestion,new DownloadQuestion(model.getBookurl(),model.getSem(),model.getYear()),null)
                        .addToBackStack(null).commit();

            }
        });



    }




    @NonNull
    @NotNull
    @Override
    public questionViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_question,parent,false);

        return new questionViewHolder(view);

    }




    public class  questionViewHolder extends RecyclerView.ViewHolder
    {
       ImageView  question_profile;
       TextView year,sem;

        public questionViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            question_profile=itemView.findViewById(R.id.question_profile);
            year=itemView.findViewById(R.id.year);
            sem=itemView.findViewById(R.id.sem);

        }
    }
}
