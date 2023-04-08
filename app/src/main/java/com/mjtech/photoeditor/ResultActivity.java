package com.mjtech.photoeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.mjtech.photoeditor.databinding.ActivityResultBinding;

import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

    ActivityResultBinding binding;
    Uri data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        binding.ResultImageView.setImageURI(getIntent().getData());



//        data = getIntent().getData();
//        binding.ResultImageView.setImageURI(getIntent().getData());

        binding.shareBtn.setOnClickListener(v -> {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_STREAM, data);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setType("image/*");
            startActivity(intent);
        });

        binding.BackBtn.setOnClickListener(v -> {
            finish();
        });

        binding.homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

//        binding.email.setOnClickListener(v -> {
//            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
//            emailIntent.setType("application/image");
//            emailIntent.putExtra(Intent.EXTRA_STREAM, data);
//            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//        });

        binding.whatsappBtn.setOnClickListener(v -> {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            //Target whatsapp:
            shareIntent.putExtra(Intent.EXTRA_STREAM, data);
            shareIntent.setType("image/*");
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            try {
                shareIntent.setPackage("com.whatsapp");
                startActivity(shareIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                try {
                    shareIntent.setPackage("com.whatsapp.w4b");
                    startActivity(shareIntent);
                } catch (android.content.ActivityNotFoundException ex2) {
                    Toast.makeText(getApplicationContext(), "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}