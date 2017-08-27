package com.androvista.kaustubh.mypersonaldiary;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

public class ViewEntryActivity extends HomeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);
        try{
            File f=new File(getFilesDir()+"/user/"+title);
            File entryFile=new File(f, "Entry");
            BufferedReader br=new BufferedReader(new FileReader(entryFile));
            TextView titleView=(TextView) findViewById(R.id.view_title);
            titleView.setText(title);
            TextView bodyView=(TextView) findViewById(R.id.view_body);
            bodyView.setMovementMethod(new ScrollingMovementMethod());
            String link, view="";
            while((link=br.readLine())!=null)
                view=view+link+"\n";
            bodyView.setText(view);
            br.close();
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void edit(View v)
    {
        Intent i=new Intent(this, EditActivity.class);
        startActivity(i);
    }

    public void delete(View v)
    {
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        File f=new File(getFilesDir()+"/user/"+title);
                        File ff[]=f.listFiles();
                        try {
                            for (int i = 0; i < ff.length; i++) ff[i].delete();
                            f.delete();
                            f=new File(getFilesDir()+"/user/Entries");
                            f.createNewFile();
                            ArrayList<String> entries=new ArrayList<String>();
                            BufferedReader br=new BufferedReader(new FileReader(f));
                            String list;
                            while((list=br.readLine())!=null)
                            {
                                if(list.equals(title))
                                    continue;
                                entries.add(list);
                            }
                            br.close();
                            FileOutputStream fout=new FileOutputStream(f);
                            for(int i=0;i<entries.size();i++) {
                                list=entries.get(i)+"\n";
                                fout.write(list.getBytes());
                            }
                            fout.close();
                            Intent i=new Intent(getApplicationContext(), HomeActivity.class);
                            Toast.makeText(ViewEntryActivity.this, "Entry Deleted.", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }
                        catch (Exception e) { Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show(); }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // no delete
                    }
                })
                .setIcon(R.drawable.ic_alert)
                .show();
    }

}
