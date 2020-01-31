package com.eduard.dogs.dogs.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.eduard.dogs.R;
import com.eduard.dogs.dogs.presentation.model.BreedImage;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class DogsImgAdapter extends RecyclerView.Adapter<DogsImgAdapter.ImageViewHolder> {

    public Context context;
    public List<BreedImage> listDogs = new ArrayList<>();
    private LayoutInflater mInflater;

    public DogsImgAdapter(Context cont) {
        this.mInflater = LayoutInflater.from(cont);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.view_dogs_detail_img, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        Picasso.get().load(listDogs.get(i).getBreedImage()).into(imageViewHolder.imgBreedView);
    }

    @Override
    public int getItemCount() {
        return listDogs.size();
    }

    public void setImgList(List<BreedImage> dogs) {
        this.listDogs = dogs;
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView imgBreedView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBreedView = itemView.findViewById(R.id.img_dods_detail);
        }
    }
}
