package com.toostronk.timeinmoney;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class SingleView extends SlidingActivity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_single);
        setBehindContentView(R.layout.activity_menu);
        
        getSlidingMenu().setBehindOffset(200);


        //Enable top button
        ActionBar actionBar = getActionBar();
    	actionBar.setDisplayHomeAsUpEnabled(true);
    	
    	TextView name   = (TextView) findViewById(R.id.Name_Label);
    	TextView type   = (TextView) findViewById(R.id.Type_Label);
    	TextView cost  = (TextView) findViewById(R.id.Cost_Label);
    	TextView notes  = (TextView) findViewById(R.id.Notes_Field);
    	TextView data  = (TextView)  findViewById(R.id.Date_Label);
    	
    	
    	 Intent intent = getIntent();
         Bundle b = intent.getExtras();
         Data e = b.getParcelable("parse");
  
         name.setText("Event: " +e.name);
         type.setText(e.type);
         cost.setText(String.valueOf(e.cost)+"�");
         notes.setText(e.notes);
         data.setText(e.data);
 
    	
    	
        final Button Butstart = (Button)findViewById(R.id.about);

        Butstart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SingleView.this,SingleView.class); 
                 startActivity(intent);
                 finish();
            }
        });
        
        
        final Button Butstat = (Button)findViewById(R.id.statistics);

        Butstat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SingleView.this,StatisticsActivity.class); 
                 startActivity(intent);
                 finish();
            }
        });
        final Button Butmain = (Button)findViewById(R.id.home);

        Butmain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SingleView.this,MainActivity.class); 
                 startActivity(intent);
                 finish();
            }
        });
        final Button Butnew = (Button)findViewById(R.id.newevent);

        Butnew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SingleView.this,NewEventActivity.class); 
                finish();
                startActivity(intent);
            }
        });
        
        final Button Butexit = (Button)findViewById(R.id.exit);

        Butexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	 new AlertDialog.Builder(SingleView.this)
    	           .setMessage("Are you sure you want to exit?")
    	           .setCancelable(false)
    	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	               public void onClick(DialogInterface dialog, int id) {
    	            	   finish();
    	                   System.exit(0);
    	               }
    	           })
    	           .setNegativeButton("No", null)
    	           .show();
            }
        });  
	}
	
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
	    toggle();
	    return true;

	}
}
