package com.example.segproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class EmployeeProfile extends AppCompatActivity {

    String uid;
    String addressName;
    String phoneNumber;
    Button addService;
    Button deleteService;
    ListView branchServiceListView;
    List<NewService> branchServiceList;
    DatabaseReference dbref;
    String services;
    String[] individualServices;
    String[] individualServicesRefined;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        dbref = FirebaseDatabase.getInstance().getReference("branch");
        uid = getIntent().getStringExtra("branchID");


        final TextView addressEBanner = (TextView) findViewById(R.id.addressEmployeeBanner);
        final TextView phoneNumberEBanner = (TextView) findViewById(R.id.phoneNumberEmployeeBanner);


        dbref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ProfileInfo profile = snapshot.getValue(ProfileInfo.class);

                if(profile != null){
                    int addressNum = profile.streetNum;
                    addressName = " " + profile.streetName;
                    phoneNumber = profile.phoneNum;
                    services = profile.services;
                    addressEBanner.setText("Address: " + addressNum + " " + addressName);
                    phoneNumberEBanner.setText("Phone number: " + phoneNumber);
                    individualServices = services.split(",");
                    individualServicesRefined = new String[individualServices.length-1];
                    for (int i = 1; i < individualServices.length; i++) {
                        for(int j = 0; j < individualServicesRefined.length;j++){
                            individualServicesRefined[j] = individualServices[i];
                        }

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
               Toast.makeText(EmployeeProfile.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

        addService = findViewById(R.id.add);
        deleteService = findViewById(R.id.delete);
        branchServiceListView = findViewById(R.id.branchServiceListView);
        branchServiceList = new ArrayList<>();



        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddBranchService();
            }
        });
    }

    protected void onStart(){//have list of all services
        super.onStart();
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                branchServiceList.clear();

                for (String individualService : individualServicesRefined) {
                    for (DataSnapshot info : snapshot.getChildren()) {
                        NewService ns = info.getValue(NewService.class);
                        if(ns != null) {
                            if (individualService.equals(ns.serviceID)) {
                                branchServiceList.add(ns);
                            }
                        }
                    }
                    NewServiceList branchServiceAdapter = new NewServiceList(EmployeeProfile.this, branchServiceList);
                    branchServiceListView.setAdapter(branchServiceAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EmployeeProfile.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openAddBranchService(){

        Intent intent = new Intent(this,AddBranchService.class);
        intent.putExtra("id",uid);
        startActivity(intent);

    }

}