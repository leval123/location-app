package com.example.assignment_2;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_add, btn_delete, btn_update, btn_search, btn_view;
    EditText address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.add);
        btn_delete = findViewById(R.id.delete);
        btn_update = findViewById(R.id.update);
        btn_search = findViewById(R.id.search);
        btn_view = findViewById(R.id.view_all);

        address = findViewById(R.id.address);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model data;
                String add = address.getText().toString();

                try {
                    data = new model(-1, add, String.valueOf(geoLocate(add).getLongitude()),String.valueOf(geoLocate(add).getLatitude()));
                    Toast.makeText(MainActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                    data = new model(-1, "error", "na", "na");

                }

                database db = new database(MainActivity.this);
                boolean b = db.addOne(data);
                Toast.makeText(MainActivity.this, "pass=" + b, Toast.LENGTH_SHORT).show();

            }
        });


        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, display.class));
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database db = new database(MainActivity.this);
                String search = db.searchOne(address.getText().toString());
                Toast.makeText(MainActivity.this, "" + search, Toast.LENGTH_SHORT).show();



            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database db = new database(MainActivity.this);
                boolean delete = db.deleteOne(address.getText().toString());
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();

            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do to the update fragment
                startActivity(new Intent(MainActivity.this, update.class));
            }
        });
    }

    public Address geoLocate(String searchString){

        //create a geocoder obj and a new arraylist

        Geocoder geocoder = new Geocoder(MainActivity.this);
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
        }

        if(list.size() > 0){
            Address address = list.get(0);
            return  address;
        }
        return null;

    }


}