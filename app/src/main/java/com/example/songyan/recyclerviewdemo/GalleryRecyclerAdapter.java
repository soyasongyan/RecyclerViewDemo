package com.example.songyan.recyclerviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GalleryRecyclerAdapter extends RecyclerView.Adapter<GalleryRecyclerAdapter.GalleryViewHolder> {

    private LayoutInflater inflater;
    private List<Bitmap> mData;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public GalleryRecyclerAdapter(Context context,List<Bitmap> datas){
        inflater=LayoutInflater.from(context);
        mData=datas;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.gallery_item_layout,viewGroup,false);
        GalleryViewHolder viewHolder=new GalleryViewHolder(view);
        viewHolder.imageView=view.findViewById(R.id.imageview_id);
        viewHolder.textView=view.findViewById(R.id.textview_id);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GalleryViewHolder galleryViewHolder,final int i) {
        //给ViewHolder绑定数据
        galleryViewHolder.imageView.setImageBitmap(mData.get(i));

        galleryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(galleryViewHolder.itemView,i);
                }
            }
        });
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder{
        GalleryViewHolder(View view){
            super(view);
        }
        ImageView imageView;
        TextView textView;
    }
}
