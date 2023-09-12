package com.kat.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private  static final  String dbName = "testDB";
    private static final int databaseVersion = 1;
    private static final String tableName = "testTable";
    private static final String columnID = "id";
    private static final String column1= "duration";
    private static final String column2= "time";
    private static final String column3= "courseName";
    private static final String column4= "classes";

public DBHandler(Context context) {
 super(context, dbName, null,databaseVersion);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + tableName + " ("

                + columnID + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                + column1 + " TEXT,"

                + column2 + " TEXT,"

                + column3 + " TEXT,"

                + column4 + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
     sqLiteDatabase.execSQL("");
    }
    public void updateCourse(String originalCourseName, String courseName, String courseDescription, String courseTracks,
                             String courseDuration) {
    SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(column3, courseName);
        values.put(column1, courseDuration);
        values.put(column4, courseTracks);
        values.put(column1, courseDuration);

        db.update(tableName,values,"name=?",new String[] {
                originalCourseName
        });
        db.close();


    }
    public void deleteCourse(String course) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName,"name=?",new String[]{course});
    }
    public void addNewCourse(String courseName, String courseDuration, String courseDescription, String courseTracks) {

// on below line we are creating a variable for

// our sqlite database and calling writable method

// as we are writing data in our database.

        SQLiteDatabase db = this.getWritableDatabase();

// on below line we are creating a

// variable for content values.

        ContentValues values = new ContentValues();

// on below line we are passing all values

// along with its key and value pair.

        values.put(column3, courseName);

        values.put(column1, courseDuration);

        values.put(column2, courseDescription);

        values.put(column4, courseTracks);

// after adding all values we are passing

// content values to our table.

        db.insert(tableName, null, values);

// at last we are closing our

// database after adding database.

        db.close();

    }
}
