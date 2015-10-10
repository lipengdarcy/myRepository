package com.example.myfirstapp.opengl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;



//1.为OpenGL ES图形创建一个activity


public class OpenGLES20Activity extends Activity {
	private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }

}