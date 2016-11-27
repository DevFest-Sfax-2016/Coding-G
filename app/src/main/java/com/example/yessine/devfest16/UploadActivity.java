package com.example.yessine.devfest16;

/**
 * Created by Hamdi Rekik on 04/02/2016.
 */


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UploadActivity extends Activity {



    private ProgressBar progressBar;
    private String filePath = null;
    private TextView txtPercentage;
    private ImageView imgPreview;
    private EditText namespace;
    private EditText descspace;
    private Button btnUpload;
    long totalSize = 0;
    String nom = "";
    String desc = "";
    double lat ;
    double lng ;
    GPSTracker gps;
    String lien ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gps = new GPSTracker(UploadActivity.this);
        setContentView(R.layout.activity_upload);
        txtPercentage = (TextView) findViewById(R.id.txtPercentage);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imgPreview = (ImageView) findViewById(R.id.imgPreview);
        namespace = (EditText) findViewById(R.id.editText1);
        descspace = (EditText) findViewById(R.id.editText2);

        // Changing action bar background color
        // getActionBar().setBackgroundDrawable(
        // new ColorDrawable(Color.parseColor(getResources().getString(
        // R.color.action_bar))));

        // Receiving the data from previous activity
        Intent i = getIntent();

        // image or video path that is captured in previous activity
        filePath = i.getStringExtra("filePath");
        gps = new GPSTracker(this);

    }
}
