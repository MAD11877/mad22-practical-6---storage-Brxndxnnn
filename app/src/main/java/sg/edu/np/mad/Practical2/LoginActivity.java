package sg.edu.np.mad.Practical2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText loginUsername, loginPassword;
    Button loginButton;
    FirebaseAuth fAuth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);

        loginUsername = findViewById(R.id.editTextTextPersonName);
        loginPassword = findViewById(R.id.editTextNumberPassword);
        loginButton = findViewById(R.id.button5);
        fAuth = FirebaseAuth.getInstance();




        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = loginUsername.getText().toString();
                String password = loginPassword.getText().toString();


                ref = FirebaseDatabase.getInstance("https://practical6-a6bdc-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Users");
                Query usernameCheck = ref.orderByChild("username").equalTo(username);
                Query passwordCheck = ref.orderByChild("password").equalTo(password);
                usernameCheck.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            passwordCheck.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        startActivity(new Intent(getApplicationContext(), ListActivity.class));
                                    }
                                    else{
                                        Toast.makeText(LoginActivity.this, "Wrong password",Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            //String passwordfromDb = snapshot.child(password).child("password").getValue(String.class);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "User does not exist",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                //if(TextUtils.isEmpty(username)){
                //    loginUsername.setError("Username is required");
                //}

                //if(TextUtils.isEmpty(password)){
                //    loginPassword.setError("Password is required");
                //}

                // authenticate the user
                //fAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                //    @Override
                //    public void onComplete(@NonNull Task<AuthResult> task) {
                //        String test;
                //        if(task.isSuccessful()){
                //            Toast.makeText(LoginActivity.this, "Logged in successfully",Toast.LENGTH_SHORT).show();
                //            startActivity(new Intent(getApplicationContext(), ListActivity.class));
                //        }
                //        else{
                //            Toast.makeText(LoginActivity.this, "Credentials are wrong, please try again",Toast.LENGTH_SHORT).show();
                //        }
                //    }
                //});




            }
        });
    }
}