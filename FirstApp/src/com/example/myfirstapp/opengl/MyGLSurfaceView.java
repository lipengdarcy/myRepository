package com.example.myfirstapp.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
//2.一个GLSurfaceView是一个特定的View，在View中你可以绘制OpenGL ES图形
public class MyGLSurfaceView extends GLSurfaceView {

    public MyGLSurfaceView(Context context){
        super(context);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new MyGLRenderer());
    }
}