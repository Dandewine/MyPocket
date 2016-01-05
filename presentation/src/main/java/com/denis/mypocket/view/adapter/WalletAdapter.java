package com.denis.mypocket.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.denis.mypocket.databinding.ItemWalletBinding;
import com.denis.mypocket.model.WalletModel;
import com.denis.mypocket.view.adapter.viewholder.BindableHolder;

public class WalletAdapter extends RecyclerBindableAdapter<WalletModel,BindableHolder> {
    @Override
    protected BindableHolder getItemHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        return new BindableHolder(ItemWalletBinding.inflate(inflater));
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

    }
}
