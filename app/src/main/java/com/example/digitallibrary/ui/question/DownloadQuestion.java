package com.example.digitallibrary.ui.question;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.digitallibrary.ui.book.BookFragment;
import com.example.digitallibrary.viewPdf;


public class DownloadQuestion extends Fragment {
    Activity context;
    String qurl;
    TextView viewQuestion, downloadQuestion;
    String  bookurl, year, sem;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public DownloadQuestion() {
    }

    public DownloadQuestion( String bookurl, String year, String sem) {


        this.bookurl = bookurl;
        this.year = year;
        this.sem = sem;
    }

    public static DownloadQuestion newInstance(String param1, String param2) {
        DownloadQuestion fragment = new DownloadQuestion();
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
        View view = inflater.inflate(R.layout.fragment_download_question, container, false);

        context=getActivity();
        ImageView questionprofile=view.findViewById(R.id.dtlsQuestionprofile);
        TextView questionYear=view.findViewById(R.id.dtlsQuestionYear);
        TextView questionsem=view.findViewById(R.id.dtlsSem);

        questionYear.setText(year);
        questionsem.setText(sem);

         qurl=bookurl;

         viewQuestion=view.findViewById(R.id.viewquestiondocs);
         downloadQuestion=view.findViewById(R.id.questiondownload);

         viewQuestion.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent = new Intent(getContext(), viewPdf.class);
                 intent.putExtra("url", qurl);
                 intent.putExtra("name", sem);
                 startActivity(intent);


             }


         });

        downloadQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Downloading....",Toast.LENGTH_LONG).show();
                startdownloading();
                Toast.makeText(getActivity(),"Download complet",Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }



    private void startdownloading() {

        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(qurl));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI|DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(year+sem);
        request.setDescription("Downloading files.....");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,year+sem+".pdf");

        DownloadManager downloadManager=(DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);



    }



}