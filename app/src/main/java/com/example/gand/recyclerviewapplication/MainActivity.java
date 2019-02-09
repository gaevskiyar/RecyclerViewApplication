package com.example.gand.recyclerviewapplication;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gand.recyclerviewapplication.mock.MockAdapter;

public class MainActivity extends AppCompatActivity implements MockAdapter.OnItemClickListener{

    private RecyclerFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            fragment = RecyclerFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }


    @Override
    public void onItemClick(Object item) {
        fragment.dropItem(item);
    }
}
