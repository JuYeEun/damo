package com.example.demoproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_idr, et_passr,et_namer, et_ager;
    private Button btn_register;
    private TextView tv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_login = findViewById(R.id.tv_login);
        et_idr = findViewById(R.id.et_id);
        et_passr = findViewById(R.id.et_pass);
        et_ager = findViewById(R.id.et_age);
        et_namer = findViewById(R.id.et_name);
        btn_register = findViewById(R.id.btn_register);
        //회원가입 버튼 클릭 시 수행
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //editText 에 입력된 값은 가져온다.
                String userID = et_idr.getText().toString();
                String userPass = et_passr.getText().toString();
                String userName = et_namer.getText().toString();
                int userAge = Integer.parseInt(et_ager.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            //회원가입에 실패인지 성공인지
                            if(success) {
                                Toast.makeText(getApplicationContext(),"회원가입이 완료되었습니다",Toast.LENGTH_SHORT).show();
                                Intent it =  new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(it);
                            }else {
                                Toast.makeText(getApplicationContext(),"회원가입에 실패하었습니다",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //서버로 Volley 를 이용해서 요청을 한다
                RegisterRequest registerRequest = new RegisterRequest(userID,userPass,userName,userAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });


    }
}