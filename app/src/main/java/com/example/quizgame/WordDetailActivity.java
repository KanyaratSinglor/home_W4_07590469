package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizgame.model.WordItem;
import com.google.gson.Gson;

public class WordDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);

        Intent intent = getIntent();
        String jsonItem = intent.getStringExtra("itemJson");
        WordItem item =  new Gson().fromJson(jsonItem, WordItem.class);

        ImageView imageView = findViewById(R.id.image_view_word);
        TextView textView = findViewById(R.id.text_view_word);

        textView.setText(item.word);
        imageView.setImageResource(item.imageResId);


    }
}