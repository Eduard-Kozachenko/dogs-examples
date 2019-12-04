package com.eduard.dogs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.eduard.dogs.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class DogsImgAdapter extends RecyclerView.Adapter<DogsImgAdapter.ImageViewHolder> {

    List<String> listDogs = new ArrayList<>();
    private LayoutInflater mInflater;

    public DogsImgAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.view_dogs_detail_img, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        Picasso.get().load(listDogs.get(i)).into(imageViewHolder.imgBreedView);
    }

    @Override
    public int getItemCount() {
        return listDogs.size();
    }

    public void setImgList(List<String> data) {
        listDogs = data;
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView imgBreedView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBreedView = itemView.findViewById(R.id.img_dods_detail);
        }
    }
}
