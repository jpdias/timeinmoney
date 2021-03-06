package com.toostronk.timeinmoney;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;


public class NewEventActivity extends SlidingActivity {

	public Data data = new Data();
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);

		
	 setBehindContentView(R.layout.activity_menu);
        getSlidingMenu().setBehindOffset(200);
        
        //Enable top button
        ActionBar actionBar = getActionBar();
    	actionBar.setDisplayHomeAsUpEnabled(true);
    	
    	final EditText name   = (EditText) findViewById(R.id.Name_Field);
    	final Spinner type   = (Spinner) findViewById(R.id.Type_Spinner);
    	final EditText cost  = (EditText) findViewById(R.id.Cost_field);
    	final EditText notes  = (EditText) findViewById(R.id.Notes_Field);
    	final DatePicker datePicker;
        datePicker = (DatePicker)  findViewById(R.id.Date_Picker);
        
        final Button Butmain = (Button)findViewById(R.id.home);

        Butmain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NewEventActivity.this,MainActivity.class); 
                finish();
                startActivity(intent);
            }
        });
        
		final Button Butadd = (Button)findViewById(R.id.Save_button);
		
		Butadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NewEventActivity.this,NewEventActivity.class); 
                finish();
                startActivity(intent);
            }
        });
		
        final Button Butexit = (Button)findViewById(R.id.exit);

        Butexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	 new AlertDialog.Builder(NewEventActivity.this)
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
        
		 final Button Butcancel = (Button)findViewById(R.id.Cancel_button);
		
		Butcancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	  new AlertDialog.Builder(NewEventActivity.this)
   	           .setMessage("Are you sure?")
   	           .setCancelable(false)
   	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
   	               public void onClick(DialogInterface dialog, int id) {
   	            	   
   	            	   Intent intent = new Intent(NewEventActivity.this,MainActivity.class); 
   	            	   startActivity(intent);
   	            	   finish();
   	               }
   	           })
   	           .setNegativeButton("No", null)
   	           .show();
            }
        });
		
		final Button Butsave = (Button)findViewById(R.id.Save_button);

        Butsave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	
            	data.data=String.valueOf(datePicker.getDayOfMonth())
                        + "/" + String.valueOf(datePicker.getMonth()) + "/"
                        + String.valueOf(datePicker.getYear());
            	data.notes=notes.getText().toString();
            	data.cost=Double.valueOf(cost.getText().toString()).doubleValue();
            	
            	data.name=name.getText().toString();
            	
            	data.type=type.getSelectedItem().toString();
            	ArrayList<Data> dat = new ArrayList<Data>();
            	try {
            		   // Save the list of entries to internal storage
            		try {
        				dat = (ArrayList<Data>) InternalStorage.readObject(NewEventActivity.this, "KEY");
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			} catch (ClassNotFoundException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
            		dat.add(data);
            		   InternalStorage.writeObject(NewEventActivity.this, "KEY", dat);
            		 
            		   
            		} catch (IOException e) {
            		   
            		} 
            	
            	
                Intent intent = new Intent(NewEventActivity.this,MainActivity.class); 
                 startActivity(intent);
                 finish();
            }
        });
		
        final Button Butstat = (Button)findViewById(R.id.statistics);

        Butstat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NewEventActivity.this,StatisticsActivity.class); 
                 startActivity(intent);
                 finish();
            }
        });
        final Button Butstart = (Button)findViewById(R.id.about);

        Butstart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NewEventActivity.this,AboutActivity.class); 
                 startActivity(intent);
                 finish();
            }
        });
    }


    
    @Override
	public void onBackPressed() {
	    new AlertDialog.Builder(this)
	           .setMessage("Are you sure?")
	           .setCancelable(false)
	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   
	            	   Intent intent = new Intent(NewEventActivity.this,MainActivity.class); 
	            	   startActivity(intent);
	            	   finish();
	               }
	           })
	           .setNegativeButton("No", null)
	           .show();
	}
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
	
			   toggle();
				  return true;
	        
			  
	}
}

