package com.example;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anganwadi.R;
import com.example.anganwadi.databinding.ActivityViewsupervisorBinding;
import com.google.firebase.database.DatabaseReference;


public class viewsupervisor extends AppCompatActivity {
    ActivityViewsupervisorBinding binding;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsupervisor);
        /*readdata("aadhar");

    }

    private void readdata(String aadhar) {
        //reference = FirebaseDatabase.getInstance().getReferenceFromUrl("");
        reference.child(aadhar).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    if (task.getResult().exists()) {

                        Toast.makeText(viewsupervisor.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String SupervisorNameStr = String.valueOf(dataSnapshot.child("Supervisor name").getValue());
                        String SupervisoremailStr = String.valueOf(dataSnapshot.child("Supervisor email").getValue());
                        String SupervisorphnoStr = String.valueOf(dataSnapshot.child("Supervisor phno").getValue());
                        String SupervisordobrStr = String.valueOf(dataSnapshot.child("Supervisor dob").getValue());
                        String SupervisoraddressStr = String.valueOf(dataSnapshot.child("Supervisor address").getValue());
                        String SupervisoraadharnoStr = String.valueOf(dataSnapshot.child("Supervisor aadharno").getValue());
                        String SupervisorplaceStr = String.valueOf(dataSnapshot.child("Supervisor place").getValue());
                        String SupervisordistrictStr = String.valueOf(dataSnapshot.child("Supervisor district").getValue());
                        String SupervisorstateStr = String.valueOf(dataSnapshot.child("Supervisor state").getValue());
                        String SupervisorpanchayathnameStr = String.valueOf(dataSnapshot.child("Supervisor panchayathname").getValue());
                        binding.tname.setText(SupervisorNameStr);
                        binding.temail.setText(SupervisoremailStr);
                        binding.mobileno.setText(SupervisorphnoStr);
                        binding.tdob.setText(SupervisordobrStr);
                        binding.taddress.setText(SupervisoraddressStr);
                        binding.taadharid.setText(SupervisoraadharnoStr);
                        binding.tpanchayath.setText(SupervisorpanchayathnameStr);
                        binding.tplace.setText(SupervisorplaceStr);
                        binding.tdistrict.setText(SupervisordistrictStr);
                        binding.tstate.setText(SupervisorstateStr);


                    } else {
                        Toast.makeText(viewsupervisor.this, "user does not exist", Toast.LENGTH_SHORT).show();

                    }
                } else {

                    Toast.makeText(viewsupervisor.this, "Failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    */
}
}
