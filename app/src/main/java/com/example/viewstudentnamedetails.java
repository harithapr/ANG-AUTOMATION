package com.example;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class viewstudentnamedetails extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference;
    TextView name,fathername,mothername,email,dob,address,aadharno,village,ward,panchayath,birthplace,anganwadiid;
    String nameStr,anganvadiStr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstudentnamedetails);

        name=findViewById(R.id.vname);
        fathername=findViewById(R.id.vfathername);
        mothername=findViewById(R.id.vmothername);
        email=findViewById(R.id.vemailid);
        dob=findViewById(R.id.vdob);
        address=findViewById(R.id.vaddress);
        aadharno=findViewById(R.id.vaadharno);
        village=findViewById(R.id.vvillage);
        ward=findViewById(R.id.vward);
        panchayath=findViewById(R.id.vpanchayath);
        birthplace=findViewById(R.id.vbirthplace);
        anganwadiid=findViewById(R.id.vanagnwadiid);


        Bundle bundle=getIntent().getExtras();
        anganvadiStr=bundle.get("AnganwadiId").toString();
        nameStr=bundle.get("Name").toString();
        Toast.makeText(getApplicationContext(),anganvadiStr+nameStr,Toast.LENGTH_SHORT).show();

        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        databaseReference.child("STUDENT1").child(anganvadiStr).child(nameStr).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {



            }
                String nameStr = snapshot.child("Name").getValue().toString();
               String fathernameStr = snapshot.child("fathername").getValue().toString();
                String mothernameStr = snapshot.child("mothername").getValue().toString();
                String emailStr = snapshot.child("email").getValue().toString();
                String dobStr = snapshot.child("dob").getValue().toString();
                String addressStr = snapshot.child("address").getValue().toString();
                String aadharnoStr = snapshot.child("aadharno").getValue().toString();
                String villageStr = snapshot.child("village").getValue().toString();
                String wardStr = snapshot.child("ward").getValue().toString();
                String panchayathStr = snapshot.child("panchayath").getValue().toString();
                String birthplaceStr = snapshot.child("birthplace").getValue().toString();
                String anganwadiidStr = snapshot.child("AnganwadiId").getValue().toString();



                name.setText(nameStr);
              fathername.setText(fathernameStr);
                mothername.setText(mothernameStr);
                email.setText(emailStr);
                dob.setText(dobStr);
                address.setText(addressStr);
                aadharno.setText(aadharnoStr);
                village.setText(villageStr);
                ward.setText(wardStr);
                panchayath.setText(panchayathStr);
                birthplace.setText(birthplaceStr);
                anganwadiid.setText(anganwadiidStr);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(viewstudentnamedetails.this,"Error"+error.getMessage().toString() ,Toast.LENGTH_SHORT).show();
            }

            });
    }
}