package cat.code.com.infinitour.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cat.code.com.infinitour.Classes.Countryitem;
import cat.code.com.infinitour.R;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class choose_lang extends AppCompatActivity {
    ArrayList<String>selected_items=new ArrayList<>();
    Button signin;
    ListView langs;
   // String countries[]={"Egypt","Russia","Canada","United States","China","Australia","Brazil","France","India","Germany"};
    //String cities[][]={{"Cairo","Alexandria","Giza","Aswan","Luxor"},{"Moscow","Saint Petersburg","Kazan"},{"Vancouver","Montreal","Toronto"},{"New York","Los Angeles","Chicago"},{"Hong Kong","Macau","Beijing"},{"Sydney","Melbourne","Adelaide"},{"Rio de Janeiro","São Paulo","Manaus"},{"Paris","Lyon","Marseille"},{"Mumbai","Delhi","Bangalore"},{"Berlin","Hamburg","Munich "}};
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseUser user;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lang);
        progressBar=(ProgressBar)findViewById(R.id.prog_choose_lang);
        user = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        langs=(ListView)findViewById(R.id.lv_checkable);
        langs.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String languages[]={"ENGLISH","ARABIC","RUSSIAN","SPANISH","CHINESE","HINDI","PORTUGUESE"};
        ArrayAdapter<String> adapter= new ArrayAdapter(this,R.layout.check_list,R.id.cl_choose_lang,languages);
        langs.setAdapter(adapter);
        signin=(Button)findViewById(R.id.btn_signin);

        langs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String selected_item=((TextView)view).getText().toString();
                if(selected_items.contains(selected_item)){
                    selected_items.remove(selected_item);
                }
                else{
                    selected_items.add(selected_item);
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // show_selected_items(v);
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = getIntent();
                String email=intent.getStringExtra("email");
                String name=intent.getStringExtra("name");
                String password=intent.getStringExtra("password");
                signin_user(email,password,name,selected_items);

            }
        });

    }

    private void signin_user(final String email, final String password, final String name, final ArrayList<String>selected_items) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           Intent intent=new Intent(choose_lang.this,user_interface.class);
                            save_user_information(email,name,password,selected_items);
                            intent.putStringArrayListExtra("langs",selected_items);
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(choose_lang.this,"you are already registered",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(choose_lang.this,login.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                            else {Log.w("TAGgggggggggggg","errrrrrrrror", task.getException());
                            Toast.makeText(choose_lang.this,"you may entered incorrect email",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(choose_lang.this,MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);}
                        }
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                });



    }

    private void save_user_information(String email, String name, String password, ArrayList<String> selected_items) {
        myRef.child("Tourist").child(user.getUid()).child("name").setValue(name);
        myRef.child("Tourist").child(user.getUid()).child("id").setValue(user.getUid());
        myRef.child("Tourist").child(user.getUid()).child("email").setValue(email);
        myRef.child("Tourist").child(user.getUid()).child("password").setValue(password);
        myRef.child("Tourist").child(user.getUid()).child("languages").setValue(selected_items)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(choose_lang.this,"registered",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(choose_lang.this,e.toString(),Toast.LENGTH_LONG).show();
            }
        });




    }

   /* public void register_countries(){
        int i=0;
        for(String name:countries){
            List nameList = new ArrayList<String>(Arrays.asList(cities[i]));
            myRef.child("Countries").child(name).child("Cities").setValue(nameList);
            i++;
        }
    }
*/
   /*public void show_selected_items(View view){
        String show="";
        for(String items:selected_items){
            show+="-"+items+"\n";
        }
        Toast.makeText(choose_lang.this,show.toString(),Toast.LENGTH_LONG).show();
    }*/





}
