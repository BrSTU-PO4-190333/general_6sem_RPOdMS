package com.example.lab06;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {
    private final String[] list = { "Dialog Activity",
            "Tabed Activity",
            "Another 1" ,
            "Another 2",
            "Another 3",
            "Another 4",
            "Another 5",
            "Another 6",
            "Another 7",
            "Another 8",
            "Another 9",
            "Another 10",
            "Another 11",
            "Another 12",
            "Another 13"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        setListAdapter(adapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(v.getContext(), DialogActivity.class);
                    startActivity(intent);
                }

                if(position == 1){
                    Intent intent1 = new Intent(v.getContext(), TabedActivity.class);
                    startActivity(intent1);
                }

                if(position >= 2){
                    Toast.makeText(getApplicationContext(),
                            parent.getItemAtPosition(position).toString(),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        };
        getListView().setOnItemClickListener(itemListener);
    }
}