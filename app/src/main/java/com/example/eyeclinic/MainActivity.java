package com.example.eyeclinic;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView postList;
    private Toolbar mToolbar;

    private CircleImageView NavProfileImage;
    private TextView NavProfileUserName;

    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;

    String currentUserID;

    GridLayout HomeGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeGrid = (GridLayout)findViewById(R.id.HomeGrid);


        //Set Event
        setSingleEvent(HomeGrid);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            currentUserID = mAuth.getCurrentUser().getUid();

        }

        //currentUserID = mAuth.getCurrentUser().getUid();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");


        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawable_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle( MainActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView =(NavigationView) findViewById(R.id.navigation_view);
//
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
        NavProfileImage = (CircleImageView) navView.findViewById(R.id.nav_profile_image);
        NavProfileUserName = (TextView) navView.findViewById(R.id.nav_user_full_name);

        if(currentUserID != null) {
            UsersRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        if (dataSnapshot.hasChild("fullname")) {
                            String fullname = dataSnapshot.child("fullname").getValue().toString();
                            NavProfileUserName.setText(fullname);
                        }

                        if (dataSnapshot.hasChild("profileimage")) {
                            String image = dataSnapshot.child("profileimage").getValue().toString();
                            Picasso.with(MainActivity.this).load(image).placeholder(R.drawable.profile).into(NavProfileImage);
                        } else {
                           // Toast.makeText(MainActivity.this, "Profile name do not exists...", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                UserMenuSelector(item);
                return false;
            }
        });
    }


    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null)
        {
            SendUserToLogin();
        }
        else
        {
            CheckUserExistence();
        }
    }

    private void CheckUserExistence()
    {
        final String current_user_id = mAuth.getCurrentUser().getUid();
        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!dataSnapshot.hasChild(current_user_id))
                {
//                    SendUserToSetup();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {


            }
        });

    }
//
//    private void SendUserToSetup()
//    {
//        Intent setupIntent = new Intent(MainActivity.this, Setup.class);
//        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(setupIntent);
//        finish();
//    }

    private void SendUserToLogin()
    {
        Intent loginIntent = new Intent(MainActivity.this, Login.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void UserMenuSelector(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.nav_home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,MainActivity.class));
                break;

            case R.id.nav_contactUs:
                Toast.makeText(this, "Contact Us", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,ContactUs.class));
                break;

            case R.id.nav_schedule:
                Toast.makeText(this, "Schedule", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,NoteMessage.class));
                break;

            case R.id.nav_shop:
                Toast.makeText(this, "EyeCare", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,Eyecare.class));
                break;

            case R.id.nav_logout:
                mAuth.signOut();
                SendUserToLogin();
                break;
        }
    }


    //Home Screen Link Code

    private void setSingleEvent(GridLayout homeGrid) {

        //Loop all child item of HomeGrid
        for(int i = 0 ; i<HomeGrid.getChildCount() ; i++)
        {
            //You can see , all child item is CardView , so we cast object to CardView
            CardView cardView = (CardView)HomeGrid.getChildAt(i);
            final int finalI = i;
            final int finalI1 = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(finalI1 == 0)//Open Activity one
                    {
                        startActivity(new Intent(MainActivity.this,NoteMessage.class));
                    }
                    else if(finalI1 == 1)//Open Activity two
                    {
                        startActivity(new Intent(MainActivity.this,Eyecare.class));
                    }
                    else if(finalI1 == 2)//Open Activity three
                    {
                        //startActivity(new Intent(MainActivity.this,PostArticle.class));
                        linkme();

                    }
                    else if(finalI1 == 3)//Open Activity four
                    {
                        startActivity(new Intent(MainActivity.this,ContactUs.class));
                    }

                }
            });
        }
    }

    private void linkme (){
        String linkme = "https://www.youtube.com/channel/UCaolT1yocGQ-I_LsJvc38AQ";
        Intent BrowserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkme));
        startActivity(BrowserIntent);
    }


}

