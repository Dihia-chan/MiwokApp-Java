package com.example.asus.miwok;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    MediaPlayer mp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>() ;

        words.add(new Word("red", "weṭeṭṭi" , R.drawable.color_red , R.raw.color_red));
        words.add(new Word("mustard yellow", "chiwiiṭә" , R.drawable.color_mustard_yellow , R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow , R.raw.color_dusty_yellow));
        words.add(new Word("green", "chokokki", R.drawable.color_green , R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown , R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray , R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black , R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white , R.raw.color_white));

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter = new WordAdapter(this, words , R.color.category_colors);
        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);
        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (mp != null) {
                    // Regardless of the current state of the media player, release its resources
                    // because we no longer need it.
                    mp.release();

                    // Set the media player back to null. For our code, we've decided that
                    // setting the media player to null is an easy way to tell that the media player
                    // is not configured to play an audio file at the moment.
                    mp = null;
                }
                // Get the {@link Word} object at the given position the user clicked on
                Word w = words.get(position) ;
                mp = MediaPlayer.create(ColorsActivity.this, w.getMedia() );
                mp.start();

                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (mp != null) {
                            // Regardless of the current state of the media player, release its resources
                            // because we no longer need it.
                            mp.release();

                            // Set the media player back to null. For our code, we've decided that
                            // setting the media player to null is an easy way to tell that the media player
                            // is not configured to play an audio file at the moment.
                            mp = null;
                        }
                    }
                });
            }
        });

    }
}
