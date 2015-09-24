package com.example.textreader;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class AboutActivity extends ActionBarActivity  {
	  
	protected Button bt;
	protected CheckBox cb;
	
	
	
	
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
       
	        
	        setContentView(R.layout.activity_about);
	        
	        
	        
	        
	        
		       bt = (Button) findViewById(R.id.button1);
		      cb = (CheckBox) findViewById(R.id.checkBox1); 
		        
		      bt.setOnClickListener(new OnClickListener(){

				
				public void onClick(View v) {
				if (cb.isChecked()==true){
					MainActivity.setPref();
					finish();
					
				}
				else finish();
					
				}
		       	  
		      });  
	    }
	}

