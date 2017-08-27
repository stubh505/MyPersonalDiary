package com.androvista.kaustubh.mypersonaldiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class EditActivity extends ViewEntryActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        try{
            File f=new File(getFilesDir()+"/user/"+title);
            File entryFile=new File(f, "Entry");
            BufferedReader br=new BufferedReader(new FileReader(entryFile));
            TextView titleView=(TextView) findViewById(R.id.edit_title);
            titleView.setText(title);
            StringBuilder sb=new StringBuilder();
            String s;
            while((s=br.readLine())!=null) {
                s=s+"\n";
                sb.append(s);
            }
            EditText bodyView=(EditText) findViewById(R.id.edit_body);
            bodyView.setText(sb);
            br.close();
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void submit(View v)
    {
        try{
            File f=new File(getFilesDir()+"/user/"+title);
            File entryFile=new File(f, "Entry");
            EditText bodyView=(EditText) findViewById(R.id.edit_body);
            FileOutputStream fout=new FileOutputStream(entryFile);
            fout.write(bodyView.getText().toString().getBytes());
            fout.close();
            Intent intent=new Intent(this, ViewEntryActivity.class);
            Toast.makeText(this, "Entry updated.", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
