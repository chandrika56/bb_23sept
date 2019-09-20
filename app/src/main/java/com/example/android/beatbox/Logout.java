package com.example.android.beatbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;



import com.example.android.beatbox.MainActivity;
import com.example.android.beatbox.R;
import com.google.firebase.auth.FirebaseAuth;
public class Logout extends AppCompatActivity {
    Button button;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart ( );
        mAuth.addAuthStateListener (mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        button=findViewById (R.id.signOutButton);
        mAuth= FirebaseAuth.getInstance ();
        mAuthListener=new FirebaseAuth.AuthStateListener ( ) {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser ()==null){
                    startActivity (new Intent (Logout.this, MainActivity.class));
                }
            }
        };
        button.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                mAuth.signOut ();
            }
        });
    }


}