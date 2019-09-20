package com.example.android.beatbox;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView mName;
private TextView gmail;
    private String name;
    private String mail;
    private Uri photoUrl;
    private ImageView mPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById (R.id.fab);
        fab.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Snackbar.make (view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction ("Action", null).show ( );
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById (R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener (toggle);
        toggle.syncState ( );
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent (Main2Activity.this,MainActivity.class));
                }
            }
        };
        NavigationView navigationView = (NavigationView) findViewById (R.id.nav_view);
        navigationView.setNavigationItemSelectedListener (this);
        mName   = (TextView)navigationView.getHeaderView(0).findViewById(R.id.textView);
        mPic    = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);
        gmail=(TextView)navigationView.getHeaderView (0).findViewById (R.id.gmail);
        getCurrentinfo();
mPic.setOnClickListener (new View.OnClickListener ( ) {
    @Override
    public void onClick(View v) {
        startActivity (new Intent (Main2Activity.this,UpdateProfile.class));
    }
});



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById (R.id.drawer_layout);
        if (drawer.isDrawerOpen (GravityCompat.START)) {
            drawer.closeDrawer (GravityCompat.START);
        } else {
            super.onBackPressed ( );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ( ).inflate (R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ( );

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected (item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId ( );

        if (id == R.id.nav_camera) {
           startActivity (new Intent (this,Linksubmit.class));
        } else if (id == R.id.nav_gallery) {
startActivity (new Intent (this,FavActivity.class));
        } else if (id == R.id.nav_slideshow) {
            startActivity (new Intent (this,MapsActivity.class));
        }
        else if (id == R.id.nav_manage) {
        }
            else if (id == R.id.nav_mana) {
startActivity (new Intent (this,Tutors.class));
        } else if (id == R.id.nav_share) {
startActivity (new Intent (this,Contact.class));
        } else if (id == R.id.nav_send) {
startActivity (new Intent (this,Aboutus.class));
        }
        else if (id == R.id.nav_sen) {

        }
        else if (id == R.id.nav_se) {
startActivity (new Intent (this,UpdateProfile.class));
        }
        else if (id == R.id.nav_s) {
startActivity (new Intent (this,ArtistProfiles.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById (R.id.drawer_layout);
        drawer.closeDrawer (GravityCompat.START);
        return true;
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
