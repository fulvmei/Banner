package com.chengfu.android.banner.demo;

import android.os.Bundle;

import com.chengfu.android.banner.Banner;
import com.chengfu.android.banner.transformer.OverlayTransformer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Banner banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banner=findViewById(R.id.banner);

        banner.setAdapter(new Adapter());
        banner.setOffscreenPageLimit(4);
        banner.setPageTransformer(new OverlayTransformer(4,0.03f,24));
        banner.setOffsetRight(3*24);
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        int[] images = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6};

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_image1, parent, false);

            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.imageView.setImageResource(images[position%images.length]);
            holder.itemView.setTag(position%images.length);
        }

        @Override
        public int getItemCount() {
//            return images.length;
            return Integer.MAX_VALUE;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image);
            }
        }
    }
}