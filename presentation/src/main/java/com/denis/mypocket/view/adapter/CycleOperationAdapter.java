package com.denis.mypocket.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.denis.mypocket.databinding.ItemCycleOperationBinding;
import com.denis.mypocket.model.CycleOperationModel;
import com.denis.mypocket.view.adapter.viewholder.BindableHolder;

public class CycleOperationAdapter extends RecyclerBindableAdapter<CycleOperationModel,BindableHolder> {

    @Override
    protected BindableHolder getItemHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        return new BindableHolder(ItemCycleOperationBinding.inflate(inflater));
    }

    @Override
    protected BindableHolder getHeaderHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        return null;
    }

    @Override
    protected BindableHolder getFooterHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void onBindViewHolder(BindableHolder holder, int position) {
        holder.getBinding().setVariable()
    }
}
