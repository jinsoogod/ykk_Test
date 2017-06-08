package com.example.jklim.ykk_git;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String button_mode = "ON";
    private String shake_mode = "ON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.setting_btn);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        Button siren_btn = (Button)findViewById(R.id.siren_btn);

        siren_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Emergency_Activity.class);
                startActivity(intent);
            }
        });

        Button map_btn = (Button)findViewById(R.id.map_btn);

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),maps.class);
                startActivity(intent);
            }
        });

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.name) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("닉네임 설정");

            final EditText edit_name = new EditText(this);
            alert.setView(edit_name);


            alert.setNegativeButton("저장",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String username = edit_name.getText().toString();
                    item.setTitle(username);
                }
            });

            alert.setPositiveButton("취소",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            });


            alert.show();
        } else if (id == R.id.number) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("긴급번호 설정");

            final EditText edit_name = new EditText(this);
            alert.setView(edit_name);


            alert.setNegativeButton("저장",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String username = edit_name.getText().toString();
                    item.setTitle(username);
                }
            });

            alert.setPositiveButton("취소",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            });
            alert.show();
        } else if (id == R.id.shake) {
            if(shake_mode == "ON") {
                startService(new Intent(this, Shake.class));
                Toast.makeText(this,shake_mode,Toast.LENGTH_SHORT).show();
                item.setTitle(shake_mode);
                item.setIcon(R.drawable.on01);
                shake_mode = "OFF";
            }
            else if(shake_mode == "OFF"){
                stopService(new Intent(this, Shake.class));
                Toast.makeText(this,shake_mode,Toast.LENGTH_SHORT).show();
                item.setTitle(shake_mode);
                item.setIcon(R.drawable.off01);
                shake_mode = "ON";
            }
        } else if (id == R.id.fast_button) {
            if(button_mode == "ON") {
                startService(new Intent(this, Fast_button.class));
                Toast.makeText(this,button_mode,Toast.LENGTH_SHORT).show();
                item.setTitle(button_mode);
                item.setIcon(R.drawable.on01);
                button_mode = "OFF";
            }
            else if(button_mode == "OFF"){
                stopService(new Intent(this, Fast_button.class));
                Toast.makeText(this,button_mode,Toast.LENGTH_SHORT).show();
                item.setTitle(button_mode);
                item.setIcon(R.drawable.off01);
                button_mode = "ON";
            }
        } else if (id == R.id.message) {



            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("메세지 설정");

            final EditText edit_name = new EditText(this);
            alert.setView(edit_name);


            alert.setNegativeButton("저장",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String username = edit_name.getText().toString();
                    item.setTitle(username);
                }
            });

            alert.setPositiveButton("취소",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            });


            alert.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}