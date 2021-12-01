package com.example.segproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmpWorkingHours extends AppCompatActivity {

    String branchID;
    String userid;
    String hoursID;
    TextView mon, tue, wed, thu, fri;

    DatabaseReference dbWorkingHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_working_hours);

        dbWorkingHours = FirebaseDatabase.getInstance().getReference("hours");

        branchID = getIntent().getStringExtra("branchID"); //branch id
        userid = getIntent().getStringExtra("id"); // user id.
        hoursID = getIntent().getStringExtra("hoursid");

        mon = findViewById(R.id.monTV);
        tue = findViewById(R.id.tuesTV);
        wed = findViewById(R.id.wedTV);
        thu = findViewById(R.id.thuTV);
        fri = findViewById(R.id.friTV);
    }

    @Override
    protected void onStart() {
        super.onStart();

        dbWorkingHours.child(hoursID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                WorkingHours wh = snapshot.getValue(WorkingHours.class);
                if (wh != null){
                    if (wh.getMon()){
                        mon.setText("Monday: 9am - 5pm");
                        mon.setBackgroundColor(0xFF6FAE7F);
                    }else{
                        mon.setText("Monday: CLosed");
                        mon.setBackgroundColor(0xFFBAD6C1);
                    }
                    if (wh.getTues()){
                        tue.setText("Tuesday: 9am - 5pm");
                        tue.setBackgroundColor(0xFF6FAE7F);
                    }else{
                        tue.setText("Tuesday: Closed");
                        tue.setBackgroundColor(0xFFBAD6C1);
                    }
                    if (wh.getWed()){
                        wed.setText("Wednesday: 9am - 5pm");
                        wed.setBackgroundColor(0xFF6FAE7F);
                    }else{
                        wed.setText("Wednesday: Closed");
                        wed.setBackgroundColor(0xFFBAD6C1);
                    }
                    if (wh.getThu()){
                        thu.setText("Thursday: 9am - 5pm");
                        thu.setBackgroundColor(0xFF6FAE7F);
                    }else{
                        thu.setText("Thursday: Closed");
                        thu.setBackgroundColor(0xFFBAD6C1);
                    }
                    if (wh.getFri()){
                        fri.setText("Friday: 9am - 5pm");
                        fri.setBackgroundColor(0xFF6FAE7F);
                    }else{
                        fri.setText("Friday: Closed");
                        fri.setBackgroundColor(0xFFBAD6C1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}