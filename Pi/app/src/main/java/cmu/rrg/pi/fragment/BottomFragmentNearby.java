package cmu.rrg.pi.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cmu.rrg.pi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragmentNearby extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_myposts, container, false);
        return view;
    }
}
