package ie.ul.serge.foodrater;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    RecyclerView mRecyclerView;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView=recyclerView;
    }

    //create arraylist that will hold food items
    private List<Food> mFoods = new ArrayList<>();

    public void addFood()
    {
    //TODO : creat method to add food
        //add food

        mFoods.add(0, new Food());
        // notify viewHolder that food was added
        notifyItemInserted(0);
        notifyItemRangeChanged(0,mFoods.size());
        mRecyclerView.scrollToPosition(0);
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //TODO - done
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item,parent,
                false);
        return new FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        //TODO - done implementing onbindViewHolder
        // in order to set items on the card need to get that card(holder) by position
        // then use that item to set attributes to that.
        final Food food = mFoods.get(position);
        //Then take fields and set attributes of that item.
        holder.mName.setText(food.getName());
        holder.mImageView.setImageResource(food.getImageResourceId());
        holder.mRatingBar.setRating(food.getRating());
    }

    @Override
    public int getItemCount() {
        return mFoods.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mName;
        private RatingBar mRatingBar;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.food_pic);
            mName=itemView.findViewById(R.id.name);
            mRatingBar=itemView.findViewById(R.id.rating_bar);
            //TODO - delete this food on long press.(done)

         itemView.setOnLongClickListener(new View.OnLongClickListener() {

             @Override
             public boolean onLongClick(View v) {
                 removeFood(getAdapterPosition());
                 return true;
             }
         });

         //TODO - update rating. Done together.
            mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                  if(fromUser){
                      //If input comes from user - update this food's rating
                      Food currentFood = mFoods.get(getAdapterPosition());
                      currentFood.setRating(rating);

                  }
                }
            });


        }
    }

    //Method used to remove item from the list
    private void removeFood(int adapterPosition) {
        mFoods.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        notifyItemRangeChanged(0,mFoods.size());
    }
}
