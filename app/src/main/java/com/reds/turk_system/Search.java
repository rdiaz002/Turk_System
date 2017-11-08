package com.reds.turk_system;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment {
    EditText searchbar;
    ListView projlist;

    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        searchbar = (EditText) getView().findViewById(R.id.SearchBar);
        projlist = (ListView) getView().findViewById(R.id.projlist);


        return inflater.inflate(R.layout.fragment_search, container, false);
    }

}
