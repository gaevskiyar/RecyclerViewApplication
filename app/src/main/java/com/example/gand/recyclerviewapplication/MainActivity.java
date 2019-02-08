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
        fragment = RecyclerFragment.newInstance();
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.im_add_img:
                fragment.addItem(RecyclerFragment.IMAGE);
                break;
            case R.id.im_add_text:
                fragment.addItem(RecyclerFragment.USER);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_item_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemClick(String id) {
        Toast.makeText(this, "clicked "+id, Toast.LENGTH_SHORT).show();
    }
}
