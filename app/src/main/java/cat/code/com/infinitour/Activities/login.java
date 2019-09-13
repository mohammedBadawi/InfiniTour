package cat.code.com.infinitour.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import cat.code.com.infinitour.R;

public class login extends AppCompatActivity {
    EditText etemail,etpassword;
    Button login;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        etemail=(EditText)findViewById(R.id.et_login_email);
        etpassword=(EditText)findViewById(R.id.et_login_password);
        login=(Button) findViewById(R.id.btn_login_login);
        progressBar=(ProgressBar)findViewById(R.id.prog_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email=etemail.getText().toString();
                String password = etpassword.getText().toString().trim();
                if (etemail.getText().toString().isEmpty()) {
                    etemail.setError("enter your mail");
                } else {
                    email = etemail.getText().toString().trim();
                }
                if (etpassword.getText().toString().trim().isEmpty()) {
                    etpassword.setError("enter your password");
                } else {
                    password = etpassword.getText().toString().trim();
                }
                if (!(etemail.getText().toString().isEmpty()||etpassword.getText().toString().isEmpty())){
                    login(email,password);
                }




            }
        });
    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(login.this,"signedin successful",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(login.this,user_interface.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(login.this,"error",Toast.LENGTH_LONG).show();

                        }

                        progressBar.setVisibility(View.INVISIBLE);

                    }
                });
    }


}
