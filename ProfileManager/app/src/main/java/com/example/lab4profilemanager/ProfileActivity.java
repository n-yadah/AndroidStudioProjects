package com.example.lab4profilemanager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> cameraLauncher;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private String currentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setUpImageViews();

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            // Get the URI of the selected image
                            Uri selectedImageUri = data.getData();

                            if (selectedImageUri != null) {
                                // Update the ImageView with the selected image
                                Intent resultIntent = new Intent();
                                resultIntent.putExtra("selectedImageUri", selectedImageUri.toString());
                                setResult(RESULT_OK, resultIntent);
                                finish();
                            }
                        }
                    }
                }
        );
        Button profileGalleryButton = findViewById(R.id.buttonGallery);
        profileGalleryButton.setOnClickListener(view -> openGallery());

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Image successfully captured
                        // Use currentPhotoPath to load the image from file
                        File imageFile = new File(currentPhotoPath);
                        Uri imageUri = Uri.fromFile(imageFile);

                        // Send the image URI back to MainActivity
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("selectedImageUri", imageUri.toString());
                        setResult(RESULT_OK, resultIntent);
                        finish();  // Close ProfileActivity and return to MainActivity
                    }
                }
        );

        Button profileCameraButton = findViewById(R.id.buttonCamera);
        profileCameraButton.setOnClickListener(view -> openCamera());
    }

    private void setUpImageViews(){
        int[]imageViewIds = {
                R.id.imageView1, R.id.imageView2, R.id.imageView3,
                R.id.imageView4, R.id.imageView5, R.id.imageView6

        };

        for (int id : imageViewIds){
            ImageView imageView = findViewById(id);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v){
                    onSetTeamIcon(v);
                }
            });
        }
    }

    private void onSetTeamIcon(View view) {
        // This method will handle the logic when an ImageView is clicked
        int drawableId = 0;

        if (view.getId() == R.id.imageView1) {
            drawableId = R.drawable.ic_logo_01;
        } else if (view.getId() == R.id.imageView2) {
            drawableId = R.drawable.ic_logo_02;
        } else if (view.getId() == R.id.imageView3) {
            drawableId = R.drawable.ic_logo_03;
        } else if (view.getId() == R.id.imageView4) {
            drawableId = R.drawable.ic_logo_04;
        } else if (view.getId() == R.id.imageView5) {
            drawableId = R.drawable.ic_logo_05;
        } else if (view.getId() == R.id.imageView6) {
            drawableId = R.drawable.ic_logo_00;
        }

        // Send the selected drawable resource ID back to MainActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("avatarId", drawableId);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void openCamera(){
        Intent takePictureIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        try{
            photoFile = createImageFile();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        if (photoFile != null){
            Uri photoURI = FileProvider.getUriForFile(this,
                    "com.example.lab4profilemanager.fileprovider",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            cameraLauncher.launch(takePictureIntent);
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        currentPhotoPath = image.getAbsolutePath();
        return image;

    }
    private void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }
}