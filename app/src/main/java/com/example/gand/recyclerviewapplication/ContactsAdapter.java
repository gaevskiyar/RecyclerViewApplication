package com.example.gand.recyclerviewapplication;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gand.recyclerviewapplication.mock.Mock;
import com.example.gand.recyclerviewapplication.mock.MockHolder;

public class ContactsAdapter extends RecyclerView.Adapter<MockHolder> {
    private Cursor mCursor;
    private OnItemClickListener mListener;

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public MockHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.li_mock, viewGroup, false);
        return  new MockHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MockHolder mockHolder, int i) {
        if(mCursor.moveToPosition(i)){
          String name = mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
          int id = mCursor.getInt(mCursor.getColumnIndex(ContactsContract.Contacts._ID));
          mockHolder.bind(new Mock(name, id));
          mockHolder.setListener(mListener);
        }

    }

    @Override
    public int getItemCount() {
        return mCursor!=null?mCursor.getCount():0;
    }

    public void swapCursor(Cursor cursor){
        if(cursor != null && cursor != mCursor){
            if(mCursor != null) mCursor.close();
            mCursor = cursor;
            notifyDataSetChanged();
        }

    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener{
        void OnItemClick(String id);
    }
}
