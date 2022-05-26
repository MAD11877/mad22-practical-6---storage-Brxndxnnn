package sg.edu.np.mad.Practical2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements BrandsAdapter.OnProfileListener {

    public static ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Random random1 = new Random();

        while (userList.size() < 20) {
            User randomUser = new User("Name" + random1.nextInt(), "Description" + random1.nextInt(), 1, random1.nextBoolean());
            userList.add(randomUser);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview1);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        BrandsAdapter mAdapter = new BrandsAdapter(userList, this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        //ImageView buttonImg = (ImageView) findViewById(R.id.imageView4);
        //buttonImg.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Log.d("Hello", "Hello");
        //        Toast.makeText(getApplicationContext(), "Alert Dialog Prompted", Toast.LENGTH_SHORT).show();
        //        //dialog1.show();
        //    }
        //});
    }

    @Override
    public void onProfileClick(int position) {
        userList.get(position);
        profileDialog(position);

        //Intent intent = new Intent(this, MainActivity.class);

        //startActivity(intent);
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
                //if (userList.get(position).name.charAt(-1) == 7)
                //{
                //
                //}
                //else{
                //    Intent activityName = new Intent(ListActivity.this, MainActivity.class);
                //    activityName.putExtra("position",position);
                //    startActivity(activityName);
                //}
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