package com.example.android.beatbox;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class UpdateProfile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView mName;
    private TextView gmail;
    private String name;
    private String mail;
    private Uri photoUrl;
    private ImageView mPic;

    EditText e2,e3,e4,e5,e6;
    Button b;

    ImageButton i;
    DatabaseReference rootRef,demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_update_profile);
        mAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
        //database reference pointing to demo node
        demoRef = rootRef.child("User details");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent (UpdateProfile.this,MainActivity.class));
                }
            }
        };
e2=findViewById (R.id.editText20);
        e3=findViewById (R.id.editText3);
        e4=findViewById (R.id.editText4);
        e5=findViewById (R.id.editText5);
        e6=findViewById (R.id.editText6);
        b=findViewById (R.id.button11);
        mName   = (TextView)findViewById(R.id.textView10);
        mPic    = (ImageView) findViewById(R.id.imageView6);
        gmail=(TextView)findViewById (R.id.textView11);
        getCurrentinfo();

        b.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {

                String value = "Name:"+e2.getText().toString();
                String value2 ="Artist Name:"+e3.getText().toString();
                String value3 ="Link:"+e4.getText().toString();
                String value4 = "Social Media Link:"+e5.getText().toString();
                String value5 ="Ph.No:"+ e6.getText().toString();

                demoRef.child (value).child ("Name").setValue (value);
                demoRef.child (value).child ("Artist Name").setValue (value2);
                demoRef.child (value).child ("Link").setValue (value3);
                demoRef.child (value).child ("Social Media Link").setValue (value4);
                demoRef.child (value).child ("Phone Number").setValue (value5);

                startActivity (new Intent (UpdateProfile.this,NavTab.class));

            }


        });
    }
    private void getCurrentinfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
                name = profile.getDisplayName();
                mail=profile.getEmail ();
                photoUrl = profile.getPhotoUrl();
                mName.setText(name);
                gmail.setText (mail);

                Picasso.with(getApplicationContext())
                        .load(photoUrl.toString())
                        .placeholder(R.drawable.skull)
                        .resize(100, 100)
                        .transform(new CircularTransformation())
                        .centerCrop()
                        .into(mPic);


            };
        }
    }


}


