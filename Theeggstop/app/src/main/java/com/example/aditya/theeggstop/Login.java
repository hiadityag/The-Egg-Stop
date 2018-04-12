package com.example.aditya.theeggstop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Login extends AppCompatActivity implements View.OnClickListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.


    Button login, register_here;
    EditText userid, pass;
    TextView forgot;
    SharedPreferences pref;

    //public static final String MypReferences = "MyPref";
    private static final String reg_url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Egg Stop");
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.b_login);
        register_here = (Button) findViewById(R.id.b_register_here);

        forgot = (TextView) findViewById(R.id.forgot_pass);

        login.setOnClickListener(this);
        register_here.setOnClickListener(this);
        forgot.setOnClickListener(this);

        //pref = getSharedPreferences(MypReferences,0);
    }

    public void onClick(View v) {
        //SharedPreferences.Editor editor = pref.edit();
        switch (v.getId()) {
            case R.id.b_login: {
                userid = (EditText) findViewById(R.id.txt_userid);
                pass = (EditText) findViewById(R.id.txt_password);

                String mobile = userid.getText().toString();
                String password = pass.getText().toString();

                // editor.putString("Mobile",mobile);

                if (mobile.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
                } else
                {
                    login_user(mobile, password);
                }
                break;
            }
            case R.id.b_register_here:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.forgot_pass:
                startActivity(new Intent(this, Forgot.class));
                break;
        }
    }

    private void login_user(String mobile, String password)
    {
        final String urlSuffix = "?mobile=" + mobile + "&password=" + password;
        class RegisterUser extends AsyncTask<String, Void, String>
        {
            private ProgressDialog loading;

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this, "Logging In...", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                String string = s.trim();
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();
                if(string.equals("Login Successful."))
                {
                    startActivity(new Intent(Login.this, Main2Activity.class));
                }
                else if(string.equals("Invalid Username or Password."))
                {   }
            }

            @Override
            protected String doInBackground(String... params) {
                BufferedReader bufferReader;
                try {
                    URL url=new URL(reg_url+urlSuffix);
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    bufferReader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result=bufferReader.readLine();
                    return  result;
                }catch (Exception e){
                    return null;
                }
            }
        }

        RegisterUser ur = new RegisterUser();
        ur.execute(urlSuffix);
    }
    @Override
    public void onBackPressed()
    {
        int backpressed=0;
        backpressed++;
        if(backpressed==1)
        {
            moveTaskToBack(true);
            this.finish();
        }
    }
}