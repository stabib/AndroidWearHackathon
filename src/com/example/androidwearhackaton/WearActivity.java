package com.example.androidwearhackaton;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class WearActivity extends Activity {

    
    private final Handler mHandler = new Handler();
    
    private RadioGroup mRadioGroup;
    private SensorObserver mSensorObserver;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear);

        mRadioGroup = (RadioGroup)findViewById(R.id.sensors);
        mSensorObserver = new SensorObserver(this, 1);
        
    }
    
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.bt_accel:
                if (checked)
                        Log.d("selection", "Accelerator");
                        
                break;
            case R.id.bt_speed:
                if (checked)
                    Log.d("selection", "Speedometer");
                break;
        }
    }
    
    private int value = 0;
    
    
    private Runnable mUpdateRunnable = new Runnable() {
        
        @Override
        public void run() {
            mHandler.removeCallbacks(mUpdateRunnable);
            NotificationUtil.createNotification(WearActivity.this, "Speedometer", String.valueOf(++value));
            mHandler.postDelayed(mUpdateRunnable, 1000);
            
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        mHandler.post(mUpdateRunnable);
    };
    
    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mUpdateRunnable);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wear, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }
        
        private RadioGroup mRadioGroup;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_wear, container, false);
            
            mRadioGroup = (RadioGroup)rootView.findViewById(R.id.sensors);
            
            return rootView;
        }
    }

}
