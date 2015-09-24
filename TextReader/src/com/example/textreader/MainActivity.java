package com.example.textreader;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private static DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mScreenTitles;
    private static SharedPreferences sPref;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    
	   public final int FRAGMENT_ONE = 0;
	   public final int FRAGMENT_TWO = 1;
	   public final int FRAGMENTS = 2;
	   private FragmentPagerAdapter _fragmentPagerAdapter;
	   private  final List<Fragment> _fragments = new ArrayList<Fragment>();
	   private static ViewPager _viewPager;
	   public static int fid1;
	   public static int fid2;
	   

		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	   	mScreenTitles = getResources().getStringArray(R.array.screen_array);
	   	
	     mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	     mDrawerList = (ListView) findViewById(R.id.left_drawer);
	         
	       
	        mDrawerList.setAdapter((ListAdapter) new ArrayAdapter<String>(this,R.layout.drawer_list_item, mScreenTitles));
	        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		
		    mDrawerLayout.setDrawerListener(new DrawerListener(){
				@Override
				public void onDrawerOpened(View arg0) {
					mDrawerList.bringToFront();
                    mDrawerLayout.requestLayout();
                    InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
            	}

				@Override
				public void onDrawerSlide(View arg0, float arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onDrawerStateChanged(int arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onDrawerClosed(View arg0) {
					// TODO Auto-generated method stub
					
				}
	      });
	        
		        
        _fragments.add(FRAGMENT_ONE, new SeeFragment());
        _fragments.add(FRAGMENT_TWO, new EnterFragment());
        _fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), _fragments); 
        
        fid1=_fragments.get(0).getId();
        fid2=_fragments.get(1).getId();
        
        _viewPager = (ViewPager) findViewById(R.id.pager);
        _viewPager.setAdapter(_fragmentPagerAdapter);
       mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
       
      
        
        
        _viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
             
          	if (position==0) mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
          	else mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
          });
     
        
        
        
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString("saved_text", "");
        
        if (savedText.equals("")){
        	Log.d("!!!!!!!!!!!!!!!", "FIRST LAUNCH!");
        	
            _viewPager.setCurrentItem(1); 
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        	startActivity(intent);
            
        }
        
        else  _viewPager.setCurrentItem(0); 
        
        
        }
        
        
		private class DrawerItemClickListener implements ListView.OnItemClickListener {
		    @Override
		    public void onItemClick(AdapterView parent, View view, int position, long id) {

		    	switch (position){
				
				case 0:

					try{
					Intent smsIntent = new Intent(Intent.ACTION_VIEW);
					smsIntent.setType("vnd.android-dir/mms-sms");
					smsIntent.putExtra("sms_body",EnterFragment.getTextEt2());
					startActivity(smsIntent);}
					catch (ActivityNotFoundException e) {
						
						
						e.printStackTrace();
						android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
						builder.setTitle(R.string.sms_activity_not_found)
								.setMessage(R.string.send_emal)
								.setIcon(R.drawable.ic_launcher)
								.setCancelable(false)
								.setNegativeButton("Отлично) Спасибо!) :D",
										new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog, int id) {
												dialog.cancel();
											}
										});
						android.app.AlertDialog alert = builder.create();
						alert.show();
					}
	    	
		    	break;
		    	
				case 1:
					
					try{	
					Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
	                emailIntent.setType("text/plain");
	                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{""});
	                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, EnterFragment.getTextEt1());
	                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, EnterFragment.getTextEt2());
	                startActivity(Intent.createChooser(emailIntent, "Send mail..."));}
	                catch (ActivityNotFoundException e) {
						e.printStackTrace();
						android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
						builder.setTitle(R.string.email_activity_not_found)
								.setMessage(R.string.send_sms)
								.setIcon(R.drawable.ic_launcher)
								.setCancelable(false)
								.setNegativeButton("ок :D",
										new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog, int id) {
												dialog.cancel();
											}
										});
						android.app.AlertDialog alert = builder.create();
						alert.show();
					}
	                
	                
		    	break;
		    	
		       	}
		    		    	
		                Log.d("FUCK!!!", "Position="+ Integer.toString(position));
		                mDrawerLayout.closeDrawer(mDrawerList);

		    }
		}
	
	
		
	public static void setFragment1(){
		_viewPager.setCurrentItem(0);	
	
	}	
		
	public static void setFragment2(){
		_viewPager.setCurrentItem(1);	
	
	}
	
	
	public static void setPref(){
		Editor ed = sPref.edit();
        ed.putString("saved_text", "ljkh");
        ed.commit();
				
	}
		
	
	private class MyFragmentPagerAdapter extends FragmentPagerAdapter{
		 private List<Fragment> fragments;
		 
         public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
			
		}

		@Override
         public int getCount() {

             return FRAGMENTS;
         }

         @Override
         public Fragment getItem(final int position) {
             return _fragments.get(position);
         }

         @Override
         public CharSequence getPageTitle(final int position) {
        	 
            if (position==0)
        	 return "Сохраняшка)";
            
            else 
            	return "Добавляшка)";
             }
         }
	
	
	
}
