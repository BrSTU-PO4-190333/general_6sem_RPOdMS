package com.example.lab4json;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText user_field;
    private Button main_btn;
    private TextView result_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_field = findViewById(R.id.user_field); // ссылка на объект
        main_btn = findViewById(R.id.main_btn);
        result_info = findViewById(R.id.result_info);

        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_field.getText().toString().trim().equals(""))
                    Toast.makeText(MainActivity.this,R.string.no_user_input, Toast.LENGTH_LONG).show(); //Всплывающее окно
                else {
                    String city = user_field.getText().toString();
                    String key = "12ba88a83351c9466ed2a48a06bdd2c8";
                    String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city +"&appid="+ key +"&units=metric";

                    new GetURLData().execute(url);
                }
            }
        });
    }

    private class GetURLData extends AsyncTask<String, String, String > {

        protected  void onPreExecute(){ //Как только нажали кнопку и ждем результат
            super.onPreExecute();
            result_info.setText("Ожидайте...");
        }

        @Override
        protected String doInBackground(String... strings) { //ссылаемся по URL адресу
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);// обращаемся по URL адресу
                connection = (HttpURLConnection) url.openConnection(); //открываем соединение
                connection.connect();

                InputStream stream = connection.getInputStream();// считали поток
                reader = new BufferedReader(new InputStreamReader(stream));//считываем в формате строки

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while((line = reader.readLine()) != null) // считываем информацию по URL адресу по 1 линии
                    buffer.append(line).append("\n");

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally { //проверяем если у нас что-то осталось работать то прекращаем
                if(connection != null)
                    connection.disconnect();

                try{
                    if(reader != null)
                        reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }

            }

            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected  void onPostExecute(String result){ //метод когда получили инфу и выводим
            super.onPostExecute(result);
        // Делаем чтобы выводилось красиво а не весь JSON
            try {
                JSONObject jsonObject = new JSONObject(result);
                result_info.setText("Температура: " + jsonObject.getJSONObject("main").getDouble("temp"));//выводим в на экран
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}