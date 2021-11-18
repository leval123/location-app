package com.example.assignment_2;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

    public class update extends AppCompatActivity {

        Button update;
        EditText add1,add2;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.update);
            update = findViewById(R.id.updatec);

            add1 = findViewById(R.id.add1);
            add2 = findViewById(R.id.add2);
            database db = new database(update.this);
            MainActivity main = new MainActivity();


            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String add = add2.getText().toString();

                    database db = new database(update.this);
                    boolean update = db.updateOne(add1.getText().toString(), add,String.valueOf(geoLocate(add).getLongitude()),String.valueOf(geoLocate(add).getLatitude()));
                    Toast.makeText(update.this, "update =" + update, Toast.LENGTH_SHORT).show();



                }
            });
        }

        public Address geoLocate(String srearchString){

            //create a geocoder obj and a new arraylist
            Geocoder geocoder = new Geocoder(update.this);
            List<Address> list = new ArrayList<>();
            try {
                //get the address from the locating
                list = geocoder.getFromLocationName(srearchString,1);

            }catch (IOException e)
            {
                Toast.makeText(update.this,""+e,Toast.LENGTH_SHORT);
            }
            //save the address and return it
            Address add = list.get(0);
            return  add;

        }



    }

