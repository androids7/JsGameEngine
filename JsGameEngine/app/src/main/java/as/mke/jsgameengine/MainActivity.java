package as.mke.jsgameengine;

import android.app.*;
import android.os.*;
import wendu.dsbridge.*;
import android.view.*;
import as.mke.gametet.System.view.*;
import android.widget.*;
import android.support.v4.app.*;
import android.*;
import android.content.pm.*;
import android.support.annotation.*;
import java.util.*;
import android.support.v4.content.*;
import android.util.*;
import android.support.v7.app.*;

public class MainActivity extends AppCompatActivity
{
	public DWebView dWebView;

	MainView main;
	
	GameLogic logic;
	
	
	
	String[] permissions = new String[]{
		Manifest.permission.INTERNET,
		Manifest.permission.READ_EXTERNAL_STORAGE,
		Manifest.permission.WRITE_EXTERNAL_STORAGE,
		Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS
		};
	List<String> mPermissionList = new ArrayList<>();
	
	//10000比较霸气
	private static final int MY_PERMISSIONS_REQUEST_CODE = 10000;
	
	
    public <T extends View> T getView(int viewId) {
        View view = findViewById(viewId);
        return (T) view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		//main=new MainView(this);
		
		
		setTheme(android.support.v7.appcompat.R.style.Theme_AppCompat);
		
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,      
							 WindowManager.LayoutParams. FLAG_FULLSCREEN);

		main=new MainView(this);
		main.setHandle(this);
		main.init();

		//requestWindowFeature(1);
		
        
		logic=new GameLogic(main);
		
		
		/*
        getView(R.id.addValue).setOnClickListener(this);
        getView(R.id.append).setOnClickListener(this);
        getView(R.id.startTimer).setOnClickListener(this);
        getView(R.id.synAddValue).setOnClickListener(this);
        getView(R.id.synGetInfo).setOnClickListener(this);
        getView(R.id.asynAddValue).setOnClickListener(this);
        getView(R.id.asynGetInfo).setOnClickListener(this);
        getView(R.id.hasMethodAddValue).setOnClickListener(this);
        getView(R.id.hasMethodXX).setOnClickListener(this);
        getView(R.id.hasMethodAsynAddValue).setOnClickListener(this);
        getView(R.id.hasMethodAsynXX).setOnClickListener(this);
		*/
        DWebView.setWebContentsDebuggingEnabled(true);
        dWebView=new DWebView(this);
		//getView(R.id.webview);
		
		getPermissions();
		dWebView.loadUrl("file:///sdcard/0/nativecall.html");
		setContentView(dWebView);
		
        
		
		logic.logic();

		
		

    }


    void showToast(Object o) {
        Toast.makeText(this, o.toString(), Toast.LENGTH_SHORT).show();
    }
	
	
	
	
	private void getPermissions() {
		mPermissionList.clear();                                    //清空已经允许的没有通过的权限
		for (int i = 0; i < permissions.length; i++) {          //逐个判断是否还有未通过的权限
			if (ContextCompat.checkSelfPermission(MainActivity.this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
				mPermissionList.add(permissions[i]);
			}
		}

		if (mPermissionList.size() > 0) {                           //有权限没有通过，需要申请
			ActivityCompat.requestPermissions(this, permissions, MY_PERMISSIONS_REQUEST_CODE);
		} else {
			Log.e("getPermissions() >>>", "已经授权");     //权限已经都通过了
		}
	}
	
	
	
	
	
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		boolean hasPermissionDismiss = false;      //有权限没有通过
		if (MY_PERMISSIONS_REQUEST_CODE == requestCode) {
			for (int i = 0; i < grantResults.length; i++) {
				if (grantResults[i] == -1) {
					hasPermissionDismiss = true;   //发现有未通过权限
					break;
				}
			}
		}

		if (hasPermissionDismiss) {                //如果有没有被允许的权限
			//假如存在有没被允许的权限,可提示用户手动设置 或者不让用户继续操作
		} else {
			Log.e("onRequestPermissionsResult >>>", "已全部授权");
		}
	}
	
	
/*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addValue:
                dWebView.callHandler("addValue", new Object[]{3, 4}, new OnReturnValue<Integer>() {
						@Override
						public void onValue(Integer retValue) {
							showToast(retValue);
						}
					});
                break;
            case R.id.append:
                dWebView.callHandler("append", new Object[]{"I", "love", "you"}, new OnReturnValue<String>() {
						@Override
						public void onValue(String retValue) {
							showToast(retValue);
						}
					});
                break;
            case R.id.startTimer:
                dWebView.callHandler("startTimer", new OnReturnValue<Integer>() {
						@Override
						public void onValue(Integer retValue) {
							showToast(retValue);
						}
					});
                break;
            case R.id.synAddValue:
                dWebView.callHandler("syn.addValue", new Object[]{5, 6}, new OnReturnValue<Integer>() {
						@Override
						public void onValue(Integer retValue) {
							showToast(retValue);
						}
					});
                break;
            case R.id.synGetInfo:
                dWebView.callHandler("syn.getInfo", new OnReturnValue<JSONObject>() {
						@Override
						public void onValue(JSONObject retValue) {
							showToast(retValue);
						}
					});
                break;
            case R.id.asynAddValue:
                dWebView.callHandler("asyn.addValue", new Object[]{5, 6}, new OnReturnValue<Integer>() {
						@Override
						public void onValue(Integer retValue) {
							showToast(retValue);
						}
					});
                break;
            case R.id.asynGetInfo:
                dWebView.callHandler("asyn.getInfo", new OnReturnValue<JSONObject>() {
						@Override
						public void onValue(JSONObject retValue) {
							showToast(retValue);
						}
					});
                break;
            case R.id.hasMethodAddValue:
                dWebView.hasJavascriptMethod("addValue", new OnReturnValue<Boolean>() {
						@Override
						public void onValue(Boolean retValue) {
							showToast(retValue);
						}
					});
                break;
            case R.id.hasMethodXX:
                dWebView.hasJavascriptMethod("XX", new OnReturnValue<Boolean>() {
						@Override
						public void onValue(Boolean retValue) {
							showToast(retValue);
						}
					});
                break;
            case R.id.hasMethodAsynAddValue:
                dWebView.hasJavascriptMethod("asyn.addValue", new OnReturnValue<Boolean>() {
						@Override
						public void onValue(Boolean retValue) {
							showToast(retValue);
						}
					});
                break;
            case R.id.hasMethodAsynXX:
                dWebView.hasJavascriptMethod("asyn.XX", new OnReturnValue<Boolean>() {
						@Override
						public void onValue(Boolean retValue) {
							showToast(retValue);
						}
					});
                break;
        }
		}
*/
    
}
