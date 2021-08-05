package com.example.digitallibrary.ui.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibrary.R;
import com.example.digitallibrary.databinding.FragmentNotesBinding;
import com.example.digitallibrary.ui.book.bookadaptor;
import com.example.digitallibrary.ui.book.model;
import com.example.digitallibrary.ui.question.questionadapter;
import com.example.digitallibrary.ui.question.questionmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class NotesFragment extends Fragment {


    private FragmentNotesBinding binding;

    RecyclerView recviewnote;
    noteadapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentNotesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        recviewnote = root.findViewById(R.id.recviewnotes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recviewnote.setLayoutManager(linearLayoutManager);


        FirebaseRecyclerOptions<notemodel> options =
                new FirebaseRecyclerOptions.Builder<notemodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("notes"), notemodel.class)
                        .build();

        adapter = new noteadapter(options);
        recviewnote.setAdapter(adapter);





        return root;
    }



    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}