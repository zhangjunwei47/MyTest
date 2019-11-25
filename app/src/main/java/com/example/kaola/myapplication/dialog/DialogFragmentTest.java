package com.example.kaola.myapplication.dialog;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zc.test.R;


/**
 * @author zhangchao on 2018/9/26.
 */

public class DialogFragmentTest extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        Button button  = view.findViewById(R.id.show_two);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTwo();
            }
        });
        return view;
    }

    private void showTwo()
    {
        getChildFragmentManager().beginTransaction().add(R.id.add_fragment, new FragmentTest2(), "haha").commit();
    }
}
