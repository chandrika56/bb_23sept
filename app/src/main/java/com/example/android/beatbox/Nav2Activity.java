package com.example.android.beatbox;

import android.annotation.SuppressLint;
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
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Nav2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView mName;
    private String name;
    private TextView gmail;
    private String mail;
    private Uri photoUrl;
    private ImageView mPic;

    private EditText addressBar;

    private WebView webView;
    private Button buttonGo;
    private Button buttonStatic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_nav2);



        addressBar =(EditText)findViewById(R.id.editText_addressBar);
        webView =(WebView)findViewById(R.id.webView);

        // Custom WebViewClient to handle event on WebView.
        webView.setWebViewClient(new MyWebViewClient(addressBar));





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
                    startActivity(new Intent (Nav2Activity.this,MainActivity.class));
                }
            }
        };
        NavigationView navigationView = (NavigationView) findViewById (R.id.nav_view);
        navigationView.setNavigationItemSelectedListener (this);
        mName   = (TextView)navigationView.getHeaderView(0).findViewById(R.id.textView);
        mPic    = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);
        gmail    =  navigationView.getHeaderView(0).findViewById(R.id.gmail);
        getCurrentinfo();
        mPic.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (Nav2Activity.this,UpdateProfile.class));
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
    @SuppressLint("SetJavaScriptEnabled")



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ( ).inflate (R.menu.nav2, menu);
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

        if (id == R.id.nav_solo) {
            String url = "https://www.youtube.com/results?search_query="+"solo Beatbox";

            if(url.isEmpty())  {
                Toast.makeText(this,"Please enter url", Toast.LENGTH_SHORT).show();
            }
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(url);
        } else if (id == R.id.nav_tag) {
            String url = "https://www.youtube.com/results?search_query="+"TagTeam Beatbox";

            if(url.isEmpty())  {
                Toast.makeText(this,"Please enter url", Toast.LENGTH_SHORT).show();
            }
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(url);
        } else if (id == R.id.nav_smoke) {
            String url = "https://www.youtube.com/results?search_query="+"7 to Smoke Beatbox";

            if(url.isEmpty())  {
                Toast.makeText(this,"Please enter url", Toast.LENGTH_SHORT).show();
            }
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(url);
        } else if (id == R.id.nav_loop) {
            String url = "https://www.youtube.com/results?search_query="+"Loop station Beatbox";

            if(url.isEmpty())  {
                Toast.makeText(this,"Please enter url", Toast.LENGTH_SHORT).show();
            }
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(url);
        } else if (id == R.id.nav_crew) {
            String url = "https://www.youtube.com/results?search_query="+"Crew Beatbox";

            if(url.isEmpty())  {
                Toast.makeText(this,"Please enter url", Toast.LENGTH_SHORT).show();
            }
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(url);
        } else if (id == R.id.nav_Jamming) {
            String url = "https://www.youtube.com/results?search_query="+"Jamming Beatbox";

            if(url.isEmpty())  {
                Toast.makeText(this,"Please enter url", Toast.LENGTH_SHORT).show();
            }
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(url);
        }
        else if (id == R.id.nav_studio) {
            String url = "https://www.youtube.com/results?search_query="+"Studio session Beatbox";

            if(url.isEmpty())  {
                Toast.makeText(this,"Please enter url", Toast.LENGTH_SHORT).show();
            }
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(url);
        }
        else if (id == R.id.nav_beats) {
            String url = "https://www.youtube.com/results?search_query="+"Beats Club Beatbox";

            if(url.isEmpty())  {
                Toast.makeText(this,"Please enter url", Toast.LENGTH_SHORT).show();
            }
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(url);
        }
        else if (id == R.id.nav_all) {
            String url = "https://www.youtube.com/results?search_query="+"Variety Beatbox";

            if(url.isEmpty())  {
                Toast.makeText(this,"Please enter url", Toast.LENGTH_SHORT).show();
            }
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(url);
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
                photoUrl = profile.getPhotoUrl();
                mName.setText(name);
                gmail.setText (profile.getEmail ());

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
