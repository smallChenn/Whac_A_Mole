package org.god.alien.whackamole_final;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;

import rx.functions.Action1;

public class Zidingyi extends Activity{


    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"TMDFACE.jpg");
    String pathStr;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zidingyi);
        if(file.exists())
            file.delete();

        RxPermissions.getInstance(this).request(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {

            }
        });

        Button btn_Photo = (Button) findViewById(R.id.takePhoto);
        Button btn_FilePhoto = (Button) findViewById(R.id.filePhoto);

        // 创建拍照Intent并将控制权返回给调用的程序
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

 /*       if(!file.exists())
            file.mkdirs();
            */
        Log.i("=====1",file.getPath());
        // 创建保存图片的文件
        btn_Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
 /*               intent.putExtra(MediaStore.EXTRA_OUTPUT, file.getPath()+"/face.jpg");
                // 设置图片文件名
                // 启动图像捕获Intent
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
*/
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                File file_tem = new File(file.getPath());
                if(file_tem.exists())
                    file_tem.delete();
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(file));
                //intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                startActivityForResult(intent, 10);
            }
        });

        btn_FilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX",1);
                intent.putExtra("aspectY",1);
                intent.putExtra("outputX", 80);
                intent.putExtra("outputY", 80);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                intent.putExtra("return-data",true);
                startActivityForResult(intent, 11);
            }
        });

        ((Button)findViewById(R.id.button8)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent();
                        intent1.setClass(getApplicationContext(), Game.class);
                        intent1.putExtra("mode", "easy");
                        intent1.putExtra("moleImagePath", pathStr);
                        startActivity(intent1);
                        finish();
                    }
                }
        );
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putString("pathStr", pathStr);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state){
        super.onRestoreInstanceState(state);
        pathStr = state.getString("pathStr");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {;
            Log.i("======2_0","data-->"+data);
            Log.i("======2_0",file.getPath());
            pathStr = file.getPath().toString();
        }else
        if (requestCode == 11 && resultCode ==Activity.RESULT_OK) {
            try {
                //获得图片的uri

                Log.i("=====2_1_1", "daiii-->" + file.getPath());
                //Log.i("======2_1_2",originalUri.getPath().toString());
                pathStr = file.getPath();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}




