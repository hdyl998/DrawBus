package com.example.test1.drawbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BusView busView;

    TextView tvLineNo;
    TextView tvRote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReadConfigManager.restConfig();
        setContentView(R.layout.activity_main);
        busView = findViewById(R.id.busView);
        findViewById(R.id.btnReset).setOnClickListener(this);
        findViewById(R.id.btnNext).setOnClickListener(this);
        findViewById(R.id.btnSet).setOnClickListener(this);
        tvLineNo = findViewById(R.id.tvLineNo);
        tvRote = findViewById(R.id.tvRote);
        updateBusInfo();
    }

    public void updateBusInfo() {
        tvLineNo.setText(busView.getInfoManager().getBusNo());
        tvRote.setText(busView.getInfoManager().getAreaString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReset:
                busView.reset();
                updateBusInfo();
                break;
            case R.id.btnNext:
                busView.nextStation();
                break;
            case R.id.btnSet:
                busView.setCurrentStation(5);
                break;
        }
    }
}
