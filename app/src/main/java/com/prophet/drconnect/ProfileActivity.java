package com.prophet.drconnect;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.prophet.drconnect.models.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private StorageReference storageReference;


    private int RC_SIGN_IN = 1, RC_PHOTO_PICKER =2;
    private ImageView imageView;
    private EditText titleEditText, specialtyEditText, degreeEditText, bioEditText;
    private Button button, photo;
    private Spinner spinner;

    private String userId, title, image, department, specialty, degree, bio;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        storageReference = storageReference.child("users");

        // Get current internal user ID
        userId = FirebaseAuth.getInstance().getUid();
        databaseReference = databaseReference.child("users").child(userId);
        storageReference = storageReference.child(userId);

        imageView = (ImageView) findViewById(R.id.imageView);
        titleEditText = (EditText) findViewById(R.id.title);
        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title = titleEditText.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        specialtyEditText = (EditText) findViewById(R.id.specialty);
        specialtyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                specialty = specialtyEditText.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        photo = (Button) findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });
        degreeEditText = (EditText) findViewById(R.id.degree);
        degreeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                degree = degreeEditText.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bioEditText = (EditText) findViewById(R.id.bio);
        bioEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bio = bioEditText.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("ANESTHESIOLOGY");
        spinnerArray.add("CARDIAC & VASCULAR SURGERY");
        spinnerArray.add("CARDIOLOGY (INTERNATIONAL");
        spinnerArray.add("CLINICAL HEMATOLOGY");
        spinnerArray.add("DENTAL & MAXILLOFACIAL SURGERY");
        spinnerArray.add("Dental Care");
        spinnerArray.add("DERMATOLOGY");
        spinnerArray.add("EMERGENCY Room");
        spinnerArray.add("ENDOCRINOLOGY");
        spinnerArray.add("ENT, HEAD & NECK SURGERY");
        spinnerArray.add("EXECUTIVE HEALTH CHECK-UP");
        spinnerArray.add("GASTROENTEROLOGY");
        spinnerArray.add("GENERAL SURGERY");
        spinnerArray.add("ICU");
        spinnerArray.add("INTERNAL MEDICINE");
        spinnerArray.add("IVF");
        spinnerArray.add("NEONATOLOGY");
        spinnerArray.add("NEPHROLOGY");
        spinnerArray.add("NEURO SURGERY");
        spinnerArray.add("NEUROMEDICINE");
        spinnerArray.add("OBYGN");
        spinnerArray.add("ONCOLOGY");
        spinnerArray.add("OPTHALMOLOGY");
        spinnerArray.add("ORTHOPEDICS");
        spinnerArray.add("PATHOLOGY & LAB. MEDICINE");
        spinnerArray.add("Pediatic Nephrology");
        spinnerArray.add("PEDIATIC SURGERY");
        spinnerArray.add("PEDIATRICS");
        spinnerArray.add("PHYSICAL MEDICINE");
        spinnerArray.add("PSYCHIATRY");
        spinnerArray.add("RADIOLOGY & IMAGING");
        spinnerArray.add("RESPIRATORY MEDICINE");
        spinnerArray.add("RHEUMATOLOGY");
        spinnerArray.add("UROLOGY");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, spinnerArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProfile();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    private void setProfile() {
        final StorageReference photoRef = storageReference.child("cover");
        final UploadTask uploadTask = photoRef.putFile(imageUri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this, "Error in uploading photo !", Toast.LENGTH_SHORT);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return photoRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Uri downloadUri = task.getResult();
                        image = downloadUri.toString();
                        Profile profile = new Profile(title, image, department, specialty, degree, bio);
                        databaseReference.setValue(profile);

                    }
                });
            }
        });
    }
}
