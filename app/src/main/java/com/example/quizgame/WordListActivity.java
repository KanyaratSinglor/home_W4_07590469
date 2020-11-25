package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizgame.model.WordItem;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class WordListActivity extends AppCompatActivity {
    static WordItem[] items = {
            new WordItem(R.drawable.lion, "LION"),
            new WordItem(R.drawable.cat, "CAT"),
            new WordItem(R.drawable.dolphin, "DOLPHIN"),
            new WordItem(R.drawable.dog, "DOG"),
            new WordItem(R.drawable.owl, "OWL "),
            new WordItem(R.drawable.tiger, "TIGER"),
            new WordItem(R.drawable.penguin, "PENGUIN"),
            new WordItem(R.drawable.pig, "PIG"),
            new WordItem(R.drawable.rabbit, "RABBIT"),
            new WordItem(R.drawable.koala, "KOALA"),

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        RecyclerView recyclerView =  findViewById(R.id.word_list_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(WordListActivity.this);
        recyclerView.setLayoutManager(layoutManager);




        List<WordItem> wordItems = Arrays.asList(items);




        // specify an adapter (see also next example)
        MyAdapter mAdapter = new MyAdapter(WordListActivity.this, wordItems);
        recyclerView.setAdapter(mAdapter);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        //private WordItem [] item;
        final Context mContext;
        final List<WordItem> mWords;




        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public  class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            TextView textView;
            ImageView imageView;
            WordItem item;

            public MyViewHolder(View v) {
                super(v);
                textView = v.findViewById(R.id.word_text_view);
                imageView = v.findViewById(R.id.word_image_view);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, textView.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, WordDetailActivity.class);
                        intent.putExtra("word", textView.getText());
                        intent.putExtra("image", imageView.getId());

                        String itemJson = new Gson().toJson(item);
                        intent.putExtra("itemJson", itemJson);
                        startActivity(intent);

//                        new AlertDialog.Builder(mContext)
//                                .setTitle("My Dialog")
//                                .setMessage(word)
//                                .setPositiveButton("OK", null)
//                                .show();
                    }


                });

            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(Context context, List<WordItem> mWords) {
            this.mWords = mWords;
            mContext = context;

        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            // create a new view
            View v =  (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_word, parent, false);
            MyViewHolder vh = new MyViewHolder(v);


            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            //holder.textView.setText(mDataset[position]);
            holder.imageView.setImageResource(mWords.get(position).imageResId);
            holder.textView.setText(mWords.get(position).word);
            //item.word = mWords.get(position).word;
            holder.item = mWords.get(position);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mWords.size();
        }
    }
}