package com.example.android.beatbox;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ArtistProfiles extends AppCompatActivity {
    DatabaseReference rootRef,cRef;
    ListView mListView;
    private ArrayList<String> keysList=new ArrayList<> ();
    private ArrayList<String> mUsername=new ArrayList<> ();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_artist_profiles);
        rootRef = FirebaseDatabase.getInstance().getReference();
        cRef=rootRef.child("User details");
        mListView=findViewById (R.id.lv);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<> (this,android.R.layout.simple_list_item_1,mUsername);
        mListView.setAdapter (arrayAdapter);

        cRef.addChildEventListener (new ChildEventListener ( ) {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child ("Name").getValue(String.class);
                String name2 = dataSnapshot.child ("Artist Name").getValue(String.class);
                String name3 = dataSnapshot.child ("Link").getValue(String.class);
                String name4 = dataSnapshot.child ("Phone Number").getValue(String.class);
                String name5 = dataSnapshot.child ("Social Media Link").getValue(String.class);
                mUsername.add(name+"\n"+name2+"\n"+name3+"\n"+name4+"\n"+name5);
                keysList.add(dataSnapshot.getKey());
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

