package io.github.Pavel_Innokentevich_Galanin.gpi_rpodms6_lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private String JSON_URI = "https://api.github.com/search/users?q=Pa";
    private ArrayList<HashMap<String, String>> userLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLists = new ArrayList<>();
        lv = findViewById(R.id.lv);

        GetUrlData class_instance;
        class_instance = new GetUrlData();
        class_instance.execute(JSON_URI);
    }

    private class GetUrlData extends AsyncTask<String, String, String> {

//        protected void onPreExecute() {
//            super.onPreExecute();
//        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }

                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonResponse = new JSONObject(result);
                JSONArray jsonItems = jsonResponse.getJSONArray("items");
                for (int i = 0; i < jsonItems.length(); ++i) {
                    JSONObject jsonArrayItem = jsonItems.getJSONObject(i);
                    String user__login = jsonArrayItem.getString("login");
//                    String user__avatar_url = jsonArrayItem.getString("avatar_url");
//                    String user__html_url = jsonArrayItem.getString("html_url");

                    HashMap<String, String> item = new HashMap<>();
                    item.put("user__index", Integer.toString(i + 1));
                    item.put("user__login", user__login);
                    userLists.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this,
                    userLists,
                    R.layout.user_item,
                    new String[] {"user__index", "user__login"},
                    new int[] { R.id.user__index, R.id.user__login }
            );
            lv.setAdapter(adapter);
        }
    }
}