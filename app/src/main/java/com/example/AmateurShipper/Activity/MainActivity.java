package com.example.AmateurShipper.Activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.AmateurShipper.Fragment.CaNhanFragment;
import com.example.AmateurShipper.Fragment.CartFragment;
import com.example.AmateurShipper.Fragment.HomeFragment;
import com.example.AmateurShipper.Fragment.MapFragment;
import com.example.AmateurShipper.R;

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener{

    private AHBottomNavigation bottomNavigationView;
    private Fragment fragment = null;
    public static int gallarey_count_number = 0;
    private int mCountOrder;
    HomeFragment homeFragment = new HomeFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (AHBottomNavigation)findViewById(R.id.chipNavigation);
        loadFragment(new HomeFragment());



        //BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.cart);
        //badgeDrawable.setNumber(3333);
        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Bài đăng", R.drawable.ic_home, R.color.whiteTextColor);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Trạng thái", R.drawable.ic_status, R.color.whiteTextColor);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Tuyến đường", R.drawable.ic_map, R.color.whiteTextColor);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Cá nhân", R.drawable.ic_profile, R.color.whiteTextColor);

        bottomNavigationView.addItem(item1);
        bottomNavigationView.addItem(item2);
        bottomNavigationView.addItem(item3);
        bottomNavigationView.addItem(item4);
        bottomNavigationView.setCurrentItem(0);
        bottomNavigationView.setOnTabSelectedListener(this);

        bottomNavigationView.setAccentColor(Color.parseColor("#FF3C4673"));
        bottomNavigationView.setInactiveColor(Color.parseColor("#FF3C4673"));
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    public int getmCountOrder() {
        return mCountOrder;
    }

    public void setCountOrder(int countOrer) {
        mCountOrder = countOrer;
        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(mCountOrder))
                .build();
        bottomNavigationView.setNotification(notification, 1);
    }

    public void disableNotification(){
        AHNotification notification = new AHNotification.Builder()
                .setText(null)
                .build();
        bottomNavigationView.setNotification(notification, 1);
    }


    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        return false;
    }
}

