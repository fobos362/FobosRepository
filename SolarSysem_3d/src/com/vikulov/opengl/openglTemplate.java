package com.vikulov.opengl;

import android.app.Activity;
import android.os.Bundle;

public class openglTemplate extends Activity {
	private SimpleOpenGlView mGLView=null;
	
    //������ ����������� ��� ���������	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mGLView = new SimpleOpenGlView(this);
		setContentView(mGLView);
    }
}