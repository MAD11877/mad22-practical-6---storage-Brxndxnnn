package sg.edu.np.mad.Practical2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements BrandsAdapter.OnProfileListener {

    public static ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DBHandler dbHandler = new DBHandler(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        userList = dbHandler.getUsers();

        Random random1 = new Random();
        for (int i = 0; i < 20; i++) {
            User randomUser = new User("Name" + random1.nextInt(), "Description" + random1.nextInt(), i+1, random1.nextBoolean());
            dbHandler.insertUsers(randomUser);
        }



        RecyclerView recyclerView = findViewById(R.id.recyclerview1);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        BrandsAdapter mAdapter = new BrandsAdapter(userList, this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onProfileClick(int position) {
        userList.get(position);
        profileDialog(position);

    }

    private void profileDialog(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile");
        builder.setMessage(userList.get(position).name);
        builder.setCancelable(true);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Intent activityName = new Intent(ListActivity.this, MainActivity.class);
                //activityName.putExtra("position",position);
                //startActivity(activityName);
                Intent activityName = new Intent(ListActivity.this, MainActivity.class);
                activityName.putExtra("position",position);
                startActivity(activityName);
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog1 = builder.create();
        dialog1.show();


    }
}