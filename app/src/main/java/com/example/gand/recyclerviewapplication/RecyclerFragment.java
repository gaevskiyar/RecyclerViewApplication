package com.example.gand.recyclerviewapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gand.recyclerviewapplication.mock.MockAdapter;
import com.example.gand.recyclerviewapplication.mock.MockGenerator;

import java.util.Random;

public class RecyclerFragment extends Fragment {

    private RecyclerView mRecycler;
    private final MockAdapter mockAdapter = new MockAdapter();
    private Random mRandom = new Random();
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
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mockAdapter);
        mockAdapter.setListener(mListener);
    }

    public void addItem(int type) {
        switch (type){
            case IMAGE:
                mockAdapter.addData(MockGenerator.generate(IMAGE));
                break;
            case USER:
                mockAdapter.addData(MockGenerator.generate(USER));
                break;
            default:
                break;
        }
    }
//    private void showData(int count) {
//        mErrorView.setVisibility(View.GONE);
//        mRecycler.setVisibility(View.VISIBLE);
//        mMockAdapter.addData(MockGenerator.generate(5), true);
//
//    }
//
//    private void showError() {
//        mErrorView.setVisibility(View.VISIBLE);
//        mRecycler.setVisibility(View.GONE);
//    }

}
