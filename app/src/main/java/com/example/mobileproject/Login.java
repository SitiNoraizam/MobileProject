package com.example.mobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText emailET;
    EditText passET;
    TextView signuptv;

    Button loginbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        emailET = findViewById(R.id.etRegEmail);
        passET = findViewById(R.id.etRegPass);
        loginbtn = findViewById(R.id.btnRegister);
        signuptv = findViewById(R.id.tvSignupHere);

        signuptv.setOnClickListener(view ->{
            startActivity(new Intent(Login.this, SignUp.class));
        });
//        //if someone already logged in
//        if(firebaseAuth.getCurrentUser()!=null) {
//            String email="";
//            email = emailET.getText().toString().trim();
//            startActivity(new Intent(this, MainActivity.class));
//            //onDestroyView();
//        }

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString().trim();
                String pass = passET.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    emailET.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)) {
                    passET.setError("Email is required");
                    return;
                }

                //progressBar.setVisibility(View.VISIBLE);

                //AUTHENTICATE USE
                firebaseAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                }
                                else {

                                    //Toast.makeText(, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    //signuptv.setVisibility(View.VISIBLE);
                                    //progressBar.setVisibility(View.INVISIBLE);
                                }
                            }
                        });
//                forgotpasstv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        //Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();
//                        final EditText resetmail = new EditText(view.getContext());
//                        final AlertDialog.Builder passwordresetdialog = new AlertDialog.Builder(view.getContext());
//                        passwordresetdialog.setTitle("Reset Password");
//                        passwordresetdialog.setMessage("Enter Your Email to Receive Reset Link");
//                        passwordresetdialog.setView(resetmail);
//
//                        passwordresetdialog.setPositiveButton("Yes",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        //extract email and send reset link
//                                        String email = resetmail.getText().toString().trim();
//                                        firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void unused) {
//                                                //Toast.makeText(getActivity(), "Reset link is sent to your email", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }).addOnFailureListener(new OnFailureListener() {
//                                            @Override
//                                            public void onFailure(@NonNull Exception e) {
//                                                //Toast.makeText(getActivity(), "Error! "+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                            }
//                                        });
//
//                                    }
//                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        });
//
//                        passwordresetdialog.create().show();
//                    }
//
//                });
            }
        });

    }
}