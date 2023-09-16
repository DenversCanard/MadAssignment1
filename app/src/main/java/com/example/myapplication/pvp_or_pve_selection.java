package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link pvp_or_pve_selection#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pvp_or_pve_selection extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public pvp_or_pve_selection() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pvp_or_pve_selection.
     */
    // TODO: Rename and change types and number of parameters
    public static pvp_or_pve_selection newInstance(String param1, String param2) {
        pvp_or_pve_selection fragment = new pvp_or_pve_selection();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pvp_or_pve_selection, container, false);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).
                get(MainActivityData.class);
        Button PVPButton = view.findViewById(R.id.PVPButton);
        Button PVEButton = view.findViewById(R.id.PVEButton);
        Button BackButton = view.findViewById(R.id.BackButton);

        PVPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP2IsAi(false);
                mainActivityDataViewModel.setDisplayScreen("P2SelectionScreen");
            }
        });

        PVEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP2IsAi(true);
                mainActivityDataViewModel.setP2Name("Zuck GPT");
                mainActivityDataViewModel.setP2Icon(R.drawable.icon7);
                mainActivityDataViewModel.setP2Icon(R.drawable.marker7);
                mainActivityDataViewModel.setDisplayScreen("Home");
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setDisplayScreen("P1SelectionScreen");
            }
        });

        return view;
    }
}