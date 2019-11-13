package com.eduard.dogs.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.eduard.dogs.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DogsListAdapter extends RecyclerView.Adapter<DogsListAdapter.ViewHolder> {

    private List<String> breeds = new ArrayList<>();
    private LayoutInflater mInflater;
    private ListItemClickListener mClickListener;

    public DogsListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_dogs_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = breeds.get(position);
        holder.myTextView.setText(animal);
    }

    @Override
    public int getItemCount() {
        return breeds.size();
    }

    public void setClickListener(ListItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public void setBreedsList(List<String> breeds) {
        this.breeds = breeds;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.dogs_name_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.itemClick(view, getAdapterPosition());
        }
    }
}
