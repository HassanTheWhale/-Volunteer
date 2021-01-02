package site.thewhale.volunteer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.bitvale.lightprogress.LightProgress;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LightProgress light = findViewById(R.id.light);
        light.on();

        Handler h = new Handler(getMainLooper());
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                    KtFunctionsKt.motionI(MainActivity.this, "مرحباً", "لقد سجلت دخولك 3>");
                } else {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, 3000);
    }
}