package cat.code.com.infinitour.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cat.code.com.infinitour.R;

public class MainActivity extends AppCompatActivity {
    EditText etname,etemail,etpassword;
    Button next;
    TextView login;
    String name,emil,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname=findViewById(R.id.et_signin_name);
        etemail=findViewById(R.id.et_signin_email);
        etpassword=findViewById(R.id.et_signin_password);
        next=findViewById(R.id.btn_signin_Next);
        login=findViewById(R.id.tv_signin_have_account);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, cat.code.com.infinitour.Activities.login.class);
                startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etname.getText().toString().isEmpty()){
                    etname.setError("enter your name");
                }
                else{
                    name=etname.getText().toString().trim();
                }
                if(etemail.getText().toString().isEmpty()){
                    etemail.setError("enter your mail");
                }
                else{
                    emil=etemail.getText().toString().trim();
                }
                if(etpassword.getText().toString().isEmpty()){
                    etpassword.setError("enter your password");
                }
                else{
                    password=etpassword.getText().toString().trim();
                }
                if((etname.getText().toString().isEmpty())||(etpassword.getText().toString().isEmpty())||(etemail.getText().toString().isEmpty())){}
                    else{Intent intent=new Intent(MainActivity.this,choose_lang.class);
                            intent.putExtra("email",etemail.getText().toString().trim());
                            intent.putExtra("password",etpassword.getText().toString().trim());
                            intent.putExtra("name",etname.getText().toString().trim());
                            startActivity(intent);}

            }
        });
    }
}
