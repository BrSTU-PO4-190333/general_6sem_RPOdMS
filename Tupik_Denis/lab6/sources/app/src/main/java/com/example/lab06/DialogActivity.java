package com.example.lab06;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {
    private Button bgButton;
    private RelativeLayout relativeLayout;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        bgButton = findViewById(R.id.background_button);
        relativeLayout = findViewById(R.id.relativelayout);
        context = DialogActivity.this;

        bgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.change_color);
                final CharSequence[] items = {
                        getText(R.string.red), getText(R.string.yellow), getText(R.string.green)
                };

                builder.setItems(items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        relativeLayout.setBackgroundResource(R.color.redColor);
                                        Toast.makeText(getApplicationContext(), "Red color",Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
                                        relativeLayout.setBackgroundResource(R.color.yellowColor);
                                        Toast.makeText(getApplicationContext(), "Yellow color",Toast.LENGTH_SHORT).show();
                                        break;
                                    case 2:
                                        relativeLayout.setBackgroundResource(R.color.greenColor);
                                        Toast.makeText(getApplicationContext(), "Green color",Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        }
                );

                AlertDialog alert = builder.create();
                alert.show();
            }
        });



    }
}