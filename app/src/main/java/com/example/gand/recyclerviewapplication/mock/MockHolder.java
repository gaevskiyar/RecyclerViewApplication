package com.example.gand.recyclerviewapplication.mock;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.gand.recyclerviewapplication.mock.MockAdapter;
import com.example.gand.recyclerviewapplication.R;

public class MockHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mValue;
    private Mock mMock;

    public MockHolder(@NonNull View itemView) {
        super(itemView);
        mName = itemView.findViewById(R.id.tv_name);
        mValue = itemView.findViewById(R.id.tv_value);
    }

    public void bind(Mock mock) {
        mName.setText(mock.getName());
        mValue.setText(mock.getValue());
        mMock = mock;
    }

    public void setListener(final MockAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(mMock);
            }
        });
    }
}
