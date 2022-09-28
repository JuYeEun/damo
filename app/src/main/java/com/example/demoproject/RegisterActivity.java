package com.example.demoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText Edit_registerEmail, Edit_registerPwd, Edit_registerName;
    private FirebaseAuth auth;
    private TextView Tv_Login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViews();

        Tv_Login = findViewById(R.id.tv_login);
        Tv_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button Btn_Register = findViewById(R.id.btn_register);
        Btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Edit_Email = Edit_registerEmail.getText().toString();
                String Edit_Password = Edit_registerPwd.getText().toString();
                String Edit_Name = Edit_registerName.getText().toString();

                if (TextUtils.isEmpty(Edit_Name)) {
                    Toast.makeText(RegisterActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    Edit_registerName.setError("이름을 입력하세요");
                    Edit_registerName.requestFocus();
                } else if (TextUtils.isEmpty(Edit_Email)) {
                    Toast.makeText(RegisterActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                    Edit_registerEmail.setError("이메일을 입력하세요");
                    Edit_registerEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(Edit_Email).matches()) {
                    Toast.makeText(RegisterActivity.this, "이메일 형식이 아닙니다", Toast.LENGTH_SHORT).show();
                    Edit_registerEmail.setError("이메일 형식이 아닙니다");
                    Edit_registerEmail.requestFocus();
                } else if (TextUtils.isEmpty(Edit_Password)) {
                    Toast.makeText(RegisterActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    Edit_registerPwd.setError("비밀번호를 입력해주세요");
                    Edit_registerPwd.requestFocus();
                }else if (Edit_Password.length() <= 6) {
                    Toast.makeText(RegisterActivity.this, "비밀번호를 6자리 이상으로 입력해주세요", Toast.LENGTH_SHORT).show();
                    Edit_registerPwd.setError("비밀번호를 6자리 이상으로 입력해주세요");
                    Edit_registerPwd.requestFocus();
                }else{
                    registerUser(Edit_Email, Edit_Password, Edit_Name);
                }
            }
            private void registerUser(String edit_email, String edit_password, String edit_name) {
                auth = FirebaseAuth.getInstance();
                auth.createUserWithEmailAndPassword(edit_email,edit_password).addOnCompleteListener(RegisterActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser firebaseUser = auth.getCurrentUser();
                                    if(firebaseUser != null){
                                       // UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                         //       .setDisplayName(edit_name).build();
                                       // UserProfileChangRequest profileUpdates = new UserProfileChangRequest.Builder().setDisplayName(edit_name).build();
                                        //firebaseUser.updateProfile(profileUpdates);
                                        Toast.makeText(RegisterActivity.this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show();
                                        Intent userProfileActivity = new Intent(RegisterActivity.this,LoginActivity.class);
                                        startActivity(userProfileActivity);
                                        finish();
                                    }
                                }else{
                                    try {
                                        throw  task.getException();
                                    }catch (Exception e){
                                        Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
    }

    private void findViews() {
        Edit_registerEmail = findViewById(R.id.edit_register_email);
        Edit_registerPwd = findViewById(R.id.edit_register_password);
        Edit_registerName = findViewById(R.id.edit_register_name);

    }

}