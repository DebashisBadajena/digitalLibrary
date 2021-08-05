package com.example.digitallibrary.ui.book;

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
import com.example.digitallibrary.databinding.FragmentBookBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BookFragment extends Fragment {


    private FragmentBookBinding binding;

    RecyclerView recviewBook;
    bookadaptor adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentBookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recviewBook = root.findViewById(R.id.recviewBook);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recviewBook.setLayoutManager(linearLayoutManager);




        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("books"), model.class)
                        .build();

        adapter = new bookadaptor(options);
        recviewBook.setAdapter(adapter);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
//        recviewBook.setLayoutManager(gridLayoutManager);



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