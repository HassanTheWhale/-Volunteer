package site.thewhale.volunteer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.loginEmail);
        EditText password = findViewById(R.id.loginPassword);
        TextView create = findViewById(R.id.loginCreate);
        Button btn = findViewById(R.id.loginBtn);

        mAuth = FirebaseAuth.getInstance();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "You didn't write valid information!", Toast.LENGTH_SHORT).show();
                    return;
                }
                sign(email.getText().toString(), password.getText().toString());
            }
        });
    }

    private void sign(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("login", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            boolean emailVerified = user.isEmailVerified();
                            if (emailVerified) {
                                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(i);
                                finish();
                                KtFunctionsKt.motionI(LoginActivity.this, "مرحباً", "لقد سجلت دخولك 3>");
                            } else {
                                Toast.makeText(LoginActivity.this, "You have to confirm your email first!",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("login", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}