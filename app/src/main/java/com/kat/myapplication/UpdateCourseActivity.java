package com.kat.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCourseActivity extends AppCompatActivity {
    private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdt;

    private Button updateCourseBtn, deleteCourseBtn, addCourseBtn;

    private DBHandler dbHandler;

    String courseName, courseDesc, courseDuration, courseTracks;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_course);

// initializing all our variables.

        courseNameEdt =  findViewById(R.id.idEdtCourseName);

        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);

        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);

        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);

        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);

        deleteCourseBtn = findViewById(R.id.idBtnDelete);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);

// on below line we are initializing our dbhandler class.

        dbHandler = new DBHandler(UpdateCourseActivity.this);

// on below lines we are getting data which

// we passed in our adapter class.

        courseName = getIntent().getStringExtra("name");

        courseDesc = getIntent().getStringExtra("description");

        courseDuration = getIntent().getStringExtra("duration");

        courseTracks = getIntent().getStringExtra("tracks");
        // setting data to edit text

// of our update activity.

        courseNameEdt.setText(courseName);

        courseDescriptionEdt.setText(courseDesc);

        courseTracksEdt.setText(courseTracks);

        courseDurationEdt.setText(courseDuration);

// adding on click listener to our update course button.

        updateCourseBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

// inside this method we are calling an update course

// method and passing all our edit text values.

                dbHandler.updateCourse(courseName, courseNameEdt.getText().toString(), courseDescriptionEdt.getText().toString(), courseTracksEdt.getText().toString(), courseDurationEdt.getText().toString());

// displaying a toast message that our course has been updated.

                Toast.makeText(UpdateCourseActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();

// launching our main activity.

                Intent i = new Intent(UpdateCourseActivity.this, UpdateCourseActivity.class);

                startActivity(i);

            }

        });
        // adding on click listener for delete button to delete our course.

        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

// calling a method to delete our course.

                dbHandler.deleteCourse(courseName);

                Toast.makeText(UpdateCourseActivity.this, "Deleted the course", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(UpdateCourseActivity.this, UpdateCourseActivity.class);

                startActivity(i);

            }

        });
        // below line is to add on click listener for our add course button.

        addCourseBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

// below line is to get data from all edit text fields.

                String courseName = courseNameEdt.getText().toString();

                String courseTracks = courseTracksEdt.getText().toString();

                String courseDuration = courseDurationEdt.getText().toString();

                String courseDescription = courseDescriptionEdt.getText().toString();

// validating if the text fields are empty or not.

                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {

                    Toast.makeText(UpdateCourseActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();

                    return;

                }

// on below line we are calling a method to add new

// course to sqlite data and pass all our values to it.

                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseTracks);

// after adding the data we are displaying a toast message.

                Toast.makeText(UpdateCourseActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();

                courseNameEdt.setText("");

                courseDurationEdt.setText("");

                courseTracksEdt.setText("");

                courseDescriptionEdt.setText("");

            }

        });

    }
}