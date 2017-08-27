package com.androvista.kaustubh.mypersonaldiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        try
        {
            File f=new File(getFilesDir()+"/user");
            File seq=new File(f, "SecurityQuestion");
            BufferedReader br=new BufferedReader(new FileReader(seq));
            TextView seq_tv=(TextView)findViewById(R.id.get_sec_ques);
            seq_tv.setText(br.readLine());
            br.close();
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void submit(View v)
    {
        EditText new_pass=(EditText) findViewById(R.id.n_password);
        EditText cnf_pass=(EditText) findViewById(R.id.cnf_n_password);
        EditText sec_ans=(EditText) findViewById(R.id.cnf_sec_ans);
        if(new_pass.getText().toString().isEmpty()||cnf_pass.getText().toString().isEmpty()||sec_ans.getText().toString().isEmpty())
        {
            Toast.makeText(this, "No fields can be left empty.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!new_pass.getText().toString().equals(cnf_pass.getText().toString()))
        {
            Toast.makeText(this, "Passwords are not matching.", Toast.LENGTH_SHORT).show();
            return;
        }
        try
        {
            File f=new File(getFilesDir()+"/user");
            File secAns=new File(f, "SecurityAnswer");
            BufferedReader br=new BufferedReader(new FileReader(secAns));
            if(!sec_ans.getText().toString().equalsIgnoreCase(br.readLine()))
            {
                Toast.makeText(this, "Security answer is not matching.", Toast.LENGTH_SHORT).show();
                return;
            }
            br.close();
            File newPass=new File(f, "passcode");
            FileOutputStream fout=new FileOutputStream(newPass);
            fout.write(new_pass.getText().toString().getBytes());
            fout.close();
            Intent i=new Intent(this, MainActivity.class);
            Toast.makeText(this, "Password updated.", Toast.LENGTH_SHORT).show();
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
