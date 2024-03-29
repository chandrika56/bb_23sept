package com.example.android.beatbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;



import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {


    SignInButton button;
    FirebaseAuth mAuth;
    private final static int RC_SIGN_IN=2;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart ( );
        mAuth.addAuthStateListener (mAuthListener);

    }

    GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        button=(SignInButton)findViewById (R.id.signInButton);
        mAuth= FirebaseAuth.getInstance ();

        mAuthListener=new FirebaseAuth.AuthStateListener ( ) {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser ()!=null){
                    startActivity (new Intent (LoginActivity.this,NavTab.class));
                }
            }
        };
        button.setOnClickListener (new OnClickListener ( ) {

            @Override
            public void onClick(View v) {
                signIn ();
            }
        });

        GoogleSignInOptions gso=new GoogleSignInOptions.Builder (GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken (getString (R.string.default_web_client_id))
                .requestEmail ()
                .build ();

        mGoogleApiClient=new GoogleApiClient.Builder (this)
                .enableAutoManage (this, new GoogleApiClient.OnConnectionFailedListener ( ) {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText (LoginActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show ();
                    }
                })
                .addApi (Auth.GOOGLE_SIGN_IN_API,gso)
                .build ();

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent (data);
            if(result.isSuccess ()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount ( );
                firebaseAuthWithGoogle (account);
            }else{
                Toast.makeText (LoginActivity.this,"Auth went wrong",Toast.LENGTH_SHORT).show ();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential= GoogleAuthProvider.getCredential (account.getIdToken (),null);
        mAuth.signInWithCredential (credential)
                .addOnCompleteListener (this, new OnCompleteListener<AuthResult> ( ) {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful ()){
                            Log.d("TAG","signInWithCredential:success");
                            FirebaseUser user=mAuth.getCurrentUser ();
                            //updateUI(null);
                        }else {

                            Log.w("TAG","signInWithCredential:failure",task.getException ());
                            Toast.makeText (LoginActivity.this,"Authentication failed",Toast.LENGTH_SHORT).show ();
                        }
                    }
                });
    }



}
