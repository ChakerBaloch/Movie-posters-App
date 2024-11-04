package com.example.movieposters;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// MainActivity class to display movie posters and manage watchlist actions
public class MainActivity extends AppCompatActivity implements PostersListener {

    private Button buttonAddToWatchList; // Button to add selected posters to the watchlist

    /**
     * Initializes activity and sets up layout, buttons, and data.
     *
     * @param savedInstanceState Data from a previous instance, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set padding for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // RecyclerView for displaying movie posters
        RecyclerView posterRecyclerView = findViewById(R.id.postersRecyclerView);

        // Button for adding to watchlist
        buttonAddToWatchList = findViewById(R.id.buttonAddToWatchList);

        // List of posters
        List<Poster> posterList = new ArrayList<>();

        // Adding posters with details to posterList
        Poster poster1 = new Poster();
        poster1.image = R.drawable.friday;
        poster1.name = "Friday";
        poster1.createdBy = "F. Gary Gray";
        poster1.rating = 4f;
        poster1.story = "Two friends spend a Friday in their Los Angeles neighborhood, encountering various hilarious situations.";
        posterList.add(poster1);

        Poster poster2 = new Poster();
        poster2.image = R.drawable.godfather;
        poster2.name = "The Godfather";
        poster2.createdBy = "Francis Ford Coppola";
        poster2.rating = 5f;
        poster2.story = "An aging patriarch of an organized crime dynasty transfers control to his reluctant son.";
        posterList.add(poster2);

        Poster poster3 = new Poster();
        poster3.image = R.drawable.harold;
        poster3.name = "Harold & Kumar Go to White Castle";
        poster3.createdBy = "Danny Leiner";
        poster3.rating = 4f;
        poster3.story = "Two friends go on a wild adventure to satisfy their craving for White Castle burgers.";
        posterList.add(poster3);

        Poster poster4 = new Poster();
        poster4.image = R.drawable.gladiator;
        poster4.name = "Gladiator";
        poster4.createdBy = "Ridley Scott";
        poster4.rating = 4.5f;
        poster4.story = "A betrayed Roman general seeks revenge and fights for his freedom as a gladiator.";
        posterList.add(poster4);

        Poster poster5 = new Poster();
        poster5.image = R.drawable.jaws;
        poster5.name = "Jaws";
        poster5.createdBy = "Steven Spielberg";
        poster5.rating = 4.5f;
        poster5.story = "A great white shark terrorizes a small beach town, and three men set out to hunt it down.";
        posterList.add(poster5);

        Poster poster6 = new Poster();
        poster6.image = R.drawable.matrix;
        poster6.name = "The Matrix";
        poster6.createdBy = "The Wachowskis";
        poster6.rating = 5f;
        poster6.story = "A hacker discovers the shocking truth about his reality and joins a rebellion against machines.";
        posterList.add(poster6);

        Poster poster7 = new Poster();
        poster7.image = R.drawable.pussinboots;
        poster7.name = "Puss in Boots";
        poster7.createdBy = "Chris Miller";
        poster7.rating = 4f;
        poster7.story = "The daring adventures of the swashbuckling feline, Puss, before he met Shrek.";
        posterList.add(poster7);

        Poster poster8 = new Poster();
        poster8.image = R.drawable.qanda;
        poster8.name = "Slumdog Millionaire";
        poster8.createdBy = "Danny Boyle";
        poster8.rating = 4.5f;
        poster8.story = "A young man from the slums of Mumbai answers questions on a game show to reunite with his lost love.";
        posterList.add(poster8);

        Poster poster9 = new Poster();
        poster9.image = R.drawable.redone;
        poster9.name = "Red";
        poster9.createdBy = "Robert Schwentke";
        poster9.rating = 3.5f;
        poster9.story = "A retired CIA agent assembles his old team to uncover a conspiracy threatening their lives.";
        posterList.add(poster9);

        Poster poster10 = new Poster();
        poster10.image = R.drawable.thebeatles;
        poster10.name = "The Beatles: Get Back";
        poster10.createdBy = "Peter Jackson";
        poster10.rating = 4.5f;
        poster10.story = "A documentary capturing the creative process of The Beatles during the making of their final album.";
        posterList.add(poster10);

        // Set up adapter with posterList
        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        posterRecyclerView.setAdapter(posterAdapter);

        // Show selected poster names in a Toast when button is clicked
        buttonAddToWatchList.setOnClickListener(new View.OnClickListener() {
            /**
             * Shows a toast with names of selected posters.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                List<Poster> selectPosters = posterAdapter.getSelectedPosters();
                StringBuilder posterNames = new StringBuilder();
                for (int i = 0; i < selectPosters.size(); i++) {
                    if (i == 0) {
                        posterNames.append(selectPosters.get(i).name);
                    } else {
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this, posterNames.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Controls the visibility of the watchlist button based on selection.
     *
     * @param isSelected True if a poster is selected, false otherwise.
     */
    @Override
    public void onPosterAction(Boolean isSelected) {
        if (isSelected) {
            buttonAddToWatchList.setVisibility(View.VISIBLE);
        } else {
            buttonAddToWatchList.setVisibility(View.GONE);
        }
    }
}
