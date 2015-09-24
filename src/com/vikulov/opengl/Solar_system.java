/**
 * 
 */
package com.vikulov.opengl;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;




public class Solar_system implements GLSurfaceView.Renderer 
{	

		
	private final Context mActivityContext;
	
	public float X=0.0f;
	public float Y=0.0f;
		
	public float eyeX = X;
	public float eyeY = Y;
	public float eyeZ = 10.5f;

	
	private float lookX = 0.0f;
	private float lookY = 0.0f;
	private float lookZ = -5.0f;
	
	private float upX = 0.0f;
	private float upY = 1.0f;
	private float upZ = 0.0f;
	
	
	private float[] mModelMatrix = new float[16];
	public float[] mViewMatrix = new float[16];
	private float[] mProjectionMatrix = new float[16];
	private float[] mMVPMatrix = new float[16];
	private float[] mLightModelMatrix = new float[16];	
	
	
	private final FloatBuffer mCubePositions;
	private final FloatBuffer mCubeNormals;
	private final FloatBuffer mCubeTextureCoordinates;
	private final FloatBuffer mCubePositionsc;
	private final FloatBuffer mCubeNormalsc;
	private final FloatBuffer mCubeTextureCoordinatesc;
	
	
	private int mMVPMatrixHandle;
	private int mFMVPMatrixHandle;
	private int mSMVPMatrixHandle;
	private int mMVMatrixHandle;
	private int mFMVMatrixHandle;
	private int mLightPosHandle;
	private int mTextureUniformHandle;
	private int mFTextureUniformHandle;
	private int mSTextureUniformHandle;
    private int mDTextureUniformHandle;
	private int mPositionHandle;
	private int mFPositionHandle;
	private int mSPositionHandle;
	private int mNormalHandle;
	private int mTextureCoordinateHandle;
	private int mFTextureCoordinateHandle;
	private int mSTextureCoordinateHandle;
	
	private int mProgramHandle;
	private int mPhoneProgramHandle;
	private int mPointProgramHandle;
	private int mSunProgramHandle;
	private int mTextureDataHandle;
	private int mTextureDataHandleFone;
	private int mTextureDataHandleSun;
	private int mTextureDataHandleMoon;
	private int mTextureDataHandleDistortion;
	private int mTextureDataHandleSunAlpha;
	private int mTextureDataHandleDistortionAlpha;
	private int mTextureDataHandleMercury;
	private int mTextureDataHandleVenus;
	private int mTextureDataHandleMars;
	private int mTextureDataHandleJupiter;
	private int mTextureDataHandleSaturn;
	private int distortPowerHandle;
	private int hazeSpeedHandle;
	private int time1Handle;
	
	private final int mBytesPerFloat = 4;	
	private final int mPositionDataSize = 3;	
	private final int mColorDataSize = 4;	
	private final int mNormalDataSize = 3;
	private final int mTextureCoordinateDataSize = 2;
	
	private final float[] mLightPosInModelSpace = new float[] {0.0f, 0.0f, 0.0f, 1.0f};
	private final float[] mLightPosInWorldSpace = new float[4];
	private final float[] mLightPosInEyeSpace = new float[4];
		
	private int ver=0;
	private int verc=0;
	private float distortPower=0.05320f;
	private float hazeSpeed=0.05000f;
	private float time1;


