package com.example.demoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText Edit_LoginEmail, Edit_LoginPwd;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getSupportActionBar().setTitle("로그인");

        Edit_LoginEmail = findViewById(R.id.edit_email);
        Edit_LoginPwd = findViewById(R.id.edit_password);

        showHidePassword();

        TextView Tv_Register = findViewById(R.id.tv_register);
        Tv_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        auth = FirebaseAuth.getInstance();
        Button Btn_Login = findViewById(R.id.btn_login);
        Btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Edit_Email = Edit_LoginEmail.getText().toString();
                String Edit_Password = Edit_LoginPwd.getText().toString();

                if (TextUtils.isEmpty(Edit_Email)) {
                    Toast.makeText(LoginActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                    Edit_LoginEmail.setError("이메일을 입력하세요");
                    Edit_LoginEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(Edit_Email).matches()) {
                    Toast.makeText(LoginActivity.this, "이메일 형식이 아닙니다", Toast.LENGTH_SHORT).show();
                    Edit_LoginEmail.setError("이메일 형식이 아닙니다");
                    Edit_LoginEmail.requestFocus();
                } else if (TextUtils.isEmpty(Edit_Password)) {
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    Edit_LoginPwd.setError("비밀번호를 입력해주세요");
                    Edit_LoginPwd.requestFocus();
                }else{
                    loginUser(Edit_Email, Edit_Password);
                }

            }
        });
    }
    private void loginUser(String edit_email, String edit_password) {
        auth.signInWithEmailAndPassword(edit_email,edit_password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"로그인 되었습니다",Toast.LENGTH_SHORT).show();
                    Intent userProfileActivity = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(userProfileActivity);
                }else{
                    try {
                        throw task.getException();
                    }catch (Exception e){
                        Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void showHidePassword(){
        ImageView imageViewShowHidePwd  = findViewById(R.id.imageView_show_hide_pwd);
        imageViewShowHidePwd.setImageResource(R.drawable.hidden);

        imageViewShowHidePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Edit_LoginPwd.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    Edit_LoginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imageViewShowHidePwd.setImageResource(R.drawable.hidden);
                }else{
                    Edit_LoginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHidePwd.setImageResource(R.drawable.eyes);
                }
            }
        });
    }
}