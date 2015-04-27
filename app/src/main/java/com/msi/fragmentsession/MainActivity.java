package com.msi.fragmentsession;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity
    // <MAC> Implement UserListFragmentListener
    implements UserListFragment.UserListFragmentListener, SingleUser.UserListFragmentListener {

    UserListFragment m_userListFragment = new UserListFragment();
    SingleUser m_singleRecordFragment = new SingleUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // <MAC> - Instantiate the UserListFragment
        FragmentManager fragmentManager = getFragmentManager();

        if(!IsPhoneRotated())
        {
            fragmentManager.beginTransaction()
                    .add(R.id.mainContainer, m_userListFragment)
                    .commit();
        }
        else {

            // <MAC> - Get the FragmentManager
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.frag1Container, m_userListFragment)
                    .add(R.id.frag2Container, m_singleRecordFragment)
                    .commit();
        }

    }

    public boolean IsPhoneRotated()
    {
        boolean rotated = false;

        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getRotation();
        if(orientation == Surface.ROTATION_90 || orientation == Surface.ROTATION_270)
        {
            rotated = true;
        }
        return rotated;
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Context context = getApplicationContext();
            CharSequence text = "You selected settings";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // <MAC> - Realize the interface method from UserListFragmentListener
    public void onUserSelected(UserInfo userInfo) {
        /* <MAC-HW> Do something interesting here. Suggestions (easiest to hardest):
        1. Make a Toast!
        2. Create a public method in the UserListFragment to pass back data. You will need to
        assign your UserListFragment instance to a class scoped attribute so you can get to it.
        3. Launch another Fragment (hint use FragmentManager and "replace" instead of "add" your
        new Fragment)! Do something interesting in that Fragment.
        4. Launch another Fragment. Get some data from the User (hint EditText with a Button).
        Pass that data back to the Activity and have the Activity pass it to the other Fragment.
         */

        // Show a toast with the phone number
        String phn = userInfo.getPhoneNumber();

        Toast.makeText(this,
                "Phone: " + phn, Toast.LENGTH_LONG)
                .show();

        // update the support fragment with the selected UserInfo data
        m_singleRecordFragment.UpdateUserDataDisplay(userInfo);

        FragmentManager fragmentManager = getFragmentManager();


        if(IsPhoneRotated())
        {
            // if rotated, the second frag is already shown
        }
        else {

            // need to replace the existing frag
            // <MAC> - Get the FragmentManager
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .remove(m_userListFragment)
                    .add(R.id.frag1Container, m_singleRecordFragment)
                    .commit();
        }

    }

    public void hideFragment()
    {
/*        if(IsPhoneRotated())
        {
            // if rotated, the second frag is shown along side so no need to hide
        }
        else {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .remove(m_singleRecordFragment)
                    .add(R.id.frag1Container, m_userListFragment)
                    .commit();
        }
*/
    }
}
