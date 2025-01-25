package com.example.multipleactivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OutputActivity extends AppCompatActivity {

    ImageView albumPic;
    TextView result;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        albumPic = findViewById(R.id.imageView);
        result = findViewById(R.id.textView2);
        backBtn = findViewById(R.id.button2);

        // Retrieve data from the intent
        String userName = getIntent().getStringExtra("userName");
        String selectedArtist = getIntent().getStringExtra("selectedArtist");
        String selectedAlbums = getIntent().getStringExtra("selectedAlbums");
        int selectedImg = getIntent().getIntExtra("selectedImg", 0);

        // Set the result text
        String resultMessage = userName + "'s favorite artist is " + selectedArtist + " and their songs " + selectedAlbums + ".";
        result.setText(resultMessage);

        // Set the album image
        if (selectedImg != 0) {
            albumPic.setImageResource(selectedImg);
        }

        // Handle the Back button click
        backBtn.setOnClickListener(v -> {
            finish(); // Finish the current activity and return to MainActivity
        });
    }
}
