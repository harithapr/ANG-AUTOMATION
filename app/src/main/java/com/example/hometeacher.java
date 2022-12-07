package com.example;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.anganwadi.R;
import com.example.anganwadi.settingsfrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class hometeacher extends AppCompatActivity {
    DrawerLayout drawer;
    ImageView menu, Notifi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hometeacher);

        drawer = findViewById(R.id.drawer_id);


        Bundle bundle = getIntent().getExtras();
        String module = bundle.get("Modules").toString();

        if (module.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No Module Selected", Toast.LENGTH_LONG).show();
        } else if (module.equals("Click here to select user")) {
            Toast.makeText(getApplicationContext(), "You didnt slect a module", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), module, Toast.LENGTH_LONG).show();
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        menu = findViewById(R.id.nav);
        Notifi = findViewById(R.id.notifi);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_view);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        /**
         * inflating menu in navigation view by checking the module selceted
         */
        if (module.equals("PANCHAYATH")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.panchayathnavdrawer);
            Fragment fr = panchayathhomepage.newInstance("dff", "sdd");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction tns = fragmentManager.beginTransaction();
            tns.replace(R.id.frame_layout, fr);
            tns.commit();
        }
        else if (module.equals("TEACHER")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.teachernavdrawer);
            Fragment fr = anganteacherhomepage.newInstance("dff", "sdd");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction tns = fragmentManager.beginTransaction();
            tns.replace(R.id.frame_layout, fr);
            tns.commit();
        }
        else if (module.equals("USER")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.usernavdrawer);
            Fragment fr = userhomepage.newInstance("dff", "sdd");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction tns = fragmentManager.beginTransaction();
            tns.replace(R.id.frame_layout, fr);
            tns.commit();
        } else if (module.equals("CDPO")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.navdrawer);
            Fragment fr = homepagefrag.newInstance("dff", "sdd");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction tns = fragmentManager.beginTransaction();
            tns.replace(R.id.frame_layout, fr);
            tns.commit();
        } else if (module.equals("SUPERVISOR")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.supervisornavdrawer);
            Fragment fr = supervisorhomepage.newInstance("dff", "sdd");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction tns = fragmentManager.beginTransaction();
            tns.replace(R.id.frame_layout, fr);
            tns.commit();
        } else if (module.equals("HEALTH INSPECTOR")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.healthnavdrawer);
            Fragment fr = healthinspectorhomepagefrag.newInstance("dff", "sdd");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction tns = fragmentManager.beginTransaction();
            tns.replace(R.id.frame_layout, fr);
            tns.commit();
        }

        Notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte=new Intent(hometeacher.this,Notificationxmljava.class);
                startActivity(inte);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //panchayath
                if (module.equals("PANCHAYATH")) {
                    int itemId = item.getItemId();

                    if (itemId == R.id.view_id) {
                        Intent i = new Intent(hometeacher.this, ViewSupervisorDutiesButtonnew.class);
                        startActivity(i);

                    } else if (itemId == R.id.view_id1) {
                        Intent i = new Intent(hometeacher.this, reportpanchayath.class);
                        startActivity(i);
                    } else if (itemId == R.id.view_id2) {
                        Intent i = new Intent(hometeacher.this, panchayathnotification.class);
                        startActivity(i);
                    }else if (itemId == R.id.view_id3){
                        Intent i = new Intent(hometeacher.this,Complaintviewpanchayath.class);
                        startActivity(i);
                    }/*else if (itemId==R.id.view_id4){
                        Intent i =new Intent(hometeacher.this,Foodstockdetailviewuser.class);
                        startActivity(i);
                    }*/
                }


                //CDPO
                else if (module.equals("CDPO")) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.view_id) {
                        Intent i = new Intent(hometeacher.this, ViewSupervisorDutiesButtonnew.class);
                        startActivity(i);
                        return true;

                    }else if(itemId == R.id.food){
                        Intent i= new Intent(hometeacher.this,viewfoodinspectionreport.class);
                        startActivity(i);
                    }

                }


                //Teacher
                else if (module.equals("TEACHER")) {
                    int itemId = item.getItemId();

                    if (itemId == R.id.mem_id) {
                        Intent i = new Intent(hometeacher.this, memberviewhomedetails.class);
                        startActivity(i);

                    } else if (itemId == R.id.teacherid) {
                        Intent i = new Intent(hometeacher.this, reportteacher.class);
                        startActivity(i);
                    }else if (itemId == R.id.Pregnentwomenid){
                        Intent i = new Intent(hometeacher.this,viewpregnentwomens.class);
                        startActivity(i);
                    }else if (itemId == R.id.viewstudatten){
                        Intent i = new Intent(hometeacher.this,ViewAttendancexmljava.class);
                        startActivity(i);
                    }else if (itemId == R.id.stud_id){
                        Intent i=new Intent(hometeacher.this,Studentdeatailsxmljava.class);
                        startActivity(i);
                    }else if (itemId==R.id.foodstockdetails){
                        Intent i= new Intent(hometeacher.this,foodstockdetails.class);
                        startActivity(i);
                    }else if (itemId==R.id.children){
                        Intent i = new Intent(hometeacher.this,viewchildren0to3yearsdetails.class);
                        startActivity(i);
                    }
                }
                    //healthinspector
                    else if (module.equals("HEALTH INSPECTOR")) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.food) {
                            Intent i = new Intent(hometeacher.this, viewfoodinspectionreport.class);
                            startActivity(i);

                        }else if (itemId==R.id.view_id2){
                            Intent i=new Intent(hometeacher.this,panchayathnotification.class);
                            startActivity(i);
                        }else if(itemId == R.id.vaccine){
                            Intent i =new Intent(hometeacher.this,vaccineviewstudents.class);
                            startActivity(i);
                        }
                    }
                    //user
                    else if (module.equals("USER")) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.view_id) {
                            Intent i = new Intent(hometeacher.this, viewcomplaint.class);
                            startActivity(i);


                        }else if (itemId==R.id.vaccine){
                            Intent i =new Intent(hometeacher.this,vaccineviewstudents.class);
                            startActivity(i);
                        }/*else if (itemId==R.id.food);
                        Intent i = new Intent(hometeacher.this,FoodStockDetailsViewNew.class);
                        startActivity(i);*/
                    }
                    //supervisor
                    else if (module.equals("SUPERVISOR")) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.view_id2) {
                            Intent i = new Intent(hometeacher.this, reportssupervisor.class);
                            startActivity(i);
                        }
                    }
                    return false;


                }

            });



        /**
         * Inflate layout for bottomnavigationview
         *
         */


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //CDPO
                if (module.equals("CDPO")) {
                    Fragment selectedFragment = null;
                    int itemId = item.getItemId();

                    if (itemId == R.id.home) {
                        selectedFragment = new homepagefrag();

                    } else if (itemId == R.id.profile) {
                        selectedFragment = new profilefrag();
                    } else if (itemId == R.id.settings) {
                        selectedFragment = new settingsfrag();
                    }
                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
                    }
                }

                //Panchayath
                else if (module.equals("PANCHAYATH")) {
                    Fragment selectedFragment = null;

                    int itemId = item.getItemId();

                    if (itemId == R.id.home) {
                        selectedFragment = new panchayathhomepage();

                    }
                    else if (itemId == R.id.profile) {
                        selectedFragment = new panchayathprofilefrag();
                    }
                    else if (itemId == R.id.settings) {
                        selectedFragment = new settingsfrag();
                    }
                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
                    }
                }

                //TEACHER
                else if (module.equals("TEACHER")){
                    Fragment selectedFragment = null;
                    int itemId = item.getItemId();

                    if (itemId == R.id.home) {
                        selectedFragment = new anganteacherhomepage();

                    } else if (itemId == R.id.profile) {
                        selectedFragment = new teacherprofilefrag();
                    } else if (itemId == R.id.settings) {
                        selectedFragment = new settingsfrag();
                    }
                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
                    }
                }
                //user
                else if (module.equals("USER")){
                    Fragment selectedFragment = null;
                    int itemId = item.getItemId();

                    if (itemId == R.id.home) {
                        selectedFragment = new userhomepage();

                    } else if (itemId == R.id.profile) {
                        selectedFragment = new userprofilefrag();
                    } else if (itemId == R.id.settings) {
                        selectedFragment = new settingsfrag();
                    }
                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
                    }
                }
                //health inspector
                else if (module.equals("HEALTH INSPECTOR")){
                    Fragment selectedFragment = null;
                    int itemId = item.getItemId();

                    if (itemId == R.id.home) {
                        selectedFragment = new healthinspectorhomepagefrag();

                    } else if (itemId == R.id.profile) {
                        selectedFragment = new healthinspectorprofilefrag();
                    } else if (itemId == R.id.settings) {
                        selectedFragment = new settingsfrag();
                    }
                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
                    }
                }
                //supervisor
                else if (module.equals("SUPERVISOR")){
                    Fragment selectedFragment = null;
                    int itemId = item.getItemId();

                    if (itemId == R.id.home) {
                        selectedFragment = new supervisorhomepage();

                    } else if (itemId == R.id.profile) {
                        selectedFragment = new supervisorprofilefrag();
                    } else if (itemId == R.id.settings) {
                        selectedFragment = new settingsfrag();
                    }
                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();
                    }
                }



                return false;
            }
        });




        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);

            }
        });

    }

        }