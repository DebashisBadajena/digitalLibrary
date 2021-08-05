package com.example.digitallibrary.ui.notes;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.digitallibrary.R;
import com.example.digitallibrary.viewPdf;


public class downloadnotes extends Fragment {

   Activity context;
   String nurl;
    private static final int PERMISSION_STORAGE_CODE =1000 ;

    TextView viewnotes,downloadnotes;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    String bookurl,name,author,size;
    public downloadnotes() {

    }



    public downloadnotes(String bookurl, String name, String author, String size) {


        this.bookurl = bookurl;
        this.name = name;
        this.author = author;
        this.size = size;
    }



    public static downloadnotes newInstance(String param1, String param2) {
        downloadnotes fragment = new downloadnotes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    context=getActivity();
         View view=inflater.inflate(R.layout.fragment_downloadnotes, container, false);

        TextView notename=view.findViewById(R.id.notenamedtls);
        TextView authorname=view.findViewById(R.id.dtlsnoteauthor);
        TextView notesize=view.findViewById(R.id.dtlsnotesize);


        notename.setText(name);
        authorname.setText(author);
        notesize.setText(size);

        nurl=bookurl;

        viewnotes=view.findViewById(R.id.viewnotedocs);
        downloadnotes=view.findViewById(R.id.notedownload);

        viewnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Loading....",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), viewPdf.class);
                intent.putExtra("url", nurl);
                intent.putExtra("name", name);
                startActivity(intent);

            }
        });

        downloadnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Downloading....",Toast.LENGTH_LONG).show();

                startDownloading();
                Toast.makeText(getActivity(),"Download complet",Toast.LENGTH_LONG).show();
            }
        });

         return view;
    }

    private void startDownloading() {

        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(nurl));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI|DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(name);
        request.setDescription("Downloading files.....");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,name+".pdf");

        DownloadManager downloadManager=(DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }
}