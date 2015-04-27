package com.msi.fragmentsession;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserListFragmentListener} interface
 * to handle interaction events.
 */
public class UserListFragment extends Fragment {

    // <MAC> rename Listener
    // Right click->Refactor->Rename
    private UserListFragmentListener mListener;
    private ListView m_listView;
    public UserInfo[] m_contactList;

    public UserListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
        //Generate list View from ArrayList
        CreateContactList();
        m_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              /*  String phn = m_contactList[position].getPhoneNumber();

                Toast.makeText(getActivity().getApplicationContext(),
                        "Phone: " + phn, Toast.LENGTH_LONG)
                        .show();
*/
                // Call the main activity - pass the selected user info obj
                mListener.onUserSelected(m_contactList[position]);
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    /* <MAC-HW> Overload onViewCreated() here
        1. Retrieve the Button created in the layout XML file here.
        2. Give the button a listener (use - setOnClickListener())
        3. Have the action for this button invoke the callback to the activity
    */
    public void CreateContactList()
    {
        m_contactList = new UserInfo[25];

        UserInfo ui1 = new UserInfo("Alan", "Ft Lauderdale, Florida", "954-295-8168");
        UserInfo ui2 = new UserInfo("Deckard", "Los Angeles, California", "800-555-1212");
        m_contactList[0] = ui1;
        m_contactList[1] = ui2;

        for(int x = 2; x < m_contactList.length; x++)
        {
            String tempName = "TestName" + x;
            UserInfo temp = new UserInfo(tempName, "Unknown Address", "No Phone");
            m_contactList[x] = temp;
        }

        m_listView = (ListView)getActivity().findViewById(R.id.listViewMain);


        List<String> userNameList = new ArrayList<String>();

        for(int x = 0; x < m_contactList.length; x++)
        {
            String temp = m_contactList[x].getName();
            userNameList.add(temp);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                userNameList);


        m_listView.setAdapter(arrayAdapter);


    }



    /* <MAC> - Comment out this method it is not needed
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (UserListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement UserListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface UserListFragmentListener {
        // <MAC> Delete generated interface method and replace with below
        public void onUserSelected(UserInfo userInfo);

    }


    public void ShowUserInfoInToast(int selectedIndex)
    {

        Context context = getActivity().getApplicationContext();
        CharSequence text = "Phone Number: " + m_contactList[selectedIndex].getPhoneNumber();
        int duration = Toast.LENGTH_LONG;

        Toast.makeText(context, text, duration).show();
    }

}
