package cat.code.com.infinitour.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

import cat.code.com.infinitour.Adapters.TourGuide_Adapter;
import cat.code.com.infinitour.Classes.TourGudie;
import cat.code.com.infinitour.R;

public class Choose_TourGuide extends AppCompatActivity {
    ArrayList<TourGudie>guides_list;
    DatabaseReference myRef;
    String country,city;
    FirebaseUser user;
    ArrayList<String>langs;
    TourGuide_Adapter adapter;
    ListView listView;
    String language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__tour_guide);
        try {
        Intent i=getIntent();
        langs=new ArrayList<>();
        langs=i.getStringArrayListExtra("langs");
        get_guides();
        Intent intent=getIntent();
        country=intent.getStringExtra("country");
        city=intent.getStringExtra("city");
       // get_user_langs();
        adapter=new TourGuide_Adapter(Choose_TourGuide.this,R.layout.tourguides_row,guides_list,country,city,langs);
        listView=(ListView)findViewById(R.id.lv_tourguides);
        listView.setAdapter(adapter);}
        catch (Exception e){
            Toast.makeText(Choose_TourGuide.this,e.toString(),Toast.LENGTH_LONG).show();
        }

    }




  /*  private void get_user_langs(){
        user= FirebaseAuth.getInstance().getCurrentUser();
        myRef=FirebaseDatabase.getInstance().getReference().child("Tourist").child(user.getUid()).child("languages");
        langs=new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    langs.add(postSnapshot.getValue().toString());
                    language=postSnapshot.getValue().toString();
                    Toast.makeText(Choose_TourGuide.this,langs.get(0),Toast.LENGTH_LONG).show();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Choose_TourGuide.this,databaseError.toString(),Toast.LENGTH_LONG).show();

            }

        });
    }*/

   private void get_guides(){
        guides_list=new ArrayList<>();

        myRef = FirebaseDatabase.getInstance().getReference().child("TourGuides");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                     String name= (String) postSnapshot.child("name").getValue();
                     String age= (String) postSnapshot.child("age").getValue();
                     String email= (String) postSnapshot.child("email").getValue();
                     String rate= (String) postSnapshot.child("rate").getValue();
                     String photo_url= (String) postSnapshot.child("photo_url").getValue();
                     TourGudie t =new TourGudie(age,email,name,photo_url,rate);
                     guides_list.add(t);
                     adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Choose_TourGuide.this,databaseError.toString(),Toast.LENGTH_LONG).show();


            }

        });



    }

    private void save_tourguides() {
        myRef=FirebaseDatabase.getInstance().getReference();
        ArrayList<TourGudie>list=new ArrayList<>();
       TourGudie t1=new TourGudie("22","tourguide1@gmail.com","Mohamed Emad","fff","4.5");
       TourGudie t2=new TourGudie("25","tourguide2@gmail.com","Amr Mohamed","fff","4.5");
       TourGudie t3=new TourGudie("29","tourguide3@gmail.com","Ali Ahmed","fff","5");
       TourGudie t4=new TourGudie("30","tourguide4@gmail.com","Hussien Ahmed","fff","3.7");
       TourGudie t5=new TourGudie("40","tourguide5@gmail.com","Youssef Yahia","fff","4");
        list.add(t1);list.add(t2);list.add(t3);list.add(t4);list.add(t5);
        myRef.child("TourGuides").setValue(list);

    }


}
