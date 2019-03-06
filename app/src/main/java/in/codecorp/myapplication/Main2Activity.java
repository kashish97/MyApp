package in.codecorp.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;

import in.codecorp.myapplication.Fragments.CategoryFragment;
import in.codecorp.myapplication.Fragments.HomeFragment;
import in.codecorp.myapplication.Fragments.MyOrderFragment;
import in.codecorp.myapplication.Fragments.ProfileFragment;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sessionManager =  new SessionManager(getApplicationContext());
       // HashMap<String, String> user = sessionManager.getUserDetails();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       Fragment fragment = new CategoryFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
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
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_camera) {
            fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
           // Handle the camera action
        } else if (id == R.id.nav_gallery) {
           fragment = new CategoryFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
        } else if (id == R.id.nav_slideshow) {
           fragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
        } else if (id == R.id.nav_manage) {
            fragment = new MyOrderFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
        }

        else if(id == R.id.logout){
            sessionManager.createLoginSession(null);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
