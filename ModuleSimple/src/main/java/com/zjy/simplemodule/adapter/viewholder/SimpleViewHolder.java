package com.zjy.simplemodule.adapter.viewholder;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SimpleViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    B binding;

    public SimpleViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public SimpleViewHolder(B binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public <T extends View> T findViewById(int viewId) {
        return itemView.findViewById(viewId);
    }

}
