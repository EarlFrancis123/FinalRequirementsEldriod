package com.example.StudentsApplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailSignUp , passSignUp, SingUpPhoneNumber,SignUpAddress;
    private Button signUpBtn;
    private TextView signINText;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailSignUp = findViewById(R.id.sign_up_email);
        passSignUp = findViewById(R.id.sing_up_pass);

        signUpBtn = findViewById(R.id.sign_up_btn);
        signINText = findViewById(R.id.sign_in_text);


        signINText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , LoginActivity.class));
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(emailSignUp.getText().toString())) {
                    emailSignUp.setError("This field is required");
                    Toast.makeText(getApplicationContext(), "Email Required", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(passSignUp.getText().toString())) {
                    passSignUp.setError("This field is required");
                    Toast.makeText(getApplicationContext(), "Password Required", Toast.LENGTH_SHORT).show();
                }else {

                    String email = emailSignUp.getText().toString();
                    String password = passSignUp.getText().toString();
                    HashMap<String, String> userMap = new HashMap<>();

                    userMap.put("password", password);
                    userMap.put("email", email);

                    root.push().setValue(userMap);

                    Toast.makeText(getApplicationContext(), "REgistered Successfully !!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });
    }
}