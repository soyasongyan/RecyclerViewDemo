package com.example.songyan.recyclerviewdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends Activity {

    private List<Bitmap> mData;
    private GalleryRecyclerView recyclerView;
    private ImageView imageView;
    private GalleryRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_gallery_recycler_layout);

        recyclerView=findViewById(R.id.galleryrecyclerview_id);
        imageView=findViewById(R.id.content_id);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        initData();

        adapter=new GalleryRecyclerAdapter(this,mData);
        adapter.setOnItemClickListener(new GalleryRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(GalleryActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setOnItemScrollChangeListener(new GalleryRecyclerView.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {
                imageView.setImageBitmap(mData.get(position));
            }
        });
        recyclerView.setAdapter(adapter);

    }

    private void initData(){
        List<String> fileList=new ArrayList<>();
        String directoryPath=Environment.getExternalStorageDirectory().toString()+ File.separator+"Pictures";
        Log.e("songsong","directoryPath is "+directoryPath);
        File directoryFile=new File(directoryPath);
        if(directoryFile.exists()){
            File[] files=directoryFile.listFiles();
            for(int i=0;i<files.length;i++){
                fileList.add(files[i].toString());
            }
            mData=loadBitmap(fileList);
        }
    }

    private List<Bitmap> loadBitmap(List<String> list){
        List<Bitmap> bitmaps=new ArrayList<>();
        for(String path:list){
            if(BitmapFactory.decodeFile(path)!=null){
                bitmaps.add(BitmapFactory.decodeFile(path));
            }
        }
        return  bitmaps;
    }
}
