package com.hbb.android_multiselect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    TextView tvPiping;
    boolean[] selectedPipe;
    ArrayList<Integer> ListPiping = new ArrayList<>();
    String[] ListPipeItems = {"value 1", "value 2", "value 3", "value 4", "value 5", "value 6"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // assign variable
        tvPiping = findViewById(R.id.textView);

        // initialize selected pipe array
        selectedPipe = new boolean[ListPipeItems.length];

        tvPiping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // set title
                builder.setTitle("Select Item");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(ListPipeItems, selectedPipe, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang ListPiping
                            ListPiping.add(i);
                            // Sort array list
                            Collections.sort(ListPiping);
                        } else {
                            // when checkbox unselected
                            // Remove position from ListPiping
                            ListPiping.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < ListPiping.size(); j++) {
                            // concat array value
                            stringBuilder.append(ListPipeItems[ListPiping.get(j)]);
                            // check condition
                            if (j != ListPiping.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // Seçilen item'ı dropDown'a set ettim
                        tvPiping.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });

                // show dialog
                builder.show();
            }
        });
    }

}