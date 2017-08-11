package com.alyafei.myapplication.Practice;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alyafei.myapplication.Main6Activity_Spinner;
import com.alyafei.myapplication.R;



public class FragmentOne extends Fragment {


    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_fragment_one, container, false);


        Button button = (Button) root.findViewById(R.id.button23_frag);

        // this is how you go from fragment to an activity...
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),Main6Activity_Spinner.class);
                startActivity(intent);
            }
        });
        return root;


    }


public void goToElse(){
    Intent intent = new Intent(getActivity(), Main6Activity_Spinner.class);
    startActivity(intent);
}

}
