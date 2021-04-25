package com.example.harjoitustyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

/*MainActivity. All fragment swaps are controlled here as well as logging off.*/
public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    Fragment fragment;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Person_manager person_manager = Person_manager.getInstance();
        Context context = MainActivity.this;

        /* Making DrawerLayout and NavigationView to create SideMenu */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        //Setting header title:
        User user = User.getInstance();
        View hviev = navigationView.getHeaderView(0);
        TextView nav = (TextView) hviev.findViewById(R.id.textView);
        nav.setText(user.getPerson().getName());

        //Setting fragment at start based on previous visit
        Person person = user.getPerson();
        String previousfragment = person.getFragment();
        if (previousfragment == null) {
            fragment = new HomeFragment();
        }
        else {
            if (previousfragment.equals("Home")) {
                fragment = new HomeFragment();
            }
            else if (previousfragment.equals("Data")){
                fragment = new AddDataFragment();
            }
            else if (previousfragment.equals("View")){
                fragment = new ViewDataFragment();
            }
            else if (previousfragment.equals("Help")){
                fragment = new HelpFragment();
            }
            else if (previousfragment.equals("Profile")) {
                fragment = new ProfileFragment();
            }
            else {
                fragment = new HomeFragment();
            }
        }


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentWindow, fragment);
        transaction.commit();
        //Setting fragment at start based on previous visit
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Boolean resume  = true;
                Person person;
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    fragment = new HomeFragment();
                    person = user.getPerson();
                    person.setFragment("Home");
                    user.setPerson(person);
                }
                else if (id == R.id.nav_profile) {
                    fragment = new ProfileFragment();
                    person = user.getPerson();
                    person.setFragment("Profile");
                    user.setPerson(person);
                }
                else if (id == R.id.nav_add_data) {
                    fragment = new AddDataFragment();
                    person = user.getPerson();
                    person.setFragment("Data");
                    user.setPerson(person);
                }
                else if (id == R.id.nav_view_data) {
                    fragment = new ViewDataFragment();
                    person = user.getPerson();
                    person.setFragment("View");
                    user.setPerson(person);
                }
                else if (id == R.id.nav_help) {
                    fragment = new HelpFragment();
                    person = user.getPerson();
                    person.setFragment("Help");
                    user.setPerson(person);
                }
                else if (id == R.id.nav_logout) {
                    //Replacing original file data with user's data

                    person_manager.removePerson(user.getPerson().getPassword(), user.getPerson().getUsername());
                    person_manager.addPerson(user.getPerson());
                    person_manager.writeFile(context);

                    logout();
                    resume = false;
                    finish();
                }
                if (resume) {
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragmentWindow, fragment);
                    transaction.commit();
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item))   {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
