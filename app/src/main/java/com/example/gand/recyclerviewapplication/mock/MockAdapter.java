package com.example.gand.recyclerviewapplication.mock;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gand.recyclerviewapplication.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.gand.recyclerviewapplication.RecyclerFragment.IMAGE;
import static com.example.gand.recyclerviewapplication.RecyclerFragment.USER;

public class MockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Object> mMockList = new ArrayList<>();
    private OnItemClickListener mListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (i) {
            case IMAGE:
                View imgview = inflater.inflate(R.layout.li_img_mock, viewGroup, false);
                holder = new ImgMockHolder(imgview);
                break;
            default:
                View textview = inflater.inflate(R.layout.li_mock, viewGroup, false);
                holder = new MockHolder(textview);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (viewHolder.getItemViewType()){
            case IMAGE:
                ImgMockHolder imgMockHolder = (ImgMockHolder) viewHolder;
                ImgMock imgMock = (ImgMock) mMockList.get(i);
                if (imgMock != null) imgMockHolder.bind(imgMock);
                break;
            default:
                MockHolder MockHolder = (MockHolder) viewHolder;
                Mock mock = (Mock) mMockList.get(i);
                if (mock != null) MockHolder.bind(mock);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mMockList.get(position) instanceof Mock){
            return USER;
        } else  if(mMockList.get(position) instanceof ImgMock){
            return IMAGE;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return mMockList.size();
    }

    public void addData(Object mock){
        mMockList.add(mock);
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(String id);
    };
}
