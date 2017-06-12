package com.etaimuallem.firebaseauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressWarnings("deprecation")
public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;


    @BindView(R.id.etmail)
    EditText etmail;
    @BindView(R.id.etpassword)
    EditText etpassword;
    @BindView(R.id.btnLogin)
    BootstrapButton btnLogin;
    @BindView(R.id.btnLogOut)
    BootstrapButton btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //loadTheFonts
        mAuth = FirebaseAuth.getInstance();
    }


    private String getEmail() {
        return etmail.getText().toString();
    }

    private String getPassword() {
        return etpassword.getText().toString();
    }

    private boolean isEmailValid() {
        String email = getEmail();
        boolean vailid = email.length() > 6 && email.contains("@");
        // Boolean valid = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if (!vailid)
            etmail.setError("Invalid Email address.");
        return vailid;
    }

    private boolean isPasswordValid() {
        String password = etpassword.getText().toString();
        boolean valid = password.length() > 5;
        if (!valid)
            etpassword.setError("the password must contain at least 6 charcaters.");
        return valid;
    }

    ProgressDialog dialog ;

    private void showProgress(boolean show) {
        if (dialog == null) {
                dialog = new ProgressDialog(this);
            dialog.setTitle("connecting to server");
            dialog.setMessage("please wait");
            dialog.setCancelable(false);
            }
            if (show) {
                dialog.show();
            }
            else
                dialog.dismiss();
    }

    @OnClick(R.id.btnLogin)
    public void onBtnLoginClicked() {
        // validetion
        if (!isEmailValid() | !isPasswordValid()) return;
        showProgress(true);
        String email = getEmail();
        String password = getPassword();
        //internet premision
        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                showProgress(false);
                Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentMain);
            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showProgress(false);
                Snackbar.make(btnLogin, e.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
            }
        });


    }

    @OnClick(R.id.btnLogOut)
    public void onBtnLogOutClicked() {

    }
}
