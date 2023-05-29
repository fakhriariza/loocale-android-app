package com.example.loocale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements IAdapter<T> {

    private final Context mContext;
    private final LayoutInflater mInflater;
    private OnItemClickListener mListener;
    private OnClickListener<T> onClickListener;
    private List<T> mDisplayItems;
    private int maxSize = 0;

    public BaseAdapter(Context context, List<T> items) {
        mContext = context;
        mDisplayItems = items;
        if (mDisplayItems == null) {
            mDisplayItems = new ArrayList<>();
        }
        mInflater = LayoutInflater.from(context);
        this.maxSize = 0;
    }

    @Override
    public int getItemViewType(int position) {
        T item = getItemAtPosition(position);
        if (item == null) return super.getItemViewType(position);
        return getItemViewType(item);
    }

    protected boolean useCustomClickListener() {
        return false;
    }

    protected int getItemViewType(T item) {
        return -1;
    }

    protected int getLayoutId(int viewType) {
        return getLayoutId();
    }

    protected int getLayoutId() {
        return -1;
    }

    protected VH createViewHolder(View view, int viewType) {
        return createViewHolder(view);
    }

    protected VH createViewHolder(View view) {
        return null;
    }

    protected abstract void bindView(VH holder, T item, int position);

    public final void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public final void setOnClickListener(OnClickListener<T> listener) {
        this.onClickListener = listener;
    }

    public final Context getContext() {
        return mContext;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public final LayoutInflater getLayoutInflater() {
        return mInflater;
    }

    public final List<T> getDisplayItems() {
        return mDisplayItems;
    }

    public void setDisplayItems(List<T> list) {
        this.mDisplayItems = list;
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(getLayoutId(viewType), parent, false);
        return createViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, @SuppressLint("RecyclerView") final int position) {
        T item = getItemAtPosition(position);
        if (!useCustomClickListener())
            holder.itemView.setOnClickListener(v -> onItemClicked(v, item, position));

        if (item != null) bindView(holder, item, position);
    }

    protected void onItemClicked(View v, T item, int position) {
        if (mListener != null) mListener.onItemClick(BaseAdapter.this, v, position);
        if (onClickListener != null) onClickListener.onClick(this, v, item, position);
    }

    @Override
    public int getItemCount() {
        if (mDisplayItems == null) return 0;

        return (maxSize > 0 && mDisplayItems.size() > maxSize) ? maxSize : mDisplayItems.size();
    }

    public final T getItemAtPosition(int position) {
        if (mDisplayItems == null || mDisplayItems.size() <= position) return null;
        return mDisplayItems.get(position);
    }

    public final void addData(List<T> items) {
        if (mDisplayItems == null) mDisplayItems = new ArrayList<>();

        mDisplayItems.addAll(items);
        notifyDataSetChanged();
    }

    public void updateData(List<T> items) {
        mDisplayItems = new ArrayList<>();
        addData(items);
    }

    public String getAdapterName() {
        return this.getClass().getSimpleName();
    }

    public interface OnItemClickListener {
        void onItemClick(BaseAdapter adapter, View view, int position);
    }

    public interface OnClickListener<T> {
        void onClick(BaseAdapter adapter, View view, T item, int position);
    }

}
