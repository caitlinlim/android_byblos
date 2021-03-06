package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class FeedbackPage extends AppCompatActivity {

    TextView rateCount;
    TextView showRating;
    EditText review;
    Button submit;

    RatingBar ratingBar;
    float rateValue;
    String temp;
    String branchID;
    String userID;
    DatabaseReference dbFeedback;
    String feedbackID;
    float rateValueTemp;
    String comment;
    Button backButton;
    String username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);

        showRating = findViewById(R.id.showRating);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setNumStars(5);
        submit = findViewById(R.id.submitReview);
        review = findViewById(R.id.comment);
        backButton = findViewById(R.id.custRatingBackBTN);

        rateCount = findViewById(R.id.rateCount);

        dbFeedback = FirebaseDatabase.getInstance().getReference("feedback");

        branchID = getIntent().getStringExtra("branchID"); //branch id
        userID = getIntent().getStringExtra("id");
        username = getIntent().getStringExtra("username");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedbackPage.this, BranchDisplay.class);
                intent.putExtra("branchID",branchID);
                intent.putExtra("id",userID);
                intent.putExtra("username", username);
                startActivity(intent);
                Toast.makeText(FeedbackPage.this, "Back to branch profile", Toast.LENGTH_LONG).show();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = ratingBar.getRating();
                rateValueTemp = rateValue;

                if(rateValue <= 1 && rateValue > 0){
                    rateCount.setText("Very user-unfriendly " + rateValue + "/5");
                }else if(rateValue <= 2 && rateValue > 1){
                    rateCount.setText("User-unfriendly "  + rateValue + "/5");
                }else if(rateValue <= 3 && rateValue > 2){
                    rateCount.setText("Neither user-unfriendly nor user-friendly "  + rateValue + "/5");
                }else if(rateValue <= 4 && rateValue > 3){
                    rateCount.setText("User-friendly "  + rateValue + "/5");
                }else if(rateValue <= 5 && rateValue > 4){
                    rateCount.setText("Very user-friendly "  + rateValue + "/5");
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = rateCount.getText().toString();
                showRating.setText("Your Rating: \n" + temp + "\n" + review.getText());
                comment = review.getText().toString();
                if (validComment(comment.toLowerCase())){
                    review.setText("");
                    rateCount.setText("");
                    submitFeedback();
                }else {
                    review.requestFocus();
                    review.setError("Invalid comment");
                }
            }
        });
    }

    static public boolean validComment(String comment){
        if (comment.isEmpty() || comment.equals(""))
            return false;

        if (comment.contains("fuck") || comment.contains("shit") || comment.contains("bitch") || comment.contains("ass"))
            return false;


        return true;
    }

    private void submitFeedback(){
        feedbackID = dbFeedback.push().getKey(); // get unique service request id.

        Feedback fb = new Feedback(userID, branchID, comment,rateValueTemp,feedbackID);

        dbFeedback.child(feedbackID).setValue(fb);

        Intent intent = new Intent(this,BranchDisplay.class);
        intent.putExtra("branchID",branchID);
        intent.putExtra("id",userID);
        startActivity(intent);
        Toast.makeText(FeedbackPage.this, "Back to branch profile", Toast.LENGTH_LONG).show();
    }
}


