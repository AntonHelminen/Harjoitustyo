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
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.sql.SQLOutput;

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
        nav.setText(user.getPerson().getUsername());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Boolean resume  = true;
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    fragment = new HomeFragment();
                }
                else if (id == R.id.nav_add_data) {
                    fragment = new AddDataFragment();
                }
                else if (id == R.id.nav_view_data) {
                    fragment = new ViewDataFragment();
                }
                else if (id == R.id.nav_help) {
                    fragment = new HelpFragment();
                }
                else if (id == R.id.nav_logout) {
                    User user = User.getInstance();
                    for(String key: person_manager.getPeopleMap().keySet()) {
                        System.out.println(person_manager.getPeopleMap().get(key).getName());
                    }
                    person_manager.removePerson(user.getPerson().getPassword(), user.getPerson().getUsername());
                    person_manager.addPerson(user.getPerson());
                    person_manager.writeFile(context);
                    for(String key: person_manager.getPeopleMap().keySet()) {
                        System.out.println(person_manager.getPeopleMap().get(key).getBioWaste());
                    }
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
