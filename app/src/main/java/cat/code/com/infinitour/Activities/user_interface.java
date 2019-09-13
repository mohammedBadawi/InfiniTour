package cat.code.com.infinitour.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cat.code.com.infinitour.Adapters.Autocomplete_country_adapter;
import cat.code.com.infinitour.Adapters.City_Adapter_listview;
import cat.code.com.infinitour.Classes.Countryitem;
import cat.code.com.infinitour.R;

public class user_interface extends AppCompatActivity {

    private List<Countryitem> countrylist;
    ArrayList<String> test;
    Button btn;
    DatabaseReference mdatabase;
    AutoCompleteTextView edittext;
    Autocomplete_country_adapter adapter;
    City_Adapter_listview myadapter;
    ListView cities;
    TextView select_city;
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);
        fill_countrylist();
        edittext = findViewById(R.id.auto_select_country);
        adapter = new Autocomplete_country_adapter(this, countrylist);
        edittext.setAdapter(adapter);
        cities = (ListView) findViewById(R.id.lv_cities);
        select_city = (TextView) findViewById(R.id.tv_select_city);
        btn = (Button) findViewById(R.id.btnbtn);
        test = new ArrayList<>();
        myadapter = new City_Adapter_listview(this, R.layout.city_element, test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(user_interface.this, edittext.getText(), Toast.LENGTH_SHORT).show();
                mdatabase = FirebaseDatabase.getInstance().getReference().child("Countries").child(edittext.getText().toString()).child("Cities");
                mdatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            test.add(postSnapshot.getValue().toString());
                            myadapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }


        });
        select_city.setVisibility(View.VISIBLE);
        cities.setAdapter(myadapter);
        cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city=parent.getItemAtPosition(position).toString();
                Toast.makeText(user_interface.this,city, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),Choose_TourGuide.class);
                intent.putExtra("city",city);
                intent.putExtra("country",edittext.getText().toString());
                startActivity(intent);

            }
        });


    }


    private void fill_countrylist() {
        countrylist = new ArrayList<>();
        countrylist.add(new Countryitem("Egypt"));
        countrylist.add(new Countryitem("Russia"));
        countrylist.add(new Countryitem("Canada"));
        countrylist.add(new Countryitem("United States"));
        countrylist.add(new Countryitem("China"));
        countrylist.add(new Countryitem("Australia"));
        countrylist.add(new Countryitem("Brazil"));
        countrylist.add(new Countryitem("France"));
        countrylist.add(new Countryitem("India"));
        countrylist.add(new Countryitem("Germany"));
        countrylist.add(new Countryitem("Algeria"));
        countrylist.add(new Countryitem("Mexico"));
    }


}
