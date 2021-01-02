package site.thewhale.volunteer.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import site.thewhale.volunteer.KtFunctionsKt;
import site.thewhale.volunteer.LoginActivity;
import site.thewhale.volunteer.R;

public class Profile extends Fragment {
    private FirebaseAuth mAuth;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        EditText email = view.findViewById(R.id.profileEmail);
        EditText name = view.findViewById(R.id.profileName);
        EditText password = view.findViewById(R.id.profilePassword);
        EditText passwordConfirmation = view.findViewById(R.id.profilePasswordConfirmation);

        email.setText(user.getEmail());
        name.setText(user.getDisplayName());

        Button registerBtn = view.findViewById(R.id.profileBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(password.getText().toString().equals(passwordConfirmation.getText().toString()))) {
                    KtFunctionsKt.motionE((Activity) view.getContext(),"هناك عطب!", "لم يتم تحديث معلوماتك!");
                    return;
                }

                if (email.getText().toString().equals("") || name.getText().toString().equals("") || password.getText().toString().equals("") || passwordConfirmation.getText().toString().equals("")) {
                    KtFunctionsKt.motionE((Activity) view.getContext(),"هناك عطب!", "لم يتم تحديث معلوماتك!");
                    return;
                }
                update(email.getText().toString(), password.getText().toString(), name.getText().toString(), user, view);
            }
        });

        return view;
    }

    private void update(String email, String password, String name, FirebaseUser user, View view) {
        user.updateEmail(email);
        user.updatePassword(password);
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            KtFunctionsKt.motionS((Activity) view.getContext(),"تم!", "تم تحديث معلوماتك بنجاح");
                            FirebaseAuth.getInstance().signOut();
                            Intent i = new Intent(view.getContext(), LoginActivity.class);
                            view.getContext().startActivity(i);
                            ((Activity) view.getContext()).finish();
                        } else {
                            KtFunctionsKt.motionE((Activity) view.getContext(),"هناك عطب!", "لم يتم تحديث معلوماتك!");
                        }
                    }
                });
    }
}