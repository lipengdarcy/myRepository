package com.example.myfirstapp.photo;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

//创建相机预览界面(Create the Camera Preview)

public class Preview extends ViewGroup implements SurfaceHolder.Callback {

	private SurfaceView mSurfaceView;
	private SurfaceHolder mHolder;

	private Camera mCamera;

	private List<Size> mSupportedPreviewSizes;

	private Size mPreviewSize;

	public Preview(Context context) {
		super(context);

		mSurfaceView = new SurfaceView(context);
		addView(mSurfaceView);

		// Install a SurfaceHolder.Callback so we get notified when the
		// underlying surface is created and destroyed.
		mHolder = mSurfaceView.getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void setCamera(Camera camera) {
		if (mCamera == camera) {
			return;
		}
		stopPreviewAndFreeCamera();
		mCamera = camera;
		if (mCamera != null) {
			List<Size> localSizes = mCamera.getParameters()
					.getSupportedPreviewSizes();
			mSupportedPreviewSizes = localSizes;
			requestLayout();

			try {
				mCamera.setPreviewDisplay(mHolder);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Important: Call startPreview() to start updating the preview
			// surface. Preview must be started before you can take a picture.
			mCamera.startPreview();
		}
	}

	//@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}
	


	//修改相机设置(Modify Camera Settings)
	//@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// Now that the size is known, set up the camera parameters and begin
		// the preview.
		Camera.Parameters parameters = mCamera.getParameters();
		parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
		requestLayout();
		mCamera.setParameters(parameters);

		// Important: Call startPreview() to start updating the preview surface.
		// Preview must be started before you can take a picture.
		mCamera.startPreview();

	}

	//@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * When this function returns, mCamera will be null.
	 */
	private void stopPreviewAndFreeCamera() {

	    if (mCamera != null) {
	        // Call stopPreview() to stop updating the preview surface.
	        mCamera.stopPreview();
	        // Important: Call release() to release the camera for use by other
	        // applications. Applications should release the camera immediately
	        // during onPause() and re-open() it during onResume()).
	        mCamera.release();
	        mCamera = null;
	    }
	}
	
	//设置预览方向(Set the Preview Orientation)  setCameraDisplayOrientation()) 

}
