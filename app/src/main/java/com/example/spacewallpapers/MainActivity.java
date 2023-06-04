package com.example.spacewallpapers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
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
            R.drawable.WallpapersSpaceHDOuter1,
            R.drawable.WallpapersSpaceHDOuter2,
            R.drawable.WallpapersSpaceHDOuter3,
            R.drawable.WallpapersSpaceHDOuter4,
            R.drawable.WallpapersSpaceHDOuter5,
            R.drawable.WallpapersSpaceHDOuter6,
            R.drawable.WallpapersSpaceHDOuter7,
            R.drawable.WallpapersSpaceHDOuter8,
            R.drawable.WallpapersSpaceHDOuter9,
            R.drawable.WallpapersSpaceHDOuter10,
            R.drawable.WallpapersSpaceHDOuter11,
            R.drawable.WallpapersSpaceHDOuter12,
            R.drawable.WallpapersSpaceHDOuter13,
            R.drawable.WallpapersSpaceHDOuter14,
            R.drawable.WallpapersSpaceHDOuter15,

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
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        drawable = wallpaperManager.getDrawable();
        imageView.setImageDrawable(drawable);
    }

    public class Adapter extends BaseAdapter {

        private Context mContext;

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
            gridImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        wallpaperManager.setResource(Integers[i]);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    UpdateWallpaper();

                    }
                });

            return gridImageView;
        }
    }
}
