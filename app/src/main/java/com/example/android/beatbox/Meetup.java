package com.example.android.beatbox;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Meetup extends AppCompatActivity {
    DatabaseReference mRef, rootRef,demoRef,cRef;
    ListView mListView;

    private ArrayList<String> keysList = new ArrayList<> ();
    private ArrayList<String> mUsername=new ArrayList<> ();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_meetup);
        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child("meetup");
        mListView=findViewById (R.id.lv);
        mRef=rootRef.child ("deletedmeetup");
        cRef=rootRef.child ("Completedmeetup");
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,mUsername){
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent){
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                view.setBackground(getContext().getDrawable(R.drawable.divider));
                return view;

            }
        };
                mListView.setAdapter (arrayAdapter);

        demoRef.addChildEventListener (new ChildEventListener ( ) {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String string = dataSnapshot.getValue(String.class);

                mUsername.add(string);
                keysList.add(dataSnapshot.getKey());

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // whenever data at this location is updated.
                // mUsername.clear ();
                mUsername.add(dataSnapshot.getValue(String.class));

                arrayAdapter.notifyDataSetChanged ();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                String string = dataSnapshot.getValue(String.class);

                mUsername.remove(string);
                keysList.remove(dataSnapshot.getKey());

                arrayAdapter.notifyDataSetChanged();
                // startActivity (new Intent (ViewActivity.this,CancelledActivity.class));
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

