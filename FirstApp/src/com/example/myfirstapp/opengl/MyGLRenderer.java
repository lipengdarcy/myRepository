package com.example.myfirstapp.opengl;



import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.example.myfirstapp.opengl.shape.Square;
import com.example.myfirstapp.opengl.shape.Triangle;

public class MyGLRenderer implements GLSurfaceView.Renderer {
	
	private Triangle mTriangle;
	private Square mSquare;
	

	//调用一次，用来配置视图的OpenGL ES环境。
	//@Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        
        // initialize a triangle
        mTriangle = new Triangle();
        // initialize a square
        mSquare = new Square();
    }

	//每次重画视图时被调用。
    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        
        //画一个三角形
     // Set the camera position (View matrix)
        //Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
       // Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        // Draw shape
        //mTriangle.draw(mMVPMatrix);
        mTriangle.draw();
    }

    //如果视图的几何形态发生变化时会被调用，例如当设备的屏幕方向发生改变时。
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

	

}