	public Solar_system (final Context activityContext)
	{	
		mActivityContext = activityContext;
	
	
		
	String v = mActivityContext.getString(R.string.v);
	String n = mActivityContext.getString(R.string.n);
	String t = mActivityContext.getString(R.string.t);
	String f = mActivityContext.getString(R.string.f);
	
	String vc = mActivityContext.getString(R.string.vc);
	String nc = mActivityContext.getString(R.string.nc);
	String tc = mActivityContext.getString(R.string.tc);
	String fc = mActivityContext.getString(R.string.fc);
	
	
		String razd = "f/v vn vt ";
	
	List<Float> vv = new ArrayList<Float>();
	List<Float> nn = new ArrayList<Float>();
	List<Float> tt = new ArrayList<Float>();
	List<Integer> ff = new ArrayList<Integer>();
	
	List<Float> vvc = new ArrayList<Float>();
	List<Float> nnc = new ArrayList<Float>();
	List<Float> ttc = new ArrayList<Float>();
	List<Integer> ffc = new ArrayList<Integer>();
		
	List<Float> cubePositionDataL=new ArrayList<Float>();
	List<Float> cubeNormalDataL=new ArrayList<Float>();
	List<Float> cubeTextureCoordinateDataL=new ArrayList<Float>();
	
	List<Float> cubePositionDataLc=new ArrayList<Float>();
	List<Float> cubeNormalDataLc=new ArrayList<Float>();
	List<Float> cubeTextureCoordinateDataLc=new ArrayList<Float>();
	
	
	
	
	
	StringTokenizer stv=new StringTokenizer(v, razd, false);
	
	while (stv.hasMoreTokens()) {
		  vv.add(Float.parseFloat(stv.nextToken()));
		 
		 }
	
StringTokenizer stn=new StringTokenizer(n, razd, false);
	
	while (stn.hasMoreTokens()) {
		  nn.add(Float.parseFloat(stn.nextToken()));
		 }
	
StringTokenizer stt=new StringTokenizer(t, razd, false);
	
	while (stt.hasMoreTokens()) {
		  tt.add(Float.parseFloat(stt.nextToken()));
		 }
		
StringTokenizer sff=new StringTokenizer(f, razd, false);
	
	while (sff.hasMoreTokens()) {
		  ff.add(Integer.parseInt(sff.nextToken()));
		 }

	
	

	
	
	 stv=new StringTokenizer(vc, razd, false);
	
	while (stv.hasMoreTokens()) {
		  vvc.add(Float.parseFloat(stv.nextToken()));
		 
		 }
	
stn=new StringTokenizer(nc, razd, false);
	
	while (stn.hasMoreTokens()) {
		  nnc.add(Float.parseFloat(stn.nextToken()));
		 }
	
stt=new StringTokenizer(tc, razd, false);
	
	while (stt.hasMoreTokens()) {
		  ttc.add(Float.parseFloat(stt.nextToken()));
		 }
		
sff=new StringTokenizer(fc, razd, false);
	
	while (sff.hasMoreTokens()) {
		  ffc.add(Integer.parseInt(sff.nextToken()));
		 }
	
		
	ver=ff.size()/3; 
	verc=ffc.size()/3; 
	 
	

	for (int i=0;i<ff.size();i+=3){
		
		for (int j=0;j<3;j++) cubePositionDataL.add(vv.get(ff.get(i)*3-3+j));
		
		for (int j=0;j<2;j++) cubeTextureCoordinateDataL.add(tt.get(ff.get(i+1)*2-2+j));
				
		for (int j=0;j<3;j++) cubeNormalDataL.add(nn.get(ff.get(i+2)*3-3+j));
		
	}
	
	
	
	for (int i=0;i<ffc.size();i+=3){
		
		for (int j=0;j<3;j++) cubePositionDataLc.add(vvc.get(ffc.get(i)*3-3+j));
		
		for (int j=0;j<2;j++) cubeTextureCoordinateDataLc.add(ttc.get(ffc.get(i+1)*2-2+j));
				
		for (int j=0;j<3;j++) cubeNormalDataLc.add(nnc.get(ffc.get(i+2)*3-3+j));
		
	}
	
		
	float [] cubePositionData=new float[cubePositionDataL.size()];
	float [] cubeNormalData=new float[cubeNormalDataL.size()];
	float [] cubeTextureCoordinateData=new float[cubeTextureCoordinateDataL.size()];
	
	for (int i=0;i<cubePositionDataL.size();i++) cubePositionData[i]=cubePositionDataL.get(i);
	for (int i=0;i<cubeNormalDataL.size();i++) cubeNormalData[i]=cubeNormalDataL.get(i);
	for (int i=0;i<cubeTextureCoordinateDataL.size();i++) cubeTextureCoordinateData[i]=cubeTextureCoordinateDataL.get(i);
	
	
	
	float [] cubePositionDatac=new float[cubePositionDataLc.size()];
	float [] cubeNormalDatac=new float[cubeNormalDataLc.size()];
	float [] cubeTextureCoordinateDatac=new float[cubeTextureCoordinateDataLc.size()];
	
	for (int i=0;i<cubePositionDataLc.size();i++) cubePositionDatac[i]=cubePositionDataLc.get(i);
	for (int i=0;i<cubeNormalDataLc.size();i++) cubeNormalDatac[i]=cubeNormalDataLc.get(i);
	for (int i=0;i<cubeTextureCoordinateDataLc.size();i++) cubeTextureCoordinateDatac[i]=cubeTextureCoordinateDataLc.get(i);
	
	
	
	
	
	
		
		mCubePositions = ByteBuffer.allocateDirect(cubePositionData.length * mBytesPerFloat)
        .order(ByteOrder.nativeOrder()).asFloatBuffer();							
		mCubePositions.put(cubePositionData).position(0);		
		
				
		mCubeNormals = ByteBuffer.allocateDirect(cubeNormalData.length * mBytesPerFloat)
        .order(ByteOrder.nativeOrder()).asFloatBuffer();							
		mCubeNormals.put(cubeNormalData).position(0);
		
		mCubeTextureCoordinates = ByteBuffer.allocateDirect(cubeTextureCoordinateData.length * mBytesPerFloat)
		.order(ByteOrder.nativeOrder()).asFloatBuffer();
		mCubeTextureCoordinates.put(cubeTextureCoordinateData).position(0);
	
		
	
				mCubePositionsc = ByteBuffer.allocateDirect(cubePositionDatac.length * mBytesPerFloat)
		        .order(ByteOrder.nativeOrder()).asFloatBuffer();							
				mCubePositionsc.put(cubePositionDatac).position(0);		
				
						
				mCubeNormalsc = ByteBuffer.allocateDirect(cubeNormalDatac.length * mBytesPerFloat)
		        .order(ByteOrder.nativeOrder()).asFloatBuffer();							
				mCubeNormalsc.put(cubeNormalDatac).position(0);
				
				mCubeTextureCoordinatesc = ByteBuffer.allocateDirect(cubeTextureCoordinateDatac.length * mBytesPerFloat)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
				mCubeTextureCoordinatesc.put(cubeTextureCoordinateDatac).position(0);
	
	
	
	}
	
