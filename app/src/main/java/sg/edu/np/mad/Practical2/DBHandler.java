package sg.edu.np.mad.Practical2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context c) {
        super(c, "Users20.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE UserDetails (Name TEXT, Description TEXT, ID INTEGER PRIMARY KEY, Follow INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS UserDetails");
        onCreate(db);
    }

    public void insertUsers(User user) {
        //int f;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", user.name);
        contentValues.put("Description", user.description);
        contentValues.put("ID", user.id);
        if(user.followed){
            // = 1;
            contentValues.put("Follow", 1);
        }
        else{
            //f = 0;
            contentValues.put("Follow", 0);
        }

        db.insert("UserDetails", null, contentValues);
        //db.execSQL("INSERT INTO UserDetails VALUES(\"" + user.name + "\", \"" + user.description + "\" ,  "+ user.id +", " + f + ")");
        db.close();
    }

    public ArrayList<User> getUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<User> usersList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT*FROM UserDetails", null);
        while(cursor.moveToNext()){
            User user = new User();
            user.name = cursor.getString(0);
            user.description = cursor.getString(1);
            user.id = cursor.getInt(2);
            if(cursor.getInt(3) == 1){
                user.followed = true;
            }
            else{
                user.followed = false;
            }
            usersList.add(user);
        }
        db.close();
        return usersList;
    }

    public void updateUsers(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String id = String.valueOf(user.id);
        if(user.followed){
            // = 1;
            cv.put("Follow", 1);
        }
       else{
            cv.put("Follow", 0);
        }
        db.update("UserDetails", cv , "ID=?", new String[]{id});
           db.close();
    }

}