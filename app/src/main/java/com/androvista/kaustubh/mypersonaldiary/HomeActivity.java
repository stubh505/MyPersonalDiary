package com.androvista.kaustubh.mypersonaldiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    String s1[];
    protected static String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final ArrayList<RelativeLayout> entries=new ArrayList<RelativeLayout>();
        int dimen, layout_dimen, img_dimen, add_dimen, addimg;

        //img_dimen=(int)(getResources().getDimension(R.dimen.logo_dimen)/getResources().getDisplayMetrics().density);
        dimen=(int)(getResources().getDimension(R.dimen.layout_margin)/getResources().getDisplayMetrics().density);
        //layout_dimen=(int)(getResources().getDimension(R.dimen.layout_height)/getResources().getDisplayMetrics().density);
        LinearLayout homeLayout=(LinearLayout) findViewById(R.id.home_layout);
        try {
            File f = new File(getFilesDir() + "/user");
            File entriesFile = new File(f, "Entries");
            if (!entriesFile.exists())
            {
                entriesFile.createNewFile();
                TextView tv = new TextView(this);
                tv.setId(R.id.add_new_text);
                tv.setText(R.string.no_entries);
                tv.setTextColor(getResources().getColor(R.color.noEntries));
                tv.setTextSize(26);
                tv.setPadding(dimen, dimen, dimen, dimen);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,0,0,dimen*3);
                tv.setLayoutParams(params);
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                homeLayout.addView(tv);
            }
            BufferedReader br= new BufferedReader(new FileReader(entriesFile));
            int size=0,i=0;
            while((br.readLine())!=null)
                size++;
            br= new BufferedReader(new FileReader(entriesFile));
            String s[]=new String[size];
            while((s[i]=br.readLine())!=null)
                i++;
            br.close();
            s1=s;
            if(size!=0)
            {
                RelativeLayout rl;
                for (i = 0; i < size; i++) {
                    rl = (RelativeLayout) findViewById(R.id.rlhome);
                /*rl[i] = new RelativeLayout(this);
                LinearLayout.LayoutParams rparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, layout_dimen);
                rparams.setMargins(0,0,0,dimen*2);
                rl[i].setLayoutParams(rparams);
                rl[i].setPadding(dimen, dimen, dimen, dimen);
                rl[i].setBackgroundColor(getResources().getColor(R.color.layoutBackground));
                ImageView iv = new ImageView(this);
                iv.setImageResource(R.drawable.my_diary);
                RelativeLayout.LayoutParams ivParams = new RelativeLayout.LayoutParams(img_dimen, img_dimen);
                iv.setLayoutParams(ivParams);
                iv.setId(R.id.svId);
                ivParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                rl[i].addView(iv);*/

                    TextView tv = (TextView) findViewById(R.id.title_rlhome);
                    tv.setText(s[i]);

                    File f1 = new File(getFilesDir() + "/user/" + s1[i]);
                    File f2 = new File(f1, "Entry");
                    br = new BufferedReader(new FileReader(f2));
                    tv = (TextView) findViewById(R.id.text_rlhome);
                    tv.setText(br.readLine());
                    br.close();

                    f2 = new File(f1, "Date");
                    br = new BufferedReader(new FileReader(f2));
                    tv = (TextView) findViewById(R.id.date_rlhome);
                    tv.setText(br.readLine());
                    br.close();

                    entries.add(rl);

                /*final String tit=entries.get(i);
                rl[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HomeActivity.title=tit;
                        Intent intent=new Intent(HomeActivity.this, ViewEntryActivity.class);
                        startActivity(intent);
                    }
                });*/

                }
            }
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<RelativeLayout> entriesAdapter=new ArrayAdapter<RelativeLayout>(this, R.layout.home_list, entries);
        ListView listView=(ListView) findViewById(R.id.entryList);
        listView.setAdapter(entriesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setHomeTitle(i);
            }
        });

        add_dimen=(int)(getResources().getDimension(R.dimen.layout_add)/getResources().getDisplayMetrics().density);
        RelativeLayout add=new RelativeLayout(this);
        RelativeLayout.LayoutParams rparams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, add_dimen);
        add.setBackgroundColor(getResources().getColor(R.color.layoutBackground));
        add.setPadding(dimen,dimen,dimen,dimen);
        add.setLayoutParams(rparams);

        addimg=(int)(getResources().getDimension(R.dimen.add_img)/getResources().getDisplayMetrics().density);
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.drawable.note);
        RelativeLayout.LayoutParams ivParams = new RelativeLayout.LayoutParams(addimg, addimg);
        ivParams.setMargins(dimen,0,0,0);
        ivParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        imageView.setLayoutParams(ivParams);
        imageView.setId(R.id.add_new_pic);
        add.addView(imageView);

        TextView tv = new TextView(this);
        tv.setId(R.id.add_new_text);
        tv.setText(R.string.add_new);
        tv.setTextColor(getResources().getColor(R.color.textSecondary));
        tv.setTextSize(26);
        tv.setPadding(dimen, 0, 0,(dimen/2));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        add.addView(tv);

        homeLayout.addView(add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNew();
            }
        });
    }

    void setHomeTitle(int i){
        title=s1[i];
        Intent intent=new Intent(this, ViewEntryActivity.class);
        startActivity(intent);
    }

    public void addNew()
    {
        Intent i=new Intent(this, AddNewActivity.class);
        startActivity(i);
    }
}
