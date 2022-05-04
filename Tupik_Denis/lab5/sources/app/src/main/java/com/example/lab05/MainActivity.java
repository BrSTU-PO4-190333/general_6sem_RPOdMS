package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String tournamentURL = "https://lichess.org/api/tournament/";
    private Button btnDownloadImage;
    private Button btnReadJSON;
    private EditText id;
    private EditText readJSON;
    private RecyclerView recyclerView;
    private ArrayList<String> username = new ArrayList<>();
    private ArrayList<Integer> score = new ArrayList<>();
    private ArrayList<Player> players;
    private final int requestCode = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownloadImage = findViewById(R.id.btnDownloadImage);
        btnReadJSON = findViewById(R.id.btnReadJSON);
        id = (EditText) findViewById(R.id.id_tour);
        readJSON = (EditText) findViewById(R.id.read_json);
        recyclerView = findViewById(R.id.recyclerView);


        btnDownloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile(tournamentURL + id.getText() + "/results", id.getText() + ".ndjson");
            }
        });

        btnReadJSON.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                readJSON();
            }
        });
    }


    private void downloadFile(String url, String outputFileName) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(outputFileName);
        request.setDescription("Downloading " + outputFileName);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, outputFileName);
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        long enqueue = manager.enqueue(request);
    }

    private void readJSON() {
        @SuppressLint("SdCardPath") File myFile = new File("/sdcard/Download/" + readJSON.getText().toString());
        players = new ArrayList<>();

        try {
            FileInputStream inputStream = new FileInputStream(myFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{\"players\":[");
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append(",");
                }
                stringBuilder.setLength(stringBuilder.length() - 1);
                stringBuilder.append("]}");

                String json = String.valueOf(stringBuilder);
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("players");

                String result = "Tournament results\n";
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject playerInfo = jsonArray.getJSONObject(i);
                    username.add(playerInfo.getString("username"));
                    score.add(playerInfo.getInt("score"));

                    players.add(new Player(
                            playerInfo.getInt("rank"),
                            playerInfo.getInt("score"),
                            playerInfo.getInt("rating"),
                            playerInfo.getString("username")
                    ));
                }

                HelperAdapter helperAdapter = new HelperAdapter(username,score,players, MainActivity.this);
                recyclerView.setAdapter(helperAdapter);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}