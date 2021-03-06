package com.toostronk.timeinmoney;

import java.io.IOException;
import java.util.ArrayList;

import graphview.GraphView;
import graphview.GraphView.GraphViewData;
import graphview.GraphViewSeries;
import graphview.MyBarGraphView;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

public class StatisticsActivity extends SlidingActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  
	        setContentView(R.layout.graphs);
	        setBehindContentView(R.layout.activity_menu);
	        
	        getSlidingMenu().setBehindOffset(200);
		

	        //Enable top button
	        ActionBar actionBar = getActionBar();
	    	actionBar.setDisplayHomeAsUpEnabled(true);
	        
	        final Button Butstart = (Button)findViewById(R.id.about);

	        Butstart.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                Intent intent = new Intent(StatisticsActivity.this,AboutActivity.class); 
	                 startActivity(intent);
	                 finish();
	            }
	        });
	        
	        final Button Butnew = (Button)findViewById(R.id.newevent);

	        Butnew.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                Intent intent = new Intent(StatisticsActivity.this,NewEventActivity.class); 
	                finish();
	                startActivity(intent);
	            }
	        });
	        
	        final Button Butstat = (Button)findViewById(R.id.statistics);

	        Butstat.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                Intent intent = new Intent(StatisticsActivity.this,StatisticsActivity.class); 
	                 startActivity(intent);
	                 finish();
	            }
	        });
	        final Button Butmain = (Button)findViewById(R.id.home);

	        Butmain.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                Intent intent = new Intent(StatisticsActivity.this,MainActivity.class); 
	                 startActivity(intent);
	                 finish();
	            }
	        });
	        
	        final Button Butexit = (Button)findViewById(R.id.exit);

	        Butexit.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	 new AlertDialog.Builder(StatisticsActivity.this)
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
	        
	        
        String[] array_spinner=new String[2];
        array_spinner[0]="First 7";
        array_spinner[1]="Group by 2";
        
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);

        ArrayList<Double> Dados = new ArrayList<Double>();
        ArrayList<Data> dat = new ArrayList<Data>();
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
		Dados.add(dat.get(i).cost);

		GraphViewSeries week  = new GraphViewSeries(new GraphViewData[] {	
		});
		// init example series data
		
		for(int i=0; i<Dados.size()&& i<7;i++){
		
			week.appendData(
					new GraphViewData(i,Dados.get(i))
			,true);
		}
		
        GraphViewSeries month = new GraphViewSeries(new GraphViewData[] {
              
        });
        Double total;
        for(int i=0; i<Dados.size();i+=2){
        	if(i+1 < Dados.size()){
        		total=Dados.get(i)+Dados.get(i+1);
        	}
        	else
        		total=Dados.get(i);
			month.appendData(
					
					 new GraphViewData(i,total)
					
			,true);
		}
      

		// graph with dynamically genereated horizontal and vertical labels
	    final MyBarGraphView graphView =new MyBarGraphView(this,"");
	    graphView.colwidth = 30;
        final MyBarGraphView graphView1=new MyBarGraphView(this,"");
        graphView1.colwidth = 100;
      

        
        

     
		// custom static labels
		graphView.setHorizontalLabels(new String[]{}); //Ajustar a label do eixo dos xx's necessita de espa�os
        graphView1.setHorizontalLabels(new String[]{});
       // graphView2.setHorizontalLabels(new String[]{"Jan","Feb","Mar", "Apr","May","Jun","Jul", "Aug","Sep","Oct","Nov", "Dec "});
       /// graphView.setHorizontalScrollBarEnabled(true);
       
		//graphView.setVerticalLabels(new String[] {"1", "middle", "low",});
        graphView1.addSeries(month);
		graphView.addSeries(week); // data
        

      final  LinearLayout layout = (LinearLayout) findViewById(R.id.graph2);


        s.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                if(arg2==0){
                    layout.removeView(graphView);
                    layout.removeView(graphView1);
                    
                    layout.addView(graphView);
                }
                else if(arg2==1){
                    layout.removeView(graphView);
                    layout.removeView(graphView1);
                    
                    layout.addView(graphView1);
                }
                else{
                    layout.removeView(graphView);
                    layout.removeView(graphView1);
                    
                }
            }
            @Override
            public void onNothingSelected(android.widget.AdapterView<?> adapterView){

            }
        });
	}
	
	@Override
	public void onBackPressed() {
	    new AlertDialog.Builder(this)
	           .setMessage("Are you sure you want to exit?")
	           .setCancelable(false)
	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                  StatisticsActivity.this.finish();
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
