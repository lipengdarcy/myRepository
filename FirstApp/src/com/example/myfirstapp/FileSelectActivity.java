package com.example.myfirstapp;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

//文件选择 在代码中定义文件选择Activity
public class FileSelectActivity extends Activity {

	// The path to the root of this app's internal storage
	private File mPrivateRootDir;
	// The path to the "images" subdirectory
	private File mImagesDir;
	// Array of files in the images subdirectory
	File[] mImageFiles;
	// Array of filenames corresponding to mImageFiles
	String[] mImageFilenames;

	ListView mFileListView;

	Intent mResultIntent;

	// Initialize the Activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// Set up an Intent to send back to apps that request a file
		mResultIntent = new Intent("com.example.myapp.ACTION_RETURN_FILE");
		// Get the files/ subdirectory of internal storage
		mPrivateRootDir = getFilesDir();
		// Get the files/images subdirectory;
		mImagesDir = new File(mPrivateRootDir, "images");
		// Get the files in the images subdirectory
		mImageFiles = mImagesDir.listFiles();
		// Set the Activity's result to null to begin with
		setResult(Activity.RESULT_CANCELED, null);
		/*
		 * Display the file names in the ListView mFileListView. Back the
		 * ListView with the array mImageFilenames, which you can create by
		 * iterating through mImageFiles and calling File.getAbsolutePath() for
		 * each File
		 */

		// 一旦一个用户选择了一个共享的文件，你的应用必须明确哪个文件被选择了，然后为这个文件生成一个对应的URI。
		// Define a listener that responds to clicks on a file in the ListView
		mFileListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					/*
					 * When a filename in the ListView is clicked, get its
					 * content URI and send it to the requesting app
					 */
					public void onItemClick(AdapterView<?> adapterView,
							View view, int position, long rowId) {
						/*
						 * Get a File for the selected file name. Assume that
						 * the file names are in the mImageFilename array.
						 */
						File requestFile = new File(mImageFilenames[position]);

						/*
						 * Most file-related method calls need to be in
						 * try-catch blocks.
						 */
						// Use the FileProvider to get a content URI
						Uri fileUri = null;
						try {
							fileUri = FileProvider.getUriForFile(
									FileSelectActivity.this,
									"com.example.myfirstapp.fileprovider",
									requestFile);
						} catch (IllegalArgumentException e) {
							Log.e("File Selector",
									"The selected file can't be shared: " + "");
						}

						if (fileUri != null) {
							// 为文件授权
							// Grant temporary read permission to the content
							// URI
							mResultIntent
									.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
							// Put the Uri and MIME type in the result Intent
							mResultIntent.setDataAndType(fileUri,
									getContentResolver().getType(fileUri));
							// Set the result
							FileSelectActivity.this.setResult(
									Activity.RESULT_OK, mResultIntent);
						} else {
							mResultIntent.setDataAndType(null, "");
							FileSelectActivity.this.setResult(RESULT_CANCELED,
									mResultIntent);
						}
					}

				});

	}

	/**
	 * 
	 * 向用户提供一个一旦他们选择了文件就能立即回到客户端应用的方法。一种实现的方法是提供一个勾选框或者一个完成按钮。
	 * 使用按钮的android:onClick属性字段为它关联一个方法。在该方法中，调用finish())
	 * */
	public void onDoneClick(View v) {
		// Associate a method with the Done button
		finish();
	}

}