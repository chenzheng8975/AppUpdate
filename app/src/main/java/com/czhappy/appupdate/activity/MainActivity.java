package com.czhappy.appupdate.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.czhappy.appupdate.R;
import com.czhappy.appupdate.dialog.CommonDialog;
import com.czhappy.appupdate.utils.UpdateService;

public class MainActivity extends AppCompatActivity {

    private Button update_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        update_btn = (Button) this.findViewById(R.id.update_btn);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkVersion();

            }
        });
        checkVersion();
    }

    private void checkVersion(){

        //这里不发送检测新版本网络请求，直接进入下载新版本安装
        CommonDialog.Builder builder = new CommonDialog.Builder(this);
        builder.setTitle("升级提示");
        builder.setMessage("发现新版本，请及时更新");
        builder.setPositiveButton("立即升级", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(MainActivity.this, UpdateService.class);
                intent.putExtra("apkUrl", "http://121.42.53.175:8080/hello_project/resources/upload/TianQiBao201605231.apk");
                startService(intent);
            }
        });
        builder.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        builder.create().show();
    }
}
