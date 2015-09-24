/**
 * 
 */
package com.vikulov.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
//Устанавливаем класс отрисовки для поверхности


public class SimpleOpenGlView extends GLSurfaceView {

	private Solar_system mRenderer=null;
	
	public SimpleOpenGlView(Context context) {
		super(context);
		
		mRenderer = new Solar_system(context);
		setEGLContextClientVersion(2);
		setRenderer(mRenderer);
	}
	
	    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
	    private float mPreviousX;
	    private float mPreviousY;
	    private float mPreviousRasst;
	    private float multix=0.0f;
	    private float multiy=0.0f;	
	    private float x = 0;
        private float y = 0;
        private float tochki;
        public static float rasst;
        public static  float dx;
		public static float dy;
	
		
		
         
        
        
	    @SuppressWarnings("deprecation")
		@Override
		
		//Добавляем возможность управлять положением камеры и поддержку мультитач)
	    public boolean onTouchEvent(MotionEvent e) {
	       
	    	 int pointerIndex = e.getActionIndex();
	    	 int pointerId = e.getPointerId(pointerIndex);
	    	 
	    	 
	        x = e.getX();
	        y = e.getY();
	        
	        
	        switch (e.getAction()) {
	            case MotionEvent.ACTION_MOVE:
	            	 int pointerCount = e.getPointerCount();
	            	 
	            	 
	            	   for(int i = 0; i < pointerCount; ++i)
	                   {
	                       pointerIndex = i;
	                       pointerId = e.getPointerId(pointerIndex);
	                       Log.d("pointer id - move",Integer.toString(pointerId));
	                       if(pointerId == 0)
	                       {
	                           
	                           x = e.getX(pointerIndex);
	                           y = e.getY(pointerIndex);
	                       }
	                       if(pointerId == 1)
	                       {
	                           
	                           multix = e.getX(pointerIndex);
	                           multiy = e.getY(pointerIndex);
	                       }
	                   }
	            	 
	            	 
	            	            	 
	               dx = (x - mPreviousX)/15;
	               dy = (y - mPreviousY)/15;
	               
	               mRenderer.eyeX=dx;
	               mRenderer.eyeY=dy;
	               
	               
	               
	               if (multix>0){
	            	   
	            	   if ((multix*multix)>(x*x)&&((multiy*multiy)>(y*y)))
	            		  tochki=((float) Math.sqrt( (multix*multix)-(x*x)+(multiy*multiy)-(y*y))/10000);
	            	   
	            	  if ((x*x)>(multix*multix)&&((y*y)>(multiy*multiy)))
	            		  tochki=((float) Math.sqrt( (x*x)-(multix*multix)+(y*y)-(multiy*multiy))/10000);
	            	  
	            	  if ((x*x)>(multix*multix)&&((multiy*multiy)>(y*y)))
	            		  tochki=((float) Math.sqrt( (x*x)-(multix*multix)+(multiy*multiy)-(y*y))/10000);
	            		  
	            	  if ((multix*multix)>(x*x)&&((y*y)>(multiy*multiy)))
	            		  tochki=((float) Math.sqrt( (multix*multix)-(x*x)+(y*y)-(multiy*multiy))/10000);	  
	            	  
	            	if (mPreviousRasst>tochki)
	            	{
	            		
	            	
		            		rasst-=tochki;
	            		
	            	}
	            	
	            	if (mPreviousRasst<tochki){
	            		
	            	
	            			rasst+=tochki;	
	            	}
	            	
	               }
	            
	               
	               
	              
	               Log.d("MY_LOGS!!!!", "multix="+multix+" x="+x + "tochki="+tochki );
	                      
	                
	            break;    
	                
	            case MotionEvent.ACTION_POINTER_DOWN:
	            	
	            	if (pointerId == 0)
	                {
	                    
	                    x = e.getX(pointerIndex);
	                    y = e.getY(pointerIndex);
	                }
	                if (pointerId == 1)
	                {
	                  
	                	multix = e.getX(pointerIndex);
	                	multiy = e.getY(pointerIndex);
	                }
	                break;
	        
	            case MotionEvent.ACTION_UP:
	            	dx=dy=0.0f;
	            	rasst=0;
	            	multix=multiy=0;
	            	break;
	        
	        
	        }
	    	
	  

	        mPreviousX = x;
	        mPreviousY = y;
	        mPreviousRasst=tochki;
	      
	        
	        
	     
	        return true;
	    }


}
