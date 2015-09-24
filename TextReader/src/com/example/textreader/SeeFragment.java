package com.example.textreader;

import java.util.ArrayList;






import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar.LayoutParams;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SeeFragment extends Fragment {

    private Context context;
	private DBHelper dbHelper;
	public static SQLiteDatabase db;
	private int n;
	private static TextView ed1;
    private static TextView ed2;
    private static TextView ed3;
    private static TextView twAll;
   
    private static Button bm;
    
    
    private Button btn;
    public static LinearLayout linLayout;
    private Cursor c;   
    public ArrayList<String> all = new ArrayList<String>();
    public static LayoutInflater ltInflater;
    private static OnClickListener oncl;
    private static int dbi;
  
    
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        
       oncl=new OnClickListener(){

		@Override
		public void onClick(View v) {
			ed1 = (TextView) v.findViewById(R.id.editText1);
			ed2 = (TextView) v.findViewById(R.id.editText2);
			ed3 = (TextView) v.findViewById(R.id.textView1);
			twAll = (TextView) v.findViewById(R.id.textView2);
			
			switch (v.getId()){
			
		case R.id.button11:
			
			View item = (View) v.getParent();
			ed1 = (TextView) item.findViewById(R.id.editText1);
			
		
			View item_1=(View) item.getParent();
			ed2 = (TextView) item_1.findViewById(R.id.editText2);
			twAll = (TextView) item_1.findViewById(R.id.textView2);
				
			if (ed2.getText().toString().length()>135){
				String s = ed2.getText().toString();
				ed2.setText(twAll.getText().toString());
				twAll.setText(s);
			}
			else {
				String s = twAll.getText().toString();
				twAll.setText(ed2.getText().toString());
				ed2.setText(s);
				
			}
			
			
			
			break;
			
			default:
			
			
			
			
			
			
			if (ed2.length()>twAll.length()) {EnterFragment.setEditText(ed1.getText().toString(), ed2.getText().toString());} else if 
			(twAll.length()>ed2.length()) {EnterFragment.setEditText(ed1.getText().toString(), twAll.getText().toString());} else 
				EnterFragment.setEditText(ed1.getText().toString(), twAll.getText().toString());
			EnterFragment.id=linLayout.indexOfChild(v);
			EnterFragment.iddb=Long.parseLong(ed3.getText().toString());
			EnterFragment.isRefresh=true;
			MainActivity.setFragment2();
		}
     	   
		}   
       };
        
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            
        final View view = inflater.inflate(R.layout.see_fragment, container, false);

        context = view.getContext();
             
       
        
        ltInflater=inflater;   
        
   	 linLayout = (LinearLayout) view.findViewById(R.id.linLayout);

	    dbHelper=new DBHelper(this.getActivity().getApplicationContext());
	    db = dbHelper.getWritableDatabase();
	     
	   
	    
		c = db.query("mytable", null, null, null, null, null, null);
	    if (c.moveToFirst()) {
	          
	          int idColIndex = c.getColumnIndex("id");
	          int nameColIndex = c.getColumnIndex("name");
	          int infoColIndex = c.getColumnIndex("info");

	          do {
	          
	          	Log.d("FUCK!!!", "id="+c.getString(idColIndex)+"\n"+"name="+c.getString(nameColIndex)+"\n"+"info="+c.getString(infoColIndex)+"\n" );
	        	  
	        	  
	        	
	            
			    View item = inflater.inflate(R.layout.my, linLayout, false);
			    ed1 = (TextView) item.findViewById(R.id.editText1);
			    ed2 = (TextView) item.findViewById(R.id.editText2);
			    ed3 = (TextView) item.findViewById(R.id.textView1);
			    twAll = (TextView) item.findViewById(R.id.textView2);
			    bm = (Button) item.findViewById(R.id.button11);
			    
			    ed1.setText(c.getString(nameColIndex));
			    twAll.setText(c.getString(infoColIndex));
			    
		    	ed2.setText(cut140(c.getString(infoColIndex)));
		    	
		    	ed3.setText(c.getString(idColIndex));
		    	dbi=Integer.parseInt(c.getString(idColIndex));
		    	
		    	
		    	
		    	item.getLayoutParams().width = LayoutParams.MATCH_PARENT;
			    
		    	
			    item.setOnClickListener(oncl);
			    bm.setOnClickListener(oncl);				    
			    linLayout.addView(item);
	            Log.d("FUCK!!!", "indecs of child="+linLayout.indexOfChild(item));
	            
	          } while (c.moveToNext());
	        } else
	          Log.d("mylogs", "0 rows");
	        c.close();
        
 
	        if (linLayout.getChildAt(0)==null)MainActivity.setFragment2();else MainActivity.setFragment1();
	        
	        
        return view;
    }
    
    
 public static void EnterInDataBase(String s1, String s2){
	 ContentValues cv = new ContentValues();
     cv.put("name", s1);
     cv.put("info", s2);
     dbi = (int) db.insert("mytable", null, cv);
	 
	  View item = ltInflater.inflate(R.layout.my, linLayout, false);
      ed1 = (TextView) item.findViewById(R.id.editText1);
      ed2 = (TextView) item.findViewById(R.id.editText2);
      ed3 = (TextView) item.findViewById(R.id.textView1);
      twAll = (TextView) item.findViewById(R.id.textView2);
      bm = (Button) item.findViewById(R.id.button11);
     
          ed1.setText(s1);
          twAll.setText(s2);
          
          ed2.setText(cut140(s2));
          ed3.setText(Long.toString(dbi));
     
          
      item.getLayoutParams().width = LayoutParams.MATCH_PARENT;

      item.setOnClickListener(oncl);
      bm.setOnClickListener(oncl);
      linLayout.addView(item);
    MainActivity.setFragment1();
 }   
    
 public static void RefreshDataBase (String s1, String s2, int id, long iddb){
	 View item = linLayout.getChildAt(id);
	 
	 ed1 = (TextView) item.findViewById(R.id.editText1);
     ed2 = (TextView) item.findViewById(R.id.editText2);
     twAll = (TextView) item.findViewById(R.id.textView2);
     ed3 = (TextView) item.findViewById(R.id.textView1);	
     
     ed1.setText(s1);
     twAll.setText(s2);
     
     ed2.setText(cut140(s2));
     ed3.setText(Long.toString(iddb));
     
     ContentValues cv = new ContentValues();
     cv.put("name", ed1.getText().toString());
     cv.put("info", twAll.getText().toString());
     db.update("mytable", cv, "id = ?",new String[] {Long.toString(iddb) } );
     MainActivity.setFragment1();
 
 }   
    
public static void DeleteRow(int id, long iddb){
	
		linLayout.removeViewAt(id);	
		db.delete("mytable", "id = " + iddb, null);
		MainActivity.setFragment1();
	
	}
    
 
private static String cut140(String s){
	if (s.length()>135)
	{
	s= s.substring(0, 135);
	s+="...";
	return s;
	}
	else 
	
	return s;
}
    

    
}
