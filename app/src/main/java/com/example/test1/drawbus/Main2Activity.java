package com.example.test1.drawbus;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * <p>Created by Administrator on 2018/9/21.<p>
 * <p>佛祖保佑，永无BUG<p>
 */

public class Main2Activity extends Activity {


    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        toast = Toast.makeText(getApplicationContext(), "确定退出？", 0);

    }
    public void onBackPressed() {
        quitToast();
    }
    /*
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println(keyCode + "...." + event.getKeyCode());
        if(keyCode == KeyEvent.KEYCODE_BACK){
            quitToast();
        }
        return super.onKeyDown(keyCode, event);
    }
    */
    private void quitToast() {
        if(null == toast.getView().getParent()){
            toast.show();
        }else{
            System.exit(0);
        }
    }
}