package com.example.textreader;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class EnterFragment extends Fragment {

    private static Context context;
    public static boolean isRefresh = false;
    public static int id;
    public static long iddb;
    public static int idb;
    
    

    
    
    private static EditText et1;
    private static EditText et2;
    private static TextView simvols;
    private OnClickListener oncl;
    private TextWatcher tw;
 
    
    /** Handle the results from the voice recognition activity. */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            
        final View view = inflater.inflate(R.layout.enter_fragment, container, false);

        context = view.getContext();
             
        
       
        
        
 
        
        et1 = (EditText) view.findViewById(R.id.editText1);
        et2 = (EditText) view.findViewById(R.id.editText2);
        simvols = (TextView) view.findViewById(R.id.simvols);
        Button bt1 = (Button) view.findViewById(R.id.button1);
        Button bt2 = (Button) view.findViewById(R.id.button2);
        Button bt3 = (Button) view.findViewById(R.id.button3);
        
        tw = new TextWatcher(){
           
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
			
			public void afterTextChanged(Editable s) {
				simvols.setText(Integer.toString(et1.length())+"/"+Integer.toString(et2.length()));
    	    	Log.d("FUCK!!!",Integer.toString(et1.length())+"/"+Integer.toString(et2.length()) );
    	
				
			}
        };

        
        			   
        	    	
        	
        
        oncl = new OnClickListener(){

			@Override
			public void onClick(View v) {
				switch (v.getId()){
				
				case R.id.button1:
					
					if (isRefresh==false){
						
					if (ifOnlySpases(et1.getText().toString())==true) et1.setText(R.string.bez_zagolovka);
					if (ifOnlySpases(et2.getText().toString())==true) et2.setText(R.string.bez_teksta);
						
						
					SeeFragment.EnterInDataBase(et1.getText().toString(), et2.getText().toString());
					cleanEditText();
					
					escapeClaviature(et1);
					escapeClaviature(et2);
										
					}
					else{ 
					
					if (ifOnlySpases(et1.getText().toString())==true) et1.setText(R.string.bez_zagolovka);
					if (ifOnlySpases(et2.getText().toString())==true) et2.setText(R.string.bez_teksta);
					
					SeeFragment.RefreshDataBase(et1.getText().toString(), et2.getText().toString(), id, iddb);	
					cleanEditText();
					
					escapeClaviature(et1);
					escapeClaviature(et2);
					isRefresh=false;}	
					break;
				case R.id.button2:
					
					if (isRefresh==true){
					SeeFragment.DeleteRow(id, iddb);
					cleanEditText();
					
					escapeClaviature(et1);
					escapeClaviature(et2);
					isRefresh=false;
					}
					break;
					
				case R.id.button3:
					cleanEditText();
					
					break;	
			}
        	
			}
        };
        
        bt1.setOnClickListener(oncl);
        bt2.setOnClickListener(oncl);
        bt3.setOnClickListener(oncl);
        et1.addTextChangedListener(tw); 
        et2.addTextChangedListener(tw); 
       
           
       
       
       
       
    
        
        
        return view;
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
public static int setEditText(String s1, String s2){
	
	
	Log.d("FUCK!!!", s1);
	Log.d("FUCK!!!", s2);
	
	if (s1.equals("Без заголовка (^_^)")) et1.setText(""); else et1.setText(s1);
	if (s2.equals("Без текста (^_^)")) et2.setText(""); else et2.setText(s2);

	return 0;
} 
    

public static void cleanEditText(){
	et1.setText("");
	et2.setText("");
}


public static void escapeClaviature (EditText et){
	InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
	imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
	
}


  
private boolean ifOnlySpases (String s){
	boolean b=true;
	if (s.trim().length() == 0)	return b;
	else return b = false; 
	
}

public static String getTextEt2(){
	
	
	return et2.getText().toString();
	
}

public static String getTextEt1(){
	
	
	return et1.getText().toString();
	
}





}

     

