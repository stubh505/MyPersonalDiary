package com.androvista.kaustubh.mypersonaldiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class PasswordActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        Toast.makeText(this, "This app has been opened for the first time.\nPlease register a password.", Toast.LENGTH_SHORT).show();
    }

    public void submit(View v)
    {
        EditText pass=(EditText)findViewById(R.id.f_password);
        EditText cnf_pass=(EditText)findViewById(R.id.cnf_f_password);
        EditText sec_ques=(EditText)findViewById(R.id.sec_question);
        EditText sec_ans=(EditText)findViewById(R.id.sec_answer);
        String password=pass.getText()+"";
        String secQues=sec_ques.getText()+"";
        String secAns=sec_ans.getText().toString();
        if(password.isEmpty()||cnf_pass.getText().toString().isEmpty()||secAns.isEmpty()||secQues.isEmpty())
        {
            Toast.makeText(this, "No fields can be left empty.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(cnf_pass.getText().toString()))
        {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return;
        }
        File f=new File(getFilesDir()+"/user");
        f.mkdirs();
        try {
            File passFile = new File(f, "passcode");
            passFile.createNewFile();
            FileOutputStream fout=new FileOutputStream(passFile);
            fout.write(password.getBytes());
            fout.close();
            File quesFile = new File(f, "SecurityQuestion");
            quesFile.createNewFile();
            FileOutputStream qout=new FileOutputStream(quesFile);
            qout.write(secQues.getBytes());
            qout.close();
            File ansFile = new File(f, "SecurityAnswer");
            ansFile.createNewFile();
            FileOutputStream aout=new FileOutputStream(ansFile);
            aout.write(secAns.getBytes());
            aout.close();
            Toast.makeText(this, "Registration Complete", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this, MainActivity.class);
            pass.setText("");
            cnf_pass.setText("");
            sec_ques.setText("");
            sec_ans.setText("");
            startActivity(i);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
