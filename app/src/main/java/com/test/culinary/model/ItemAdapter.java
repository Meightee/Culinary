package com.test.culinary.model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.test.culinary.R;

import java.util.ArrayList;
import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> implements Filterable {

    public List<Recipe> getRecipeListFilter;

    private final OnRecipeClickListener onRecipeClickListener;

    Context context;
    private List<Recipe> recipeModelList;

    public ItemAdapter(List<Recipe> recipes, Context context, OnRecipeClickListener onRecipeClickListener) {
        this.onRecipeClickListener = onRecipeClickListener;
        this.getRecipeListFilter = recipes;
        this.context = context;
        this.recipeModelList = recipes;

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(context == null|| constraint.length() == 0){
                    filterResults.values = getRecipeListFilter;
                    filterResults.count = getRecipeListFilter.size();
                }else{
                    String searchString = constraint.toString().toLowerCase();
                    List<Recipe> recipes = new ArrayList<>();
                    for(Recipe recipe: getRecipeListFilter){
                        if(recipe.getTitle().toLowerCase().contains(searchString)){
                            recipes.add(recipe);
                        }

                    }

                    filterResults.values = recipes;
                    filterResults.count = recipes.size();
                }
                return filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                recipeModelList = (List<Recipe>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    public interface OnRecipeClickListener{
        void onRecipeClick(Recipe recipe);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = (Recipe) recipeModelList.get(position);

        ((ViewHolder) holder).titleView.setText(recipe.getTitle());

        Glide.with(context).load(recipe.getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(itemView -> onRecipeClickListener.onRecipeClick(recipe));
    }


    @Override
    public int getItemCount() {
        return recipeModelList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleView;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            titleView = (TextView) itemView.findViewById(R.id.titleView);
        }
    }
}