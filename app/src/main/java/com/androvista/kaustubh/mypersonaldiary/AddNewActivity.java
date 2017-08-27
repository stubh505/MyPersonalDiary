package com.androvista.kaustubh.mypersonaldiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

public class AddNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
    }
    public void add(View v)
    {
        EditText ed1=(EditText)findViewById(R.id.title);
        EditText ed2=(EditText)findViewById(R.id.body);
        String title=ed1.getText()+"\n";
        try {
            File f = new File(getFilesDir() + "/user");
            File f1 = new File(f, "Entries");
            FileOutputStream fo = new FileOutputStream(f1, true);
            fo.write(title.getBytes());
            fo.close();
            File newDir=new File(getFilesDir()+"/user/"+ed1.getText().toString());
            newDir.mkdirs();
            File newFile=new File(newDir, "Entry");
            FileOutputStream fo1 = new FileOutputStream(newFile, true);
            fo1.write(ed2.getText().toString().getBytes());
            fo1.close();
            File newDate=new File(newDir, "Date");
            FileOutputStream fo2 = new FileOutputStream(newDate, true);
            Calendar c= Calendar.getInstance();
            String date="Dt-"+c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+(c.get(Calendar.YEAR)%100);
            fo2.write(date.getBytes());
            fo2.close();
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        Intent i=new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}
