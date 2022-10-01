package com.mjtech.photoeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mjtech.photoeditor.databinding.ActivityResultBinding;

import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

    ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        binding.ResultImageView.setImageURI(getIntent().getData());

    }
}