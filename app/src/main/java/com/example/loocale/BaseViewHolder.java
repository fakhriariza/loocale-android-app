package com.example.loocale;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    private final Context context;

    private ViewBinding binding;

    protected <VB extends ViewBinding> VB getBinding() { return (VB) binding; }

    public BaseViewHolder(ViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        this.context = binding.getRoot().getContext();
    }

    public BaseViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        context = view.getContext();
    }

    protected void bindView(T item) { }

    protected Context getContext() {
        return context;
    }

}
