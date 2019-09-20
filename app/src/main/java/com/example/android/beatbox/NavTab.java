package com.example.android.beatbox;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NavTab extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView20,recyclerView,recyclerView2,recyclerView3,recyclerView4,recyclerView5,recyclerView6,recyclerView7,recyclerView8,recyclerView9,recyclerView10,recyclerView11;
    Vector<YouTubeVideos> youtubeVideos = new Vector<YouTubeVideos> ();
    Vector<YouTubeVideos> youtubeVideos20 = new Vector<YouTubeVideos> ();
    Vector<YouTubeVideos> youtubeVideos2 = new Vector<YouTubeVideos> ();
    Vector<YouTubeVideos> youtubeVideos3 = new Vector<YouTubeVideos> ();
    Vector<YouTubeVideos> youtubeVideos4 = new Vector<YouTubeVideos> ();
    Vector<YouTubeVideos> youtubeVideos5 = new Vector<YouTubeVideos> ();
    Vector<YouTubeVideos> youtubeVideos6 = new Vector<YouTubeVideos> ();
    Vector<YouTubeVideos> youtubeVideos7 = new Vector<YouTubeVideos> ();
    Vector<YouTubeVideos> youtubeVideos8 = new Vector<YouTubeVideos> ();
    Vector<YouTubeVideos> youtubeVideos9 = new Vector<YouTubeVideos> ();
    Vector<YouTubeVideos> youtubeVideos10 = new Vector<YouTubeVideos> ();
    Vector<YouTubeVideos> youtubeVideos11 = new Vector<YouTubeVideos> ();

    TextView mMyButton,sc;
    WebView mWebView;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView mName;
    ImageButton i1,i2;
    private String name;
    DatabaseReference rootRef,demoRef,cRef;
    private TextView gmail;

    private String mail;
    private Uri photoUrl;
    private ImageView mPic;
    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_nav_tab);
        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        rootRef = FirebaseDatabase.getInstance().getReference();
        cRef=rootRef.child("pri");
        demoRef=rootRef.child ("Adminlink");
        sc=findViewById (R.id.textView14);
        FloatingActionButton fab = (FloatingActionButton) findViewById (R.id.fab);
        fab.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (NavTab.this,Linksubmit.class));
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
                    startActivity(new Intent (NavTab.this,MainActivity.class));
                }
            }
        };
        AdView adView = new AdView (this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-1630308447220111/6027078494");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        NavigationView navigationView = (NavigationView) findViewById (R.id.nav_view);
        navigationView.setNavigationItemSelectedListener (this);

        mName   = (TextView)navigationView.getHeaderView(0).findViewById(R.id.textView);
        mPic    = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);
        gmail=navigationView.getHeaderView(0).findViewById(R.id.gmail);

        mWebView=findViewById (R.id.videoWebView);

        mPic.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (NavTab.this,UpdateProfile.class));
            }
        });

        getCurrentinfo();
       /* mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
*/  new Handler ().postDelayed(new Runnable() {
            @Override
            public void run() {

                mInterstitialAd = new InterstitialAd (NavTab.this);
                mInterstitialAd.setAdUnitId("ca-app-pub-1630308447220111/7276387465");
                AdRequest adRequest = new AdRequest.Builder()
                        .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                        .build();
                mInterstitialAd.loadAd(adRequest);
                mInterstitialAd.setAdListener(new AdListener () {
                    @Override

                    public void onAdLoaded() {

                        //  mWebView.onPause();    // This will pause videos and needs to be called for EVERY WebView you create
                        // mWebView.pauseTimers();
                        displayinterstitial();
                    }
                });

            }
        } , 60000);



        MobileAds.initialize(this, "ca-app-pub-1630308447220111/7276387465");

     /*   recyclerView20 = (RecyclerView) findViewById(R.id.recyclerView20);

        recyclerView20.setLayoutManager( new LinearLayoutManager (this,LinearLayoutManager.HORIZONTAL,false));
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/obsANB1rtYU\" frameborder=\"0\" allowfullscreen></iframe>") );



        VideoAdapter videoAdapter20 = new VideoAdapter(youtubeVideos20);

        recyclerView20.setAdapter(videoAdapter20);

*/


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager( new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration (this, LinearLayoutManager.HORIZONTAL));

        demoRef.child ("viral_videos").addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child ("1").getValue(String.class);
                String value2 = dataSnapshot.child ("2").getValue(String.class);
                String value3 = dataSnapshot.child ("3").getValue(String.class);
                String value4 = dataSnapshot.child ("4").getValue(String.class);
                String value5 = dataSnapshot.child ("5").getValue(String.class);
                String value6 = dataSnapshot.child ("6").getValue(String.class);

                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value2+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value3+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value4+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value5+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value6+"\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qcblzukoJqA\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/YLL4i0NHmsc\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/-U0AFQbfv88\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/YAyQ8NchlQ8\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CPKq9sDIs2M\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/PdbXD0ziSIk\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/a2zgdoP50js\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/mhA-FeUrQJg\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/fMFQtNOMbTk\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Nday2C4rBs0\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/-gHgXmMXvAg\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/p-XoMvxC4IY\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ctcDesDhmb0\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/5uDZBxBJf2Y\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/1dLQJCbDu28\" frameborder=\"0\" allowfullscreen></iframe>") );


                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

                recyclerView.setAdapter(videoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);

        recyclerView2.setLayoutManager( new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL,false));
        demoRef.child ("learn_videos").addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child ("1").getValue(String.class);
                String value2 = dataSnapshot.child ("2").getValue(String.class);
                String value3 = dataSnapshot.child ("3").getValue(String.class);
                String value4 = dataSnapshot.child ("4").getValue(String.class);
                String value5 = dataSnapshot.child ("5").getValue(String.class);
                String value6 = dataSnapshot.child ("6").getValue(String.class);

                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value2+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value3+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value4+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value5+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value6+"\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/YGAgERz-6nQ\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/b1-6SyiViYs\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/QOEjIsZS6e8\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/LYhMhMb5ByQ\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/wiyJlKcxV2w\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/jw6fs9bfpCE\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/IxF5eWAGq5Y\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/AtVHcB5E4p4\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/WpRprvtl0dk\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/-XLP1IHYUKw\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/H9I3ABNzn70\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/LjDpp-AT4lA\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/_HziA_wdgWc\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/EAHExoZIgjM\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/b2MJkTHgt1M\" frameborder=\"0\" allowfullscreen></iframe>") );

                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos2);

                recyclerView2.setAdapter(videoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager( new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL,false));
        demoRef.child ("learn_videos").addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child ("1").getValue(String.class);
                String value2 = dataSnapshot.child ("2").getValue(String.class);
                String value3 = dataSnapshot.child ("3").getValue(String.class);
                String value4 = dataSnapshot.child ("4").getValue(String.class);
                String value5 = dataSnapshot.child ("5").getValue(String.class);
                String value6 = dataSnapshot.child ("6").getValue(String.class);

                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value2+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value3+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value4+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value5+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos2.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value6+"\" frameborder=\"0\" allowfullscreen></iframe>") );


                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos2);

                recyclerView2.setAdapter(videoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        recyclerView3 = (RecyclerView) findViewById(R.id.recyclerView3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager( new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL,false));
        demoRef.child ("solo_videos").addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child ("1").getValue(String.class);
                String value2 = dataSnapshot.child ("2").getValue(String.class);
                String value3 = dataSnapshot.child ("3").getValue(String.class);
                String value4 = dataSnapshot.child ("4").getValue(String.class);
                String value5 = dataSnapshot.child ("5").getValue(String.class);
                String value6 = dataSnapshot.child ("6").getValue(String.class);

                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value2+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value3+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value4+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value5+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value6+"\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/yC4XF0DNoM4\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ad7iGkSY5Zw\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/mSdanvSdiOo\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/4w6EQMqCllM\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/lheFDdB0NWY\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/yefHtxAmWu4\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/OY-Ec8zX2bw\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KyNqhR1hg_w\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/F7An7Z5k0YI\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/dNrsa9kqyb4\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/c8f0mnrhtmA\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/JuQK8hJq_lY\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/f2SsEz57d4A\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/65xhYomywx0\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/JeyMB4CRzPs\" frameborder=\"0\" allowfullscreen></iframe>") );
                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos3);

                recyclerView3.setAdapter(videoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        /*youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/-7PxYcszErA\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/J8MfL6yjxeg\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/F6Y-eujlUpM\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/gdmsXrM7L3k\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/oTNLR89vvRI\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/SO47SN-7L7Q\" frameborder=\"0\" allowfullscreen></iframe>") );
*/
        youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8jokKZUBTTo\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/yW6EwhM9l68\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/lhcaBsD5CbA\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/xcPWiq1ZSDs\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/HE4DJTcMqCU\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Sd_2iYXJ3s4\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/roNzmnlMUWk\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/D5AtLWgBB-I\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/aRpS9LJUFVg\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos3.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/k1mfDOLZvY4\" frameborder=\"0\" allowfullscreen></iframe>") );


        recyclerView4 = (RecyclerView) findViewById(R.id.recyclerView4);
        recyclerView4.setHasFixedSize(true);
        recyclerView4.setLayoutManager( new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL,false));
        demoRef.child ("tag_videos").addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child ("1").getValue(String.class);
                String value2 = dataSnapshot.child ("2").getValue(String.class);
                String value3 = dataSnapshot.child ("3").getValue(String.class);
                String value4 = dataSnapshot.child ("4").getValue(String.class);
                String value5 = dataSnapshot.child ("5").getValue(String.class);
                String value6 = dataSnapshot.child ("6").getValue(String.class);

                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value2+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value3+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value4+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value5+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value6+"\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/e5Xl3H1NqRI\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/1rcHjRBO8Ek\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/mrK-qDoMKLk\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/DCkcmYkk9ko\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/p-XoMvxC4IY\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/QMuVrbQ9SGo\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7JXuxURXmeU\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/yIbjNcbEyug\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/TxoDJDvSHww\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/03yuNoxBbpQ\" frameborder=\"0\" allowfullscreen></iframe>") );

                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos4);

                recyclerView4.setAdapter(videoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
       /* youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8Mj0CjDU9Wo\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/SVT8aW11-Ng\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/FkGTl7lo4QY\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/LB-ljXf8eiY\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos4.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CHNN9NSN7RI\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos4.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/HuqcVowGx6w\" frameborder=\"0\" allowfullscreen></iframe>") );
        */

        recyclerView5 = (RecyclerView) findViewById(R.id.recyclerView5);
        recyclerView5.setHasFixedSize(true);
        recyclerView5.setLayoutManager( new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL,false));
        demoRef.child ("smoke_videos").addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child ("1").getValue(String.class);
                String value2 = dataSnapshot.child ("2").getValue(String.class);
                String value3 = dataSnapshot.child ("3").getValue(String.class);
                String value4 = dataSnapshot.child ("4").getValue(String.class);
                String value5 = dataSnapshot.child ("5").getValue(String.class);
                String value6 = dataSnapshot.child ("6").getValue(String.class);

                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value2+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value3+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value4+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value5+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value6+"\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/MpJq_66tCGk\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/el-sTabdNPA\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/x0FXlbLag6g\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/OjbYK4rujB0\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/JkCH9Y2wcPg\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/IwgloxqXn-s\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Irh_C6f7D_c\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/gSYUfFgJ9yo\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/URTd4pV2Omg\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ZKhem6OXi7g\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/PRQ4OX-xsbg\" frameborder=\"0\" allowfullscreen></iframe>") );


                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos5);

                recyclerView5.setAdapter(videoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
      /*  youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Irh_C6f7D_c\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/MpJq_66tCGk\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/AQphovQnwrE\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/JkCH9Y2wcPg\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos5.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Gclu8Azemag\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos5.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Dcn0VnBxTH4\" frameborder=\"0\" allowfullscreen></iframe>") );
        VideoAdapter videoAdapter5 = new VideoAdapter(youtubeVideos5);
        recyclerView5.setAdapter(videoAdapter5);
*/
        recyclerView6 = (RecyclerView) findViewById(R.id.recyclerView6);
        recyclerView6.setHasFixedSize(true);
        recyclerView6.setLayoutManager( new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL,false));
        demoRef.child ("loop_videos").addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child ("1").getValue(String.class);
                String value2 = dataSnapshot.child ("2").getValue(String.class);
                String value3 = dataSnapshot.child ("3").getValue(String.class);
                String value4 = dataSnapshot.child ("4").getValue(String.class);
                String value5 = dataSnapshot.child ("5").getValue(String.class);
                String value6 = dataSnapshot.child ("6").getValue(String.class);

                youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value2+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value3+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value4+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value5+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value6+"\" frameborder=\"0\" allowfullscreen></iframe>") );


                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos6);

                recyclerView6.setAdapter(videoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        /*youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/vWuSRXZSJ7Y\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/UWIAoMtcPbI\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/D5gF1ztRbPw\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/veIoayDknOs\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/d8nmMNRKxS4\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/rbdA6qyyfEc\" frameborder=\"0\" allowfullscreen></iframe>") );
*/
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/b_zTPuOMtL8\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/stHPewtg_KA\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/dBLPpvJGzy8\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/gxBJyhPHtLY\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/XA72-mzHkAY\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/NwDv0QSI0Jk\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/2I_Vd41Phfc\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/mIZlQQ6y-uo\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/fv1scc-v3g8\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Pyzg2ptktgQ\" frameborder=\"0\" allowfullscreen></iframe>") );

        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3SQg6Bm4DJs\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/be85CA2UBbM\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/fk1X0GiGlIU\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/MiHB0z_szqc\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/UWIAoMtcPbI\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ZHknRUTl9xY\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/tufkZKABM3M\" frameborder=\"0\" allowfullscreen></iframe>") );

        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/-U0AFQbfv88\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/lFos3m8S-ms\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/vgXuALpC3zs\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/mhA-FeUrQJg\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/vWuSRXZSJ7Y\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/wrXz_NUug8E\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ZHknRUTl9xY\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/1dLQJCbDu28\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3FPVxGMOAS4\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos6.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3r1Kpe-5u0Y\" frameborder=\"0\" allowfullscreen></iframe>") );

        VideoAdapter videoAdapter6 = new VideoAdapter(youtubeVideos6);
        recyclerView6.setAdapter(videoAdapter6);

        recyclerView7 = (RecyclerView) findViewById(R.id.recyclerView7);
        recyclerView7.setHasFixedSize(true);
        recyclerView7.setLayoutManager( new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL,false));
        demoRef.child ("crew").addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child ("1").getValue(String.class);
                String value2 = dataSnapshot.child ("2").getValue(String.class);
                String value3 = dataSnapshot.child ("3").getValue(String.class);
                String value4 = dataSnapshot.child ("4").getValue(String.class);
                String value5 = dataSnapshot.child ("5").getValue(String.class);
                String value6 = dataSnapshot.child ("6").getValue(String.class);

                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value2+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value3+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value4+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value5+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value6+"\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/19G10o8gPRY\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/YYqNv1CNb7M\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/YDQobMGKEw0\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ki1tKzvvwr0\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8c4buRPT944\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qcblzukoJqA\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/81HZteMAUA4\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/kIebh_bMeSg\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/PdbXD0ziSIk\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/wPXfHEXtZF8\" frameborder=\"0\" allowfullscreen></iframe>") );

                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos7);

                recyclerView7.setAdapter(videoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
 /*       youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/prKqkZRVeHQ\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eqVAF2C9oHc\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/aFotWzKDXXw\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cOusbrZrdZ4\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos7.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/FqXNXx_tDS4\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos7.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/QBY6GnzjObY\" frameborder=\"0\" allowfullscreen></iframe>") );
        VideoAdapter videoAdapter7 = new VideoAdapter(youtubeVideos7);
        recyclerView7.setAdapter(videoAdapter7);
*/
        recyclerView8 = (RecyclerView) findViewById(R.id.recyclerView8);
        recyclerView8.setHasFixedSize(true);
        recyclerView8.setLayoutManager( new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL,false));
        demoRef.child ("jam").addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child ("1").getValue(String.class);
                String value2 = dataSnapshot.child ("2").getValue(String.class);
                String value3 = dataSnapshot.child ("3").getValue(String.class);
                String value4 = dataSnapshot.child ("4").getValue(String.class);
                String value5 = dataSnapshot.child ("5").getValue(String.class);
                String value6 = dataSnapshot.child ("6").getValue(String.class);

                youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value2+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value3+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value4+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value5+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value6+"\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/YHT3PUuFdqA\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/GKMy2XJaPKw\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/wPXfHEXtZF8\" frameborder=\"0\" allowfullscreen></iframe>") );
                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos8);

                recyclerView8.setAdapter(videoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
 /*       youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/hjvXOyi0uEE\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/U5uyIyOXdnw\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/GNZBSZD16cY\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cB_c3GGv2dM\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos8.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0649Hc8kJ5w\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos8.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0649Hc8kJ5w\" frameborder=\"0\" allowfullscreen></iframe>") );
        VideoAdapter videoAdapter8 = new VideoAdapter(youtubeVideos8);
        recyclerView8.setAdapter(videoAdapter8);
*/
        recyclerView9 = (RecyclerView) findViewById(R.id.recyclerView9);
        recyclerView9.setHasFixedSize(true);
        recyclerView9.setLayoutManager( new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL,false));
        demoRef.child ("studio_videos").addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child ("1").getValue(String.class);
                String value2 = dataSnapshot.child ("2").getValue(String.class);
                String value3 = dataSnapshot.child ("3").getValue(String.class);
                String value4 = dataSnapshot.child ("4").getValue(String.class);
                String value5 = dataSnapshot.child ("5").getValue(String.class);
                String value6 = dataSnapshot.child ("6").getValue(String.class);

                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value2+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value3+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value4+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value5+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value6+"\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/d8ZgcH4TP5Y\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/6x6jpb_LPxM\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/iylC-AX684k\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/_gd5JPdCRU0\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/vIxhfqWbT-U\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/51Bq20FOCzk\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/UKGxBygm36c\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/pXRPoXSq488\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qtydtDX5PNg\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3e9CTKSlLh4\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qCobQ7MdGF0\" frameborder=\"0\" allowfullscreen></iframe>") );

                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos9);

                recyclerView9.setAdapter(videoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        /*youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/6x6jpb_LPxM\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ShVPYcsQos8\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/GNZBSZD16cY\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cB_c3GGv2dM\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos9.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0649Hc8kJ5w\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos9.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0649Hc8kJ5w\" frameborder=\"0\" allowfullscreen></iframe>") );
        VideoAdapter videoAdapter9 = new VideoAdapter(youtubeVideos9);
        recyclerView9.setAdapter(videoAdapter9);
*/
        recyclerView10 = (RecyclerView) findViewById(R.id.recyclerView10);
        recyclerView10.setHasFixedSize(true);
        recyclerView10.setLayoutManager( new LinearLayoutManager (this, LinearLayoutManager.HORIZONTAL,false));
        demoRef.child ("beats_club").addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child ("1").getValue(String.class);
                String value2 = dataSnapshot.child ("2").getValue(String.class);
                String value3 = dataSnapshot.child ("3").getValue(String.class);
                String value4 = dataSnapshot.child ("4").getValue(String.class);
                String value5 = dataSnapshot.child ("5").getValue(String.class);
                String value6 = dataSnapshot.child ("6").getValue(String.class);

                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value2+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value3+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value4+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value5+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value6+"\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/d8ZgcH4TP5Y\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/6x6jpb_LPxM\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/iylC-AX684k\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/_gd5JPdCRU0\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/vIxhfqWbT-U\" frameborder=\"0\" allowfullscreen></iframe>") );

                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/51Bq20FOCzk\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/UKGxBygm36c\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/pXRPoXSq488\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qtydtDX5PNg\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3e9CTKSlLh4\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qCobQ7MdGF0\" frameborder=\"0\" allowfullscreen></iframe>") );


                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos10);

                recyclerView10.setAdapter(videoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
 /*       youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/oMlufzK_lM0\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/VmWpu6w6mcM\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/GNZBSZD16cY\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cB_c3GGv2dM\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos10.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0649Hc8kJ5w\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos10.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0649Hc8kJ5w\" frameborder=\"0\" allowfullscreen></iframe>") );
        VideoAdapter videoAdapter10 = new VideoAdapter(youtubeVideos10);
        recyclerView10.setAdapter(videoAdapter10);
*/
        recyclerView11 = (RecyclerView) findViewById(R.id.recyclerView11);
        // recyclerView11.setHasFixedSize(true);

        recyclerView11.setLayoutManager( new LinearLayoutManager (this, LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration (this, LinearLayoutManager.VERTICAL));
        ;


// set the adapter
        demoRef.child ("All_videos").addListenerForSingleValueEvent(new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child ("1").getValue(String.class);
                String value2 = dataSnapshot.child ("2").getValue(String.class);
                String value3 = dataSnapshot.child ("3").getValue(String.class);
                String value4 = dataSnapshot.child ("4").getValue(String.class);
                String value5 = dataSnapshot.child ("5").getValue(String.class);
                String value6 = dataSnapshot.child ("6").getValue(String.class);

                youtubeVideos11.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos11.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value2+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos11.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value3+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos11.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value4+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos11.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value5+"\" frameborder=\"0\" allowfullscreen></iframe>") );
                youtubeVideos11.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+value6+"\" frameborder=\"0\" allowfullscreen></iframe>") );


                VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos11);

                recyclerView11.setAdapter(videoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        /*youtubeVideos11.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/obsANB1rtYU\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos11.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/zmHgs-eBuN8\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos11.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/GNZBSZD16cY\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos11.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cB_c3GGv2dM\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos11.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0649Hc8kJ5w\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos11.add (new YouTubeVideos ("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/0649Hc8kJ5w\" frameborder=\"0\" allowfullscreen></iframe>") );
        VideoAdapter videoAdapter11 = new VideoAdapter(youtubeVideos11);
        recyclerView11.setAdapter(videoAdapter11);
*/

    }


    public void displayinterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById (R.id.drawer_layout);
        if (mInterstitialAd.isLoaded())
            mInterstitialAd.show();

        if (drawer.isDrawerOpen (GravityCompat.START)) {
            drawer.closeDrawer (GravityCompat.START);
        } else {
            super.onBackPressed ( );
        }
    }
    private String getYouTubeId (String youTubeUrl) {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        getMenuInflater ( ).inflate (R.menu.nav_tab, menu);
        return true;

    }
    @Override
    protected void onPause(){
        super.onPause();
        if(mWebView != null){
            mWebView.onPause();
            mWebView.pauseTimers();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(mWebView != null){
            mWebView.onResume();
            mWebView.resumeTimers();
        }
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // a
        // s you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ( );
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity (new Intent (NavTab.this,Logout.class));

            return true;
        }

        switch (id){
            case R.id.viral:
                ScrollView s=findViewById (R.id.sc);
                s.smoothScrollTo (0,0);
                break;
            case R.id.learn:
                ScrollView sp=findViewById (R.id.sc);
                sp.smoothScrollTo (0,1100);
                break;
            case R.id.solo:
                ScrollView sq=findViewById (R.id.sc);
                sq.smoothScrollTo (0,2000);
                break;
            case R.id.tag:
                ScrollView a=findViewById (R.id.sc);
                a.smoothScrollTo (0,2700);
                break;
            case R.id.smoke:
                ScrollView b=findViewById (R.id.sc);
                b.smoothScrollTo (0,3500);
                break;
            case R.id.loop:
                ScrollView c=findViewById (R.id.sc);
                c.smoothScrollTo (0,4200);
                break;
            case R.id.crew:
                ScrollView d=findViewById (R.id.sc);
                d.smoothScrollTo (0,5000);
                break;
            case R.id.jamming:
                ScrollView e=findViewById (R.id.sc);
                e.smoothScrollTo (0,5700);
                break;
            case R.id.studio:
                ScrollView f=findViewById (R.id.sc);
                f.smoothScrollTo (0,6400);
                break;
            case R.id.beats:
                ScrollView g=findViewById (R.id.sc);
                g.smoothScrollTo (0,7100);
                break;
            case R.id.all:
                ScrollView h=findViewById (R.id.sc);
                h.smoothScrollTo (0,7800);
                break;
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

        } else if (id == R.id.nav_slideshow) {
            startActivity (new Intent (this,MapsActivity.class));
        }
        else if (id == R.id.nav_manage) {
            startActivity (new Intent (this,Meetup.class));
        }
        else if (id == R.id.nav_manag) {
            startActivity (new Intent (this,e.class));
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
            startActivity (new Intent (this, ArtistProfiles.class));
        }
        else if(id==R.id.help) {
            startActivity (new Intent (this,forms.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById (R.id.drawer_layout);
        drawer.closeDrawer (GravityCompat.START);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
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
                        .transform((Transformation) new CircularTransformation())
                        .centerCrop()
                        .into(mPic);


            };
        }
    }

}
