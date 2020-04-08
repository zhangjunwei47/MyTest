package com.example.kaola.myapplication.dinatortablayout;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaola.myapplication.dinatortablayout.adapter.FirstFragmentAdapter;
import com.zc.test.R;

import java.util.ArrayList;

/**
 * @author zhangchao on 2018/9/6.
 */

public class FirstFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<String> dataArrayList = new ArrayList<>();
    FirstFragmentAdapter firstFragmentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = view.findViewById(R.id.first_fragment_recycler);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        firstFragmentAdapter = new FirstFragmentAdapter(R.layout.item_fragment_first);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(firstFragmentAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initdata();
    }

    private void initdata() {
        for (int i = 0; i < 50; i++) {
            String temp = "第" + i + "条, 数据";
            dataArrayList.add(temp);
        }
        firstFragmentAdapter.addData(dataArrayList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
