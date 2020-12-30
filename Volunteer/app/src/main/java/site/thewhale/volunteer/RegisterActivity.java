package site.thewhale.volunteer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();


        EditText email = findViewById(R.id.registerEmail);
        EditText name = findViewById(R.id.registerName);
        EditText password = findViewById(R.id.registerPassword);
        EditText passwordConfirmation = findViewById(R.id.registerPasswordConfirmation);

        Button registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(password.getText().toString().equals(passwordConfirmation.getText().toString()))) {
                    Toast.makeText(RegisterActivity.this, "Password is not valid!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.getText().toString().equals("") || name.getText().toString().equals("") || password.getText().toString().equals("") || passwordConfirmation.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "You didn't write valid information!", Toast.LENGTH_SHORT).show();
                    return;
                }
                sign(email.getText().toString(), password.getText().toString(), name.getText().toString());
            }
        });
    }

    private void sign(String email, String password, String name) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Register", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("register", "User profile updated.");
                                            }
                                        }
                                    });
                            user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("Register", "Email sent.");
                                            }
                                        }
                                    });
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Register", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}