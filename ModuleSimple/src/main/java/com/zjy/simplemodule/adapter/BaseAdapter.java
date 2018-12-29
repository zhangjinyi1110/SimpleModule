package com.zjy.simplemodule.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjy.simplemodule.adapter.viewholder.SimpleViewHolder;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<SimpleViewHolder> {

    private Context context;
    private List<T> list;
    private RecyclerView recyclerView;
    public final int TYPE_HEADER = 0x00;
    public final int TYPE_CONTENT = 0x01;
    public final int TYPE_LOAD = 0x02;
    public final int TYPE_FOOTER = 0x03;

    public BaseAdapter(Context context) {
        this.context = context;
    }

    public BaseAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @LayoutRes
    protected abstract int getLayoutId(int type);

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (isBinding()) {
            return new SimpleViewHolder(getBinding(viewGroup, i));
        }
        return getViewHolder(LayoutInflater.from(context).inflate(getLayoutId(i), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads);
        else
            convert(holder, list.get(position), position, payloads);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder simpleViewHolder, int i) {
        convert(simpleViewHolder, list.get(i), i);
    }

    protected void convert(SimpleViewHolder holder, T t, int position, List<Object> payloads) {
        convert(holder, t, position);
    }

    protected abstract void convert(SimpleViewHolder holder, T t, int position);

    protected abstract SimpleViewHolder getViewHolder(View view);

    protected boolean isBinding() {
        return false;
    }

    protected ViewDataBinding getBinding(ViewGroup viewGroup, int type) {
        return null;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addList(List<T> list) {
        if (this.list == null) {
            setList(list);
            return;
        }
        this.list.addAll(list);
        notifyItemChanged(getItemCount() - 1, list.size());
    }
}
