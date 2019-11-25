package com.example.kaola.myapplication.dinatortablayout.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zc.test.R;

/**
 * @author zhangchao on 2018/9/6.
 */

public class FirstFragmentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public FirstFragmentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_fragment_first_text_view, item);
    }


}
