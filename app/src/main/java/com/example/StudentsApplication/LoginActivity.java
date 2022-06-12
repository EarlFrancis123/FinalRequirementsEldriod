package com.example.StudentsApplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText signInEmail , signInPass;
    private Button signInBtn;
    private TextView sign_up_text;

    TextView EForgotPassword;
    DatabaseReference databaseReference2;
    FirebaseDatabase firebaseDatabase2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseDatabase2 = FirebaseDatabase.getInstance();
        signInEmail = findViewById(R.id.sign_in_email);
        signInPass = findViewById(R.id.sing_in_pass);
        signInBtn = findViewById(R.id.sign_in_btn);
        sign_up_text = findViewById(R.id.sign_up_text);
        EForgotPassword = findViewById(R.id.forgotPasswordTV);

        sign_up_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signInEmail.getText().toString();
                String pass = signInPass.getText().toString();
                databaseReference2=firebaseDatabase2.getReference().child("Users");
                databaseReference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Boolean check = false;
                        for (DataSnapshot dataSnapshot2 : snapshot.getChildren()) {

                            String value1 = String.valueOf(dataSnapshot2.child("email").getValue());
                            String value2 = String.valueOf(dataSnapshot2.child("password").getValue());
                            if (value1.equals(signInEmail.getText().toString()) && value2.equals(signInPass.getText().toString())) {
                                check = true;
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }

                        }
                        if (check == true) {

                        }else{
                            Toast.makeText(getApplicationContext(), "User Not Registered!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}