package com.msi.fragmentsession;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SingleUser.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SingleUser extends Fragment {

    private UserListFragmentListener mListener;

    public SingleUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_user, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        final Button button = (Button)getActivity().findViewById(R.id.Exit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });
        */
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (UserListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
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
        // TODO: Update argument type and name
        public void onUserSelected(UserInfo userInfo);
        public void hideFragment();
    }

    public void UpdateUserDataDisplay(UserInfo ui)
    {
        TextView textView = (TextView)this.getView().findViewById(R.id.NameData);
        textView.setText(ui.getName());

        textView = (TextView)this.getView().findViewById(R.id.AddressData);
        textView.setText(ui.getAddress());

        textView = (TextView)this.getView().findViewById(R.id.PhoneData);
        textView.setText(ui.getPhoneNumber());
    }
}
