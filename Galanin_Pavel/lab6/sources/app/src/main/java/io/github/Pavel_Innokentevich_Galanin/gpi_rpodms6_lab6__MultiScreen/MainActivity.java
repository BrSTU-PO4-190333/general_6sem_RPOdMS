package io.github.Pavel_Innokentevich_Galanin.gpi_rpodms6_lab6__MultiScreen;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends ListActivity {
    private ArrayAdapter<String> adapter;
    private String[] islands = {
            "Канары",
            "Курилы",
            "Мальдивы",
            "Филиппины",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, islands);
        setListAdapter(adapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch(position) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, Canari.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, Curili.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, Maldivi.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, Philippini.class);
                        startActivity(intent3);
                        break;
                }
                Toast.makeText(getApplicationContext(),
                    "Вы выбрали " + parent.getItemAtPosition(position).toString(),
                    Toast.LENGTH_SHORT).show();
            }
        };
        getListView().setOnItemClickListener(itemListener);
    }
}