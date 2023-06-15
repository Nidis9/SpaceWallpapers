package com.example.spacewallpapers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ImageView imageView;
    Drawable drawable;
    WallpaperManager wallpaperManager;

    Integer[] Integers = {
            R.drawable.wallpapersspacehdouter1,
            R.drawable.wallpapersspacehdouter2,
            R.drawable.wallpapersspacehdouter3,
            R.drawable.wallpapersspacehdouter4,
            R.drawable.wallpapersspacehdouter5,
            R.drawable.wallpapersspacehdouter6,
            R.drawable.wallpapersspacehdouter7,
            R.drawable.wallpapersspacehdouter8,
            R.drawable.wallpapersspacehdouter9,
            R.drawable.wallpapersspacehdouter10,
            R.drawable.wallpapersspacehdouter11,
            R.drawable.wallpapersspacehdouter12,
            R.drawable.wallpapersspacehdouter13,
            R.drawable.wallpapersspacehdouter14,
            R.drawable.wallpapersspacehdouter15,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview);
        imageView = findViewById(R.id.ImageView);
        gridView.setAdapter(new Adapter(getApplicationContext()));
        UpdateWallpaper();
    }

    private void UpdateWallpaper() {
        wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        drawable = wallpaperManager.getDrawable();
        imageView.setImageDrawable(drawable);
    }

    public class Adapter extends BaseAdapter {

        private final Context mContext;

        public Adapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return Integers.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ImageView gridImageView;

            if (view == null) {
                gridImageView = new ImageView(mContext);
                gridImageView.setLayoutParams(new GridView.LayoutParams(512, 512));
                gridImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                gridImageView = (ImageView) view;
            }
            gridImageView.setImageResource(Integers[i]);
            gridImageView.setOnClickListener(view1 -> {

                try {
                    wallpaperManager.setResource(Integers[i]);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                UpdateWallpaper();

                });

            return gridImageView;
        }
    }
}
