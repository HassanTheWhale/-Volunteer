package site.thewhale.volunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mikepenz.materialdrawer.Drawer;

import site.thewhale.volunteer.other.ShareCodes;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setBackgroundDrawableResource(R.drawable.bgpublic);


        ImageView menu = findViewById(R.id.drawer);
        Drawer result = ShareCodes.createDrawer(HomeActivity.this);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.openDrawer();
            }
        });
    }
}