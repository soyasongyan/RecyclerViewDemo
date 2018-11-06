package com.example.songyan.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GalleryRecyclerView extends RecyclerView {

    public GalleryRecyclerView(Context context, AttributeSet attr){
        super(context,attr);
        this.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                View view=getChildAt(0);
                if(onItemScrollChangeListener!=null){
                    if(view!=null&&view!=mCurrentView){
                        mCurrentView=view;
                        onItemScrollChangeListener.onChange(mCurrentView,getChildPosition(mCurrentView));
                    }
                }
            }
        });
    }

    private View mCurrentView;

    public interface OnItemScrollChangeListener{
        void onChange(View view,int position);
    }

    private OnItemScrollChangeListener onItemScrollChangeListener;

    public void setOnItemScrollChangeListener(OnItemScrollChangeListener onItemScrollChangeListener){
        this.onItemScrollChangeListener=onItemScrollChangeListener;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mCurrentView=getChildAt(0);
        if(onItemScrollChangeListener!=null){
            onItemScrollChangeListener.onChange(mCurrentView,getChildPosition(mCurrentView));
        }
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent e) {
        if(e.getAction()==MotionEvent.ACTION_MOVE){
            mCurrentView=getChildAt(0);
            if(onItemScrollChangeListener!=null){
                onItemScrollChangeListener.onChange(mCurrentView,getChildAdapterPosition(mCurrentView));
            }
        }
        return super.onTouchEvent(e);
    }*/
}
