package com.example.myfirstapp.photo;



import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.LruCache;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.myfirstapp.R;

// µœ÷º”‘ÿÕº∆¨µΩViewPager(Load Bitmaps into a ViewPager Implementation)
public class ImageDetailActivity extends FragmentActivity {
    public static final String EXTRA_IMAGE = "extra_image";

    private ImagePagerAdapter mAdapter;
    private ViewPager mPager;
    
    private ImageView mImageView;
    
    private LruCache mMemoryCache;

    // A static dataset to back the ViewPager adapter
    public final static Integer[] imageResIds = new Integer[] {
            R.drawable.sample_1, R.drawable.sample_2, R.drawable.sd_anxi,
            R.drawable.sd_caizi, R.drawable.sd_chimu, R.drawable.sd_gongcheng,
            R.drawable.sd_liuchuan, R.drawable.sd_mumu, R.drawable.sd_qingzi,
            R.drawable.sd_sanjin,R.drawable.sd_yinmu};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_pager); // Contains just a ViewPager

        mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), imageResIds.length);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
    }
    
    public void loadBitmap(int resId, ImageView imageView) {  
        final String imageKey = String.valueOf(resId);

        final Bitmap bitmap = (Bitmap) mMemoryCache.get(imageKey);
        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        } else {
            mImageView.setImageResource(R.drawable.image_placeholder);
            BitmapWorkerTask task = new BitmapWorkerTask(mImageView);
            task.execute(resId);
        }
    }

    //Õº∆¨  ≈‰∆˜
    public static class ImagePagerAdapter extends FragmentStatePagerAdapter {
        private final int mSize;

        public ImagePagerAdapter(FragmentManager fm, int size) {
            super(fm);
            mSize = size;
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public Fragment getItem(int position) {
            return ImageDetailFragment.newInstance(position);
        }
    }
}