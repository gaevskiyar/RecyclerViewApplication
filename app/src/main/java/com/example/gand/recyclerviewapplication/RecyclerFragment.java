package com.example.gand.recyclerviewapplication;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gand.recyclerviewapplication.mock.MockAdapter;
import com.example.gand.recyclerviewapplication.mock.MockGenerator;

import java.util.Random;

public class RecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView mRecycler;
    private final ContactsAdapter mContactsAdapter = new ContactsAdapter();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mErrorView;
    private Random mRandom = new Random();
    private ContactsAdapter.OnItemClickListener mListener;

    public static RecyclerFragment newInstance() {
        
       return  new RecyclerFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ContactsAdapter.OnItemClickListener){
            mListener = (ContactsAdapter.OnItemClickListener) context;
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
        mSwipeRefreshLayout = view.findViewById(R.id.refresher);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mErrorView = view.findViewById(R.id.error_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mContactsAdapter);
        mContactsAdapter.setListener(mListener);
    }

    @Override
    public void onRefresh() {
//        mSwipeRefreshLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                int count = mRandom.nextInt(4);
//                if(count == 0){
//                    showError();
//                } else {
//                    showData(count);
//                }
//                if(mSwipeRefreshLayout.isRefreshing()){
//                    mSwipeRefreshLayout.setRefreshing(false);
//                }
//
//            }
//        }, 2000);
        getLoaderManager().restartLoader(0, null, this);
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

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new CursorLoader(getActivity(),
                ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME},
                null, null,ContactsContract.Contacts._ID);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        mContactsAdapter.swapCursor(cursor);
        if(mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
