package com.example.lab4profilemanager;

import static androidx.activity.result.ActivityResultLauncher.*;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> avatarLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView logoImageView = findViewById(R.id.logoImageView);
        avatarLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result ->{
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if (data != null) {
                            int avatarId = data.getIntExtra("avatarId", 0);
                            String imageUriString = data.getStringExtra("selectedImageUri");

                            if (avatarId != 0) {
                                logoImageView.setImageResource(avatarId);
                            } else if (imageUriString != null) {
                                // If an image was selected from the gallery, display it
                                Uri imageUri = Uri.parse(imageUriString);
                                logoImageView.setImageURI(imageUri);
                            }
                        }
                    }
                });
        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSetAvatarButton(view);
            }
        });


    }
    public void onOpenInGoogleMaps (View view){
        EditText teamAddress = (EditText) findViewById(R.id.editLocation);

        //Create a Uri fro an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q="+teamAddress.getText());

        //Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        //Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

        //Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);
    }
    public void onSetAvatarButton (View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        avatarLauncher.launch(intent);
    }


}