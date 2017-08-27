package com.androvista.kaustubh.mypersonaldiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    static private byte f=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (f==0)
        {
            f++;
            Intent i=new Intent(this, LoadActivity.class);
            startActivity(i);
        }
        else setContentView(R.layout.activity_main);

    }

    public void forgotPassword(View v)
    {
        Intent intent=new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    public void login(View v)
    {
        EditText access_pass=(EditText)findViewById(R.id.password);
        if(access_pass.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Password field should not be empty.", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            File f = new File(getFilesDir() + "/user");
            File pass = new File(f, "passcode");
            BufferedReader br = new BufferedReader(new FileReader(pass));
            String passcode=br.readLine();
            br.close();
            if(!passcode.equals(access_pass.getText().toString())){
                Toast.makeText(this, "Wrong password entered.", Toast.LENGTH_SHORT).show();
                access_pass.setText("");
                return;
            }
            Toast.makeText(this, "Login Successfull.", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this, HomeActivity.class);
            access_pass.setText("");
            startActivity(i);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
