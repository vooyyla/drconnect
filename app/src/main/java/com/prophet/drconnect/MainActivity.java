package com.prophet.drconnect;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prophet.drconnect.adapters.ChatMessageAdapter;
import com.prophet.drconnect.fragments.AppointmentsFragment;
import com.prophet.drconnect.fragments.ChatsFragment;
import com.prophet.drconnect.fragments.ClinicsFragment;
import com.prophet.drconnect.fragments.DoctorsFragment;
import com.prophet.drconnect.fragments.HomeFragment;
import com.prophet.drconnect.fragments.ServicesFragment;
import com.prophet.drconnect.models.ChatMessage;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN = 1;

    private String mUsername;
    private ChildEventListener childEventListener;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        // Set Home fragment by default
        Fragment fragment = new HomeFragment();
        displaySelectedFragment(fragment);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.EmailBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder().build()))
                                            .build(),
                                            RC_SIGN_IN);
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in canceled (show stacktrace here)", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_menu_home) {
            fragment = new HomeFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_menu_appointments) {
            fragment = new AppointmentsFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_menu_clinics) {
            fragment = new ClinicsFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_menu_doctors) {
            fragment = new DoctorsFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_menu_services) {
            fragment = new ServicesFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_menu_chats) {
            fragment = new ChatsFragment();
            displaySelectedFragment(fragment);

        }else if (id == R.id.nav_menu_about_us) {
            fragment = new DoctorsFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_menu_contact_us) {
            fragment = new DoctorsFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_menu_news) {
            fragment = new DoctorsFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_menu_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_menu_sign_out) {
            AuthUI.getInstance().signOut(this);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Loads the specified fragment to the frame
     *
     * @param fragment
     */
    private void displaySelectedFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.recycler_view, fragment);
        fragmentTransaction.commit();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
        detachDatabaseReadListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);
    }



    private void onSignedInInitialize(String username) {
        mUsername = username;
        attachDatabaseReadListener();
    }

    private void onSignoutCleanUp(String username) {
        mUsername = username;
        detachDatabaseReadListener();
        finish();
    }

    private void attachDatabaseReadListener() {
        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    // Do something here


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
            };
            databaseReference.addChildEventListener(childEventListener);
        }
    }

    private void detachDatabaseReadListener() {
        if (childEventListener != null) {
            databaseReference.removeEventListener(childEventListener);
        }
    }
}
