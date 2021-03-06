package com.toostronk.timeinmoney;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;


public class MainActivity extends SlidingActivity {
	final ArrayList<String> Dados = new ArrayList<String>();
	ArrayList<Data> dat = new ArrayList<Data>();
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.activity_menu);
        getSlidingMenu().setBehindOffset(200);
        
        //Enable top button
        ActionBar actionBar = getActionBar();
    	actionBar.setDisplayHomeAsUpEnabled(true);
    	
    	dat.clear();
		
			try {
				dat = (ArrayList<Data>) InternalStorage.readObject(this, "KEY");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i=0;i<dat.size();i++)
			Dados.add(dat.get(i).toString());
			
		ListView listview ;
		    ArrayAdapter arrayAdapter;
			
			
     
      
        listview = (ListView) findViewById(R.id.listview);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Dados);
 
        listview.setAdapter(arrayAdapter);
    	
    	
        listview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
                 
                Intent intent = new Intent(MainActivity.this,SingleView.class);
                Bundle b = new Bundle();
                Data e = dat.get(position);
                b.putParcelable("parse", e);
                intent.putExtras(b);
                startActivity(intent);
                
            }
          });

    	
        listview.setLongClickable(true);
        
        listview.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    final int arg2, long arg3) {
            	 new AlertDialog.Builder(MainActivity.this)
    	           .setMessage("Are you sure you want to delete this event?")
    	           .setCancelable(false)
    	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	               public void onClick(DialogInterface dialog, int id) {
    	            	 DeleteEvent(arg2);
    	            	 Intent intent = getIntent();
    	            	 finish();
    	            	 startActivity(intent);
    	               }
    	           })
    	           .setNegativeButton("No", null)
    	           .show();
return true;
    }
    });

        final Button Butmain = (Button)findViewById(R.id.home);

        Butmain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class); 
               
                finish();
                startActivity(intent);
            }
        });
        
        final Button Butexit = (Button)findViewById(R.id.exit);

        Butexit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	 new AlertDialog.Builder(MainActivity.this)
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
       
        final Button Butnew = (Button)findViewById(R.id.newevent);

        Butnew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewEventActivity.class); 
                finish();
                startActivity(intent);
            }
        });
        
        final Button Butstat = (Button)findViewById(R.id.statistics);

        Butstat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StatisticsActivity.class); 
                 startActivity(intent);
                 finish();
            }
        });
        final Button Butstart = (Button)findViewById(R.id.about);

        Butstart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AboutActivity.class); 
                 startActivity(intent);
                 finish();
            }
        });
    }

    public void DeleteEvent(int pos){
    	ArrayList<Data> dat = new ArrayList<Data>();
    	try {
    		   // Save the list of entries to internal storage
    		try {
				dat = (ArrayList<Data>) InternalStorage.readObject(MainActivity.this, "KEY");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		dat.remove(pos);
    		   InternalStorage.writeObject(MainActivity.this, "KEY", dat);
    		 
    		   
    		} catch (IOException e) {
    		   
    		} 
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    // TODO Auto-generated method stub
    return super.onTouchEvent(event);
    }
    
    @Override
	public void onBackPressed() {
	    new AlertDialog.Builder(this)
	           .setMessage("Are you sure you want to exit?")
	           .setCancelable(false)
	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                  MainActivity.this.finish();
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
