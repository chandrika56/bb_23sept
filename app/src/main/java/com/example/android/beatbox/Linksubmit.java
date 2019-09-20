package com.example.android.beatbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Linksubmit extends AppCompatActivity {
    DatabaseReference rootRef,demoRef;
    EditText e;
Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_linksubmit);
        rootRef = FirebaseDatabase.getInstance().getReference();
        //database reference pointing to demo node
        demoRef = rootRef.child("URL");
        e=findViewById (R.id.editText);
        b=findViewById (R.id.button4);
        b.setOnClickListener (new View.OnClickListener ( ) {

            @Override
            public void onClick(View v) {
                String value3 =e.getText().toString();
                demoRef.setValue (value3);
                Toast.makeText(getApplicationContext(), "Link has been Submitted!", Toast.LENGTH_SHORT).show();
            }
            });
    }
}
