package com.example.commondialog;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.commondialog.commondialog.CommonDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View viewById = findViewById(R.id.btn);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonDialog.Builder builder1 = new CommonDialog.Builder(MainActivity.this);
                CommonDialog commonDialog = builder1.setContentView(R.layout.layout_dialog_test2)
                        .fullWidth(true)
                        .setClickListener(R.id.button_copy, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "button_copy", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setClickListener(R.id.button_overwrite, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "button_overwrite", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .bottom(true)
                        .setAnimation(R.style.anim_bottom_in)
                        .create();
                commonDialog.show();
                ((TextView)commonDialog.getView(R.id.button_copy)).setTextColor(Color.parseColor("#FF0000"));
            }

        });

    }
}
