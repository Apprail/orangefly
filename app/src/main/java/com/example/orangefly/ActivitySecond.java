package com.example.orangefly;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.orangefly.ui.address.AddAddressFragment;
import com.example.orangefly.ui.cart.CartFragment;
import com.example.orangefly.ui.prints.PreviewFragment;
import com.example.orangefly.ui.prints.PrintFragment;


public class ActivitySecond extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_second);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String items = getIntent().getStringExtra("item");
        Fragment fragments = null;

        switch (items) {
            case "Shipment Address":
                break;
            case "Add Address":
                fragments = new AddAddressFragment();
                break;
            case "Cart":
                fragments = new CartFragment();
                break;

        }
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            assert fragments != null;
            fragmentTransaction.replace(R.id.activity_second_frame, fragments);
            fragmentTransaction.commit();
        }
        setTitle(" "+items);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int ids = menuItem.getItemId();
        if (ids == android.R.id.home) {//moveTaskToBack(true);
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {
        //moveTaskToBack(true);
        finish();
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