	protected String getVertexShader()
	{
		return RawResourceReader.readTextFileFromRawResource(mActivityContext, R.raw.per_pixel_vertex_shader);
	}
	
	protected String getFragmentShader()
	{
		return RawResourceReader.readTextFileFromRawResource(mActivityContext, R.raw.per_pixel_fragment_shader);
	}
	
	protected String getPhoneVertexShader()
	{
		return RawResourceReader.readTextFileFromRawResource(mActivityContext, R.raw.phone_vertex_shader);
	}
	
	protected String getPhoneFragmentShader()
	{
		return RawResourceReader.readTextFileFromRawResource(mActivityContext, R.raw.phonel_fragment_shader);
	}
	
	protected String getSunVertexShader()
	{
		return RawResourceReader.readTextFileFromRawResource(mActivityContext, R.raw.sun_vertex_shader);
	}
	
	protected String getSunFragmentShader()
	{
		return RawResourceReader.readTextFileFromRawResource(mActivityContext, R.raw.sun_pixel_shader);
	}
	
	@Override
	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) 
	{
		
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GLES20.glEnable(GLES20.GL_CULL_FACE_MODE); 
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);

		eyeX = X;
		eyeY = Y;
		eyeZ = 25.5f;

		lookX = 0.0f;
		lookY = 0.0f;
		lookZ = -5.0f;
		
		upX = 0.0f;
		upY = 1.0f;
		upZ = 0.0f;

		
		Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);		

		final String vertexShader = getVertexShader();   		
 		final String fragmentShader = getFragmentShader();
 		
 		final String phonevertexShader = getPhoneVertexShader();   		
 		final String phonefragmentShader = getPhoneFragmentShader();
 		
 		final String sunvertexShader = getSunVertexShader();   		
 		final String sunfragmentShader = getSunFragmentShader();
 		
		
		final int vertexShaderHandle = ShaderHelper.compileShader(GLES20.GL_VERTEX_SHADER, vertexShader);		
		final int fragmentShaderHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader);
		
		final int phonevertexShaderHandle = ShaderHelper.compileShader(GLES20.GL_VERTEX_SHADER, phonevertexShader);		
		final int phonefragmentShaderHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, phonefragmentShader);
		
		final int sunvertexShaderHandle = ShaderHelper.compileShader(GLES20.GL_VERTEX_SHADER, sunvertexShader);		
		final int sunfragmentShaderHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, sunfragmentShader);
		
		
		mProgramHandle = ShaderHelper.createAndLinkProgram(vertexShaderHandle, fragmentShaderHandle, 
				new String[] {"a_Position",  "a_Color", "a_Normal", "a_TexCoordinate"});
		
		mPhoneProgramHandle = ShaderHelper.createAndLinkProgram(phonevertexShaderHandle, phonefragmentShaderHandle, 
				new String[] {"a_Position",  "a_Color",  "a_TexCoordinate"});
		
		mSunProgramHandle = ShaderHelper.createAndLinkProgram(sunvertexShaderHandle, sunfragmentShaderHandle, 
				new String[] {"Vertex",  "MultiTexCoord0"});
        
      
     
       
        mTextureDataHandle = TextureHelper.loadTexture(mActivityContext, R.drawable.earth);
        mTextureDataHandleFone = TextureHelper.loadTexture(mActivityContext, R.drawable.hui2);
        mTextureDataHandleSun = TextureHelper.loadTexture(mActivityContext, R.drawable.sun);
        mTextureDataHandleMoon = TextureHelper.loadTexture(mActivityContext, R.drawable.moon);
        mTextureDataHandleDistortion = TextureHelper.loadTexture(mActivityContext, R.drawable.distortion);
        mTextureDataHandleMercury = TextureHelper.loadTexture(mActivityContext, R.drawable.mercury);
        mTextureDataHandleVenus = TextureHelper.loadTexture(mActivityContext, R.drawable.venus);
        mTextureDataHandleMars = TextureHelper.loadTexture(mActivityContext, R.drawable.mars);
        mTextureDataHandleJupiter = TextureHelper.loadTexture(mActivityContext, R.drawable.jupiter);
        mTextureDataHandleSaturn = TextureHelper.loadTexture(mActivityContext, R.drawable.saturn);
        
        mTextureDataHandleSunAlpha = TextureHelper.loadTexture(mActivityContext, R.drawable.sun_alpha);
        mTextureDataHandleDistortionAlpha = TextureHelper.loadTexture(mActivityContext, R.drawable.distortion_alpha);
	}	
		
	@Override
	public void onSurfaceChanged(GL10 glUnused, int width, int height) 
	{
		
		GLES20.glViewport(0, 0, width, height);
		final float ratio = (float) width / height;
		final float left = -ratio;
		final float right = ratio;
		final float bottom = -1.0f;
		final float top = 1.0f;
		final float near = 1.0f;
		final float far = 1000.0f;
		
		Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
			
		
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_MVPMatrix");
        mMVMatrixHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_MVMatrix"); 
        mLightPosHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_LightPos");
        mTextureUniformHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_Texture");
        mPositionHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_Position");
        mNormalHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_Normal"); 
        mTextureCoordinateHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_TexCoordinate");
  
        mFMVPMatrixHandle = GLES20.glGetUniformLocation(mPhoneProgramHandle, "u_MVPMatrix");
        mFMVMatrixHandle = GLES20.glGetUniformLocation(mPhoneProgramHandle, "u_MVMatrix"); 
        mFTextureUniformHandle = GLES20.glGetUniformLocation(mPhoneProgramHandle, "u_Texture");
        mFPositionHandle = GLES20.glGetAttribLocation(mPhoneProgramHandle, "a_Position");
        mFTextureCoordinateHandle = GLES20.glGetAttribLocation(mPhoneProgramHandle, "a_TexCoordinate");
        
        mSMVPMatrixHandle = GLES20.glGetUniformLocation(mSunProgramHandle, "ModelViewProjectionMatrix");
        mSPositionHandle = GLES20.glGetAttribLocation(mSunProgramHandle, "Vertex");
        mSTextureCoordinateHandle = GLES20.glGetAttribLocation(mSunProgramHandle, "MultiTexCoord0");
        mSTextureUniformHandle=GLES20.glGetUniformLocation(mSunProgramHandle, "Texture0");
        mDTextureUniformHandle=GLES20.glGetUniformLocation(mSunProgramHandle, "Texture1");
        distortPowerHandle=GLES20.glGetUniformLocation(mSunProgramHandle, "distortPower");
        hazeSpeedHandle=GLES20.glGetUniformLocation(mSunProgramHandle, "hazeSpeed");
        time1Handle=GLES20.glGetUniformLocation(mSunProgramHandle, "fTime0_X");
        
        Matrix.setIdentityM(mLightModelMatrix, 0);
        Matrix.translateM(mLightModelMatrix, 0, 0.0f, 0.0f, 0.0f);      
        
	}	

	@Override
	public void onDrawFrame(GL10 glUnused) 
	{
		
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);			        
		  
		       
        float angleInDegrees = SystemClock.currentThreadTimeMillis();   
        time1+=0.01f;
        
       
        GLES20.glUseProgram(mProgramHandle);
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
        GLES20.glUniform1i(mTextureUniformHandle, 0);        
        Matrix.multiplyMV(mLightPosInWorldSpace, 0, mLightModelMatrix, 0, mLightPosInModelSpace, 0);
        Matrix.multiplyMV(mLightPosInEyeSpace, 0, mViewMatrix, 0, mLightPosInWorldSpace, 0);                        
        Matrix.setIdentityM(mModelMatrix, 0);
        GLES20.glUseProgram(mProgramHandle);

        
       
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees*0.003f, 0.0f, 1.0f, 0.0f);
        Matrix.translateM(mModelMatrix, 0, 0.0f, 0.0f, 17.0f);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees*0.1f, 0.0f, 4.0f, 0.0f);
        drawSphere();
        
        
        
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandleMoon);
        Matrix.scaleM(mModelMatrix, 0, 0.2f, 0.2f, 0.2f);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees*0.01f, 0.0f, 1.0f, 0.0f);
        Matrix.translateM(mModelMatrix, 0, 0.0f, 0.0f, 9.0f);      
        drawSphere();
       
       
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandleMercury);
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.scaleM(mModelMatrix, 0, 0.3f, 0.3f, 0.3f);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees*0.012f, 0.0f, 1.0f, 0.0f);
        Matrix.translateM(mModelMatrix, 0, 0.0f, 0.0f, 26.0f);     
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees*0.02f, 0.0f, 1.0f, 0.0f);
        drawSphere();
        
        
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandleVenus);
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.scaleM(mModelMatrix, 0, 0.9f, 0.9f, 0.9f);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees*0.002f, 0.0f, 1.0f, 0.0f);
        Matrix.translateM(mModelMatrix, 0, 0.0f, 0.0f, 13.0f);      
        drawSphere();
        
        
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandleMars);
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.scaleM(mModelMatrix, 0, 0.5f, 0.5f, 0.5f);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees*0.0015f, 0.0f, 1.0f, 0.0f);
        Matrix.translateM(mModelMatrix, 0, 0.0f, 0.0f, 46.0f);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees*0.1f, 0.0f, 4.0f, 0.0f);
        drawSphere();

        
        
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandleJupiter);
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.scaleM(mModelMatrix, 0, 2.0f, 2.0f, 2.0f);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees*0.0003f, 0.0f, 1.0f, 0.0f);
        Matrix.translateM(mModelMatrix, 0, 0.0f, 0.0f, 17.0f);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees*0.01f, 0.0f, 4.0f, 0.0f);
        drawSphere();
        
        
     
        
        
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandleSaturn);
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.scaleM(mModelMatrix, 0, 5.0f, 5.0f, 5.0f);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees*0.0003f, 0.0f, 1.0f, 0.0f);
        Matrix.translateM(mModelMatrix, 0, 0.0f, 0.0f, 9.0f);
        drawSaturn();
        
        
        
        
        GLES20.glUseProgram(mPhoneProgramHandle); 
          
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandleFone);
        GLES20.glUniform1i(mFTextureUniformHandle, 0);
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.scaleM(mModelMatrix, 0, 100.0f, 100.0f, 100.0f);
        Matrix.rotateM(mModelMatrix, 0, 50.0f, 0.0f, 1.0f, 0.0f);
        drawPhone();
        
         
      
         GLES20.glUseProgram(mSunProgramHandle);
         GLES20.glActiveTexture(GLES20.GL_TEXTURE1);      
         GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandleSun);
         GLES20.glUniform1i(mSTextureUniformHandle, 1);
         GLES20.glActiveTexture(GLES20.GL_TEXTURE2);
         GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandleDistortion);
         GLES20.glUniform1i(mDTextureUniformHandle, 2);
                 
         Matrix.setIdentityM(mModelMatrix, 0);
         Matrix.scaleM(mModelMatrix, 0, 4.0f, 4.0f, 4.0f);
         drawSun(); 
         
         GLES20.glEnable(GL10.GL_ALPHA);
         GLES20.glEnable(GL10.GL_BLEND); 
         GLES20.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
         GLES20.glActiveTexture(GLES20.GL_TEXTURE1); 
         GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandleSunAlpha);
         GLES20.glUniform1i(mSTextureUniformHandle, 1);
         GLES20.glActiveTexture(GLES20.GL_TEXTURE2);
         GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandleDistortionAlpha);
         GLES20.glUniform1i(mDTextureUniformHandle, 2);
         Matrix.setIdentityM(mModelMatrix, 0);
         Matrix.scaleM(mModelMatrix, 0, 4.2f, 4.2f, 4.2f);
         drawSun();
         Matrix.setIdentityM(mModelMatrix, 0);
         Matrix.scaleM(mModelMatrix, 0, 4.5f, 4.5f, 4.5f);
         drawSun();
         
         GLES20.glDisable(GL10.GL_ALPHA);
         GLES20.glDisable(GL10.GL_BLEND); 
         
        
         Matrix.rotateM(mViewMatrix, 0, SimpleOpenGlView.dx,0.0f ,1.0f ,0.0f);	
         Matrix.rotateM(mViewMatrix, 0, SimpleOpenGlView.dy,1.0f ,0.0f ,0.0f);
         mViewMatrix[14]+=SimpleOpenGlView.rasst;
         mViewMatrix[11]=0.0f;
         mViewMatrix[12]=0.0f;
         mViewMatrix[13]=0.0f;
         if (mViewMatrix[14]>-6.0f)mViewMatrix[14]=-6.0f;
         if (mViewMatrix[14]<-98.0f)mViewMatrix[14]=-98.0f;   
         if (mViewMatrix[14]>98.0f)mViewMatrix[14]=98.0f;
        
	}				
	
		
	private void drawSaturn()
	{		
		
		mCubePositionsc.position(0);		
        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
        		0, mCubePositionsc);        
                
        GLES20.glEnableVertexAttribArray(mPositionHandle);        
                
      
        mCubeNormalsc.position(0);
        GLES20.glVertexAttribPointer(mNormalHandle, mNormalDataSize, GLES20.GL_FLOAT, false, 
        		0, mCubeNormalsc);
        
        GLES20.glEnableVertexAttribArray(mNormalHandle);
        
      
        mCubeTextureCoordinatesc.position(0);
        GLES20.glVertexAttribPointer(mTextureCoordinateHandle, mTextureCoordinateDataSize, GLES20.GL_FLOAT, false, 
        		0, mCubeTextureCoordinatesc);
        
        GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);   
        GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mMVPMatrix, 0);                
        
              Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);
              GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
        
               
        GLES20.glUniform3f(mLightPosHandle, mLightPosInEyeSpace[0], mLightPosInEyeSpace[1], mLightPosInEyeSpace[2]);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, verc);                               
	}		
	
	
	private void drawSphere()
	{		
	
		mCubePositions.position(0);		
        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
        		0, mCubePositions);        
                
        GLES20.glEnableVertexAttribArray(mPositionHandle);        
                
       
        mCubeNormals.position(0);
        GLES20.glVertexAttribPointer(mNormalHandle, mNormalDataSize, GLES20.GL_FLOAT, false, 
        		0, mCubeNormals);
        
        GLES20.glEnableVertexAttribArray(mNormalHandle);
        

        mCubeTextureCoordinates.position(0);
        GLES20.glVertexAttribPointer(mTextureCoordinateHandle, mTextureCoordinateDataSize, GLES20.GL_FLOAT, false, 
        		0, mCubeTextureCoordinates);
        GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);
                
		
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);   
        GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mMVPMatrix, 0);                
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
        
        GLES20.glUniform3f(mLightPosHandle, mLightPosInEyeSpace[0], mLightPosInEyeSpace[1], mLightPosInEyeSpace[2]);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, ver);                               
	}		
	
	
	private void drawPhone()
	{		
		mCubePositions.position(0);		
        GLES20.glVertexAttribPointer(mFPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
        		0, mCubePositions);        
                
        GLES20.glEnableVertexAttribArray(mFPositionHandle);        
        
        mCubeTextureCoordinates.position(0);
        GLES20.glVertexAttribPointer(mFTextureCoordinateHandle, mTextureCoordinateDataSize, GLES20.GL_FLOAT, false, 
        		0, mCubeTextureCoordinates);
        
        GLES20.glEnableVertexAttribArray(mFTextureCoordinateHandle);
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);   
        GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mMVPMatrix, 0);                
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);
        GLES20.glUniformMatrix4fv(mFMVPMatrixHandle, 1, false, mMVPMatrix, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, ver);                               
	}
	
	
	
	private void drawSun()
	{		
		mCubePositions.position(0);		
        GLES20.glVertexAttribPointer(mSPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
        		0, mCubePositions);        
        GLES20.glEnableVertexAttribArray(mSPositionHandle);        
                 
        mCubeTextureCoordinates.position(0);
        GLES20.glVertexAttribPointer(mSTextureCoordinateHandle, mTextureCoordinateDataSize, GLES20.GL_FLOAT, false, 
        		0, mCubeTextureCoordinates);
        GLES20.glEnableVertexAttribArray(mSTextureCoordinateHandle);
                
		
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);   
        GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mMVPMatrix, 0);                
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);
        GLES20.glUniformMatrix4fv(mSMVPMatrixHandle, 1, false, mMVPMatrix, 0);
        
        GLES20.glUniform1f(distortPowerHandle, distortPower);
        GLES20.glUniform1f(hazeSpeedHandle, hazeSpeed);
        GLES20.glUniform1f(time1Handle, time1);
        
        
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, ver);                               
	}
	
	


		
	
	
	
	
	
}