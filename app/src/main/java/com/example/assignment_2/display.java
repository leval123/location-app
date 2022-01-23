package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class display extends AppCompatActivity {

    ListView Llist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        Llist = findViewById(R.id.list);
        database db = new database(display.this);
        List<model> viewAll = db.getdata();
        ArrayAdapter loc_adapter = new ArrayAdapter<model>(display.this, android.R.layout.simple_list_item_1, viewAll);
        Llist.setAdapter(loc_adapter);
    }


}