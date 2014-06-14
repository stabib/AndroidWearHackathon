package com.example.androidwearhackaton;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class WearActivity extends Activity {

    
    private final Handler mHandler = new Handler();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        
        
    }
    
    private int value = 0;
    
    
    private Runnable mUpdateRunnable = new Runnable() {
        
        @Override
        public void run() {
            mHandler.removeCallbacks(mUpdateRunnable);
            NotificationUtil.createNotification(WearActivity.this, ++value);
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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_wear, container, false);
            return rootView;
        }
    }

}
