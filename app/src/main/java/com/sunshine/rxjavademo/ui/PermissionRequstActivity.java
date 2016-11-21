package com.sunshine.rxjavademo.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fitsleep.sunshinelibrary.utils.Logger;
import com.sunshine.rxjavademo.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

public class PermissionRequstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_requst);
    }

    public void requestPermission(View view){
        AndPermission.with(this).requestCode(100).permission(Manifest.permission.CAMERA).rationale(rationaleListener).send();
    }

    public void requestPermissions(View view){

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            new AlertDialog.Builder(PermissionRequstActivity.this)
                    .setTitle("友好提醒")
                    .setMessage("您已拒绝过定位权限，没有定位权限无法为您推荐附近妹子，请把定位权限赐给我吧！")
                    .setPositiveButton("好，给你", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.resume();
                        }
                    })
                    .setNegativeButton("我拒绝", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.cancel();
                        }
                    }).show();
        }
    };

    @PermissionYes(100)
    public void getPermissionYes() {
        Logger.e(getClass().getSimpleName(), "申请成功了");
    }

    @PermissionNo(100)
    public void getPermissionNo() {
        Logger.e(getClass().getSimpleName(), "申请失败了");
    }
}
