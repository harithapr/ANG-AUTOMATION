package com.example;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anganwadi.PDFModelClass;
import com.example.anganwadi.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class reportpanchayath extends AppCompatActivity {

    Button upload;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    EditText panchayathid;
    String panchayathIdStr,reportNameStr;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportpanchayath);

        panchayathid=findViewById(R.id.panchayathid);
        upload=findViewById(R.id.upload);


        storageReference= FirebaseStorage.getInstance().getReferenceFromUrl("gs://anganwadiautomation.appspot.com/");
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 panchayathIdStr = panchayathid.getText().toString();


                Intent intent=new Intent(); intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select PDF Files..."),1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data.getData()!=null){
            UploadFiles(data.getData());
            Toast.makeText(reportpanchayath.this,"phase 1" ,Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(reportpanchayath.this,"error" ,Toast.LENGTH_SHORT).show();
        }
    }

    private void UploadFiles(Uri data){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploading");
        progressDialog.show();


        StorageReference reference=storageReference.child("Uploads");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri url=uriTask.getResult();

                        PDFModelClass pdfclas=new PDFModelClass(panchayathid.getText().toString(),url.toString());
                        databaseReference.child("Uploads").child(databaseReference.push().getKey()).setValue(pdfclas);

                        Toast.makeText(reportpanchayath.this,"files uploaded" ,Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress=(100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                        progressDialog.setMessage("uploaded:"+(int)progress+"%");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(reportpanchayath.this,"Error"+e.getMessage().toString() ,Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
