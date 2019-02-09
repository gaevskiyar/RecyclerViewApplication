package com.example.gand.recyclerviewapplication.mock;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gand.recyclerviewapplication.R;

public class ImgMockHolder extends RecyclerView.ViewHolder {

    private ImageView mImage;
    private TextView mImgName;
    private ImgMock mMock;

    public ImgMockHolder(@NonNull View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.iv_img);
        mImgName = itemView.findViewById(R.id.tv_imgname);
    }

    public void bind(ImgMock mock){
        mImage.setBackgroundColor(mock.getBGColor());
        mMock = mock;
        mImgName.setText(String.valueOf(mock.getName()));
    }

    public void setListener(final MockAdapter.OnItemClickListener listener){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(mMock);
            }
        });
    }
}
