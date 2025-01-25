package com.example.multipleactivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nameInput;
    RadioGroup artists;
    RadioButton taylor, selena;
    CheckBox album1, album2, album3;
    Button okBtn;
    ToggleButton resetBtn;
    String previousName = ""; // Store the previous name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.editTextText);
        artists = findViewById(R.id.radio);
        taylor = findViewById(R.id.radioButton);
        selena = findViewById(R.id.radioButton2);
        album1 = findViewById(R.id.checkBox3);
        album2 = findViewById(R.id.checkBox4);
        album3 = findViewById(R.id.checkBox5);
        okBtn = findViewById(R.id.button);
        resetBtn = findViewById(R.id.toggleButton);

        artists.setOnCheckedChangeListener((rg, checkedId) -> {
            if (checkedId == R.id.radioButton) {
                album1.setText("High Infidelity");
                album2.setText("Enchanted");
                album3.setText("August");
            } else if (checkedId == R.id.radioButton2) {
                album1.setText("Rare");
                album2.setText("Who says");
                album3.setText("999");
            }

            album1.setChecked(false);
            album2.setChecked(false);
            album3.setChecked(false);
        });

        okBtn.setOnClickListener(v -> {
            String userName = nameInput.getText().toString();
            String selectedArtist = "";
            int selectedImg = 0;

            if (taylor.isChecked()) {
                selectedArtist = taylor.getText().toString();
                selectedImg = R.drawable.taylor;
            } else if (selena.isChecked()) {
                selectedArtist = selena.getText().toString();
                selectedImg = R.drawable.selena;
            }

            StringBuilder selectedAlbums = new StringBuilder();
            if (album1.isChecked()) {
                selectedAlbums.append(album1.getText().toString()).append(", ");
            }
            if (album2.isChecked()) {
                selectedAlbums.append(album2.getText().toString()).append(", ");
            }
            if (album3.isChecked()) {
                selectedAlbums.append(album3.getText().toString()).append(", ");
            }

            if (selectedAlbums.length() > 0) {
                selectedAlbums.setLength(selectedAlbums.length() - 2); // Remove the last comma and space
            }

            // Prepare data to pass to the next activity
            Intent intent = new Intent(MainActivity.this, OutputActivity.class);
            intent.putExtra("userName", userName);
            intent.putExtra("selectedArtist", selectedArtist);
            intent.putExtra("selectedAlbums", selectedAlbums.toString());
            intent.putExtra("selectedImg", selectedImg);

            // Start ResultActivity
            startActivity(intent);

            Toast.makeText(MainActivity.this, "Hello, " + userName + ". You like " + selectedArtist + ".", Toast.LENGTH_LONG).show();
        });

        // ToggleButton functionality for Reset and Undo
        resetBtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Reset: Save the current name and then clear the field
                previousName = nameInput.getText().toString();
                nameInput.setText("");
            } else {
                // Undo: Restore the previous name if available
                if (!previousName.isEmpty()) {
                    nameInput.setText(previousName);
                } else {
                    Toast.makeText(MainActivity.this, "No name to restore", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
