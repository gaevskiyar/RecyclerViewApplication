package com.example.gand.recyclerviewapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.gand.recyclerviewapplication.mock.MockAdapter;
import com.example.gand.recyclerviewapplication.mock.MockGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerFragment extends Fragment {

    private RecyclerView mRecycler;
    private final MockAdapter mockAdapter = new MockAdapter();
    private MockAdapter.OnItemClickListener mListener;

    public static final int USER = 0;
    public static final int IMAGE = 1;

    public static RecyclerFragment newInstance() {
        
       return  new RecyclerFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MockAdapter.OnItemClickListener){
            mListener = (MockAdapter.OnItemClickListener) context;
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecycler = view.findViewById(R.id.recycler);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mockAdapter);
        mockAdapter.setListener(mListener);
        if(savedInstanceState == null) {
            List<Object> data = new ArrayList<>(2);
            data.add(MockGenerator.generate(USER));
            data.add(MockGenerator.generate(IMAGE));
            mockAdapter.addData(data, true);
        }else {
            int count = mockAdapter.getItemCount();
            Log.d("FR ", String.valueOf(count));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_item_menu, menu);
        Drawable drawable = menu.findItem(R.id.im_add_img).getIcon();
        setColor(drawable, Color.WHITE);
        drawable = menu.findItem(R.id.im_add_text).getIcon();
        setColor(drawable, Color.WHITE);

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setColor(Drawable drawable, int color) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.im_add_img:
                mockAdapter.addItem(MockGenerator.generate(IMAGE));
                break;
            case R.id.im_add_text:
                mockAdapter.addItem(MockGenerator.generate(USER));
                break;
            default:
                break;
        }
        mRecycler.scrollToPosition(mockAdapter.getItemCount()-1);
        return super.onOptionsItemSelected(item);
    }

    public void dropItem(Object item) {
        mockAdapter.dropItem(item);
        }

}
