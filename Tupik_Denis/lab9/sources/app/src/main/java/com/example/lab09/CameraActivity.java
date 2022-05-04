package com.example.lab09;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CameraActivity extends Activity {
    private Camera camera;
    private SurfaceHolder surfaceHolder;
    private SurfaceView preview;
    private View shotBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        preview = (SurfaceView) findViewById(R.id.surfaceCamera);
        surfaceHolder = preview.getHolder();
        surfaceHolder.addCallback(new MyCallback(this));
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        shotBtn = findViewById(R.id.bCameraShot);
        shotBtn.setOnClickListener(new MyViewListener());

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        camera = Camera.open();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (camera != null){
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    class MyCallback implements SurfaceHolder.Callback{
        private Activity host;

        public MyCallback(Activity act){
            host=act;
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

        @Override
        public void surfaceCreated(SurfaceHolder holder){
            try {
                camera.setPreviewDisplay(holder);
                camera.setPreviewCallback(new MyPreviewCallback());
            }
            catch (IOException e){
                Log.d("myLogs","Ошибка камеры");
                e.printStackTrace();
            }
            Size previewSize = camera.getParameters().getPreviewSize();
            float aspect = (float) previewSize.width / previewSize.height;

            int previewSurfaceWidth = preview.getWidth();
            int previewSurfaceHeight = preview.getHeight();

            LayoutParams lp = preview.getLayoutParams();

            if (host.getResources().getConfiguration().orientation !=
                    Configuration.ORIENTATION_LANDSCAPE){
                // портретный вид
                camera.setDisplayOrientation(90);
                lp.height = previewSurfaceHeight;
                lp.width = (int) (previewSurfaceHeight / aspect);
            }
            preview.setLayoutParams(lp);
            camera.startPreview();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder){}
    }

    class MyViewListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (v == shotBtn) {
                //camera.takePicture(null, null, null, );
                camera.autoFocus(new MyAutoFocusCallback());
            }
        }
    }

    class MyAutoFocusCallback implements Camera.AutoFocusCallback{

        @Override
        public void onAutoFocus(boolean paramBoolean, Camera paramCamera){
            if (paramBoolean){
                paramCamera.takePicture(null, null, null, new MyPictureCallback());
            }
        }
    }

    class MyPictureCallback implements Camera.PictureCallback{

        @Override
        public void onPictureTaken(byte[] paramArrayOfByte, Camera paramCamera){
            try {
                File saveDir = new File("/storage/emulated/0/lab09");
                if (!saveDir.exists()) {
                    saveDir.mkdirs();
                }

                FileOutputStream os = new FileOutputStream(String.format("/storage/emulated/0/lab09/%d.jpg", System.currentTimeMillis()));
                os.write(paramArrayOfByte);
                os.close();
            }
            catch (Exception e) {}

            paramCamera.startPreview();
        }
    }

    class MyPreviewCallback implements Camera.PreviewCallback{

        @Override
        public void onPreviewFrame(byte[] paramArrayOfByte, Camera paramCamera) {
            // здесь можно обрабатывать изображение, показываемое в preview
        }
    }
}
