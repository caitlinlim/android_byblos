package com.example.segproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.os.Bundle;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditService extends AppCompatActivity {
    // here the admin will be able to add edit and delete services

    DatabaseReference dbRef;
    Button addservice;
    ListView listView;
    List<NewService> arrayList;

    EditText editTextName;
    EditText editTextRate;

    CheckBox cbfirstName, cblastName, cbdob, cbaddress, cbemail, cbG1, cbG2, cbG3, cbcompact,
            cbintermediate, cbSUV, cbpickupdate, cbpickuptime, cbreturndate, cbreturntime, cbmovingstartlocation,
            cbmovingendlocation, cbarea, cbkmdriven, cbnumberofmovers, cbnumberofboxes;

    boolean firstName;
    boolean lastName;
    boolean dob;
    boolean address;
    boolean email;
    boolean G1;
    boolean G2;
    boolean G3;
    boolean compact;
    boolean intermediate;
    boolean SUV;
    boolean pickupdate;
    boolean pickuptime;
    boolean returndate;
    boolean returntime;
    boolean movingstartlocation;
    boolean movingendlocation;
    boolean area;
    boolean kmdriven;
    boolean numberofmovers;
    boolean numberofboxes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);

        dbRef = FirebaseDatabase.getInstance().getReference("Service");
        listView = (ListView) findViewById(R.id.newServiceListView);
        arrayList = new ArrayList<>();


        addservice = (Button) findViewById(R.id.addServiceBTN);
        addservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddGlobalService();
            }
        });

        //listen for service long press.
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                NewService serv = arrayList.get(position);

                deleteServiceDialog(serv.getName(), serv.getRate());
                return false;
            }
        });

    }

    private void deleteServiceDialog(final String name, int rate){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_delete_service, null);
        dialogBuilder.setView(dialogView);

        editTextName = (EditText) dialogView.findViewById(R.id.nameEditText);
        editTextRate  = (EditText) dialogView.findViewById(R.id.rateEditText);

        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.updateServiceButton);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.deleteServiceButton);

        dialogBuilder.setTitle(name);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                int rate = Integer.parseInt(editTextRate.getText().toString());

                if(cbfirstName.isChecked()){
                    firstName = true;
                }else{
                    firstName = false;
                }

                if(cblastName.isChecked()){
                    lastName = true;
                }else{
                    lastName = false;
                }

                if(cbdob.isChecked()){
                    dob = true;
                }else{
                    dob = false;
                }

                if(cbaddress.isChecked()){
                    address = true;
                }else{
                    address = false;
                }

                if(cbemail.isChecked()){
                    email = true;
                }else{
                    email = false;
                }

                if(cbG1.isChecked()){
                    G1 = true;
                }else{
                    G1 = false;
                }

                if(cbG2.isChecked()){
                    G2 = true;
                }else{
                    G2 = false;
                }

                if(cbG3.isChecked()){
                    G3 = true;
                }else{
                    G3 = false;
                }

                if(cbcompact.isChecked()){
                    compact = true;
                }else{
                    compact = false;
                }

                if(cbintermediate.isChecked()){
                    intermediate = true;
                }else{
                    intermediate = false;
                }

                if(cbSUV.isChecked()){
                    SUV = true;
                }else{
                    SUV = false;
                }

                if(cbpickupdate.isChecked()){
                    pickupdate = true;
                }else{
                    pickupdate = false;
                }

                if(cbpickuptime.isChecked()){
                    pickuptime = true;
                }else{
                    pickuptime = false;
                }

                if(cbreturndate.isChecked()){
                    returndate = true;
                }else{
                    returndate = false;
                }

                if(cbreturntime.isChecked()){
                    returntime = true;
                }else{
                    returntime = false;
                }

                if(cbmovingstartlocation.isChecked()){
                    movingstartlocation = true;
                }else{
                    movingstartlocation = false;
                }

                if(cbmovingendlocation.isChecked()){
                    movingendlocation = true;
                }else{
                    movingendlocation = false;
                }

                if(cbarea.isChecked()){
                    area = true;
                }else{
                    area = false;
                }

                if(cbkmdriven.isChecked()){
                    kmdriven = true;
                }else{
                    kmdriven = false;
                }

                if(cbnumberofmovers.isChecked()){
                    numberofmovers = true;
                }else{
                    numberofmovers = false;
                }

                if(cbnumberofboxes.isChecked()){
                    numberofboxes = true;
                }else{
                    numberofboxes = false;
                }

                updateService(address, area,compact,dob, email, firstName,
                        G1, G2, G3, intermediate, kmdriven, lastName, movingendlocation,
                        movingstartlocation, name,  numberofboxes,numberofmovers,
                        pickupdate, pickuptime,rate,returndate, returntime,
                        SUV);
                b.dismiss();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteService(name);
                b.dismiss();
            }
        });
    }


    public void deleteService(String name){ //this does not delete service from authenticated database. but does delete from realtime.
        dbRef.child(name).removeValue();
        Toast.makeText(getApplicationContext(), "Service deleted", Toast.LENGTH_LONG).show();

    }

    public void updateService(boolean address, boolean area, boolean compact, boolean dob,
                              boolean email, boolean firstName, boolean G1, boolean G2, boolean G3,
                              boolean intermediate, boolean kmdriven, boolean lastName, boolean movingendlocation,
                              boolean movingstartlocation, String name, boolean numberofboxes,boolean numberofmovers,
                              boolean pickupdate, boolean pickuptime,int rate,boolean returndate, boolean returntime,
                              boolean SUV){

        NewService serv = new NewService(address, area,compact,dob, email, firstName,
                G1, G2, G3, intermediate, kmdriven, lastName, movingendlocation,
                movingstartlocation, name,  numberofboxes,numberofmovers,
                pickupdate, pickuptime,rate,returndate, returntime,
                SUV);
        dbRef.child(name).setValue(serv);
        Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();
    }

    protected void onStart(){//have list of all services
        super.onStart();
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();

                //int i = 0;
                for(DataSnapshot info : snapshot.getChildren()) {
                    NewService ns = info.getValue(NewService.class);
                    arrayList.add(ns);
                    //arrayList.get(0);
                }

                NewServiceList serviceAdapter = new NewServiceList(EditService.this, arrayList);


                listView.setAdapter(serviceAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openAddGlobalService(){
        Intent intent = new Intent(this,AddGlobalService.class);
        startActivity(intent);

    }


}