package com.example.movieposters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

// Adapter for displaying movie posters
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {

    /**
     * Inflates the layout for each poster item.
     *
     * @param parent   The parent ViewGroup.
     * @param viewType The type of the new view.
     * @return PosterViewHolder, containing the poster item layout.
     */
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    /**
     * Binds data to the ViewHolder.
     *
     * @param holder   The ViewHolder for the poster item.
     * @param position The position of the item in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPosters(posterList.get(position));
    }

    /**
     * Gets the total number of posters.
     *
     * @return The number of posters.
     */
    @Override
    public int getItemCount() {
        return posterList.size();
    }

    // List of posters to display
    private List<Poster> posterList;

    /**
     * Initializes the adapter with a list of posters and a listener.
     *
     * @param posterList       List of posters.
     * @param postersListener  Listener for poster actions.
     */
    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    // Listener for poster actions
    private PostersListener postersListener;

    /**
     * Returns the list of selected posters.
     *
     * @return List of selected posters.
     */
    public List<Poster> getSelectedPosters() {
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster : this.posterList) {
            if (poster.isSelected) {
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }

    // ViewHolder for the poster items
    class PosterViewHolder extends RecyclerView.ViewHolder {

        // Poster item components
        ConstraintLayout layoutPosters;
        View viewBackground;
        RoundedImageView imagePoster;
        TextView textName, textCreatedBy, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;

        /**
         * Initializes the poster item components.
         *
         * @param itemView The poster item view.
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutPosters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreateBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        /**
         * Sets the data for each poster and handles selection.
         *
         * @param poster The current poster.
         */
        void bindPosters(final Poster poster) {
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);

            // Check if poster is selected
            if (poster.isSelected) {
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }

            // Toggle selection on click
            layoutPosters.setOnClickListener(new View.OnClickListener() {
                /**
                 * Toggles selection and updates view.
                 *
                 * @param v The clicked view.
                 */
                @Override
                public void onClick(View v) {
                    if (poster.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if (getSelectedPosters().isEmpty()) {
                            postersListener.onPosterAction(false);
                        }
                    } else {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;
                        postersListener.onPosterAction(true);
                    }
                }
            });
        }
    }
}
